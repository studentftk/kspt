package com.example.izual.studentftk;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.izual.studentftk.Network.PlacesRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Places.ParsePlaces;
import com.example.izual.studentftk.Places.Places;
import com.example.izual.studentftk.Places.PlacesStruct;

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
    private FragmentPlacesCallbacks mCallbacks;

    public interface OnSelectedListIdListener {
        void onListIdSelected(int ListIndex);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewListPlaces = inflater.inflate(R.layout.frgment_list_please, container, false);
        Places.Init();
        LoadPlacesInformation("teachCorp");

        // определяем список и присваиваем ему адаптер
        listPlaces = (ListView) viewListPlaces.findViewById(R.id.list_places);
        listPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        // массивы данных
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(Places.List.get("teachCorp").size());
        Map<String, Object> m;
        for(PlacesStruct element: Places.List.get("teachCorp")){
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT,element.Title);
            data.add(m);
        }
        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText};
        // создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity() ,
                data,
                R.layout.item_place,from,to
        );



        listPlaces.setAdapter(adapter);

        return viewListPlaces;
    }
    private void LoadPlacesInformation(final String TYPE) {
        boolean isError = false;
        String errorReason = "";
        URI uri = PlacesRequest.BuildPlacesRequest(TYPE);
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
                    ArrayList<PlacesStruct> places = ParsePlaces.Parse(executor.GetTask().getData());
                    Places.List.put(TYPE, places);
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

    private void selectItem(int position) {
        if (mCallbacks != null) {
            mCallbacks.onFragmentPlacesItemSelected(position);
        }
    }

    public static interface FragmentPlacesCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onFragmentPlacesItemSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (FragmentPlacesCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

}
