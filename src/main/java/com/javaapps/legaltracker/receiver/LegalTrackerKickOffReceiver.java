package com.javaapps.legaltracker.receiver;


import java.io.FileNotFoundException;
import java.io.IOException;

import com.javaapps.legaltracker.pojos.Config;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.SystemClock;
import android.util.Log;

public class LegalTrackerKickOffReceiver extends BroadcastReceiver {
	private Config config = Config.getConfig();
    private LegalTrackerLocationListener locationListener;
    
     
	@Override
	public synchronized void onReceive(Context context, Intent i) {
		Log.i("legaltracker init " , "LegalTrackerKickOffReceiver received intent "+i.getAction());
		locationListener=new LegalTrackerLocationListener(context);
		scheduleLocationPolling(context);
		scheduleUploads(context);
	}


	
	private void scheduleUploads(Context context) {
		AlarmManager mgr=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent i=new Intent(context, LocationDataUploader.class);
		PendingIntent pi=PendingIntent.getBroadcast(context, 0, i, 0);
		mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, config.getLocationUploadDelay(), config.getLocationUploadPeriod(), pi);
	}



	private void scheduleLocationPolling(Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 15000, 0,
					this.locationListener);
		}

}
