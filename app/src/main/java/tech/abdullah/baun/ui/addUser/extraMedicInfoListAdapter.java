package tech.abdullah.baun.ui.addUser;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import tech.abdullah.baun.R;


public class extraMedicInfoListAdapter extends ArrayAdapter<extraMedicInfo> {

    List<extraMedicInfo> userInfo;
    Context context;
    int resource;

    EditText time_pik;

    int mhour;
    int mminute;
    final int TIME_DIALOG_ID = 1;
    String timePiker = "";
    String timeSet = "", eatSet = "", amountSet = "";

    public extraMedicInfoListAdapter(@NonNull Context context, int resource, List<extraMedicInfo> userInfo) {
        super(context, resource, userInfo);
        this.context = context;
        this.resource = resource;
        this.userInfo = userInfo;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);


        View view = layoutInflater.inflate(resource, null, false);


        Calendar myCalendar = Calendar.getInstance();

        EditText medicName, timePik, datePik, amount, note;
        EditText date_pik;
        RadioGroup timeRadio, eatRadio, amountRadio;



//        time_pik = view.findViewById(R.id.time_pik);
//        amount = view.findViewById(R.id.amount);
//        date_pik = view.findViewById(R.id.date_exp0);
//        note = view.findViewById(R.id.note);
//        timeRadio = view.findViewById(R.id.timeRadio);
//        eatRadio = view.findViewById(R.id.eatRadio);
//        amountRadio = view.findViewById(R.id.amountRadio);
//        medicName = view.findViewById(R.id.medic_name);
//
//        Button save = view.findViewById(R.id.save_medic);


//
//        extraMedicInfo userdata = userInfo.get(position);
////        medicName.setText(userdata.getMedicineName());
//
//
//        timeRadio = view.findViewById(R.id.timeRadio);
//        eatRadio = view.findViewById(R.id.eatRadio);
//        amountRadio = view.findViewById(R.id.amountRadio);
//
//        final int timeRadioID = timeRadio.getCheckedRadioButtonId();
//        final int eatRadioID = eatRadio.getCheckedRadioButtonId();
//        final int amountRadioID = amountRadio.getCheckedRadioButtonId();
//
//        timeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (timeRadioID == 1000013){
//                    timeSet = "am";
//                }else {
//                    if (timeRadioID == 1000258){
//                        timeSet = "pm";
//                    }
//                }
//            }
//        });
//
//        eatRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (eatRadioID == 1000067){
//                    eatSet = "beforeEat";
//                }else {
//                    if (eatRadioID == 1000321){
//                        eatSet = "afterEat";
//                    }
//                }
//            }
//        });
//
//        amountRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (amountRadioID == 1000158){
//                    amountSet = "pill";
//                }else {
//                    if (amountRadioID == 1000218){
//                        amountSet = "ml";
//                    }
//                }
//            }
//        });
//
//
//
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
//                new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//
//
////        time_pik.setOnClickListener(new View.OnClickListener() {
////            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
////            @Override
////            public void onClick(View v) {
////
////                createdDialog(1).show();
////
////            }
////        });
//
//        final Calendar c = Calendar.getInstance();
//        mhour = c.get(Calendar.HOUR_OF_DAY);
//        mminute = c.get(Calendar.MINUTE);
//        updateTime(mhour, mminute);




//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                boolean status = false;
//
//                if (!medicName.equals("")){
//
//                    userdata.setMedicineName(medicName.getText().toString());
//                    status = true;
//                }else  if (!time_pik.equals("")){
//
//                    userdata.setMedicineTime(time_pik.getText().toString());
//                    status = true;
//                }else if (!timeSet.equals("")){
//                    userdata.setMedicineTimeLocal(timeSet);
//                    status = true;
//                }else if (!eatSet.equals("")){
//
//                    userdata.setMedicineTimePeriod(eatSet);
//                    status = true;
//                }else if (!amountSet.equals("")){
//
//                    userdata.setAmountSet(amountSet);
//                    status = true;
//                }else if (!amount.equals("")){
//
//                    userdata.setAmount(amount.getText().toString());
//                    status = true;
//
//                }else if (!date_pik.equals("00/00/0000")){
//
//                    userdata.setExpiryDate(date_pik.getText().toString());
//                    status = true;
//
//                }else {
//
//                    Toast.makeText(context, "Pleas check fill all gaps", Toast.LENGTH_SHORT).show();
//                    status = false;
//                }
//
//
//                if (status == true){
//
//
//                }
//
//
//
//
//            }
//        });






        return view;
    }


}
