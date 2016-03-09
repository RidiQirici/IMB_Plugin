package imb.ridiqirici.plugin.cordova.universal;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class Printer {

	private String adresaPajisjes;
	

	public String getAdresaPajisjes() {
		return adresaPajisjes;
	}

	public void setAdresaPajisjes(String adresaPajisjes) {
		this.adresaPajisjes = adresaPajisjes;
	}
	
	public Printer(){
		BroadcastReceiver mReceiver = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		        String action = intent.getAction();

		        // When discovery finds a device
		        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		            // Get the BluetoothDevice object from the Intent
		            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		            setAdresaPajisjes(device.getAddress());
		        }
		    }
		};
	}
	
	public abstract Mesazh printoText(String textPerPrintim) ;
	public abstract Mesazh printoText(String adresa, String textPerPrintim) ;
}
