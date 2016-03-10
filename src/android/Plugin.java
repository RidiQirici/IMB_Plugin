package imb.ridiqirici.plugin.cordova.universal;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class Plugin extends CordovaPlugin {

	public static final String PRINT_TEXT = "printText";
	public static final String PRINT_TEXT_MEMAC = "printTextMeMac";
	private static final String LOG_TAG = "PLUGIN_IMB";
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    	Context context=this.cordova.getActivity().getApplicationContext(); 
    	
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
        return false;
    }
}
