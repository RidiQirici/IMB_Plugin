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
			case ZEBRA:
				Log.i(LOG_TAG, "Krijimi i printeri ZEBRA");
				printerKrijuar =  new PrinterZebra(contex);
				break;
			/*case PC700:
				Log.i(LOG_TAG, "Krijimi i printeri PC700");
				printerKrijuar =  new PrinterPC700(contex);
				break;*/
			case PDA3505:
				Log.i(LOG_TAG, "Krijimi i printeri PDA3505");
				printerKrijuar =  new PrinterPDA3505 (contex);
				break;
			default:
				break;
				
		}
		return printerKrijuar;		
	}
}
