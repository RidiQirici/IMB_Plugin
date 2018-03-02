package imb.ridiqirici.plugin.cordova.universal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.sunmi.printerhelper.R;
import com.sunmi.printerhelper.bean.TableItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;


public class PrinterSunMi extends Printer {
    private static final String SERVICE＿PACKAGE = "woyou.aidlservice.jiuiv5";
    private static final String SERVICE＿ACTION = "woyou.aidlservice.jiuiv5.IWoyouService";
    private static final String LOG_TAG = "SUNMI_PRINTER";
    
    private IWoyouService woyouService;

    private Context context;

    public PrinterSunMi(Context context) {
        Log.i(LOG_TAG, "Hyri ne klasen PrinterSunMi");
        connectPrinterService(context);
    }

    public void connectPrinterService(Context context) {
        this.context = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setPackage(SERVICE＿PACKAGE);
        intent.setAction(SERVICE＿ACTION);
        context.getApplicationContext().startService(intent);
        context.getApplicationContext().bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }

    @Override
	public Mesazh printoText(String adresa, String textPerPrintim) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer align, Boolean small) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
	public Mesazh printoText(String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
        return printText(textPerPrintim, 24, false, false);
    }
    
    @Override
	public Mesazh printoText(String textPerPrintim, float fontSize, boolean bold, boolean underline) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim, float fontSize, boolean bold, boolean underline)");
        return printText(textPerPrintim, fontSize, bold, underline);
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer wight, Integer hight, Integer align, Boolean small) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
	public Mesazh printoBarcode(String textPerPrintim, Integer align, Boolean small) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
	public Mesazh printoTextSpecial(String textPerPrintim, Integer width, Integer height, Integer x, Integer y, Integer fontSize, Integer lineWidth) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public void disconnectPrinterService(Context context) {
        if (woyouService != null) {
            context.getApplicationContext().unbindService(connService);
            woyouService = null;
        }
    }

    public boolean isConnect() {
        return woyouService != null;
    }

    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            woyouService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            woyouService = IWoyouService.Stub.asInterface(service);
        }
    };

    public ICallback generateCB(final PrinterCallback printerCallback){
        return new ICallback.Stub(){


            @Override
            public void onRunResult(boolean isSuccess) throws RemoteException {

            }

            @Override
            public void onReturnString(String result) throws RemoteException {
                printerCallback.onReturnString(result);
            }

            @Override
            public void onRaiseException(int code, String msg) throws RemoteException {

            }
        };
    }

    public void initPrinter() {
        if (woyouService == null) {
            Toast.makeText(context,"Printeri u hap me sukses!",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            woyouService.printerInit(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printQr(String data, int modulesize, int errorlevel) {
        if (woyouService == null) {
            Toast.makeText(context, "Nuk eshte hapur service i printerit!",Toast.LENGTH_LONG).show();
            return;
        }


        try {
		    woyouService.setAlignment(1, null);
            woyouService.printQRCode(data, modulesize, errorlevel, null);
            woyouService.lineWrap(3, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printBarCode(String data, int symbology, int height, int width, int textposition) {
        if (woyouService == null) {
            Toast.makeText(context,"Nuk eshte hapur service i printerit!",Toast.LENGTH_LONG).show();
            return;
        }


        try {
            woyouService.printBarCode(data, symbology, height, width, textposition, null);
            woyouService.lineWrap(3, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Mesazh printText(String content, float size, boolean isBold, boolean isUnderLine) {
        if (woyouService == null) {
            Toast.makeText(context,"Nuk eshte hapur service i printerit!",Toast.LENGTH_LONG).show();
            return new Mesazh(false, "Nuk eshte hapur service i printerit!"); 
        }

        try {
            if (isBold) {
                woyouService.sendRAWData(ESCUtil.boldOn(), null);
            } else {
                woyouService.sendRAWData(ESCUtil.boldOff(), null);
            }

            if (isUnderLine) {
                woyouService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
            } else {
                woyouService.sendRAWData(ESCUtil.underlineOff(), null);
            }

            woyouService.printTextWithFont(content, null, size, null);
            woyouService.lineWrap(3, null);
            return new Mesazh(true, "Printimi perfundoi me sukses!"); 
        } catch (RemoteException e) {
            e.printStackTrace();
            return new Mesazh(false, e.printStackTrace()); 
        }

    }
}