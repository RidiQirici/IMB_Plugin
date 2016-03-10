package imb.ridiqirici.plugin.cordova.universal;

import android.content.Context;
import android.util.Log;

public class KrijuesPrinter {

	private static final String LOG_TAG = "KRIJUES_PRINTER";
	
	public static Printer krijoPrinter(Context contex, PrinterEnum printeri){
		Printer printerKrijuar = null;
		switch (printeri)
		{
			case WOOSIM:
				Log.i(LOG_TAG, "Krijimi i printeri WOOSIM");
				printerKrijuar =  new PrinterWoosim(contex);
				break;
			default:
				break;
				
		}
		return printerKrijuar;		
	}
}
