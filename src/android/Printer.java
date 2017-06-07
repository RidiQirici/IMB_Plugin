package imb.ridiqirici.plugin.cordova.universal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public abstract class Printer {

	private String adresaPajisjes;
	private static final String LOG_TAG = "PRINTER";
	
	
	public String getAdresaPajisjes() {
		return adresaPajisjes;
	}

	public void setAdresaPajisjes(String adresaPajisjes) {
		this.adresaPajisjes = adresaPajisjes;
	}
	
	public Printer(){
		Log.i(LOG_TAG, "Hyri ne klasen Printer pa parametra");
	}
	
	public Printer(Context context){
		Log.i(LOG_TAG, "Hyri ne klasen Printer");
		
		BroadcastReceiver mReceiver = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		        String action = intent.getAction();
		        Log.i(LOG_TAG, "Kerkimi i bluetooth-it...");
		        // When discovery finds a device
		        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		            // Get the BluetoothDevice object from the Intent
		            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		            setAdresaPajisjes(device.getAddress());
		            Log.i(LOG_TAG, "MAC adresa e pajisjes per tu lidhur eshte " + device.getAddress());
		        }
		    }

		};

		//register receiver 
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		context.registerReceiver(mReceiver, filter); 

		//start bluetooth scan 
		BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
		mBtAdapter.startDiscovery();
		
		Toast.makeText(context, "Duke u lidhur me pajisjen " + getAdresaPajisjes(), Toast.LENGTH_LONG).show();
	}

	public abstract Mesazh printoText(String textPerPrintim) ;
	
	public abstract Mesazh printoText(String adresa, String textPerPrintim) ;

	public abstract Mesazh printoText(String textPerPrintim, Integer wight, Integer hight) ;
}
