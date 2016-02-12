package com.sambengtson.cordovaencrypt;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CordovaEncrypt extends CordovaPlugin {

/**
* Sets the context of the Command. This can then be used to do things like
* get file paths associated with the Activity.
*
* @param cordova The context of the main Activity.
* @param webView The CordovaWebView Cordova is running in.
*/

final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.v("Cordova Encrypt","Init CordovaEncrypt");
	}

	public String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {


		if (args.length() == 0 || args.length() > 1){
			callbackContext.error("Invalid arguements specified");
			return true;
		}

		String plainText = args.getString(0);

		if (action.equals("sha256")){

			String encryptedString = "";
			try{

				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));

				encryptedString = bytesToHex(hash);

			} catch(NoSuchAlgorithmException e){
				/* really shouldn't be possible */
				callbackContext.error("No such algorithm supported");
			}

			callbackContext.success(encryptedString);

			return true;

		} else{

			return false;
		}
	}

}

