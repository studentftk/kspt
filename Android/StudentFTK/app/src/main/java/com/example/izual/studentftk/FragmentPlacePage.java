package com.example.izual.studentftk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Message;

import com.example.izual.studentftk.Network.RequestBuilder.LikeRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Places.Places;
import com.example.izual.studentftk.Places.PlacesStruct;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.LogRecord;

import android.os.Handler;
import android.os.Message;
/**
 * Created by Антон on 23.12.2014.
 */

public class FragmentPlacePage extends Fragment {

    ArrayList<String> Places = new ArrayList<String>();
    ImageView m_Photo;
    ListView PlacesList;
    View viewPlacePage;
    TextView PlaceName;
    TextView PlaceAdres;
    int id_places;
    final String ATTRIBUTE_vkId = "vkId"; //разобраться с ID
    private final int connectionTimeout = 1000;
    final Activity activity = getActivity();

    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    public void refresh(ArrayList<String> mas){
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                mas.size());
        Map<String, Object> tmp;
        for (int i = 0; i < mas.size(); i++) {
            tmp = new HashMap<String, Object>();
            tmp.put("Text", mas.get(i));
            // tmp.put("Image", R.drawable.places);
            data.add(tmp);
        }

        String[] from = {/* "Image",*/"Text" };
        int[] to = { /*R.id.imgPlaces,*/ R.id.TxtPlaces };
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),//getActionBar().getThemedContext(),
                data,
                R.layout.places_layout,from,to
        );

        PlacesList.setAdapter(adapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        id_places = getArguments().getInt("Id");
        Places.add("Миша Бетаев Вчера в 18:00");
        Places.add("Владимир Ицыксон Вчера в 13:00");
        viewPlacePage = inflater.inflate(R.layout.fragment_place_page, container, false);

        PlaceName = (TextView) viewPlacePage.findViewById(R.id.placename);
        PlacesList = (ListView) viewPlacePage.findViewById(R.id.list_place);
        PlaceAdres = (TextView) viewPlacePage.findViewById(R.id.placeadres);
        m_Photo = (ImageView)viewPlacePage.findViewById(R.id.photoPlace);

        fetchImage(com.example.izual.studentftk.Places.Places.List.get("teachCorp").get(id_places).About,m_Photo);

        PlaceName.setText(com.example.izual.studentftk.Places.Places.List.get("teachCorp").get(id_places).Title);
        PlaceAdres.setText(com.example.izual.studentftk.Places.Places.List.get("teachCorp").get(id_places).Street);
        //Кнопка Check In на вкладке "О проекте"
        // --------------------------Begin-------------------
        Button chkBtn = (Button) viewPlacePage.findViewById(R.id.chkBtn);
        chkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                String time = calendar.getTime().toString();
                Places.add("Вы были здесь " + time.substring(1, 16));
                refresh(Places);
                Toast toast = Toast.makeText(getActivity(),
                        "Вы зачекинились: "+ com.example.izual.studentftk.Places.Places.List.get("teachCorp").get(id_places).Title , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //---------------------------End---------------------

        //---------------------------Кнопка с лайком---------------------
        Button likeBtn = (Button) viewPlacePage.findViewById(R.id.likeBtn);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String idPlace = Integer.toString(id_places);
                final String idPlace1 = String.valueOf(id_places);
                SendLike(idPlace, ATTRIBUTE_vkId);
                /*ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(Places.List.get("teachCorp").size());
                Map<String, Object> m;

                Places.add("Вы были здесь " + time.substring(1, 16));
                refresh(Places);
                Toast toast = Toast.makeText(getActivity(),
                        "Вы зачекинились: "+ com.example.izual.studentftk.Places.Places.List.get("teachCorp").get(id_places).Title , Toast.LENGTH_SHORT);
                toast.show();*/
            }
        });
        //---------------------------End---------------------


        refresh(Places);
        return viewPlacePage;
    }

    private void SendLike(final String id_places,final String ATTRIBUTE_vkId) {
        boolean isError = false;
        String errorReason = "";
        // надо int в string сконвертировать
        URI uri = LikeRequest.BuildRequestGet(id_places, ATTRIBUTE_vkId);

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

    public static void fetchImage(final String iUrl, final ImageView iView) {


        final Handler handler = new Handler() {

            public void handleMessage(Message message) {
                final Bitmap image = (Bitmap) message.obj;
                iView.setImageBitmap(image);
            }
        };

        final Thread thread = new Thread() {
            @Override
            public void run() {
                final Bitmap image = downloadImage(iUrl);
                if ( image != null ) {
                    final Message message = handler.obtainMessage(1, image);
                    handler.sendMessage(message);
                }
            }
        };
        //iView.setImageResource(R.drawable.cat);
        //thread.setPriority(3);
        thread.start();
    }

    public static Bitmap downloadImage(String iUrl) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        BufferedInputStream buf_stream = null;
        try {
            java.net.URL url = new java.net.URL(iUrl);
            connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            bitmap = Bitmap.createScaledBitmap(myBitmap, 200, 200, true);
            connection.disconnect();

        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        } catch (OutOfMemoryError e) {
            return null;
        } finally {
            if ( buf_stream != null )
                try { buf_stream.close(); } catch (IOException ex) {}
            if ( connection != null )
                connection.disconnect();
        }
        return bitmap;
    }


}
