package imb.ridiqirici.plugin.cordova.universal;

public class Mesazh {

	private boolean statusi;
	private String mesazhi;
	
	public boolean isStatusi() {
		return statusi;
	}
	
	public void setStatusi(boolean statusi) {
		this.statusi = statusi;
	}

	public String getMesazhi() {
		return mesazhi;
	}

	public void setMesazhi(String mesazhi) {
		this.mesazhi = mesazhi;
	}
	
	public Mesazh(boolean statusi, String mesazhi){
		this.statusi = statusi;
		this.mesazhi = mesazhi;
	}
}
