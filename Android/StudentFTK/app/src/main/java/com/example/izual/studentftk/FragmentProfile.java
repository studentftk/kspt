package com.example.izual.studentftk;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.izual.studentftk.Network.NetworkUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Антон on 11.12.2014.
 */
public class FragmentProfile extends Fragment {

    String[] Places = { "9-ый корпус вчера в 18:00", "Главное здание вчера в 13:00", "9-ый корпус 01.10 в 10:00", "9-ый корпус 01.09 в 12:00"};
    ImageView m_Photo;
    ListView PlacesList;
    View viewProfile;
    TextView PersonName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getActivity().getIntent().getStringExtra("url")!=null) {
            try {

                getProfileFromServer(getActivity().getIntent().getStringExtra("url"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }
        else{
            StartLogin();
        }

        viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);

        ListView PlacesList = (ListView) viewProfile.findViewById(R.id.list_place);
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                Places.length);
        Map<String, Object> tmp;
        for (int i = 0; i < Places.length; i++) {
            tmp = new HashMap<String, Object>();
            tmp.put("Text", Places[i]);
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
        m_Photo = (ImageView)viewProfile.findViewById(R.id.photoJen);
        //m_Photo.setImageResource(R.drawable.photo);
        return viewProfile;
    }

    private void getProfileFromServer(String url) throws InterruptedException, TimeoutException, ExecutionException {
        HttpTask httpTask = new HttpTask();
        httpTask.execute(url);

    }

    private void StartLogin()
    {
        Intent i = new Intent(getActivity(),VkontakteActivity.class); // Your list's Intent
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
        startActivity(i);

    }

    final class HttpTask extends AsyncTask<String, Integer, String> {

        private HttpURLConnection connection;
        private URL url;


        protected void onPreExecute() {

        }

        protected String doInBackground(String... params) {
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                NetworkUtils.setAllTrusted(connection);
                connection.connect();
                InputStream is = connection.getInputStream();
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(new InputStreamReader(is));
                AllProfileInform.Surname = (String) json.get("surname");
                AllProfileInform.Name = (String) json.get("name");
                AllProfileInform.Photo_URL = (String)json.get("photo");
                AllProfileInform.socialToken = (String)json.get("socialToken");
                getBitmapFromURL(AllProfileInform.Photo_URL);
                return json.toJSONString();

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                connection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String jsonObject) {
            super.onPostExecute(jsonObject);


            PersonName = (TextView)viewProfile.findViewById(R.id.personname);
            PersonName.setText(AllProfileInform.Surname + " " + AllProfileInform.Name);
            m_Photo.setImageBitmap(AllProfileInform.Photo);
        }
    }

    public void  getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Bitmap resizedbitmap = Bitmap.createScaledBitmap(myBitmap, 200, 200, true);

            AllProfileInform.Photo = resizedbitmap;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
