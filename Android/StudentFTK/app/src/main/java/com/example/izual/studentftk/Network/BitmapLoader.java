package com.example.izual.studentftk.Network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.izual.studentftk.Common.Utils;
import com.example.izual.studentftk.Network.RequestExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by oglandx on 23.05.2015.
 */
public class BitmapLoader {
    private BitmapLoader() {}

    public final int connectionTimeout = 1000;

    public static Bitmap LoadBitmap(URL url) {
        Bitmap bitmap = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return bitmap;
        }
    }

    private static class BitmapLoadTask implements Runnable{
        private Bitmap data = null;
        private boolean isDataReady = false;
        private final URL url;

        public BitmapLoadTask(final URL url){ this.url = url; }

        public Bitmap getBitmap(){ return data; }

        public boolean IsDataReady(){ return isDataReady; }

        @Override
        public void run() {
            data = LoadBitmap(url);
            isDataReady = true;
        }
    }

    public static Bitmap GetBitmapFromURL(final Activity activity,
                                          final String src, final int width, int height){
        try {
            URL url = new URL(src);
            BitmapLoadTask task = new BitmapLoadTask(url);
            Thread thread = new Thread(task);
            thread.start();
            thread.join();
            if (!task.IsDataReady()){
                throw new IOException("Cannot load bitmap");
            }
            else{
                return Bitmap.createScaledBitmap(task.getBitmap(), width, height, true);
            }
        }
        catch(Exception e){
            final String message = e.getMessage();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.ShowError(activity, message, true);
                }
            });
            return null;
        }
    }
}
