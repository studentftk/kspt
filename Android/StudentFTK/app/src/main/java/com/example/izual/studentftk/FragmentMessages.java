package com.example.izual.studentftk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Антон on 12.12.2014.
 */
public class FragmentMessages extends Fragment {
    private ListView listMessages;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // return super.onCreateView(inflater, container, savedInstanceState);

        View viewMessages = inflater.inflate(R.layout.fragment_messages, container, false);

        listMessages = (ListView)viewMessages.findViewById(R.id.listMessages);

        return viewMessages;
    }

    /**
     * Send message
     */
    public void btnSendMessageClick(View view){

    }

}
