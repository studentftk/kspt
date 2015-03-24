package com.example.izual.studentftk;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Антон on 24.12.2014.
 * Changed by Fedor(fdr91@mail.ru) 3.23.2015
 */
public class FragmentMaps extends Fragment {

    private static GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private static View viewMaps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewMaps != null) {
            ViewGroup parent = (ViewGroup) viewMaps.getParent();
            if (parent != null)
                parent.removeView(viewMaps);
        }
        try {
            viewMaps = inflater.inflate(R.layout.activity_maps, container, false);
            setUpMapIfNeeded();
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        return viewMaps;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            MapFragment mapFragment = (MapFragment) getFragmentManager ().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    private static String getFieldValue(String entry, String field){
        int index = entry.indexOf(field)+field.length()+2;
        int start = index+1;
        int end = entry.indexOf("\"", start);
        while(entry.charAt(end-1) == '\\'){
            start =  end+1;
            end = entry.indexOf("\"", start);
        }
        return entry.substring(index, end);
    }

    private void setupMarkers(){
        for(String entry : getResources().getStringArray(R.array.housings)){
            String[] pa = getFieldValue(entry, "position").split(",");
            String markerName = getFieldValue(entry, "icon_name");
            String title = getFieldValue(entry, "title");
            double position1 = Double.parseDouble(pa[0]);
            double position2 = Double.parseDouble(pa[1]);
            mMap.addMarker(new MarkerOptions().position(new LatLng(position1, position2))
                    .icon(BitmapDescriptorFactory.fromResource(
                            getResources().getIdentifier(markerName, "drawable", getActivity().getApplicationContext().getPackageName())))
                    .title(title));
        }
    }

    private void setUpMap() {

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(60.007340, 30.372820))
                .zoom(15)
                .bearing(45)
                .tilt(20)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cameraUpdate);
        setupMarkers();
    }
}
