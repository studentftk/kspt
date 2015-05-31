package com.example.izual.studentftk.FragmentManagement;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.example.izual.studentftk.FragmentAbout;
import com.example.izual.studentftk.FragmentFindUsers;
import com.example.izual.studentftk.FragmentFriendsList;
import com.example.izual.studentftk.FragmentMaps;
import com.example.izual.studentftk.FragmentMessages;
import com.example.izual.studentftk.FragmentPlaceList;
import com.example.izual.studentftk.FragmentProfile;
import com.example.izual.studentftk.FragmentSettings;
import com.example.izual.studentftk.R;


public class TopLevelFragmentManager extends AppliedFragmentManager {

    public TopLevelFragmentManager() {
        super();
    }

    private NavigationDrawerFragment mNavigationDrawerFragment;

    public static class FragmentIds{
        public static final int ID_PROFILE      = 1;
        public static final int ID_MESSAGES     = 2;
        public static final int ID_FIND_USERS   = 3;
        public static final int ID_FRIENDS_LIST = 4;
        public static final int ID_MAPS         = 5;
        public static final int ID_PLACE_LIST   = 6;
        public static final int ID_SETTINGS     = 7;
        public static final int ID_ABOUT        = 8;
    }

    private final Object[][] fragmentClasses = {
            {FragmentIds.ID_PROFILE, getClassName(FragmentProfile.class.toString())},
            {FragmentIds.ID_MESSAGES, getClassName(FragmentMessages.class.toString())},
            {FragmentIds.ID_FIND_USERS, getClassName(FragmentFindUsers.class.toString())},
            {FragmentIds.ID_FRIENDS_LIST, getClassName(FragmentFriendsList.class.toString())},
            {FragmentIds.ID_MAPS, getClassName(FragmentMaps.class.toString())},
            {FragmentIds.ID_PLACE_LIST, getClassName(FragmentPlaceList.class.toString())},
            {FragmentIds.ID_SETTINGS, getClassName(FragmentSettings.class.toString())},
            {FragmentIds.ID_ABOUT, getClassName(FragmentAbout.class.toString())}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            setContentView(R.layout.activity_profile);
            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getFragmentManager().findFragmentById(R.id.navigation_drawer);
            mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }
    }

    public String getClassName(final String class_to_string){
        return class_to_string.substring("class ".length(), class_to_string.length());
    }

    public void onSectionAttached(int number){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragment = null;
        for (Object[] fragment_class: fragmentClasses) {
            if((Integer)fragment_class[0] != number){
                continue;
            }
            try {
                fragment = (Fragment)Class.forName((String)fragment_class[1]).newInstance();
            } catch (Exception e) {}
            if(fragment != null) {
                transaction.replace(R.id.container, fragment, fragment.toString())
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .commit();
            }
            break;
        }
        if(fragment == null){
            throw new Fragment.InstantiationException("Bad fragment number", null);
        }
    }
}
