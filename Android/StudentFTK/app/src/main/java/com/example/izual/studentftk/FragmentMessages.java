package com.example.izual.studentftk;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.izual.studentftk.Messages.MessageStruct;
import com.example.izual.studentftk.Messages.MsgControl;
import com.example.izual.studentftk.Messages.ParseMessages;
import com.example.izual.studentftk.Network.RequestBuilder.ManyUsersRequest;
import com.example.izual.studentftk.Network.RequestBuilder.MessageRequest;
import com.example.izual.studentftk.Network.RequestBuilder.Utils.URLEncoderRu;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Network.RequestBuilder.UserRequest;
import com.example.izual.studentftk.Users.ParseManyUsers;
import com.example.izual.studentftk.Users.ParseUsers;
import com.example.izual.studentftk.Users.UserStruct;
import com.example.izual.studentftk.Users.Users;

/**
 * Created by Антон on 12.12.2014.
 */

public class FragmentMessages extends Fragment {
    private ListView listMessages;
    private Button btnSendMessage;
    private EditText txtMessageEdit;
    private String current_name = "Admin";
    private final int connectionTimeout = 1000;
    private final int REQUEST_CODE_FRIENDS = 1;
    private long updatePeriod = 4000;
    private Timer updateTimer;
    private SimpleAdapter sAdapter = null;
    private long updaterReinitDelay = 200;
    private final long TIMER_ONCE = -1;
    private boolean timerFirstTime = true;

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

        Users.Init();
        InitMessages();
        ReinitUpdater(0, TIMER_ONCE);

        Utils.ShowError(getActivity(), "Загрузка сообщений...");

        return viewMessages;
    }

    /* В случае личного чата - при выборе друзей получаем результат */
  /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != REQUEST_CODE_FRIENDS || data == null){
            return;
        }
        String friend = data.getStringExtra("ChoosenFriend");
        InitNetwork();
    }*/

    private void InitMessages(){
        /* Инициализируем структуру, содержащую сообщения */
        msgList = new ArrayList<Map<String, Object>>();

        /* Массивы данных */
        ArrayList<String> msg_text = new ArrayList<String>();
        final ArrayList<String> msg_time = new ArrayList<String>();
        ArrayList<String> msg_name = new ArrayList<String>();

        if(AllProfileInform.Name != null){
            current_name = AllProfileInform.Name;
        }

        /* Создаём адаптер и привязываем его к списку */
        sAdapter = MsgControl.InitFramework(msgList, getActivity(),
                msg_text, msg_time, msg_name);
        listMessages.setAdapter(sAdapter);

        /* Обработчик нажатия на кнопку отправления */
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textOfMessage = txtMessageEdit.getText().toString();
                txtMessageEdit.setText("");
                if(txtMessageEdit.hasFocus()){
                    Utils.HideSoftInput(getActivity());
                }
                String time = MsgControl.FormatDate(MsgControl.DATE_DAY_AND_TIME);
                //AddMessage(msgList, textOfMessage, time, current_name);
                SendMessage(AllProfileInform.socialToken, "1", textOfMessage);
                ReinitUpdater(updaterReinitDelay, TIMER_ONCE);
            }
        });

        /* Обработчик нажатия на сообщения */
        listMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listMessages.smoothScrollByOffset(listMessages.getMaxScrollAmount());
            }
        });
    }

    /* Добавляет сообщение в список сообщений */
    private void AddMessage(ArrayList<Map<String, Object>> msgList,
                            String msg_text, String msg_time, String msg_name){
        if(msgList != null){
            sAdapter.notifyDataSetInvalidated();
            MsgControl.AddMessageToList(msgList, msg_text, msg_time, msg_name);
            sAdapter.notifyDataSetChanged();
        }
        listMessages.smoothScrollByOffset(listMessages.getMaxScrollAmount());
    }

    /* Посылает сообщение серверу */
    private void SendMessage(final String socialToken,
                                   final String destination, final String message){
        URI uri = MessageRequest.BuildRequestSend(socialToken, destination, message);
        RequestExecutor executor = new RequestExecutor(getActivity(), uri, connectionTimeout);
    }

    /* Инициализация обновления сообщений */
    private void ReinitUpdater(long delay, long period){
        if(updateTimer != null){
            updateTimer.cancel();
        }
        updateTimer = new Timer();
        if(period == TIMER_ONCE) {
            timerFirstTime = true;
            updateTimer.schedule(new Updater(), delay);
        }
        else{
            timerFirstTime = false;
            updateTimer.schedule(new Updater(), delay, period);
        }
    }

    /* Класс, производящий в отдельном потоке обновление сообщений */
    private class Updater extends TimerTask {
        final String CommonChatToken = "d757146a03cc4a2e69c573acbd00c1e259de9782089b67";
        final Activity activity = getActivity();
        @Override
        public void run() {
            String errorReason = "";
            boolean isError = false;
            ArrayList<MessageStruct> parsed = null;
            for(;;) {
                URI uri = MessageRequest.BuildRequestGet(CommonChatToken,
                        "2012-12-01%2000:00:00", MessageRequest.Types.Receive);
                RequestExecutor executor = new RequestExecutor(
                        activity, uri, connectionTimeout);
                try{
                    executor.GetThread().join();
                }
                catch(InterruptedException e){
                    isError = true;
                    errorReason = e.toString();
                    break;
                }

                if (executor.GetTask().isDataReady()){
                    String data = URLEncoderRu.RestoreSpacesDef(executor.GetTask().getData());
                    try {
                        parsed = ParseMessages.Parse(data);
                        ArrayList<String> IDs = new ArrayList<String>();
                        for(MessageStruct msg: parsed){
                            IDs.add(msg.Source);
                        }
                        GetUsersInformation(IDs);
                    } catch (Exception e) {
                        isError = true;
                        errorReason = e.toString();
                        break;
                    }
                }
                else{
                    isError = true;
                    errorReason = "Невозможно загрузить сообщения";
                }
                break;
            }
            UpdateUI(isError, errorReason, parsed);
        }

        /* Updater.UpdateUI() Производит обновление пользовательского интерфейса*/
        private void UpdateUI(final boolean isError, final String errorReason,
                              final ArrayList<MessageStruct> parsed){
            FragmentMessages.this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(isError) {
                        Utils.ShowError(activity, errorReason);
                    }
                    try {
                        if (parsed != null && parsed.size() > msgList.size()) {
                            sAdapter.notifyDataSetInvalidated();
                            msgList.clear();
                            for (MessageStruct msg : parsed) {
                                String userName = msg.Source;
                                UserStruct user = GetUserInformation(msg.Source);
                                if (user != null) {
                                    userName = user.Name;
                                }
                                AddMessage(msgList, msg.Message,
                                        MsgControl.RoundToSeconds(msg.SendTime), userName);
                            }
                            sAdapter.notifyDataSetChanged();
                        }
                        else{
                            return;
                        }
                    }
                    finally {
                        if (timerFirstTime) {
                            ReinitUpdater(updatePeriod, updatePeriod);
                            listMessages.setSelection(listMessages.getCount() - 1);
                        }
                        else{
                            listMessages.smoothScrollByOffset(listMessages.getMaxScrollAmount());
                        }
                    }

                }
            });
        }

        /*Получает информацию о пользователях из чата*/
        private UserStruct GetUserInformation(final String ID){
            if(!Users.List.containsKey(ID)){
                LoadUserInformation(ID);
            }
            return Users.List.get(ID);
        }

        private Collection<UserStruct> GetUsersInformation(final ArrayList<String> IDs){
            ArrayList<String> unknownIDs = new ArrayList();
            for (String id : IDs){
                if(!Users.List.containsKey(id)){
                    unknownIDs.add(id);
                }
            }
            LoadUsersInformation(unknownIDs);
            return Users.List.values();
        }

        private void LoadUsersInformation(final ArrayList<String> IDs){
            boolean isError = false;
            String errorReason = "";
            URI uri = ManyUsersRequest.BuildManyUsersRequest(IDs);
            for (;;) {
                RequestExecutor executor = new RequestExecutor(getActivity(),
                        uri, connectionTimeout);
                try {
                    executor.GetThread().join();
                }
                catch (InterruptedException e) {
                    isError = true;
                    errorReason = e.toString();
                    break;
                }
                if (executor.GetTask().isError()) {
                    isError = true;
                    errorReason = executor.GetTask().getErrorReason();
                    break;
                }
                if (executor.GetTask().isDataReady()) {
                    try {
                        ArrayList<UserStruct> users =
                                    ParseManyUsers.Parse(executor.GetTask().getData());
                        for (UserStruct user: users) {
                            Users.List.put(user.ID, user);
                        }
                    } catch (Exception e) {
                        isError = true;
                        errorReason = e.toString();
                    }
                    break;
                } else {
                    isError = true;
                    errorReason = "Данные ещё не готовы";
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

        /*Загружает информацию о пользователях из чата*/
        private void LoadUserInformation(final String ID) {
            boolean isError = false;
            String errorReason = "";
            URI uri = UserRequest.BuildUserRequest(ID);
            for (;;) {
                RequestExecutor executor = new RequestExecutor(getActivity(),
                        uri, connectionTimeout);
                try {
                    executor.GetThread().join();
                }
                catch (InterruptedException e) {
                    isError = true;
                    errorReason = e.toString();
                    break;
                }
                if (executor.GetTask().isError()) {
                    isError = true;
                    errorReason = executor.GetTask().getErrorReason();
                    break;
                }
                if (executor.GetTask().isDataReady()) {
                    try {
                        UserStruct user = ParseUsers.Parse(executor.GetTask().getData());
                        Users.List.put(ID, user);
                    } catch (Exception e) {
                        isError = true;
                        errorReason = e.toString();
                    }
                    break;
                } else {
                    isError = true;
                    errorReason = "Данные ещё не готовы";
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
    };

    @Override
    public void onPause(){
        super.onPause();
        updateTimer.cancel();
        updateTimer = null;
    }

    @Override
    public void onResume(){
        super.onResume();
        ReinitUpdater(0, TIMER_ONCE);
    }
}

