package com.example.izual.studentftk.Friends;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.izual.studentftk.Common.Settings;
import com.example.izual.studentftk.Network.ImageResourceLoader;
import com.example.izual.studentftk.R;
import com.example.izual.studentftk.Users.UserStruct;

import java.util.ArrayList;

/**
 * Created by oglandx on 02.06.2015.
 */
public class FriendsListAdapter extends ArrayAdapter<UserStruct>{
    private final Activity activity;
    private final ArrayList<UserStruct> entries;

    private static class ViewHolder {
        public ImageView avatar;
        public TextView name;
    }

    public FriendsListAdapter(final Activity activity,
                              final int viewResourceId,final ArrayList<UserStruct> entries){
        super(activity, viewResourceId);
        this.activity = activity;
        this.entries = entries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        ViewHolder holder;
//        View view = convertView;
//        if(view == null){
//            LayoutInflater inflater = (LayoutInflater)
//                    activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.item_friends_list, parent, false);
//            holder = new ViewHolder();
//            holder.avatar = (ImageView) view.findViewById(R.id.imgFriendsListAvatar);
//            holder.name = (TextView) view.findViewById(R.id.txtFriendsListName);
//            view.setTag(holder);
//        }
//        else{
//            holder = (ViewHolder) view.getTag();
//        }
//        final UserStruct user = entries.get(position);
//        if(user != null){
//            holder.name.setText(user.Name + " " + user.Surname);
//            ImageResourceLoader loader =
//                    new ImageResourceLoader(activity, Settings.connectionTimeout);
//            holder.avatar = new ImageView(activity);
//            holder.avatar.setImageDrawable(loader.Load(user.Photo));
//        }
//        return view;
//    }

}
