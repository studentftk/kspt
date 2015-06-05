package com.example.izual.studentftk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.izual.studentftk.Common.Settings;
import com.example.izual.studentftk.Friends.FriendsManagement;
import com.example.izual.studentftk.Users.UserStruct;
import com.example.izual.studentftk.Users.Users;

import java.util.ArrayList;
import java.util.Map;


public class PageUserActivity extends Activity {

    UserStruct person;
    Button btnAddFriend;
    Button btnDeleteFriend;
    TextView personName;
    ImageView photo;
    ListView list_act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        person = Users.Current;

        setContentView(R.layout.activity_page_user);
        personName = (TextView)findViewById(R.id.txtUserFullName);
        photo = (ImageView)findViewById(R.id.imgUserPhoto);
        btnAddFriend = (Button) findViewById(R.id.btnUserAddFriend);
        btnDeleteFriend = (Button) findViewById(R.id.btnUserDeleteFriend);
        list_act = (ListView) findViewById(R.id.lstUserRecentPlaces);

        personName.setText(person.Name + " " + person.Surname);
        photo.setImageDrawable(Users.Photos.get(person.Photo));

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendsManagement manager =
                        new FriendsManagement(PageUserActivity.this, Settings.connectionTimeout);
                manager.ManageFriend(person.SocialID, "add");
            }
        });

        btnDeleteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FriendsManagement manager =
                        new FriendsManagement(PageUserActivity.this, Settings.connectionTimeout);
                manager.ManageFriend(person.SocialID, "del");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
