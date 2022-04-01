package tech.abdullah.baun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class usrActivity2 extends AppCompatActivity {

    private Calendar myCalendar;
    private TextView date_pik, time_pik, time_pik2, time_pik3, userName, userAge, amount, note;
    List<Data> dataList = new ArrayList<>();
    private int mhour;
    private int mminute;

    static final int TIME_DIALOG_ID = 1;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private int positionU = 0;
    private String idU = "";

    private RadioButton am, pm, after, before, ml, pill, am2, am3, pm2, pm3, after2, after3, before2, before3;

    private FrameLayout time1, time2, time3, medicineInfoLayout;
    private LinearLayout  noDataLayout, noteLayout;
    private RelativeLayout progressBarLayout;



    private String name = "", age = "", amountSize = "", amountType = "", expiryDate = "", medicineName = "",
            medicineTime1 = "", medicineTime2 = "", medicineTime3 = "", medicineTimeLocal1 = "",
            medicineTimeLocal2 = "", medicineTimeLocal3 = "", medicineTimePeriod1 = "",
            medicineTimePeriod2 = "", medicineTimePeriod3 = "", userNote = "";


//    SharedPreferences pref = getSharedPreferences("BAUN", MODE_PRIVATE);
////    Set<String> ids = prefs.getStrin("ids", null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usr2);


        final Button back = findViewById(R.id.back_to_home);

         Button edit = findViewById(R.id.edit_us);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), usrActivity.class));

            }

        });

        progressBarLayout = findViewById(R.id.progressBarLayout00);

        myCalendar = Calendar.getInstance();

        date_pik = findViewById(R.id.date_exp0);
        time_pik = findViewById(R.id.time_pik);

        time_pik2 = findViewById(R.id.time_pik2);
        time_pik3 = findViewById(R.id.time_pik3);

        userAge = findViewById(R.id.userAge);
        userName = findViewById(R.id.userName);

        am = findViewById(R.id.am);
        pm = findViewById(R.id.pm);
        before = findViewById(R.id.before_eat);
        after = findViewById(R.id.after_eat);

        am2 = findViewById(R.id.am2);
        pm2 = findViewById(R.id.pm2);
        before2 = findViewById(R.id.before_eat2);
        after2 = findViewById(R.id.after_eat2);

        am3 = findViewById(R.id.am3);
        pm3 = findViewById(R.id.pm3);
        before3 = findViewById(R.id.before_eat3);
        after3 = findViewById(R.id.after_eat3);

        ml = findViewById(R.id.ml);
        pill = findViewById(R.id.pill);
        amount = findViewById(R.id.amount);
        note = findViewById(R.id.note);

        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
//        medicineInfoLayout = findViewById(R.id.medicineInfoLayout0);
        noDataLayout = findViewById(R.id.noDataLayout);
        noteLayout = findViewById(R.id.noteLayout);


//        medicineInfoLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);


        String formatData = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Calendar.getInstance().getTime());

        String[] timeData = formatData.split(" ");

        String hours = timeData[0];
        String local = timeData[1];


        SharedPreferences pref = getSharedPreferences("BAUN", MODE_PRIVATE);
        positionU = pref.getInt("position", 0);
        idU = pref.getString("UID", null);


//        if (positionU == 0) {
//            DocumentReference docRef = firebaseFirestore.collection("users").document(idU);
//            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if (task.isSuccessful()) {
//
//                        ArrayList<String> medicName = new ArrayList<>();
//                        ArrayList<String> medicListName = new ArrayList<>();
//
//                        DocumentSnapshot document = task.getResult();
//                        if (document.exists()) {
//
//
//                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//
//                            userName.setText(document.get("userName").toString());
//                            userAge.setText(document.get("age").toString());
//
//
//                            Object object = document.getData();
//
//                            int count = 1;
//                            if (object instanceof Map){
//                                Map map = (Map) object;
//
//                                while (map.toString().contains("m"+count)) {
//
//                                    Map<String, String> m = new HashMap<String, String>((Map<? extends String, ? extends String>) map.get("m"+count));
//
//                                    medicListName.add("m"+count);
//                                    medicName.add(m.get("medicineName"));
//
//
//                                    Toast.makeText(usrActivity.this, m.get("amountSize"), Toast.LENGTH_SHORT).show();
//
//                                    count++;
//
//                                }
//                            }
//
//
//                            if (medicName.size() !=0) {
//                                medicineInfoLayout.setVisibility(View.VISIBLE);
//                                getMedicineNameData(medicName, medicListName, hours, local);
//                            }else {
//
//                                medicineInfoLayout.setVisibility(View.GONE);
//                                noDataLayout.setVisibility(View.VISIBLE);
//                            }
//
//
//
//                        } else {
//                            Log.d(TAG, "No such document");
//                        }
//                    } else {
//                        Log.d(TAG, "get failed with ", task.getException());
//                    }
//                }
//            });
//
//        }else {

        DocumentReference docRef = firebaseFirestore.collection("extraUsers").document(idU);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    ArrayList<String> medicName = new ArrayList<>();
                    ArrayList<String> medicListName = new ArrayList<>();

                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {


                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

//                            name = document.get("name").toString();
//                            age = document.get("age").toString();

                        userName.setText(document.get("name").toString());
                        userAge.setText(document.get("age").toString());


                        Object object = document.getData();

                        int count = 1;
                        if (object instanceof Map) {
                            Map map = (Map) object;

                            while (map.toString().contains("m" + count)) {

                                Map<String, String> m = new HashMap<String, String>((Map<? extends String, ? extends String>) map.get("m" + count));

                                medicListName.add("m" + count);
                                medicName.add(m.get("medicineName"));
                                dataList.add(new Data(m.get("medicineName")));
                                Log.d("Datas", m.get("medicineName"));


                                count++;

                            }





                            if (medicName.size() != 0) {


//                                for (String s : medicName){
//                                    dataList.add(new Data(s));
//                                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//
//                                }



                                try {
                                    Thread.sleep(3000);
                                    progressBarLayout.setVisibility(View.GONE);

                                    ListView show = findViewById(R.id.showData);
                                    EditUserDataAdapter2 adapter = new EditUserDataAdapter2(getApplicationContext(), R.layout.medic_name_in_list_2, dataList, medicName);
                                    show.setAdapter(adapter);
                                    setListViewHeightBasedOnChildren(show);
//                                    adapter.notifyDataSetChanged();
////
////
//                                    show.invalidateViews();
//                                    show.refreshDrawableState();
//                                    show.setAdapter(adapter);
//                                    show.invalidateViews();
//                                    show.refreshDrawableState();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            } else {
                                progressBarLayout.setVisibility(View.GONE);
                                medicineInfoLayout.setVisibility(View.GONE);
                                noDataLayout.setVisibility(View.VISIBLE);
                            }


                        }


                    } else {
                        Log.d(TAG, "No such document");
                    }


                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

//        }


//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//
//
//                String myFormat = "dd/MM/YY"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//                date_pik.setText(sdf.format(myCalendar.getTime()));
//
//            }
//
//        };
//
//        date_pik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(usrActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//
//
//        time_pik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showDialog(TIME_DIALOG_ID);
//
//            }
//        });
//
//        final Calendar c = Calendar.getInstance();
//        mhour = c.get(Calendar.HOUR_OF_DAY);
//        mminute = c.get(Calendar.MINUTE);


    }

//    private void getDataInList(ArrayList<String> medicName, ArrayList<String> medicListName, String hours, String local) {
//
//        ListView show = findViewById(R.id.showData);
//
////        getInfoMdeicine(medicName, medicListName, hours, local);
//
////        for (int i = 0; i<medicName.size(); i++){
////
////            dataList.add(new EditUserDataConst().setName(medicName.get(i)), );
////        }
//
//
//
//
//
//
//    }

//    private void getMedicineNameData(ArrayList<String> medicName, ArrayList<String> medicListName, String hours, String local) {
//
//        Spinner spin = findViewById(R.id.medicNames_spinner);
//
//        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                if (position == 0){
//
//                getInfoMdeicine(medicName, position, medicListName, hours, local);
//
//                }else if (position == 1){
//                    getInfoMdeicine(medicName, position, medicListName, hours, local);
//                }else if (position == 2){
//                    getInfoMdeicine(medicName, position, medicListName, hours, local);
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,medicName);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(aa);
//
//    }

//    private void getInfoMdeicine(ArrayList<String> medicName, ArrayList<String> medicListName, String hours, String local) {
//
//        if (medicName.size() != 0){
//
////            String userID = "";
////            if (positionU == 0){
////
////                userID = "users";
////            }else {
////
////                userID = "extraUsers";
////
////            }
//
//
//
//            DocumentReference docRef1 = firebaseFirestore.collection("extraUsers").document(idU);
//            docRef1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                    for (int i = 0; i < medicName.size(); i++) {
//
//                    Map<String, Object> myMap = (Map<String, Object>) documentSnapshot.get(medicListName.get(i));
//
//
//                    if (myMap.containsKey("medicineTime1")) {
//                        if ((myMap.get("medicineTime1").toString().equals(hours)) && myMap.get("medicineTimeLocal1").toString().equals(local.toLowerCase())) {
//
//                            notification("حان وقت دوائك");
//
//                        }
//                    }
//
//
//                    if (myMap.containsKey("medicineTime2")) {
//
//                        if ((myMap.get("medicineTime2").toString().equals(hours)) && myMap.get("medicineTimeLocal2").toString().equals(local.toLowerCase())) {
//
//                            notification("حان وقت دوائك");
//
//                        }
//                    }
//
//                    if (myMap.containsKey("medicineTime3")) {
//
//                        if ((myMap.get("medicineTime3").toString().equals(hours)) && myMap.get("medicineTimeLocal3").toString().equals(local.toLowerCase())) {
//
//                            notification("حان وقت دوائك");
//
//                        }
//                    }
//
//                    if (myMap.containsKey("medicineTimeLocal1")) {
//                        if (!myMap.get("medicineTimeLocal1").toString().isEmpty()) {
//
//                            time1.setVisibility(View.VISIBLE);
//
//                            time_pik.setText(myMap.get("medicineTime1").toString());
//
//                            if (myMap.get("medicineTimeLocal1").toString().equals("am")) {
//
//                                am.setChecked(true);
//                                pm.setChecked(false);
//                            } else if (myMap.get("medicineTimeLocal1").toString().equals("pm")) {
//
//                                am.setChecked(false);
//                                pm.setChecked(true);
//                            }
//
//                            date_pik.setText(myMap.get("expiryDate").toString());
//                            checkDateEXP(myMap.get("expiryDate").toString());
//
//
//                            if (myMap.get("medicineTimePeriod1").toString().equals("after")) {
//
//                                after.setChecked(true);
//                                before.setChecked(false);
//
//                            } else if (myMap.get("medicineTimePeriod1").toString().equals("before")) {
//
//                                after.setChecked(false);
//                                before.setChecked(true);
//                            }
//
//                            if (myMap.get("amountType").toString().equals("ml")) {
//
//                                ml.setChecked(true);
//                                pill.setChecked(false);
//                            } else if (myMap.get("amountType").toString().equals("pill")) {
//
//                                ml.setChecked(false);
//                                pill.setChecked(true);
//                            }
//
//                            amount.setText(myMap.get("amountSize").toString());
//
//                            if (!myMap.get("userNote").toString().isEmpty()) {
//
//                                note.setText(myMap.get("userNote").toString());
//                            } else {
//
//                                noteLayout.setVisibility(View.GONE);
//                            }
//
//                        }
//                    } else {
//                        time1.setVisibility(View.GONE);
//                    }
//
//
//                    if (myMap.containsKey("medicineTimeLocal2")) {
//                        if (!myMap.get("medicineTimeLocal2").toString().isEmpty()) {
//                            time2.setVisibility(View.VISIBLE);
//
//                            time_pik2.setText(myMap.get("medicineTime2").toString());
//
//                            if (myMap.get("medicineTimeLocal2").toString().equals("am")) {
//
//                                am2.setChecked(true);
//                                pm2.setChecked(false);
//                            } else if (myMap.get("medicineTimeLocal2").toString().equals("pm")) {
//
//                                am2.setChecked(false);
//                                pm2.setChecked(true);
//                            }
//
//
//                            if (myMap.get("medicineTimePeriod2").toString().equals("after")) {
//
//                                after2.setChecked(true);
//                                before2.setChecked(false);
//
//                            } else if (myMap.get("medicineTimePeriod2").toString().equals("before")) {
//
//                                after2.setChecked(false);
//                                before2.setChecked(true);
//                            }
//
//
//                        }
//                    } else {
//                        time2.setVisibility(View.GONE);
//                    }
//
//
//                    if (myMap.containsKey("medicineTimeLocal3")) {
//
//                        if (!myMap.get("medicineTimeLocal3").toString().isEmpty()) {
//                            time3.setVisibility(View.VISIBLE);
//
//                            time_pik3.setText(myMap.get("medicineTime3").toString());
//
//                            if (myMap.get("medicineTimeLocal3").toString().equals("am")) {
//
//                                am3.setChecked(true);
//                                pm3.setChecked(false);
//                            } else if (myMap.get("medicineTimeLocal3").toString().equals("pm")) {
//
//                                am3.setChecked(false);
//                                pm3.setChecked(true);
//                            }
//
//
//                            if (myMap.get("medicineTimePeriod3").toString().equals("after")) {
//
//                                after3.setChecked(true);
//                                before3.setChecked(false);
//
//                            } else if (myMap.get("medicineTimePeriod3").toString().equals("before")) {
//
//                                after3.setChecked(false);
//                                before3.setChecked(true);
//                            }
//
//
//                        }
//                    } else {
//                        time3.setVisibility(View.GONE);
//                    }
//
//
//                }
//            }
//            });
//
//
////            Toast.makeText(getApplicationContext(), "list: "+medicListName.get(i), Toast.LENGTH_SHORT).show();
//        }else {
//            noDataLayout.setVisibility(View.VISIBLE);
//        }
//
//    }

//
//    private void checkDateEXP(String expiryDate) {
//
//    }
//
//
//    public void updatetime() {
//        time_pik.setText(new StringBuilder().append(pad(mhour)).append(":").append(pad(mminute)));
//    }
//
//    private static String pad(int c) {
//        if (c >= 10)
//            return String.valueOf(c);
//        else
//            return "0" + String.valueOf(c);
//    }
//
//    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
//            new TimePickerDialog.OnTimeSetListener() {
//                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    mhour = hourOfDay;
//                    mminute = minute;
//                    updatetime();
//                }
//            };
//
//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case TIME_DIALOG_ID:
//                return new TimePickerDialog(this,
//                        mTimeSetListener, mhour, mminute, true);
//
//        }
//        return null;
//    }
//
//
//
//    private void notification(String noti) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//
//            NotificationChannel notificationChannel = new NotificationChannel("Notification","Notification", NotificationManager.IMPORTANCE_DEFAULT);
//            NotificationManager notificationManager = (NotificationManager)getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(notificationChannel);
//
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"Notification")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("تنبيه!")
//                    .setContentText(noti)
//                    .setAutoCancel(true)
//                    .setContentInfo("Info");
//
//            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
//            managerCompat.notify(new Random().nextInt(),notificationBuilder.build());
//
//
//        }
//
//    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition

            return;
        }


        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < 5; i++) //here you can set 5 row at a time if row excceded the scroll automatically available
        {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);

    }

}