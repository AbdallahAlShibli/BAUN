package tech.abdullah.baun;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.Random;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class EditUserDataAdapter extends ArrayAdapter<Data> {

    List<Data> userInfo;
    ArrayList<String> medicListName;
    Context context;
    int resource;



    boolean m1 = false, m2 = false, m3 = false;


    private Calendar myCalendar;
    private EditText date_pik, time_pik, time_pik2, time_pik3, userName, userAge, amount, note, medcin_in_list;

    private int mhour;
    private int mminute;

    static final int TIME_DIALOG_ID = 1;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private int positionU = 0;
    private String idU = "";

    String timeSet1 = "", timeSet2 = "", timeSet3 = "", eatTime1 = "", eatTime2 = "", eatTime3 = "", amountType1 = "";

    private RadioButton am, pm, after, before, ml, pill, am2, am3, pm2, pm3, after2, after3, before2, before3;

    private FrameLayout time1, time2, time3;
    LinearLayout medicineInfoLayout, noDataLayout, noteLayout, medic_info;

    List<Data> dataList = new ArrayList<>();


    private String name = "", age = "", amountSize = "", amountType = "", expiryDate = "", medicineName = "",
            medicineTime1 = "", medicineTime2 = "", medicineTime3 = "", medicineTimeLocal1 = "",
            medicineTimeLocal2 = "", medicineTimeLocal3 = "", medicineTimePeriod1 = "",
            medicineTimePeriod2 = "", medicineTimePeriod3 = "", userNote = "";


    public EditUserDataAdapter(@NonNull Context context, int resource, List<Data> userInfo, ArrayList<String> medicListName) {
        super(context, resource, userInfo);
        this.userInfo = userInfo;
        this.context = context;
        this.resource = resource;
        this.medicListName = medicListName;
    }

//    public EditUserDataAdapter(@NonNull Context context, int resource) {
//        super(context, resource);
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);



        medcin_in_list = view.findViewById(R.id.medic_name_1);
        medic_info = view.findViewById(R.id.medic_info_layout1);


//        for (int i =0; i< userInfo.size(); i++) {
        Data data = userInfo.get(position);
        medcin_in_list.setText(data.getmName());


        myCalendar = Calendar.getInstance();

        date_pik = view.findViewById(R.id.date_exp0);
        time_pik = view.findViewById(R.id.time_pik);

        time_pik2 = view.findViewById(R.id.time_pik2);
        time_pik3 = view.findViewById(R.id.time_pik3);

        userAge = view.findViewById(R.id.userAge);
        userName = view.findViewById(R.id.userName);

        am = view.findViewById(R.id.am);
        pm = view.findViewById(R.id.pm);
        before = view.findViewById(R.id.before_eat);
        after = view.findViewById(R.id.after_eat);

        am2 = view.findViewById(R.id.am2);
        pm2 = view.findViewById(R.id.pm2);
        before2 = view.findViewById(R.id.before_eat2);
        after2 = view.findViewById(R.id.after_eat2);

        am3 = view.findViewById(R.id.am3);
        pm3 = view.findViewById(R.id.pm3);
        before3 = view.findViewById(R.id.before_eat3);
        after3 = view.findViewById(R.id.after_eat3);

        ml = view.findViewById(R.id.ml);
        pill = view.findViewById(R.id.pill);
        amount = view.findViewById(R.id.amount);
        note = view.findViewById(R.id.note);

        time1 = view.findViewById(R.id.time1);
        time2 = view.findViewById(R.id.time2);
        time3 = view.findViewById(R.id.time3);
//        medicineInfoLayout = view.findViewById(R.id.medicineInfoLayout);
//        noDataLayout = view.findViewById(R.id.noDataLayout);
        noteLayout = view.findViewById(R.id.noteLayout);

        time1.setVisibility(View.GONE);
        time2.setVisibility(View.GONE);
        time3.setVisibility(View.GONE);

        ArrayList<String> medicName = new ArrayList<>();
        ArrayList<String> medicListName = new ArrayList<>();

//        medicineInfoLayout.setVisibility(View.GONE);
//        noDataLayout.setVisibility(View.GONE);


        String formatData = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Calendar.getInstance().getTime());

        String[] timeData = formatData.split(" ");

        String hours = timeData[0];
        String local = timeData[1];


        SharedPreferences pref = getContext().getSharedPreferences("BAUN", MODE_PRIVATE);
        positionU = pref.getInt("position", 0);
        idU = pref.getString("UID", null);


        DocumentReference docRef = firebaseFirestore.collection("extraUsers").document(idU);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {


                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {


                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

//                        name = document.get("name").toString();
//                        age = document.get("age").toString();

//                            userName.setText(document.get("name").toString());
//                            userAge.setText(document.get("age").toString());


                        Object object = document.getData();

                        int count = 1;
                        if (object instanceof Map) {
                            Map map = (Map) object;

                            while (map.toString().contains("m" + count)) {

                                Map<String, String> m = new HashMap<String, String>((Map<? extends String, ? extends String>) map.get("m" + count));

                                medicListName.add("m" + count);
                                medicName.add(m.get("medicineName"));


//                                Toast.makeText(getContext(), m.get("amountSize"), Toast.LENGTH_SHORT).show();

                                count++;

                            }
                        }


                        if (medicName.size() != 0) {
//                            medicineInfoLayout.setVisibility(View.VISIBLE);

//                            getDataInList(medicName, medicListName, hours, local);

                            getInfoMdeicine(medicName, medicListName, hours, local, position);
                        } else {

//                            medicineInfoLayout.setVisibility(View.GONE);
//                            noDataLayout.setVisibility(View.VISIBLE);
                        }


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

//        medic_info.setVisibility(View.GONE);
        medcin_in_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, medicListName.get(position-1), Toast.LENGTH_SHORT).show();

                if (medic_info.getVisibility() == View.GONE) {
                    Toast.makeText(context, "Visible", Toast.LENGTH_SHORT).show();
                    getInfoMdeicine(medicName, medicListName, hours, local, position);
                    medic_info.setVisibility(View.VISIBLE);
                } else {

                    medic_info.setVisibility(View.VISIBLE);
                }
            }
        });





        showCustomDialog(view);

        return view;
    }

    public void saveData(){

        if ((m1 == true) && (m2 == false) && (m3 == false)) {

            uploadM1Data();

        }
        if ((m1 == true) && (m2 == true) && (m3 == false)) {

            uploadM1M2Data();

        }
        if ((m1 == true) && (m2 == true) && (m3 == true)) {

            uploadM1M2M3Data();

        }



    }

    private void uploadM1M2M3Data() {

        uploadM1Data();
        uploadM1M2Data();

        DocumentReference washingtonRef = firebaseFirestore.collection("extraUsers").document(idU);

        washingtonRef
                .update("extraUsers", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");


                        Map<String, Object> data = new HashMap<>();



                        if (after3.isChecked()){
                            eatTime3 = "after";
                        }

                        if (before3.isChecked()){
                            eatTime3 = "before";
                        }

                        if (am3.isChecked()){
                            timeSet3 = "am";
                        }
                        if (pm3.isChecked()){
                            timeSet3 = "pm";
                        }
                        if (ml.isChecked()){
                            amountType1 = "ml";
                        }
                        if (pill.isChecked()){
                            amountType1 = "pill";
                        }

                        ////// M3 /////////
                        Map<String, Object> medicData = new HashMap<>();
                        medicData.put("medicineName", medcin_in_list.getText().toString().trim());
                        medicData.put("medicineTime2", time_pik3.getText().toString().trim());
                        medicData.put("medicineTimeLocal2", timeSet3);
                        medicData.put("medicineTimePeriod2", eatTime3);
                        medicData.put("expiryDate", date_pik.getText().toString().trim());
                        medicData.put("amountSize", amount.getText().toString().trim());
                        medicData.put("amountType", amountType1);
                        if (note.toString().isEmpty()) {
                            medicData.put("userNote", "");
                        } else {
                            medicData.put("userNote", note.getText().toString().trim());
                        }

                        data.put("m3", medicData);
                        ////// M3 /////////


                        try {
                            Thread.sleep(2000);

                            washingtonRef.update(data);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }




                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });

    }

    private void uploadM1M2Data() {
        uploadM1Data();




        DocumentReference washingtonRef = firebaseFirestore.collection("extraUsers").document(idU);

        washingtonRef
                .update("extraUsers", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");


                        Map<String, Object> data = new HashMap<>();



                        if (after2.isChecked()){
                            eatTime2 = "after";
                        }

                        if (before2.isChecked()){
                            eatTime2 = "before";
                        }

                        if (am2.isChecked()){
                            timeSet2 = "am";
                        }
                        if (pm2.isChecked()){
                            timeSet2 = "pm";
                        }
                        if (ml.isChecked()){
                            amountType1 = "ml";
                        }
                        if (pill.isChecked()){
                            amountType1 = "pill";
                        }

                        ////// M2 /////////
                        Map<String, Object> medicData = new HashMap<>();
                        medicData.put("medicineName", medcin_in_list.getText().toString().trim());
                        medicData.put("medicineTime2", time_pik2.getText().toString().trim());
                        medicData.put("medicineTimeLocal2", timeSet2);
                        medicData.put("medicineTimePeriod2", eatTime2);
                        medicData.put("expiryDate", date_pik.getText().toString().trim());
                        medicData.put("amountSize", amount.getText().toString().trim());
                        medicData.put("amountType", amountType1);
                        if (note.toString().isEmpty()) {
                            medicData.put("userNote", "");
                        } else {
                            medicData.put("userNote", note.getText().toString().trim());
                        }

                        data.put("m2", medicData);
                        ////// M2 /////////


                        try {
                            Thread.sleep(2000);

                            washingtonRef.update(data);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }




                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });


    }

    private void uploadM1Data() {


        DocumentReference washingtonRef = firebaseFirestore.collection("extraUsers").document(idU);

        washingtonRef
                .update("extraUsers", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");


                        Map<String, Object> data = new HashMap<>();

                        SharedPreferences prefs = context.getSharedPreferences("Info", MODE_PRIVATE);

                        String namee = prefs.getString("Name", "null");
                        String agee = prefs.getString("Age", "null");

                        data.put("name", namee);
                        data.put("age", agee);


                        if (after.isChecked()){
                            eatTime1 = "after";
                        }

                        if (before.isChecked()){
                            eatTime1 = "before";
                        }

                        if (am.isChecked()){
                            timeSet1 = "am";
                        }
                        if (pm.isChecked()){
                            timeSet1 = "pm";
                        }
                        if (ml.isChecked()){
                            amountType1 = "ml";
                        }
                        if (pill.isChecked()){
                            amountType1 = "pill";
                        }

                        ////// M1 /////////
                        Map<String, Object> medicData = new HashMap<>();
                        medicData.put("medicineName", medcin_in_list.getText().toString().trim());
                        medicData.put("medicineTime1", time_pik.getText().toString().trim());
                        medicData.put("medicineTimeLocal1", timeSet1);
                        medicData.put("medicineTimePeriod1", eatTime1);
                        medicData.put("expiryDate", date_pik.getText().toString().trim());
                        medicData.put("amountSize", amount.getText().toString().trim());
                        medicData.put("amountType", amountType1);
                        if (note.toString().isEmpty()) {
                            medicData.put("userNote", "");
                        } else {
                            medicData.put("userNote", note.getText().toString().trim());
                        }

                        data.put("m1", medicData);
                        ////// M1 /////////


                        try {
                            Thread.sleep(2000);

                            washingtonRef.update(data);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }




                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });



    }


    private boolean checkStatus(EditText time) {
        boolean status = false;

        if (!time.getText().toString().equals("00:00")) {
            status = true;
        }

        return status;
    }

    private void getInfoMdeicine(ArrayList<String> medicName, ArrayList<String> medicListName, String hours, String local, int position) {

        if (medicName.size() != 0) {


            DocumentReference docRef1 = firebaseFirestore.collection("extraUsers").document(idU);
            docRef1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

//                    for (int i = 0; i < medicName.size(); i++) {

                    Map<String, Object> myMap = (Map<String, Object>) documentSnapshot.get(medicListName.get(position));


                    if (myMap.containsKey("medicineTime1")) {
                        if ((myMap.get("medicineTime1").toString().equals(hours)) && myMap.get("medicineTimeLocal1").toString().equals(local.toLowerCase())) {

                            notification("حان وقت دوائك");

                        }
                    }


                    if (myMap.containsKey("medicineTime2")) {

                        if ((myMap.get("medicineTime2").toString().equals(hours)) && myMap.get("medicineTimeLocal2").toString().equals(local.toLowerCase())) {

                            notification("حان وقت دوائك");

                        }
                    }

                    if (myMap.containsKey("medicineTime3")) {

                        if ((myMap.get("medicineTime3").toString().equals(hours)) && myMap.get("medicineTimeLocal3").toString().equals(local.toLowerCase())) {

                            notification("حان وقت دوائك");

                        }
                    }

                    if (myMap.containsKey("medicineTimeLocal1")) {
                        if (!myMap.get("medicineTimeLocal1").toString().isEmpty()) {

                            m1 = true;

                            time1.setVisibility(View.VISIBLE);

                            time_pik.setText(myMap.get("medicineTime1").toString());

                            if (myMap.get("medicineTimeLocal1").toString().equals("am")) {


                                am.setChecked(true);
                                pm.setChecked(false);
                            } else if (myMap.get("medicineTimeLocal1").toString().equals("pm")) {



                                am.setChecked(false);
                                pm.setChecked(true);
                            }

                            date_pik.setText(myMap.get("expiryDate").toString());

//                                checkDateEXP(myMap.get("expiryDate").toString());


                            if (myMap.get("medicineTimePeriod1").toString().equals("after")) {



                                after.setChecked(true);
                                before.setChecked(false);

                            } else if (myMap.get("medicineTimePeriod1").toString().equals("before")) {


                                after.setChecked(false);
                                before.setChecked(true);

                            }

                            if (myMap.get("amountType").toString().equals("ml")) {

                                ml.setChecked(true);
                                pill.setChecked(false);
                            } else if (myMap.get("amountType").toString().equals("pill")) {

                                ml.setChecked(false);
                                pill.setChecked(true);
                            }

                            amount.setText(myMap.get("amountSize").toString());

                            if (!myMap.get("userNote").toString().isEmpty()) {

                                note.setText(myMap.get("userNote").toString());
                            } else {

                                noteLayout.setVisibility(View.GONE);
                            }

                        }
                    } else {
                        time1.setVisibility(View.GONE);
                    }


                    if (myMap.containsKey("medicineTimeLocal2")) {
                        if (!myMap.get("medicineTimeLocal2").toString().isEmpty()) {

                            m2 = true;

                            time2.setVisibility(View.VISIBLE);

                            time_pik2.setText(myMap.get("medicineTime2").toString());

                            if (myMap.get("medicineTimeLocal2").toString().equals("am")) {

                                am2.setChecked(true);
                                pm2.setChecked(false);
                            } else if (myMap.get("medicineTimeLocal2").toString().equals("pm")) {

                                am2.setChecked(false);
                                pm2.setChecked(true);
                            }


                            if (myMap.get("medicineTimePeriod2").toString().equals("after")) {

                                after2.setChecked(true);
                                before2.setChecked(false);

                            } else if (myMap.get("medicineTimePeriod2").toString().equals("before")) {

                                after2.setChecked(false);
                                before2.setChecked(true);
                            }


                        }
                    } else {
                        time2.setVisibility(View.GONE);
                    }


                    if (myMap.containsKey("medicineTimeLocal3")) {

                        if (!myMap.get("medicineTimeLocal3").toString().isEmpty()) {

                            m3 = true;

                            time3.setVisibility(View.VISIBLE);

                            time_pik3.setText(myMap.get("medicineTime3").toString());

                            if (myMap.get("medicineTimeLocal3").toString().equals("am")) {

                                am3.setChecked(true);
                                pm3.setChecked(false);
                            } else if (myMap.get("medicineTimeLocal3").toString().equals("pm")) {

                                am3.setChecked(false);
                                pm3.setChecked(true);
                            }


                            if (myMap.get("medicineTimePeriod3").toString().equals("after")) {

                                after3.setChecked(true);
                                before3.setChecked(false);

                            } else if (myMap.get("medicineTimePeriod3").toString().equals("before")) {

                                after3.setChecked(false);
                                before3.setChecked(true);
                            }


                        }
                    } else {
                        time3.setVisibility(View.GONE);
                    }


//                    }
                }


            });


//            Toast.makeText(getApplicationContext(), "list: "+medicListName.get(i), Toast.LENGTH_SHORT).show();
        } else {
//            noDataLayout.setVisibility(View.VISIBLE);
        }

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


    private void showCustomDialog(View view) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.med_info, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.getContext();
    }


}
