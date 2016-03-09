package imb.ridiqirici.plugin.cordova.universal;

public class KrijuesPrinter {

	public static Printer krijoPrinter(PrinterEnum printeri){
		Printer printerKrijuar = null;
		switch (printeri)
		{
			case WOOSIM:
				printerKrijuar =  new PrinterWoosim();
				break;
			default:
				break;
				
		}
		return printerKrijuar;		
	}
}
