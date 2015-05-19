package com.example.izual.studentftk.FragmentManagement;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.izual.studentftk.FragmentPlacePage;
import com.example.izual.studentftk.R;

/**
 * Created by oglandx on 18.05.2015.
 */
public class AppliedFragmentManager
        extends Activity implements NavigationDrawerCallbacks, FragmentPlacesCallbacks {

    public AppliedFragmentManager() {}

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    @Override
    public void onFragmentPlacesItemSelected(int position) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        FragmentPlacePage fragmentPlacePage = new FragmentPlacePage();
        Bundle args = new Bundle();
        args.putInt("Id", position);
        fragmentPlacePage.setArguments(args);
        transaction.replace(R.id.container, fragmentPlacePage, "fragmentPlacePage")
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commit();
    }
}
