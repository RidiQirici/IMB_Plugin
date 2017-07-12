package imb.ridiqirici.plugin.cordova.universal;

import rego.printlib.export.regoPrinter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
//import android.serialport.DeviceControl;

public class PrinterEM55 extends Printer{

	private static final String LOG_TAG = "EM55_PRINTER";
	
	private Context context = null;
	private ApplicationContext contextApp = null;
	private Integer state = 0;

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
		return  printoTextProcedure(60, 40, textPerPrintim);
	}

	@Override
	public Mesazh printoText(String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
		return  printoTextProcedure(60, 40, textPerPrintim);
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer wight, Integer hight) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
		return  printoTextProcedure(wight, hight, textPerPrintim);
	}

	private Mesazh printoTextProcedure(Integer wight, Integer hight, String textPerPrintim){
		Mesazh pergjigje = null;
		try {
			if (checkState() > 0){
				Toast.makeText(this.context, "Lidhja me printerin u krye me sukses!", Toast.LENGTH_LONG).show();
				this.contextApp.getObject().CON_PageStart(getState(), false, wight, hight);
				{
					//Left Alignment
					this.contextApp.getObject().ASCII_CtrlAlignType(getState(), 0);
					//Oposite color false
					this.contextApp.getObject().ASCII_CtrlOppositeColor(getState(), false);
					//Thick false
					this.contextApp.getObject().ASCII_PrintBuffer(getState(), new byte[] { 0x1b, 0x45, 0x00 }, 3);
					//WIGHT, HIGHT, THICK, UNDERLINE, SMALL
					this.contextApp.getObject().ASCII_PrintString(getState(), 0, 0, 0, 0, 0, textPerPrintim, "gb2312");
					this.contextApp.getObject().ASCII_CtrlFeedLines(getState(), 1);
					this.contextApp.getObject().ASCII_CtrlPrintCRLF(getState(), 1);
				}
				this.contextApp.getObject().CON_PageEnd(getState(), 0);
				Toast.makeText(this.context, "Printimi perfundoi me sukses!", Toast.LENGTH_LONG).show();
				Log.i(LOG_TAG, "Printimi perfundoi me sukses!");
				pergjigje = new Mesazh(true, "Printimi perfundoi me sukses!");
				return pergjigje;
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
		Log.i(LOG_TAG, "STATUSI PORTES " + state.toString());
		return state;
	}

	private Integer getState(){
		return state;
	}
}
