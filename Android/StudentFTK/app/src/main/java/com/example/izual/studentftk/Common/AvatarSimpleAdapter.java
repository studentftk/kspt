package com.example.izual.studentftk.Common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.izual.studentftk.Common.Settings;
import com.example.izual.studentftk.Network.ImageResourceLoader;

import java.util.List;
import java.util.Map;

/**
 * Created by oglandx on 03.06.2015.
 */
public class AvatarSimpleAdapter extends SimpleAdapter {

    private final Context context;

    public AvatarSimpleAdapter(Context context, List<? extends Map<String, ?>> data,
                               int resource, String[] from, int[] to){
        super(context, data, resource, from, to);
        this.context = context;
    }

    @Override
    public void setViewImage(@NonNull ImageView v, String value) {
        ImageResourceLoader loader =
                    new ImageResourceLoader((Activity)context, Settings.connectionTimeout);
        v.setImageDrawable(loader.GetCachedOrLoad(value));
    }
}
