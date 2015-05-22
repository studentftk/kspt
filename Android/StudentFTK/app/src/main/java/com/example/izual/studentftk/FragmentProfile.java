package com.example.izual.studentftk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.izual.studentftk.Network.BitmapLoader;
import com.example.izual.studentftk.Common.Utils;
import com.example.izual.studentftk.Network.NetworkUtils;
import com.example.izual.studentftk.Network.RequestBuilder.AddFriendRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Common.ProfileInformation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
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

    String[] Places = { "9-ый корпус вчера в 18:00", "Главное здание вчера в 13:00", "9-ый корпус 26.12 в 10:00", "9-ый корпус 25.12 в 12:00"};
    ImageView m_Photo;
    ListView PlacesList;
    Button btnAddFriend;
    Button btnDeleteFriend;
    View viewProfile;
    TextView PersonName;
    private final int connectionTimeout = 1000;
    final Activity activity = getActivity();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);
        if(ProfileInformation.Photo == null && ProfileInformation.Name == null) {
            if (getActivity().getIntent().getStringExtra("url") != null) {
                try {

                    getProfileFromServer(getActivity().getIntent().getStringExtra("url"));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }

            } else {
                StartLogin();
            }
        }
        else
        {
            PersonName = (TextView)viewProfile.findViewById(R.id.personname);
            PersonName.setText(ProfileInformation.Surname + " " + ProfileInformation.Name);
            m_Photo = (ImageView)viewProfile.findViewById(R.id.photoJen);
            m_Photo.setImageBitmap(ProfileInformation.Photo);
        }

        if((ProfileInformation.Photo != null && ProfileInformation.Name != null)||getActivity().getIntent().getStringExtra("url")!=null)
        {
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

            String[] from = {/* "Image",*/"Text"};
            int[] to = { /*R.id.imgPlaces,*/ R.id.TxtPlaces};
            SimpleAdapter adapter = new SimpleAdapter(
                    getActivity(),//getActionBar().getThemedContext(),
                    data,
                    R.layout.places_layout, from, to
            );
            PlacesList.setAdapter(adapter);
        }

        btnAddFriend = (Button) viewProfile.findViewById(R.id.btnAddFriend);
        btnDeleteFriend = (Button) viewProfile.findViewById(R.id.btnDeleteFriend);

        btnAddFriend.setVisibility(View.INVISIBLE);
        btnDeleteFriend.setVisibility(View.INVISIBLE);

        return viewProfile;
    }

    private void AddFriend(final String ATTRIBUTE_Social_Token,final String ATTRIBUTE_vkId,final String ATTRIBUTE_vkId_Friend) {
        boolean isError = false;
        String errorReason = "";
        // надо int в string сконвертировать
        URI uri = AddFriendRequest.BuildRequestGet(ATTRIBUTE_Social_Token, ATTRIBUTE_vkId, ATTRIBUTE_vkId, "add");
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

    private void DeleteFriend(final String ATTRIBUTE_Social_Token,final String ATTRIBUTE_vkId,final String ATTRIBUTE_vkId_Friend) {
        boolean isError = false;
        String errorReason = "";
        // надо int в string сконвертировать
        URI uri = AddFriendRequest.BuildRequestGet(ATTRIBUTE_Social_Token, ATTRIBUTE_vkId, ATTRIBUTE_vkId, "del");
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
            String error = "";
            boolean isError = false;
            try {
                NetworkUtils.setAllTrusted(connection);
                connection.connect();
                InputStream is = connection.getInputStream();
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(new InputStreamReader(is));
                ProfileInformation.Surname = (String) json.get("surname");
                ProfileInformation.Name = (String) json.get("name");
                ProfileInformation.Photo_URL = (String)json.get("photo");
                ProfileInformation.socialToken = (String)json.get("socialToken");
                ProfileInformation.Photo = BitmapLoader.GetBitmapFromURL(getActivity(),
                        ProfileInformation.Photo_URL, 200, 200);
                return json.toJSONString();

            } catch (ParseException e) {
                isError = true;
                error = e.toString();
                e.printStackTrace();
            } catch (IOException e) {
                isError = true;
                error = e.toString();
                e.printStackTrace();
            }
            finally {
                connection.disconnect();
                final String final_error = error;
                if (isError){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utils.ShowError(getActivity(), final_error);
                        }
                    });
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String jsonObject) {
            super.onPostExecute(jsonObject);

            if(ProfileInformation.Name !=null && ProfileInformation.Photo != null) {
                PersonName = (TextView) viewProfile.findViewById(R.id.personname);
                PersonName.setText(ProfileInformation.Surname + " " + ProfileInformation.Name);
                m_Photo = (ImageView)viewProfile.findViewById(R.id.photoJen);
                m_Photo.setImageBitmap(ProfileInformation.Photo);
            }
        }
    }
}
