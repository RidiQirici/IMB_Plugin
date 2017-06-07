package imb.ridiqirici.plugin.cordova.universal;

import rego.printlib.export.regoPrinter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
//import android.serialport.DeviceControl;

public class PrinterEM55 extends Printer{

	private regoPrinter em55;
	
	private static final String LOG_TAG = "EM55_PRINTER";
	
	private Context context = null;
	private int state = 0;
	private DeviceControl deviceControl;

	public PrinterEM55(Context context){
		super();
		this.context = context;
		Log.i(LOG_TAG, "Hyri ne klasen PrinterEM55");
		em55 = new regoPrinter(this.context);
	}

	@Override
	public Mesazh printoText(String adresa, String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String adresa, String textPerPrintim)");
		return  printoTextProcedure(60, 40, textPerPrintim);
	}
	
	public Mesazh printoText(String textPerPrintim, Integer wight, Integer hight) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
		return  printoTextProcedure(wight, hight, textPerPrintim);
	}

	private Mesazh printoTextProcedure(Integer wight, Integer hight, String textPerPrintim){
		Mesazh pergjigje = null;
		try {
			if (krijoLidhjeMePajisje()){
				Toast.makeText(this.context, "Lidhja me printerin u krye me sukses!", Toast.LENGTH_LONG).show();
				em55.CON_PageStart(getState(), false, wight, hight);
				{
					//Left Alignment
					em55.ASCII_CtrlAlignType(getState(), 0);
					//Oposite color false
					em55.ASCII_CtrlOppositeColor(getState(), false);
					//Thick false
					em55.ASCII_PrintBuffer(getState(), new byte[] { 0x1b, 0x45, 0x00 }, 3);
					//WIGHT, HIGHT, THICK, UNDERLINE, SMALL
					em55.ASCII_PrintString(getState(), 0, 0, 0, 0, 0, textPerPrintim, "gb2312");
					em55.ASCII_CtrlFeedLines(getState(), 1);
					em55.ASCII_CtrlPrintCRLF(getState(), 1);
				}
				em55.CON_PageEnd(getState(), context.getPrintway());
				prishLidhje();
			}
		}
		catch(Exception e)
		{
			Toast.makeText(this.context, "Ndodhi nje gabim gjate printimit", Toast.LENGTH_LONG).show();
			Log.e(LOG_TAG, e.toString());
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		}
	}	

	private boolean krijoLidhjeMePajisje(){
		
		try {
			/*deviceControl = new DeviceControl(DeviceControl.PowerType.MAIN_AND_EXPAND, 73,4);
			deviceControl.PowerOnDevice();*/
			setState();
			return true;
        } catch (IOException e) {
			throw e;
        }
	}

    private void prishLidhje() {
        try {
            /*deviceControl.PowerOffDevice();*/
        } catch (IOException e) {
           throw e;
        }
    }

	private void setState(){
		state = em55.CON_ConnectDevices("RG-E487", "/dev/ttyMT1:115200:1:1", 200);
	}

	private int getState(){
		return state;
	}
}
