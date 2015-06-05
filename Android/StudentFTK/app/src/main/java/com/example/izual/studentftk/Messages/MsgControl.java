package com.example.izual.studentftk.Messages;

import android.app.Activity;
import android.widget.SimpleAdapter;

import com.example.izual.studentftk.Common.AvatarSimpleAdapter;
import com.example.izual.studentftk.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oglandx on 17.12.2014.
 */
public class MsgControl {

    // имена атрибутов для Map
    public final static String ATTRIBUTE_NAME_ID = "source";
    public final static String ATTRIBUTE_NAME_TEXT = "text";
    public final static String ATTRIBUTE_NAME_TIME = "time";
    public final static String ATTRIBUTE_NAME_IMAGE = "image";
    public final static String ATTRIBUTE_NAME_NAME = "name";

    public final static int DATE_ALL = 0;
    public final static int DATE_TIME = 1;
    public final static int DATE_DAY = 2;
    public final static int DATE_DAY_AND_TIME = 3;

    // упаковывает сообщение в нужную структуру
    private static Map<String, Object> PackMessage(final String msg_src,
                                                   final String msg_text,
                                                   final String msg_time,
                                                   final String msg_name,
                                                   final String msg_img){
        Map<String, Object> msg_pack = new HashMap<String, Object>();
        msg_pack.put(ATTRIBUTE_NAME_ID, msg_src);
        msg_pack.put(ATTRIBUTE_NAME_TEXT, msg_text);
        msg_pack.put(ATTRIBUTE_NAME_TIME, msg_time);
        msg_pack.put(ATTRIBUTE_NAME_IMAGE, msg_img);
        msg_pack.put(ATTRIBUTE_NAME_NAME, msg_name);
        return msg_pack;
    }

    /**
     * Добавляет сообщение (msg_text, msg_time) в список сообщений msgList.
     * @param msgList список сообщений
     * @param msg_text текст сообщения
     * @param msg_time время отправки сообщения
     */
    public static void AddMessageToList(final ArrayList<Map<String, Object>> msgList,
                                        final String msg_src,
                                        final String msg_text,
                                        final String msg_time,
                                        final String msg_name,
                                        final String msg_img){
        msgList.add(PackMessage(msg_src, msg_text, msg_time, msg_name, msg_img));
    }

    /**
     * Создаёт адаптер для вывода списка сообщений на экран
     * @param msgList список сообщений
     * @param activity активити, на котором отображаются сообщения
     * @return адаптер
     */
    public static AvatarSimpleAdapter CreateAdapter(ArrayList<Map<String, Object>> msgList,
                                                                                Activity activity){
        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_TIME,
                ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_NAME};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvMessage, R.id.tvDate, R.id.ivImg , R.id.tvName};

        return new AvatarSimpleAdapter( activity, msgList, R.layout.item_message, from, to);
    }

    /**
     * Инициализирует список сообщений и создаёт адаптер для его вывода на экран.
     * @param msgList список сообщений
     * @param activity активити
     * @param msg_text список текстов сообщений
     * @param msg_time список времён отправки сообщений
     * @return адаптер
     */
    public static AvatarSimpleAdapter InitFramework(ArrayList<Map<String, Object>> msgList,
                                              final Activity activity,
                                              final ArrayList<String> msg_ids,
                                              final ArrayList<String> msg_text,
                                              final ArrayList<String> msg_time,
                                              final ArrayList<String> msg_name,
                                              final ArrayList<String> msg_img){
        if(msgList == null){
            return null;
        }
        for (int i = 0; i < msg_text.size(); i++) {
            AddMessageToList(msgList, msg_ids.get(i), msg_text.get(i), msg_time.get(i),
                    msg_name.get(i), msg_img.get(i));
        }

        return CreateAdapter(msgList, activity);
    }

    public static String SpacesToWebSpaces(final String message){
        StringBuilder builder = new StringBuilder(message);
        int space = 0;

        while(builder.toString().contains(" ")){
            space = builder.indexOf(" ");
            builder.replace(space, space + 1, "%20");
        }

        return builder.toString();
    }

    public static String PackToBrackets(final String message){
        return "\"" + message + "\"";
    }

    public static String UnpackFromBrackets(final String message){
        final int FirstAfterBracket = 1;
        final int LastBeforeBracket = message.length() - 1;

        if(message.charAt(FirstAfterBracket) != '\"' ||
                message.charAt(LastBeforeBracket) != '\"'){
            return message;
        }
        return message.substring(FirstAfterBracket, LastBeforeBracket);
    }

    public static String RoundToSeconds(final String date){
        return date.substring(0, date.length() - 2);
    }

    public static String FormatDate(int mode){
        Calendar calendar = Calendar.getInstance();
        String time = calendar.getTime().toString();
        switch(mode){
            case DATE_DAY_AND_TIME:
                return time.substring(0, 19);
            case DATE_DAY:
                return time.substring(0, 10);
            case DATE_TIME:
                return time.substring(10, 19);
            case DATE_ALL:
            default:
                return time;
        }
    }
}
