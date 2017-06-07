package imb.ridiqirici.plugin.cordova.universal;

import com.pda3505.printer.PrinterClassSerialPort;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class PrinterPDA3505 extends Printer {
	
	private static final String LOG_TAG = "PDA3505_PRINTER";

	private Context context = null;

	public PrinterPDA3505(Context context){
		super();
		this.context = context;
		Log.i(LOG_TAG, "Hyri ne klasen PrinterPDA3505");
	}
	
	@Override
	public Mesazh printoText(String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
		return  printoTextProcedure("", textPerPrintim);
	}

	@Override
	public Mesazh printoText(String adresa, String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String adresa, String textPerPrintim)");
		return  printoTextProcedure(adresa, textPerPrintim);
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer wight, Integer hight) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim, Integer wight, Integer hight)");
		return  printoTextProcedure("", textPerPrintim);
	}
	
	private Mesazh printoTextProcedure(String adresa, String textPerPrintim){
		Mesazh pergjigje = null;
		Log.i(LOG_TAG, "Hyri ne metoden printoTextProcedure(String adresa, String textPerPrintim)");
		try {

			PrinterClassSerialPort printerClass = new PrinterClassSerialPort();
			Log.i(LOG_TAG, "Statusi i printerit " + printerClass.getState());
			if (!printerClass.IsOpen()) {
				Log.i(LOG_TAG, "Po hapet porta e lidhjes me PDA3505...");
				pergjigje = new Mesazh(printerClass.open(this.context), "Po hapet porta e lidhjes me PDA3505...");
				Log.i(LOG_TAG, "U hap porta e lidhjes me PDA3505...");
				Toast.makeText(this.context, "U hap porta e lidhjes me PDA3505...", Toast.LENGTH_LONG).show();
			}

			if (!pergjigje.isStatusi()) {
				pergjigje.setMesazhi("Ndodhi nje problem gjate hapjes se portes seriale 38400!");
				Log.e(LOG_TAG, "Ndodhi nje problem gjate hapjes se portes seriale 38400!");
				Toast.makeText(this.context, "Ndodhi nje problem gjate hapjes se portes seriale 38400!", Toast.LENGTH_LONG).show();
				return pergjigje;
			}
			pergjigje.setStatusi(printerClass.printText(textPerPrintim));
			System.out.println(LOG_TAG + " E ekzekutoi per nder printerClass.printText(stringaXPrintim)");
			if (!pergjigje.isStatusi()) {
				Log.e(LOG_TAG, "Printimi i tekstit nuk u krye me sukses!");
				pergjigje.setMesazhi("Printimi i tekstit nuk u krye me sukses!");
				Toast.makeText(this.context, "Printimi i tekstit nuk u krye me sukses!", Toast.LENGTH_LONG).show();
				return pergjigje;
			}
			Log.i(LOG_TAG, "Printimi i tekstit u krye me sukses!");
			printerClass = null;
			pergjigje.setMesazhi("Printimi i tekstit u krye me sukses!");
			Toast.makeText(this.context, "Printimi i tekstit u krye me sukses!", Toast.LENGTH_LONG).show();
			return pergjigje;
		} catch (Exception e) {
			Log.e(LOG_TAG, "Gabim gjate printimit te tekstit! " + e.getMessage() + " " + e.toString());
			return new Mesazh(false, "Gabim gjate printimit te tekstit! " + e.getMessage() + " " + e.toString());
		}
	}

}
