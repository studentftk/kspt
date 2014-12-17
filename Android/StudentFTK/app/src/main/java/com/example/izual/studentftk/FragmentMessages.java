package com.example.izual.studentftk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // структура, содержащая сообщения в удобном виде
    private static ArrayList<Map<String, Object>> msgList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // return super.onCreateView(inflater, container, savedInstanceState);

        final View viewMessages = inflater.inflate(R.layout.fragment_messages, container, false);
        listMessages = (ListView) viewMessages.findViewById(R.id.listMessages);
        btnSendMessage = (Button)viewMessages.findViewById(R.id.btnSendMessage);

        // инициализируем структуру, содержащую сообщения
        msgList = new ArrayList<Map<String, Object>>();

        // массивы данных
        ArrayList<String> msg_text = new ArrayList<String>();
        ArrayList<String> msg_time = new ArrayList<String>();

        //BEGIN_STUB
        String[] builtInMessagesStub =
                {"Политехнический университет - многофункциональное государственное высшее учебное заведение.",
                 "В 2010 году он получил статус национального исследовательского университета, что явилось признанием его роли и возможностей как в области подготовки кадров, так и в мультидисциплинарных научных исследованиях и разработках.",
                 "В рейтинге технических университетов России Политехнический неизменно занимает ведущие позиции.",
                 "Университет готовит бакалавров и магистров по 49 направлениям науки и техники,",
                 "специалистов (инженеров, экономистов, менеджеров) по 9 специальностям" };

        String[] timesStub = { "20:00", "20:11",  "20:12",  "20:13",  "20:14" };

        for(String element: builtInMessagesStub){
            msg_text.add(element);
        }

        for(String element: timesStub){
            msg_time.add(element);
        }
        //END_STUB

        // создаём адаптер и привязываем его к списку
        SimpleAdapter sAdapter = MsgControl.InitFramework(msgList, getActivity(),
                msg_text, msg_time);
        listMessages.setAdapter(sAdapter);

        // обработчик нажатия на кнопку отправления
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtMessageEdit = (EditText)viewMessages.findViewById(R.id.txtMessageEdit);
                String textOfMessage = txtMessageEdit.getText().toString();
                String time = MsgControl.FormatDate(MsgControl.DATE_DAY_AND_TIME);
                if(msgList != null){
                    MsgControl.AddMessageToList(msgList, textOfMessage, time);
                }
                listMessages.smoothScrollByOffset(listMessages.getMaxScrollAmount());
            }
        });

        return viewMessages;
    }
}
