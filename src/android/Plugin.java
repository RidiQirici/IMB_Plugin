package imb.ridiqirici.plugin.cordova.universal;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class Plugin extends CordovaPlugin {

    public static final String PRINT_TEXT = "printText";
    public static final String PRINT_TEXT_PARAMETRA = "printTextParametra";
	public static final String PRINT_TEXT_MEMAC = "printTextMeMac";
    public static final String PRINT_TEXT_MEPERMASA = "printTextMePermasa";
    public static final String PRINT_TEXT_SIZE = "printTextSunMi";
    public static final String PRINT_BARCODE = "printBarcode";
    public static final String PRINT_TEXT_SPECIAL = "printTextSpecial";
	private static final String LOG_TAG = "PLUGIN_IMB";
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    	Context context = this.cordova.getActivity().getApplicationContext(); 
    	
        if (PRINT_TEXT.equals(action)) {
        	Log.i(LOG_TAG, "Krijimi i printerit sipas llojit");
        	Printer printeri = KrijuesPrinter.krijoPrinter(context, PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
        	Mesazh pergjigje = printeri.printoText(label);
        	if (pergjigje.isStatusi())
        	{
        		Log.i(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		Log.e(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
        	}
        }
        else if (PRINT_TEXT_PARAMETRA.equals(action)) {
        	Log.i(LOG_TAG, "Krijimi i printerit sipas llojit");
        	Printer printeri = KrijuesPrinter.krijoPrinter(context, PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
            Integer align = Integer.parseInt(args.getString(2));
            Boolean small = Boolean.parseBoolean(args.getString(3));
        	Mesazh pergjigje = printeri.printoText(label, align, small);
        	if (pergjigje.isStatusi())
        	{
        		Log.i(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		Log.e(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
        	}
        }
        else if (PRINT_TEXT_MEMAC.equals(action)) {
        	Log.i(LOG_TAG, "Krijimi i printerit sipas llojit me parameter macadress");
        	Printer printeri = KrijuesPrinter.krijoPrinter(context, PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
            String macaddress = args.getString(2);
        	Mesazh pergjigje = printeri.printoText(macaddress, label);
        	if (pergjigje.isStatusi())
        	{
        		Log.i(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		Log.e(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
        	}
        }
        else if (PRINT_BARCODE.equals(action)){
            Log.i(LOG_TAG, "Krijimi i printerit per printimin me barcode");
        	Printer printeri = KrijuesPrinter.krijoPrinter(context, PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
            Integer align = Integer.parseInt(args.getString(2));
            Boolean small = Boolean.parseBoolean(args.getString(3));
        	Mesazh pergjigje = printeri.printoBarcode(label, align, small);
        	if (pergjigje.isStatusi())
        	{
        		Log.i(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		Log.e(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
            }
        }
        else if (PRINT_BARCODE.equals(action)){
            Log.i(LOG_TAG, "Krijimi i printerit per printimin me barcode");
        	Printer printeri = KrijuesPrinter.krijoPrinter(context, PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
            Integer align = Integer.parseInt(args.getString(2));
            Boolean small = Boolean.parseBoolean(args.getString(3));
        	Mesazh pergjigje = printeri.printoBarcode(label, align, small);
        	if (pergjigje.isStatusi())
        	{
        		Log.i(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		Log.e(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
            }
        }
        else if (PRINT_TEXT_SPECIAL.equals(action)){
            Log.i(LOG_TAG, "Krijimi i printerit per printimin me karaktere speciale");
        	Printer printeri = KrijuesPrinter.krijoPrinter(context, PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
            float size = Float.parseFloat(args.getString(2));
            boolean isBold = Boolean.parseBoolean(args.getString(3));
            boolean idUnderline = Boolean.parseBoolean(args.getString(4));
        	Mesazh pergjigje = printeri.printText(label, size, isBold, idUnderline);
        	if (pergjigje.isStatusi())
        	{
        		Log.i(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		Log.e(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
            }
        }
        else if (PRINT_TEXT_SIZE.equals(action)){
            Log.i(LOG_TAG, "Krijimi i printerit per printimin me karaktere speciale");
        	Printer printeri = KrijuesPrinter.krijoPrinter(context, PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
            Integer width = Integer.parseInt(args.getString(2));
            Integer height = Integer.parseInt(args.getString(3));
            Integer x = Integer.parseInt(args.getString(4));
            Integer y = Integer.parseInt(args.getString(5));
            Integer fontSize = Integer.parseInt(args.getString(6));
            Integer lineWidth = Integer.parseInt(args.getString(7));
        	Mesazh pergjigje = printeri.printoTextSpecial(label, width, height, x, y, fontSize, lineWidth);
        	if (pergjigje.isStatusi())
        	{
        		Log.i(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		Log.e(LOG_TAG, pergjigje.getMesazhi());
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
            }
        }
        return false;
    }
}
