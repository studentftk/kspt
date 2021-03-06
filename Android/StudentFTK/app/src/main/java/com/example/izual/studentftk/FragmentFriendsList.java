package com.example.izual.studentftk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.izual.studentftk.Common.Settings;
import com.example.izual.studentftk.Common.Utils;
import com.example.izual.studentftk.Friends.Friends;
import com.example.izual.studentftk.Common.AvatarSimpleAdapter;
import com.example.izual.studentftk.Friends.FriendsStruct;
import com.example.izual.studentftk.Friends.ParseFriends;
import com.example.izual.studentftk.Network.ImageResourceLoader;
import com.example.izual.studentftk.Network.RequestBuilder.FriendRequest;
import com.example.izual.studentftk.Network.RequestBuilder.ManyUsersRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Common.ProfileInformation;
import com.example.izual.studentftk.Users.UserStruct;
import com.example.izual.studentftk.Users.Users;
import com.example.izual.studentftk.Users.UsersInformationLoader;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Антон on 12.12.2014.
 * changed by oglandx on 19.05.2015
 */
public class FragmentFriendsList extends Fragment {

    private final int connectionTimeout = Settings.connectionTimeout;
    private Activity activity;

    private final String ATTRIBUTE_NAME = "name";
    private final String ATTRIBUTE_IMAGE = "image";

    private final int AvatarWidth = 64;
    private final int AvatarHeight = 64;

    private ListView listFriends;
    private TextView friendsCountMessage;
    private AvatarSimpleAdapter adapter = null;
    private ArrayList<Map<String, Object>> data = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Friends.Init();
        Users.Init();
        View viewFriendsList = inflater.inflate(R.layout.fragment_friends_list, container, false);
        listFriends = (ListView) viewFriendsList.findViewById(R.id.list_friends);
        friendsCountMessage = (TextView) viewFriendsList.findViewById(R.id.txtFriendsCount);
        data = new ArrayList<Map<String, Object>>();
        if(ProfileInformation.socialToken != null){
            listFriends.setAdapter(getAdapter());
            LoadFriendsInformation(ProfileInformation.socialToken);
        }
        return viewFriendsList;
    }

    public SimpleAdapter getAdapter(){
        if (adapter == null) {
            String[] from = {ATTRIBUTE_NAME, ATTRIBUTE_IMAGE};
            int[] to = {R.id.txtFriendsListName, R.id.imgFriendsListAvatar};
            adapter = new AvatarSimpleAdapter(getActivity(), data, R.layout.item_friends_list, from, to);
        }
        return adapter;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(ProfileInformation.socialToken != null) {
            listFriends.setAdapter(getAdapter());
        }
        Users.Current = null;
        listFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String socialId = (String)data.get(position).get("socialId");
                Users.Current = Users.GetBySocialId("vk", socialId);
                Intent intent = new Intent(getActivity(), PageUserActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    private void LoadFriendsInformation (final String socialToken){
        boolean isError = false;
        String errorReason = "";
        URI uri = FriendRequest.BuildFriendRequest(socialToken);

        for (;;) {
            RequestExecutor executor = new RequestExecutor(getActivity(),
                    uri, connectionTimeout);
            try {
                executor.GetThread().join();
            } catch (InterruptedException e) {
                isError = true;
                errorReason = e.toString();
                break;
            }
            if (executor.GetTask().isError()) {
                isError = true;
                errorReason = executor.GetTask().getErrorReason();
                break;
            }
            if (executor.GetTask().isDataReady()) {
                try {
                    ArrayList<FriendsStruct> friends =
                            ParseFriends.Parse(executor.GetTask().getData());
                    friendsCountMessage.setText("У вас " + friends.size() + " " +
                            Utils.AdaptToNumeric(friends.size(), "друг", "друга", "друзей"));
                    Friends.List.put(socialToken, friends);
                    UsersInformationLoader loader =
                            new UsersInformationLoader(getActivity(), connectionTimeout);
                    Collection<UserStruct> friends_users = loader
                            .GetUsersInformation(Friends.getIds(ProfileInformation.socialToken),
                                    ManyUsersRequest.DataType.TYPE_SOCIAL_ID);
                    HashMap<String, UserStruct> socialIds = new HashMap<String, UserStruct>();
                    for (final UserStruct friend: friends_users){
                        if(!socialIds.keySet().contains(friend.SocialID)) {
                            socialIds.put(friend.SocialID, friend);
                        }
                    }
                    adapter.notifyDataSetInvalidated();
                    data.clear();
                    for (final String socialId: socialIds.keySet()){
                        final UserStruct friend = socialIds.get(socialId);
                        Map<String, Object> container = new HashMap<String, Object>();
                        container.put(ATTRIBUTE_NAME, friend.Name + " " + friend.Surname);
                        container.put(ATTRIBUTE_IMAGE, friend.Photo);
                        container.put("socialId", socialId);
                        data.add(container);
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    isError = true;
                    errorReason = e.toString();
                }
                break;
            } else {
                isError = true;
                errorReason = "Data is not ready yet";
                break;
            }
        }
        if(isError){
            final String finalErrorReason = errorReason;
            this.activity = getActivity();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.ShowError(activity, finalErrorReason, true);
                }
            });
        }

    }

}