package com.example.flashlight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	ImageButton buttonSwitch;
	
	private Camera c;
	private boolean isFlashlightOn = false;
	private boolean hasFlashlight;
	Parameters p;
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
		getCamera();
		
		buttonSwitch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleSwitch();				
			}
		});
		
	}
	
	
	private void toggleSwitch() {
		Log.d("buttonClick", "clicked");
		
		if(isFlashlightOn) {
			turnOff();
			buttonSwitch.setImageResource(R.drawable.button_off);
		}
		else {
			turnOn();
			buttonSwitch.setImageResource(R.drawable.button_on);
			
		}
		
		
	}
	
	private void getCamera() {
		if(c == null) {
			try {
				c = Camera.open();
				p = c.getParameters();
			}
			catch(RuntimeException e) {
				Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
			}
		}
	}
	
	private void turnOn() {
		p = c.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		c.setParameters(p);
		c.startPreview();
		isFlashlightOn = true;
	}
	
	private void turnOff() {
		p = c.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_OFF);
		c.setParameters(p);
		c.stopPreview();
		isFlashlightOn = false;
	}
}
