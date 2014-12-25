package com.example.izual.studentftk;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.izual.studentftk.Messages.MessageStruct;
import com.example.izual.studentftk.Messages.MsgControl;
import com.example.izual.studentftk.Messages.ParseMessages;
import com.example.izual.studentftk.Network.MessageRequest;
import com.example.izual.studentftk.Network.RequestTask;

/**
 * Created by Антон on 12.12.2014.
 */

public class FragmentMessages extends Fragment {
    private ListView listMessages;
    private Button btnSendMessage;
    private EditText txtMessageEdit;
    private String current_name = "Admin";
    final int connectionTimeout = 1000;
    final int REQUEST_CODE_FRIENDS = 1;

    // структура, содержащая сообщения в удобном виде
    private static ArrayList<Map<String, Object>> msgList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewMessages = inflater.inflate(R.layout.fragment_messages, container, false);
        listMessages = (ListView) viewMessages.findViewById(R.id.listMessages);
        btnSendMessage = (Button)viewMessages.findViewById(R.id.btnSendMessage);
        txtMessageEdit = (EditText)viewMessages.findViewById(R.id.txtMessageEdit);

        /* Отображение списка друзей */
        //Intent intent = new Intent(getActivity(), ChooseFriendToChat.class);
        //startActivityForResult(intent, REQUEST_CODE_FRIENDS);

        InitMessages();

        InitNetwork();

        return viewMessages;
    }

 /*   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != REQUEST_CODE_FRIENDS || data == null){
            return;
        }
        String friend = data.getStringExtra("ChoosenFriend");
        InitNetwork();
    }*/

    private void InitMessages(){
        // инициализируем структуру, содержащую сообщения
        msgList = new ArrayList<Map<String, Object>>();

        // массивы данных
        ArrayList<String> msg_text = new ArrayList<String>();
        final ArrayList<String> msg_time = new ArrayList<String>();
        ArrayList<String> msg_name = new ArrayList<String>();

        // создаём адаптер и привязываем его к списку
        SimpleAdapter sAdapter = MsgControl.InitFramework(msgList, getActivity(),
                msg_text, msg_time, msg_name);
        listMessages.setAdapter(sAdapter);

        // обработчик нажатия на кнопку отправления
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textOfMessage = txtMessageEdit.getText().toString();
                txtMessageEdit.setText("");
                if(txtMessageEdit.hasFocus()){
                    HideSoftInput(getActivity());
                }
                String newName = "";
                if((newName = ChangeNameAttempt(textOfMessage)) != ""){
                    current_name = newName;
                    return;
                }
                String time = MsgControl.FormatDate(MsgControl.DATE_DAY_AND_TIME);
                AddMessage(msgList, textOfMessage, time, current_name);
                SendMessage(AllProfileInform.socialToken, "1", textOfMessage);
            }
        });
    }

    private void AddMessage(ArrayList<Map<String, Object>> msgList,
                            String msg_text, String msg_time, String msg_name){
        if(msgList != null){
            MsgControl.AddMessageToList(msgList, msg_text, msg_time, msg_name);
        }
        listMessages.smoothScrollByOffset(listMessages.getMaxScrollAmount());
    }

    private void SendMessage(final String socialToken,
                                   final String destination, final String message){
        URI uri = MessageRequest.BuildRequestSend(socialToken, destination, message);
        RequestTask requestTask = new RequestTask(uri, connectionTimeout);
        final Thread execRequest = new Thread(requestTask);
        execRequest.setPriority(Thread.MAX_PRIORITY);
        execRequest.start();
        try{
            execRequest.join();
        }
        catch(Exception e){
            Utils.ShowError(getActivity(), requestTask.getErrorReason());
        }

        if(requestTask.isError()){
            Utils.ShowError(getActivity(), requestTask.getErrorReason());
            return;
        }
    }

    private void InitNetwork(){
        URI uri = MessageRequest.BuildRequestGet("d757146a03cc4a2e69c573acbd00c1e259de9782089b67",
                "2012-12-09%2007:27:39", MessageRequest.Types.Receive);
        //URI uri = MessageRequest.BuildRequestGet("asd",
        //                "2012-12-09%2007:27:39", MessageRequest.Types.Send);
        RequestTask requestTask = new RequestTask(uri, connectionTimeout);
        final Thread execRequest = new Thread(requestTask);
        execRequest.setPriority(Thread.MAX_PRIORITY);
        execRequest.start();

        try{
            execRequest.join();
        }
        catch(Exception e){
            Utils.ShowError(getActivity(), requestTask.getErrorReason());
        }

        if(requestTask.isError()){
            Utils.ShowError(getActivity(), requestTask.getErrorReason());
            return;
        }

        if(!requestTask.isDataReady()) {
            Utils.ShowError(getActivity(), "Не могу загрузить сообщения. Это странно.");
        }
        else {
            String data = requestTask.getData();
            ArrayList<MessageStruct> parsed = null;
            try {
                parsed = ParseMessages.Parse(data);
            }
            catch(Exception e){
                Utils.ShowError(getActivity(), e.toString());
                String time = MsgControl.FormatDate(MsgControl.DATE_DAY_AND_TIME);
                AddMessage(msgList, data, time, "System");
            }
            String time = MsgControl.FormatDate(MsgControl.DATE_DAY_AND_TIME);
            if(parsed != null) {
                for (MessageStruct msg : parsed) {
                    AddMessage(msgList, msg.Message, msg.SendTime, msg.Source);
                }
            }
        }
    }

    private final String ChangeNameAttempt(final String textOfMessage){
        final String name_change_seq = "$newnick";
        final int first_space = textOfMessage.indexOf(' ');
        if(first_space == -1){
            return "";
        }
        final String command = textOfMessage.substring(0, first_space);
        String newName = "";
        if(command.compareTo(name_change_seq) == 0){
            newName = textOfMessage.substring(name_change_seq.length() + 1);
            final int max_length_name = 16;
            if(newName.length() > max_length_name) {
                final int second_space = newName.indexOf(' ');
                if (second_space != -1 && second_space < max_length_name) {
                    newName = newName.substring(0, second_space);
                } else {
                    newName = newName.substring(0, max_length_name);
                }
            }
        }
        return newName;
    }

    private void HideSoftInput(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager)
                        getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}
