package imb.ridiqirici.plugin.cordova.universal;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Plugin extends CordovaPlugin {

	public static final String PRINT_TEXT = "printText";
	public static final String PRINT_TEXT_MEMAC = "printTextMeMac";
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (PRINT_TEXT.equals(action)) {
        	Printer printeri = KrijuesPrinter.krijoPrinter(PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
        	Mesazh pergjigje = printeri.printoText(label);
        	if (pergjigje.isStatusi())
        	{
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
        	}
        }
        else if (PRINT_TEXT_MEMAC.equals(action)) {
        	Printer printeri = KrijuesPrinter.krijoPrinter(PrinterEnum.valueOf(args.getString(0)));
            String label = args.getString(1);
            String macaddress = args.getString(2);
        	Mesazh pergjigje = printeri.printoText(macaddress, label);
        	if (pergjigje.isStatusi())
        	{
        		callbackContext.success(pergjigje.getMesazhi());
        		return true;
        	}
        	else{
        		callbackContext.error(pergjigje.getMesazhi());
        		return false;
        	}
        }
        return false;
    }
}
