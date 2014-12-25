package com.example.izual.studentftk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class ChooseFriendToChat extends Activity {
    private ListView listFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_friend_to_chat);
        listFriends = (ListView)findViewById(R.id.listFriends);
        /* Afterwards there will be name of friend */
        final String[] friends = {"Friend1", "Friend2", "Friend3"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, friends);
        listFriends.setAdapter(arrayAdapter);
        listFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("ChoosenFriend", friends[position]);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
