package com.example.izual.studentftk.Network;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by oglandx on 27.05.2015.
 */
public class ImageResourceLoader extends AbstractAndroidLoader {
    public ImageResourceLoader(final Activity activity, int connectionTimeout){
        super(activity, connectionTimeout);
    }

    private class DrawableLoadTask implements Runnable{
        private Drawable data = null;
        private boolean isDataReady = false;
        private final URL url;

        public DrawableLoadTask(final URL url){ this.url = url; }

        public Drawable getData(){ return data; }

        public boolean IsDataReady(){ return isDataReady; }

        @Override
        public void run() {
            try {
                InputStream inputStream = url.openStream();
                data = Drawable.createFromStream(inputStream, "image_friends");
                isDataReady = true;
            }
            catch (Exception e){
                Error(e.toString());
            }
        }
    }

    public Drawable Load(final String url){
        DrawableLoadTask task = null;
        try{
            task = new DrawableLoadTask(new URL(url));
            Thread thread = new Thread(task);
            thread.start();
            thread.join();
        }
        catch(Exception e){
            Error(e.toString());
        }
        finally{
            ShowError();
        }
        return (task != null && task.IsDataReady()) ? task.getData() : null;
    }
}
