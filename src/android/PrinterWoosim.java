package imb.ridiqirici.plugin.cordova.universal;

import com.woosim.bt.*;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class PrinterWoosim extends Printer{

	private WoosimPrinter woosim;
	
	private final static String EUC_KR = "EUC-KR";
	/*private final static String ISO_8859_1 = "ISO-8859-1"; 
	private final static String US_ASCII = "US-ASCII"; 
	private final static String UTF_16 = "UTF-16"; 
	private final static String UTF_16BE = "UTF-16BE";
	private final static String UTF_16LE = "UTF-16LE"; 
	private final static String UTF_8 = "UTF-8";*/
	private static final String LOG_TAG = "WOOSIM_PRINTER";
	
	private Context context = null;
	
	public PrinterWoosim(Context context){
		super(context);
		this.context = context;
		Log.i(LOG_TAG, "Hyri ne klasen PrinterWoosim");
		woosim = new WoosimPrinter();
	}

	@Override
	public Mesazh printoText(String adresa, String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String adresa, String textPerPrintim)");
		return  printoTextProcedure(adresa, textPerPrintim);
	}
	
	@Override
	public Mesazh printoText(String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
		return  printoTextProcedure(super.getAdresaPajisjes(), textPerPrintim);
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer wight, Integer hight) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim, Integer wight, Integer hight)");
		return  printoTextProcedure("", textPerPrintim);
	}

	private Mesazh printoTextProcedure(String adresa, String textPerPrintim){
		Mesazh pergjigje = null;
		try {
			Log.i(LOG_TAG, "Po krijohet lidha me bluetooth-in");
			pergjigje = krijoLidhjeBlueTooth(adresa);
			if (pergjigje.isStatusi())
			{
				Log.i(LOG_TAG, "Ruajtja ne spool");
				woosim.saveSpool(EUC_KR, textPerPrintim, 0, false);
				Log.i(LOG_TAG, "Printimi nga spool");
				woosim.printSpool(true);
				Log.i(LOG_TAG, "U mbyll lidhja me bluetooth-in");
				woosim.closeConnection();
				Toast.makeText(this.context, "Printimi u krye me sukses", Toast.LENGTH_LONG).show();
				pergjigje = new Mesazh(true, "Printimi u krye me sukses");
			}
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
	
	private Mesazh krijoLidhjeBlueTooth(String adresa){
		int connection = woosim.BTConnection(adresa, false);
		if(connection== 1){
			Log.i(LOG_TAG, "Lidhja eshte kryer me sukses!");
			Toast.makeText(this.context, "Lidhja eshte kryer me sukses!", Toast.LENGTH_LONG).show();
			return new Mesazh(true, "Lidhja eshte kryer me sukses!");
		}
		else if(connection== -2){
			Log.e(LOG_TAG, "Lidhja nuk eshte kryer me sukses!");
			Toast.makeText(this.context, "Lidhja nuk eshte kryer me sukses!", Toast.LENGTH_LONG).show();
			return new Mesazh(false, "Lidhja nuk eshte kryer me sukses!");
		}
		else if(connection== -5){
			Log.e(LOG_TAG,  "Pajisja nuk eshte lidhur!");
			Toast.makeText(this.context, "Pajisja nuk eshte lidhur!", Toast.LENGTH_LONG).show();
			return new Mesazh(false, "Pajisja nuk eshte lidhur!");
		}
		else if(connection== -6){
			Log.i(LOG_TAG, "Pajisja eshte e lidhur njehere!");
			Toast.makeText(this.context, "Pajisja eshte e lidhur njehere!", Toast.LENGTH_LONG).show();
			return new Mesazh(true, "Pajisja eshte e lidhur njehere!");
		}
		else if(connection== -8){
			Log.e(LOG_TAG, "Ju lutem aktivizoni bluetooth-in dhe provoni perseri!");
			Toast.makeText(this.context, "Ju lutem aktivizoni bluetooth-in dhe provoni perseri!", Toast.LENGTH_LONG).show();
			return new Mesazh(false, "Ju lutem aktivizoni bluetooth-in dhe provoni perseri!");
		}
		else{
			Log.e(LOG_TAG, "Ndodhi nje gabim gjate lidhjes me bluetooth-in!");
			Toast.makeText(this.context, "Ndodhi nje gabim gjate lidhjes me bluetooth-in!", Toast.LENGTH_LONG).show();
			return new Mesazh(false, "Ndodhi nje gabim gjate lidhjes me bluetooth-in!");
		}
	}
	
}
