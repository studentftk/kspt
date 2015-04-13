package com.example.izual.studentftk;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.izual.studentftk.Friends.Friends;
import com.example.izual.studentftk.Friends.FriendsStruct;
import com.example.izual.studentftk.Friends.ParseFriends;
import com.example.izual.studentftk.Network.RequestBuilder.FriendRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Places.PlacesStruct;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Антон on 12.12.2014.
 */
public class FragmentFriendsList extends Fragment {
    // имена атрибутов для Map
    private final int connectionTimeout = 1000;
    final Activity activity = getActivity();

    final String ATTRIBUTE_NAME = "name";
    final String ATTRIBUTE_IMAGE = "image";
    final String ATTRIBUTE_vkId = "vkId"; //разобраться с ID

    private ListView listFriends;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFriendsList = inflater.inflate(R.layout.fragment_friends_list, container, false);
        Friends.Init();
        LoadFriendsInformation(ATTRIBUTE_vkId); //разобраться с ID

        // массивы данных
        listFriends = (ListView) viewFriendsList.findViewById(R.id.list_friends);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(Friends.List.get(ATTRIBUTE_vkId).size()); //ID?
        Map<String, Object> m;
        /*
        for (int i = 0; i < items.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME, items[i]);
            m.put(ATTRIBUTE_IMAGE, img);
            data.add(m);
        }*/

        for(FriendsStruct element: Friends.List.get("ATTRIBUTE_vkId")){//ID
            m = new HashMap<String, Object>();
            m.put(element.Name,element.Surname);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME,
                ATTRIBUTE_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText, R.id.ivImg };

        // создаем адаптер

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity() ,//getActionBar().getThemedContext(),
                data,
                R.layout.item,from,to
        );
        // определяем список и присваиваем ему адаптер
        listFriends = (ListView) viewFriendsList.findViewById(R.id.list_friends);
        listFriends.setAdapter(adapter);
        return viewFriendsList;
    }

    private void LoadFriendsInformation (final String Id){
        boolean isError = false;
        String errorReason = "";
        URI uri = FriendRequest.BuildFriendRequest(Id);

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
                    ArrayList<FriendsStruct> friends = ParseFriends.Parse(executor.GetTask().getData());
                    Friends.List.put(Id, friends);
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
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.ShowError(activity, finalErrorReason);
                }
            });
        }

    }

}