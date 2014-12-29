package com.example.izual.studentftk;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Антон on 24.12.2014.
 */
public class FragmentMaps extends Fragment {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    MapFragment mapFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewMaps = inflater.inflate(R.layout.activity_maps, container, false);
        setUpMapIfNeeded();
        return viewMaps;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public int filter(String id){
        Integer id2 = Integer.valueOf(id.substring(1, id.length()));
        return id2;
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mapFragment = (MapFragment) getFragmentManager ().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
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
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.007387, 30.372935)).icon(BitmapDescriptorFactory.fromResource(R.drawable.gz)).title("Главный учебный корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.006716, 30.376414)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Химический корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.008072, 30.377154)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Механический корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.005817, 30.381896)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Гидрокорпус-1 СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.006598, 30.383565)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Гидрокорпус-2 СПбПУ ИСИ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.005479, 30.374064)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Гидробашня СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.007566, 30.379906)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Лабораторный корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.002972, 30.374054)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("НОЦ РАН СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.008855, 30.372992)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("1 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.008566, 30.374665)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("2 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.007273, 30.381703)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("3 корпус СпбПУ ИЭИ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.007281, 30.376811)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("4 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(59.999709, 30.374815)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("5 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.000145, 30.367498)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("6 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(59.999437, 30.372058)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("8 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.000823, 30.366485)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ftk)).title("9 корпус СПбПУ ИИТУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.000579, 30.369558)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("10 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.009263, 30.378377)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("11 корпус СПбПУ ИИТУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.007316, 30.390281)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("15 корпус СПбПУ ИМОП"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.007676, 30.390796)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Общежитие №15 СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.00767, 30.390029)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("16 корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.005002, 30.369966)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("1 профессорский корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.004997, 30.378066)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("2 профессорский корпус СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.00435, 30.379987)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Дом ученых в Лесном СПбПУ"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(60.002795, 30.368968)).icon(BitmapDescriptorFactory.fromResource(R.drawable.stud)).title("Спортивный комплекс СПбПУ"));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String check = marker.getId();
                if (filter(marker.getId()) == 15) {
                    //TODO: Marker Click
                }
                return false;
            }
        });
    }
}
