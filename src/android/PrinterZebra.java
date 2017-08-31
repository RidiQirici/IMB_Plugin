package imb.ridiqirici.plugin.cordova.universal;

import com.zebra.sdk.comm.BluetoothConnection;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import java.lang.*;

public class PrinterZebra extends Printer {

	private static final String LOG_TAG = "ZEBRA_PRINTER";

	private Context context = null;

	public PrinterZebra(Context context){
		super(context);
		this.context = context;
		Log.i(LOG_TAG, "Hyri ne klasen PrinterZebra");
	}

	@Override
	public Mesazh printoText(String adresa, String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String adresa, String textPerPrintim)");
		return  printoTextProcedure(adresa, textPerPrintim);
	}

    @Override
	public Mesazh printoText(String textPerPrintim, Integer align, Boolean small) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim, Integer align, Boolean small)");
		return  printoTextProcedure("", textPerPrintim);
    }

	@Override
	public Mesazh printoText(String textPerPrintim) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim)");
		return  printoTextProcedure(super.getAdresaPajisjes(), textPerPrintim);
	}

	@Override
	public Mesazh printoText(String textPerPrintim, Integer wight, Integer hight, Integer align, Boolean small) {
		Log.i(LOG_TAG, "Hyri ne metoden printoText(String textPerPrintim, Integer wight, Integer hight, Integer align, Boolean small)");
		return  printoTextProcedure("", textPerPrintim);
    }
    
    @Override
	public Mesazh printoBarcode(String textPerPrintim, Integer align, Boolean small) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @Override
	public Mesazh printoTextSpecial(String textPerPrintim, Integer width, Integer height, Integer x, Integer y, Integer fontSize, Integer lineWidth) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

	private Mesazh printoTextProcedure(String adresa, String textPerPrintim){
		Mesazh pergjigje = null;
		Connection connection =  new BluetoothConnection(adresa);

		try {
			connection.open();
			com.zebra.sdk.printer.ZebraPrinter printer = ZebraPrinterFactory.getInstance(connection);   
			printer.sendCommand("! U1 setvar \"zpl.label_length\" \"20\"");
			//String dergoString = "^XA^FO50,50^ADN,36,20^FD" + label + "^FS\r\n";
			connection.write(textPerPrintim.getBytes());
			//printer.printStoredFormat(label, new HashMap<Integer, String>(), "utf8");
			connection.close();

		} catch (ConnectionException e) {
			e.printStackTrace();
			Log.e(LOG_TAG, e.toString());
			Toast.makeText(this.context, e.toString(), Toast.LENGTH_LONG).show();
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		} catch (ZebraPrinterLanguageUnknownException e) {
			e.printStackTrace();
			Log.e(LOG_TAG, e.toString());
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		} 

		try {
			if(connection.isConnected()){
				connection.close();
			}   
			Log.i(LOG_TAG, "Printimi i tekstit u krye me sukses! ");
			Toast.makeText(this.context, "Printimi i tekstit u krye me sukses! ", Toast.LENGTH_LONG).show();
			pergjigje = new Mesazh(true, "Printimi i tekstit u krye me sukses! " + Environment.getExternalStorageDirectory() + " " + Environment.getRootDirectory());
			return pergjigje;
		} catch (ConnectionException e) {
			e.printStackTrace();
			Log.e(LOG_TAG, e.toString());
			Toast.makeText(this.context, e.toString(), Toast.LENGTH_LONG).show();
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		}
	}
}