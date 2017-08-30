package imb.ridiqirici.plugin.cordova.universal;

import rego.printlib.export.regoPrinter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.lang.*;
import java.io.IOException;

public class PrinterEM55 extends Printer{

	private static final String LOG_TAG = "EM55_PRINTER";
	
	private Context context = null;
	private ApplicationContext contextApp = null;
	private Integer state = 0;
    private DeviceControl DevCtrl;
    private boolean isTT43 = false;

	public PrinterEM55(Context context){
		super();
		this.context = context;
		this.contextApp = new ApplicationContext(context);
		this.contextApp.setObject();
		Log.i(LOG_TAG, "Hyri ne klasen PrinterEM55");
	}

	@Override
	public Mesazh printoText(String adresa, String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String adresa, String textPerPrintim)");
		return  printoTextProcedure(60, 40, 0, false, textPerPrintim);
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer align, Boolean small) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim, Integer align, Boolean small)");
		return  printoTextProcedure(60, 40, align, small, textPerPrintim);
    }
    
    @Override
	public Mesazh printoText(String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
		return  printoTextProcedure(60, 40, 0, false, textPerPrintim);
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer wight, Integer hight, Integer align, Boolean small) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim, Integer wight, Integer hight, Integer align, Boolean small)");
		return  printoTextProcedure(wight, hight, align, small, textPerPrintim);
    }
    
    @Override
	public Mesazh printoBarcode(String textPerPrintim, Integer align, Boolean small) {
		Log.i(LOG_TAG, "Hyri ne metoden printoBarcode(String textPerPrintim, Integer wight, Integer hight, Integer align, Boolean small)");
		return  printoBarCodeProcedure(60, 40, align, small, textPerPrintim);
    }

    @Override
	public Mesazh printoTextSpecial(String textPerPrintim, Integer x, Integer y, Integer fontSize, Integer lineWidth) {
		Log.i(LOG_TAG, "Hyri ne metoden printoTextSpecial(Integer x, Integer y, Integer fontSize, Integer lineWidth, String textPerPrintim)");
		return  printoTextSpecialProcedure(384, 300, x, y, fontSize, lineWidth, textPerPrintim);
    }

	private Mesazh printoTextProcedure(Integer wight, Integer hight, Integer align, Boolean small, String textPerPrintim){
		Mesazh pergjigje = null;
		try {
			if (checkState() > 0){
				Toast.makeText(this.context, "Lidhja me printerin u krye me sukses!", Toast.LENGTH_LONG).show();
				this.contextApp.getObject().CON_PageStart(getState(), false, wight, hight);
				{
					//Left Alignment
					this.contextApp.getObject().ASCII_CtrlAlignType(getState(), align);
					//Oposite color false
					this.contextApp.getObject().ASCII_CtrlOppositeColor(getState(), false);
					//Thick false
					this.contextApp.getObject().ASCII_PrintBuffer(getState(), new byte[] { 0x1b, 0x45, 0x00 }, 3);
					//WIGHT, HIGHT, THICK, UNDERLINE, SMALL
					this.contextApp.getObject().ASCII_PrintString(getState(), 0, 0, 0, 0, small ? 1 : 0, textPerPrintim, "gb2312");
					this.contextApp.getObject().ASCII_CtrlFeedLines(getState(), 1);
					this.contextApp.getObject().ASCII_CtrlPrintCRLF(getState(), 1);
				}
				this.contextApp.getObject().CON_PageEnd(getState(), 0);
				Toast.makeText(this.context, "Printimi perfundoi me sukses!", Toast.LENGTH_LONG).show();
				Log.i(LOG_TAG, "Printimi perfundoi me sukses!");
                pergjigje = new Mesazh(true, "Printimi perfundoi me sukses!");
                /*boolean mbyllPajisje = onDestroy();
                if (mbyllPajisje)
                    return pergjigje;
                else */
                    return new Mesazh(true, "Printimi perfundoi me sukses!");
			}
			Toast.makeText(this.context, "Deshtoi lidhja me pajisjen e printimit!", Toast.LENGTH_LONG).show();
			Log.e(LOG_TAG, "Deshtoi lidhja me pajisjen e printimit!");
			pergjigje = new Mesazh(false, "Deshtoi lidhja me pajisjen e printimit!");
			return pergjigje;
		}
		catch(Exception e)
		{
			Toast.makeText(this.context, "Ndodhi nje gabim gjate printimit", Toast.LENGTH_LONG).show();
			Log.e(LOG_TAG, e.toString());
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		}
    }	
    
    private Mesazh printoBarCodeProcedure(Integer wight, Integer hight, Integer align, Boolean small, String textPerPrintim){
		Mesazh pergjigje = null;
		try {
			if (checkState() > 0){
				Toast.makeText(this.context, "Lidhja me printerin u krye me sukses!", Toast.LENGTH_LONG).show();
				this.contextApp.getObject().CON_PageStart(getState(), false, wight, hight);
				{
					//Left Alignment
                    this.contextApp.getObject().ASCII_CtrlAlignType(getState(), align);
                    this.contextApp.getObject().ASCII_Print1DBarcode(
							getState(),
							72,
							wight,
							hight,
							small ? 1 : 0, textPerPrintim);
				}
				this.contextApp.getObject().CON_PageEnd(getState(), 0);
				Toast.makeText(this.context, "Printimi perfundoi me sukses!", Toast.LENGTH_LONG).show();
				Log.i(LOG_TAG, "Printimi perfundoi me sukses!");
                pergjigje = new Mesazh(true, "Printimi perfundoi me sukses!");
                /*boolean mbyllPajisje = onDestroy();
                if (mbyllPajisje)
                    return pergjigje;
                else */
                    return new Mesazh(true, "Printimi perfundoi me sukses!");
			}
			Toast.makeText(this.context, "Deshtoi lidhja me pajisjen e printimit!", Toast.LENGTH_LONG).show();
			Log.e(LOG_TAG, "Deshtoi lidhja me pajisjen e printimit!");
			pergjigje = new Mesazh(false, "Deshtoi lidhja me pajisjen e printimit!");
			return pergjigje;
		}
		catch(Exception e)
		{
			Toast.makeText(this.context, "Ndodhi nje gabim gjate printimit", Toast.LENGTH_LONG).show();
			Log.e(LOG_TAG, e.toString());
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		}
    }
    
    private Mesazh printoTextSpecialProcedure(Integer wight, Integer hight, Integer x, Integer y, Integer fontSize, Integer lineWidth, String textPerPrintim){
		Mesazh pergjigje = null;
		try {
			if (checkState() > 0){
				Toast.makeText(this.context, "Lidhja me printerin u krye me sukses!", Toast.LENGTH_LONG).show();
				this.contextApp.getObject().CON_PageStart(getState(), false, wight, hight);
				{
                    this.contextApp.getObject().DRAW_SetFillMode(false,0);
                    this.contextApp.getObject().DRAW_SetLineWidth(lineWidth);
                    this.contextApp.getObject().DRAW_PrintText(getState(), x, y, textPerPrintim, fontSize);
				}
				this.contextApp.getObject().CON_PageEnd(getState(), 0);
				Toast.makeText(this.context, "Printimi perfundoi me sukses!", Toast.LENGTH_LONG).show();
				Log.i(LOG_TAG, "Printimi perfundoi me sukses!");
                pergjigje = new Mesazh(true, "Printimi perfundoi me sukses!");
                return new Mesazh(true, "Printimi perfundoi me sukses!");
			}
			Toast.makeText(this.context, "Deshtoi lidhja me pajisjen e printimit!", Toast.LENGTH_LONG).show();
			Log.e(LOG_TAG, "Deshtoi lidhja me pajisjen e printimit!");
			pergjigje = new Mesazh(false, "Deshtoi lidhja me pajisjen e printimit!");
			return pergjigje;
		}
		catch(Exception e)
		{
			Toast.makeText(this.context, "Ndodhi nje gabim gjate printimit", Toast.LENGTH_LONG).show();
			Log.e(LOG_TAG, e.toString());
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		}
	}

	private Integer checkState(){
        Log.i(LOG_TAG, "Po tenton te lidhet me pajisjen...");
        state = this.contextApp.getObject().CON_ConnectDevices("RG-E487", "/dev/ttyMT1:115200", 200);
        DevCtrl = new DeviceControl(DeviceControl.powerPathKT);
        DevCtrl.setGpio(94);
        isTT43 = false;
        try {
            DevCtrl.PowerOnMTDevice();
        } catch (IOException e) {
            Toast.makeText(this.context, e.toString(), Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, e.toString());
            state = 0;
            return state;
        }
		Log.i(LOG_TAG, "STATUSI PORTES " + state.toString());
		return state;
    }
    
    protected boolean onDestroy() {
		try {
			if (isTT43) {
				DevCtrl.PowerOffDevice();
			} else {
				DevCtrl.PowerOffMTDevice();
			}
		} catch (IOException e) {
            Toast.makeText(this.context, e.toString(), Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, e.toString());
            return false;
        }
        return true;
	}

	private Integer getState(){
		return state;
	}
}
