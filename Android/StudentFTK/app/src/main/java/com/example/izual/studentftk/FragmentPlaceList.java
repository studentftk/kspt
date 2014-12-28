package com.example.izual.studentftk;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.izual.studentftk.Network.PlacesRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Network.UserRequest;
import com.example.izual.studentftk.Users.ParseUsers;
import com.example.izual.studentftk.Users.UserStruct;
import com.example.izual.studentftk.Users.Users;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Антон on 29.12.2014.
 */
public class FragmentPlaceList extends Fragment{
    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    private ListView listPlaces;
    private final int connectionTimeout = 1000;
    final Activity activity = getActivity();

    @Override
    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewListPlaces = inflater.inflate(R.layout.frgment_list_please, container, false);

        // массивы данных
        String[] items = getResources().getStringArray(R.array.items_piaces);
        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(items.length);
        Map<String, Object> m;
        for (int i = 0; i < items.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, items[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText};

        // создаем адаптер

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity() ,//getActionBar().getThemedContext(),
                data,
                R.layout.item_place,from,to
        );
        // определяем список и присваиваем ему адаптер
        listPlaces = (ListView) viewListPlaces.findViewById(R.id.list_places);
        listPlaces.setAdapter(adapter);

        return viewListPlaces;

    }
    private void LoadUserInformation(final String ID) {
        boolean isError = false;
        String errorReason = "";
        URI uri = PlacesRequest.BuildPlacesRequest(ID);//BuildPlacesRequest
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
                    UserStruct user = ParseUsers.Parse(executor.GetTask().getData());//gfhcbnm
                    Users.List.put(ID, user);
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
