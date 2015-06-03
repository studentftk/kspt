package com.example.izual.studentftk;

import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.izual.studentftk.Common.AvatarSimpleAdapter;
import com.example.izual.studentftk.Common.ProfileInformation;
import com.example.izual.studentftk.Common.Utils;
import com.example.izual.studentftk.Friends.FriendsManagement;
import com.example.izual.studentftk.Network.RequestBuilder.FindUsersRequest;
import com.example.izual.studentftk.Network.RequestBuilder.FriendRequest;
import com.example.izual.studentftk.Users.UserStruct;
import com.example.izual.studentftk.Users.Users;
import com.example.izual.studentftk.Users.UsersFinder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class FragmentFindUsers extends Fragment {

    private ListView list_friends = null;
    private TextView found_count = null;
    private EditText edit_name = null;
    int connectionTimeout = 1000;
    private SimpleAdapter adapter = null;
    private ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    public static FragmentFindUsers newInstance() {
        FragmentFindUsers fragment = new FragmentFindUsers();
        return fragment;
    }

    public FragmentFindUsers() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFindUsers = inflater.inflate(
                R.layout.fragment_fragment_find_users, container, false);
        list_friends = (ListView) viewFindUsers.findViewById(R.id.listFriendsSearch);
        edit_name = (EditText) viewFindUsers.findViewById(R.id.editNameSearch);
        found_count = (TextView) viewFindUsers.findViewById(R.id.foundRecordsSearch);
        InitSearchUsers();
        return viewFindUsers;
    }

    public SimpleAdapter getAdapter(){
        if (adapter == null) {
            String[] from = {"name", "avatar"};
            int[] to = {R.id.txtNameSearch, R.id.imgAvatarSearch,};
            adapter = new AvatarSimpleAdapter(getActivity(), data, R.layout.item_search, from, to);
            list_friends.setAdapter(adapter);
        }
        return adapter;
    }

    public void InitSearchUsers(){
        Users.Init();
        edit_name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    found_count.setText("Поиск людей...");
                    getAdapter().notifyDataSetInvalidated();
                    data.clear();
                    Collection<UserStruct> found = FindUsers(edit_name.getText().toString());
                    if (found != null && found.size() > 0) {
                        Users.SearchCache.clear();
                        Users.SearchCache.addAll(found);
                        for (UserStruct user : found) {
                            Map<String, Object> container = new HashMap<String, Object>();
                            container.put("name", user.Name + " " + user.Surname);
                            container.put("avatar", user.Photo);
                            data.add(container);
                        }
                        found_count.setText("Найдено " + found.size() + " " +
                                Utils.AdaptToNumeric(found.size(), "запись", "записи", "записей"));
                    } else {
                        found_count.setText("Никто не нашёлся :(");
                    }
                    getAdapter().notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
        list_friends.setClickable(true);
        list_friends.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (Users.SearchCache.size() > 0 && id < Integer.MAX_VALUE && id > Integer.MIN_VALUE) {
                    UserStruct user = Users.SearchCache.get((int) id);
                    FriendsManagement manager =
                            new FriendsManagement(getActivity(), connectionTimeout);
                    manager.ManageFriend(user.SocialID, FriendRequest.Operations.del);
                    return true;
                }
                return false;
            }
        });
        list_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Users.SearchCache.size() > 0 && id < Integer.MAX_VALUE && id > Integer.MIN_VALUE) {
                    UserStruct user = Users.SearchCache.get((int) id);
                    FriendsManagement manager =
                            new FriendsManagement(getActivity(), connectionTimeout);
                    manager.ManageFriend(user.SocialID, FriendRequest.Operations.add);
                }
            }
        });
    }

    public Collection<UserStruct> FindUsers(final String input){
        final String [] full_name = input.split("[ \\*\\-]+");
        UsersFinder finder = new UsersFinder(getActivity(), connectionTimeout);
        HashSet<UserStruct> result = new HashSet<UserStruct>();
        switch(full_name.length){
            case 0:
                break;
            case 1:
                final String name = full_name[0];
                URI uri_find_name = FindUsersRequest.BuildRequestGetForName(name,
                        ProfileInformation.socialToken);
                URI uri_find_surname = FindUsersRequest.BuildRequestGetForSurname(name,
                        ProfileInformation.socialToken);
                result = finder.Load(uri_find_name);
                HashSet<UserStruct> buffer = finder.Load(uri_find_surname);
                if(result == null){
                    result = buffer;
                } else {
                    result.addAll(buffer);
                }
                break;
            case 2:
                URI uri_find = FindUsersRequest.BuildRequestGetForFullName(
                        full_name[0], full_name[1], ProfileInformation.socialToken);
                result = finder.Load(uri_find);
                break;
        }
        return result;
    }

    @Override
    public void onResume(){
        super.onResume();
        getAdapter();
    }

}
