package com.example.izual.studentftk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Антон on 12.12.2014.
 */
public class FragmentMessages extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // return super.onCreateView(inflater, container, savedInstanceState);

        View vieMessages = inflater.inflate(R.layout.fragment_messages, container, false);

        return vieMessages;
    }
}
