package com.paresh.readfileasset;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Paresh N. Mayani
 * @Website http://www.technotalkative.com
 */
public class ReadFileAssetsActivity extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView txtContent = (TextView) findViewById(R.id.txtContent);
		TextView txtFileName = (TextView) findViewById(R.id.txtFileName);
		ImageView imgAssets = (ImageView) findViewById(R.id.imgAssets);
		
		AssetManager assetManager = getAssets();
		
		// To get names of all files inside the "Files" folder
		try {
			String[] files = assetManager.list("Files");
			
			for(int i=0; i<files.length; i++)
			{
				txtFileName.append("\n File :"+i+" Name => "+files[i]);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// To load text file
        InputStream input;
		try {
			input = assetManager.open("helloworld.txt");
			
	         int size = input.available();
	         byte[] buffer = new byte[size];
	         input.read(buffer);
	         input.close();

	         // byte buffer into a string
	         String text = new String(buffer);
			
	         txtContent.setText(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// To load image
    	try {
	    	// get input stream
	    	InputStream ims = assetManager.open("android_logo_small.jpg");
	    	
	    	// create drawable from stream
	    	Drawable d = Drawable.createFromStream(ims, null);
	    	
	    	// set the drawable to imageview
	    	imgAssets.setImageDrawable(d);
    	}
    	catch(IOException ex) {
    		return;
    	}
	}
}