package tech.abdullah.baun.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import tech.abdullah.baun.MainActivity;
import tech.abdullah.baun.R;
import tech.abdullah.baun.usrActivity;
import tech.abdullah.baun.usrActivity2;

import static android.content.Context.MODE_PRIVATE;


public class MyListAdapter extends ArrayAdapter<userNameListModel> {

    List<userNameListModel> userInfo;
    Context context;
    int resource;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final String userID = Objects.requireNonNull(firebaseAuth.getUid());

    public MyListAdapter(@NonNull Context context, int resource, List<userNameListModel> userInfo) {
        super(context, resource, userInfo);
        this.context = context;
        this.resource = resource;
        this.userInfo = userInfo;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view

        TextView textViewName = view.findViewById(R.id.user_name_box);
        TextView textViewNameCount = view.findViewById(R.id.user_name_box_count);

        //getting the hero of the specified position
        userNameListModel userdata = userInfo.get(position);

        //adding values to the list item
        textViewName.setText(userdata.getuName());
        textViewNameCount.setText(userdata.getuNumber());

        LinearLayout linearLayout = view.findViewById(R.id.user_name_box_layout);






        SharedPreferences prefs = getContext().getSharedPreferences("BAUN", MODE_PRIVATE);
        Set<String> ids = prefs.getStringSet("ids", null);

        ArrayList<String> id = new ArrayList<>(ids);
        Collections.reverse(id);




        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                if (position == 0) {
//
//                    Toast.makeText(getContext(), "" + userID, Toast.LENGTH_SHORT).show();
//                    SharedPreferences.Editor editor = getContext().getSharedPreferences("BAUN", MODE_PRIVATE).edit();
//                    editor.putString("UID", userID);
//                    editor.putInt("position", position);
//                    editor.apply();
//
//                    getContext().startActivity(new Intent(getContext().getApplicationContext(), usrActivity.class));
//
//                } else {

                    Toast.makeText(getContext(), "" + id.get(position), Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("BAUN", MODE_PRIVATE).edit();
                    editor.putString("UID", id.get(position));
                    editor.putInt("position", position-1);
                    editor.apply();

                    getContext().startActivity(new Intent(getContext().getApplicationContext(), usrActivity2.class));


            }
        });



        return view;
    }



}
