package com.example.izual.studentftk;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class Profile extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_profile);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }



    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentProfile fragmentProfile;
        FragmentMessages fragmentMessages;
        FragmentPlaces fragmentPlaces;
        FragmentFriendsList fragmentFriendsList;
        FragmentAbout fragmentAbout;
        FragmentSettings fragmentSettings;

        Bundle args;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (number) {
            case 1:
                fragmentProfile = new FragmentProfile();
                args = new Bundle();
                args.putInt("2", 2);
                fragmentProfile.setArguments(args);
                ft.replace(R.id.container, fragmentProfile, "fraProfile");
                //ft.addToBackStack(null);
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.commit();
                break;
            case 2:
                fragmentMessages = new FragmentMessages();
                args = new Bundle();
                args.putInt("2", 2);
                fragmentMessages.setArguments(args);
                ft.replace(R.id.container, fragmentMessages, "fragmentMessages");
                //ft.addToBackStack(null);
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.commit();
                break;
            case 3:
                Intent intentMaps = new Intent(this, MapsActivity.class);
                startActivity(intentMaps);

                //mTitle = getString(R.string.title_section3);
                break;
            case 4:
                fragmentPlaces = new FragmentPlaces();
                args = new Bundle();
                args.putInt("2", 2);
                fragmentPlaces.setArguments(args);
                ft.replace(R.id.container, fragmentPlaces, "fragmentPlaces");
                //ft.addToBackStack(null);
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.commit();
                break;
            case 5:
                fragmentSettings = new FragmentSettings();
                args = new Bundle();
                args.putInt("2", 2);
                fragmentSettings.setArguments(args);
                ft.replace(R.id.container, fragmentSettings, "fragmentSettings");
                //ft.addToBackStack(null);
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.commit();
                break;
            case 6:
                fragmentFriendsList = new FragmentFriendsList();
                args = new Bundle();
                args.putInt("2", 2);
                fragmentFriendsList.setArguments(args);
                ft.replace(R.id.container, fragmentFriendsList, "fragmentFriendsList");
                //ft.addToBackStack(null);
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.commit();
                break;
            case 7:
                fragmentAbout = new FragmentAbout();
                args = new Bundle();
                args.putInt("2", 2);
                fragmentAbout.setArguments(args);
                ft.replace(R.id.container, fragmentAbout, "fragmentAbout");
                //ft.addToBackStack(null);
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.commit();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.profile, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.navigation, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Profile) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
