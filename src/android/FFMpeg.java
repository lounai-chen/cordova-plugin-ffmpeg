package com.marin.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import com.arthenica.mobileffmpeg.FFmpeg;
import android.os.Environment;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FFMpeg extends CordovaPlugin {

    @Override
    public boolean execute(String action,  CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        //https://github.com/tanersener/mobile-ffmpeg/wiki/Android
        if (action.equals("exec")) {
            String fileName = args.getString(0);
            String fileType = args.getString(1); 
            String targetUrl = Environment.getExternalStorageDirectory().getPath()+"/"+fileName+"_ffmpeg.mp3";
            String sourceUrl = Environment.getExternalStorageDirectory().getPath()+"/"+fileName+"."+fileType;
            Date date1 = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

            //Log.i(null, "开始"+dateFormat.format(date1));
            FFmpeg.execute("-i "+sourceUrl+" "+targetUrl);
            int rc = FFmpeg.getLastReturnCode();
            String output = FFmpeg.getLastCommandOutput();
            Date date2 = new Date();
            //Log.i(null, "结束"+dateFormat.format(date2));

            String message = "FFmpeg, " + targetUrl;
            if (rc == 0) {
                String ret = "Command execution completed successfully.";
                //Log.i(Config.TAG, ret);
                message += "Command execution completed successfully.";
                callbackContext.success(message+"开始 "+dateFormat.format(date1)+ "  ，结束 "+dateFormat.format(date2));
            } else {
                String ret = String.format("Command execution failed with rc=%d and output=%s.", rc, output);
                //Log.i(Config.TAG, ret);
                message += ret;
                callbackContext.error(message);
            }

            return true;


        } else {
            
            return false;

        }
    }
}
