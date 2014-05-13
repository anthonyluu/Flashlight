package com.example.flashlight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	ImageButton buttonSwitch;
	
	private Camera camera;
	private boolean isFlashlightOn;
	private boolean hasFlashlight;
	Parameters params;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonSwitch = (ImageButton) findViewById(R.id.btnSwitch);
		
		hasFlashlight = getApplicationContext().getPackageManager()
		        .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		
		
		if(!hasFlashlight) {
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
			alertBuilder.setTitle("Support Error")
			.setMessage("Sorry, this device does not support flashlight")
			.setNegativeButton("Close", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			AlertDialog alert = alertBuilder.create();
			alert.show();
		}
		
	}
	
	// not working yet
	private void toggleSwitch() {
		if(isFlashlightOn) {
			buttonSwitch.setImageResource(R.drawable.button_on);
		}
		else {
			buttonSwitch.setImageResource(R.drawable.button_off);
		}
	}
}
