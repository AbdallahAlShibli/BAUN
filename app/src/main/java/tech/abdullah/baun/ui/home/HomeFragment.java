package tech.abdullah.baun.ui.home;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import tech.abdullah.baun.ReadWriteData;
import tech.abdullah.baun.ui.editUser.EidtUserInfoFragment;
import tech.abdullah.baun.R;
import tech.abdullah.baun.usrActivity;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static tech.abdullah.baun.R.drawable.abc_list_pressed_holo_dark;
import static tech.abdullah.baun.R.drawable.ic_launcher_background;
import static tech.abdullah.baun.R.drawable.ic_power;
import static tech.abdullah.baun.R.drawable.ic_power_off;

public class HomeFragment extends Fragment {


    private TextView percentage, firstPartDegreeView, secondPartDegreeView;
    private Button powerButton;
    private CheckBox firstFridge, firstRoom, secondFridge, secondRoom;
    private LinearLayout infoAdd, infoShow;

    private static boolean power = false;

    String checker = "False";


    ListView listView;
    private RelativeLayout progressBarLayout;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    List<userNameListModel> userList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        listView = root.findViewById(R.id.usersList);

        progressBarLayout = root.findViewById(R.id.progressBarLayout);
        infoAdd = root.findViewById(R.id.info_addUser);
        infoShow = root.findViewById(R.id.usersName_list);

        percentage = root.findViewById(R.id.battery_persent);
        firstPartDegreeView = root.findViewById(R.id.firstPartDegree);
        secondPartDegreeView = root.findViewById(R.id.secondPartDegree);
        powerButton = root.findViewById(R.id.power_button);
        firstFridge = root.findViewById(R.id.firstPartPositionFridge);
        firstRoom = root.findViewById(R.id.firstPartPositionRoom);
        secondFridge = root.findViewById(R.id.secondPartPositionFridge);
        secondRoom = root.findViewById(R.id.secondPartPositionRoom);


        infoAdd.setVisibility(View.GONE);
        infoShow.setVisibility(View.GONE);


//        CollectionReference exUserData = firebaseFirestore.collection("extraUsers");
//
//        Map<String, Object> data= new HashMap<>();
//        data.put("name", "tfty");
//        data.put("age", "5");
//        exUserData.document().set(data);


//        CollectionReference userData = firebaseFirestore.collection("users");
//        Map<String, Object> data1 = new HashMap<>();
//        data1.put("firstName", FirstName);
//        data1.put("familyName", FamilyName);
//        data1.put("email", FEmail);
//        userData.document(firebaseAuth.getUid()).set(data1);





        fragmentCategories();

        PowerButton();

//        checkUser();


        firebaseFirestore.collection("extraUsers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    String s = "F";
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        if (!document.getData().isEmpty()){

                            s = "T";


                        }

                    }

                    if (s.equals("T")) {
                        infoShow.setVisibility(View.VISIBLE);
                        showUsersNames();
                    } else {


                        infoShow.setVisibility(View.GONE);
                        infoAdd.setVisibility(View.VISIBLE);

                    }




                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });






        return root;
    }

    private void showUsersNames() {


//        final String userID = Objects.requireNonNull(firebaseAuth.getUid());
//
//        final int[] count = {0};
//        DocumentReference docRef = firebaseFirestore.collection("users").document(userID);
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document != null && document.exists()) {
//
////                        userList.add(new userNameListModel(document.get("userName").toString(), String.valueOf(count[0])));
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });


        ArrayList<String> userIDList = new ArrayList<>();


        firebaseFirestore.collection("extraUsers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                userIDList.add(document.getId().toString());

                            }


                            UsersIDs usersIDs = new UsersIDs();
                            usersIDs.setIdsList(userIDList);

                            Set<String> set = new HashSet<String>(userIDList);

                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("BAUN", MODE_PRIVATE).edit();
                            editor.putStringSet("ids", set);
                            editor.apply();


                            final int[] counter = {0};
                            for (String ss : userIDList) {


                                Log.d("ID", ss.toString());

                                DocumentReference docRef0 = firebaseFirestore.collection("extraUsers").document(ss);
                                docRef0.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                counter[0]++;

                                                userList.add(new userNameListModel(document.get("name").toString(), String.valueOf(counter[0])));


                                            } else {
                                                Log.d(TAG, "No such document");
                                            }
                                        } else {
                                            Log.d(TAG, "get failed with ", task.getException());
                                        }


                                    }
                                });


                            }


                            try {
                                Thread.sleep(3000);


                                progressBarLayout.setVisibility(View.GONE);
//                        userList.add(new userNameListModel("Salim", String.valueOf(count + 1)));

                                MyListAdapter adapter = new MyListAdapter(getActivity(), R.layout.user_name_atr, userList);

                                adapter.notifyDataSetChanged();

                                listView.invalidateViews();
                                listView.refreshDrawableState();
                                listView.setAdapter(adapter);
                                listView.invalidateViews();
                                listView.refreshDrawableState();

                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }


    private void PowerButton() {

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (power) {

                    powerButton.setBackgroundResource(ic_power_off);
                    power = false;

                    dbRef.child("batteryStatus").setValue("off");

                } else {

                    powerButton.setBackgroundResource(ic_power);
                    power = true;

                    dbRef.child("batteryStatus").setValue("on");

                }


            }
        });


        firstFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstFridge.isChecked()) {

                    firstRoom.setChecked(false);

                    dbRef.child("firstPartPOSITION").setValue("fridge");

                } else {

                    firstRoom.setChecked(true);

                    dbRef.child("firstPartPOSITION").setValue("room");

                }
            }
        });

        firstRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstRoom.isChecked()) {

                    firstFridge.setChecked(false);

                    dbRef.child("firstPartPOSITION").setValue("room");

                } else {

                    firstFridge.setChecked(true);

                    dbRef.child("firstPartPOSITION").setValue("fridge");

                }
            }
        });


        secondFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secondFridge.isChecked()) {

                    secondRoom.setChecked(false);

                    dbRef.child("secondPartPOSITION").setValue("fridge");

                } else {

                    secondRoom.setChecked(true);

                    dbRef.child("secondPartPOSITION").setValue("room");

                }
            }
        });

        secondRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secondRoom.isChecked()) {

                    secondFridge.setChecked(false);

                    dbRef.child("secondPartPOSITION").setValue("room");

                } else {

                    secondFridge.setChecked(true);

                    dbRef.child("secondPartPOSITION").setValue("fridge");

                }
            }
        });


    }

    private void fragmentCategories() {


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again

                if (dataSnapshot.exists()){
                    progressBarLayout.setVisibility(View.GONE);
                }

                String sss = Objects.requireNonNull(dataSnapshot.child("chargerPercentage").getValue()).toString();

                percentage.setText(sss);
                firstPartDegreeView.setText(Objects.requireNonNull(dataSnapshot.child("heatDegreeFirstPart").getValue()).toString());
                secondPartDegreeView.setText(Objects.requireNonNull(dataSnapshot.child("heatDegreeSecondPart").getValue()).toString());


                if (Objects.requireNonNull(dataSnapshot.child("firstPartPOSITION").getValue()).toString().equals("fridge")) {

                    firstFridge.setChecked(true);
                    firstRoom.setChecked(false);

                } else {
                    if (Objects.requireNonNull(dataSnapshot.child("firstPartPOSITION").getValue()).toString().equals("room")) {
                        firstFridge.setChecked(false);
                        firstRoom.setChecked(true);
                    } else {

                        firstFridge.setChecked(false);
                        firstRoom.setChecked(false);
                    }

                }


                if (Objects.requireNonNull(dataSnapshot.child("secondPartPOSITION").getValue()).toString().equals("fridge")) {

                    firstFridge.setChecked(true);
                    firstRoom.setChecked(false);

                } else {
                    if (Objects.requireNonNull(dataSnapshot.child("secondPartPOSITION").getValue()).toString().equals("room")) {
                        firstFridge.setChecked(false);
                        firstRoom.setChecked(true);
                    } else {

                        firstFridge.setChecked(false);
                        firstRoom.setChecked(false);
                    }

                }

                if (Objects.requireNonNull(dataSnapshot.child("batteryStatus").getValue()).toString().equals("on")) {

                    powerButton.setBackgroundResource(ic_power);
                    power = true;

                } else {

                    powerButton.setBackgroundResource(ic_power_off);
                    power = false;

                }


                if (Objects.requireNonNull(dataSnapshot.child("tempFirstPartNotfication").getValue()).toString().equals("LOW")) {

                    notification("درجة الحرارة منخفضه عن الدرجة المعتادة");

                } else {

                    notification("درجة الحرارة مرتفعه عن الدرجة المعتادة");

                }


                if (Objects.requireNonNull(dataSnapshot.child("tempSecondPartNotfication").getValue()).toString().equals("LOW")) {

                    notification("درجة الحرارة منخفضه عن الدرجة المعتادة");

                } else {

                    notification("درجة الحرارة مرتفعه عن الدرجة المعتادة");

                }


            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }


        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        myRef.removeEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    public void checkUser() {



        firebaseFirestore.collection("extraUsers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (!document.getData().isEmpty()){

                                    checker = "True";

                                }

                            }




                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


    private void notification(String noti) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext(), "Notification")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("تنبيه!")
                    .setContentText(noti)
                    .setAutoCancel(true)
                    .setContentInfo("Info");

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
            managerCompat.notify(new Random().nextInt(), notificationBuilder.build());


        }

    }

}