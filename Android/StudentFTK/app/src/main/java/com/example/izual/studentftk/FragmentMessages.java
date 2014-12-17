package com.example.izual.studentftk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.izual.studentftk.MsgControl;

/**
 * Created by Антон on 12.12.2014.
 */

public class FragmentMessages extends Fragment {
    private ListView listMessages;
    private Button btnSendMessage;
    private EditText txtMessageEdit;

    // структура, содержащая сообщения в удобном виде
    private static ArrayList<Map<String, Object>> msgList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View viewMessages = inflater.inflate(R.layout.fragment_messages, container, false);
        listMessages = (ListView) viewMessages.findViewById(R.id.listMessages);
        btnSendMessage = (Button)viewMessages.findViewById(R.id.btnSendMessage);
        txtMessageEdit = (EditText)viewMessages.findViewById(R.id.txtMessageEdit);

        // инициализируем структуру, содержащую сообщения
        msgList = new ArrayList<Map<String, Object>>();

        // массивы данных
        ArrayList<String> msg_text = new ArrayList<String>();
        ArrayList<String> msg_time = new ArrayList<String>();

        // создаём адаптер и привязываем его к списку
        SimpleAdapter sAdapter = MsgControl.InitFramework(msgList, getActivity(),
                msg_text, msg_time);
        listMessages.setAdapter(sAdapter);

        // обработчик нажатия на кнопку отправления
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textOfMessage = txtMessageEdit.getText().toString();
                String time = MsgControl.FormatDate(MsgControl.DATE_DAY_AND_TIME);
                if(msgList != null){
                    MsgControl.AddMessageToList(msgList, textOfMessage, time);
                }
                txtMessageEdit.setText("");
                listMessages.smoothScrollByOffset(listMessages.getMaxScrollAmount());
                if(txtMessageEdit.hasFocus()){
                    HideSoftInput(getActivity());
                }
            }
        });

        return viewMessages;
    }

    private void HideSoftInput(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager)
                        getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}
