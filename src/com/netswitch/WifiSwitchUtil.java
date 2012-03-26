package com.netswitch;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

class WifiSwitchUtil{
	
	private boolean wifi_state;
	WifiSwitchUtil()
	{
		
	}
	
	
	public void changeWifiState(boolean state, Context mContext)
	{
		//this.wifi_state = state;
		WifiManager mWifi = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
		mWifi.setWifiEnabled(state);
	}
	
	
	public boolean getWifiState(Context context)
	{
		String service = Context.CONNECTIVITY_SERVICE;
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(service);
		try{
			NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
					
			
			if (activeNetwork == null) {
				wifi_state = false;
			}
			else {
				int connectionType = activeNetwork.getType();
				switch (connectionType) {
					case (ConnectivityManager.TYPE_MOBILE) : 
						wifi_state = false;
						break;
				  	case (ConnectivityManager.TYPE_WIFI) : 
				  		wifi_state = true;
				  		break;
				  	default: 
				  		break;
				}
			}
		}
		catch (SecurityException e)
		{
			wifi_state = false;
		}
		return wifi_state;
	}
}