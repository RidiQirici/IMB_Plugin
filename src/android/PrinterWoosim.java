package imb.ridiqirici.plugin.cordova.universal;

import com.woosim.bt.*;

public class PrinterWoosim extends Printer{

	private WoosimPrinter woosim;
	
	private final static String EUC_KR = "EUC-KR";
	private final static String ISO_8859_1 = "ISO-8859-1"; 
	private final static String US_ASCII = "US-ASCII"; 
	private final static String UTF_16 = "UTF-16"; 
	private final static String UTF_16BE = "UTF-16BE";
	private final static String UTF_16LE = "UTF-16LE"; 
	private final static String UTF_8 = "UTF-8";
	
	public PrinterWoosim(){
		super();
		woosim = new WoosimPrinter();
	}

	@Override
	public Mesazh printoText(String adresa, String textPerPrintim) {
		return  printoTextProcedure(adresa, textPerPrintim);
	}
	
	public Mesazh printoText(String textPerPrintim) {
		return  printoTextProcedure(super.getAdresaPajisjes(), textPerPrintim);
	}

	private Mesazh printoTextProcedure(String adresa, String textPerPrintim){
		Mesazh pergjigje = null;
		try {
			pergjigje = krijoLidhjeBlueTooth(adresa);
			if (pergjigje.isStatusi())
			{
				woosim.saveSpool(EUC_KR, textPerPrintim, 0, false);
				woosim.printSpool(true);
				woosim.closeConnection();
				pergjigje = new Mesazh(true, "Printimi u krye me sukses");
			}
			return pergjigje;
		}
		catch(Exception e)
		{
			pergjigje = new Mesazh(false, e.toString());
			return pergjigje;
		}
	}
	
	private Mesazh krijoLidhjeBlueTooth(String adresa){
		int connection = woosim.BTConnection(adresa, false);
		if(connection== 1)
			return new Mesazh(true, "Lidhja eshte kryer me sukses!");
		else if(connection== -2)
			return new Mesazh(false, "Lidhja nuk eshte kryer me sukses!");
		else if(connection== -5)
			return new Mesazh(false, "Pajisja nuk eshte lidhur!");
		else if(connection== -6)
			return new Mesazh(true, "Pajisja eshte e lidhur njehere!");
		else if(connection== -8)
			return new Mesazh(false, "Ju lutem aktivizoni bluetooth-in dhe provoni perseri!");
		else
			return new Mesazh(false, "Ndodhi nje gabim gjate lidhjes me bluetooth-in!");
	}
	
}
