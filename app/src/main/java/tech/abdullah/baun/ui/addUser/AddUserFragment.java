package tech.abdullah.baun.ui.addUser;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import tech.abdullah.baun.R;
import tech.abdullah.baun.ui.home.MyListAdapter;
import tech.abdullah.baun.ui.home.UsersIDs;
import tech.abdullah.baun.ui.home.userNameListModel;
import tech.abdullah.baun.usrActivity;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class AddUserFragment extends Fragment {


    public static AddUserFragment newInstance() {
        return new AddUserFragment();
    }

    private FirebaseFirestore Ff = FirebaseFirestore.getInstance();
    private FirebaseAuth FA = FirebaseAuth.getInstance();
    private FirebaseUser FU = FA.getCurrentUser();


    boolean inputTimeStatus1 = false;
    boolean inputTimeStatus2 = false;
    boolean inputTimeStatus3 = false;

    boolean inputTimeStatus2_m2 = false;
    boolean inputTimeStatus3_m2 = false;

    boolean inputTimeStatus2_m3 = false;
    boolean inputTimeStatus3_m3 = false;


    private Button saveButton;
    private EditText ExuserName, ExuserAge;
    private LinearLayout adNewExInfoUserLayout, infolayout, time2Layout, time3Layout;
    private LinearLayout infolayout2, time2Layout2, time3Layout2;
    private LinearLayout infolayout3, time2Layout3, time3Layout3;
    private ProgressBar progressBar;

    private EditText medicineName, exDate, amount, note, time1, time2, time3;
    private EditText medicineName_m2, exDate_m2, amount_m2, note_m2, time1_m2, time2_m2, time3_m2;
    private EditText medicineName_m3, exDate_m3, amount_m3, note_m3, time1_m3, time2_m3, time3_m3;
    private Button save, addTime2, addTime3;
    private Button save_m2, addTime2_m2, addTime3_m2;
    private Button save_m3, addTime2_m3, addTime3_m3;
    private Button m2, m3;
    private RadioGroup localRadio1, localRadio2, localRadio3, eatRadio1, eatRadio2, eatRadio3, amountType;
    private RadioGroup localRadio1_m2, localRadio2_m2, localRadio3_m2, eatRadio1_m2, eatRadio2_m2, eatRadio3_m2, amountType_m2;
    private RadioGroup localRadio1_m3, localRadio2_m3, localRadio3_m3, eatRadio1_m3, eatRadio2_m3, eatRadio3_m3, amountType_m3;


    String timeSet1 = "", timeSet2 = "", timeSet3 = "";
    String eatTime1 = "", eatTime2 = "", eatTime3 = "";
    private String amountType1 = "";

    String timeSet1_m2 = "", timeSet2_m2 = "", timeSet3_m2 = "";
    String eatTime1_m2 = "", eatTime2_m2 = "", eatTime3_m2 = "";
    private String amountType1_m2 = "";

    String timeSet1_m3 = "", timeSet2_m3 = "", timeSet3_m3 = "";
    String eatTime1_m3 = "", eatTime2_m3 = "", eatTime3_m3 = "";
    private String amountType1_m3 = "";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_user_fragment, container, false);


        saveButton = root.findViewById(R.id.add_user_data);

        adNewExInfoUserLayout = root.findViewById(R.id.addNewExtraUserLayout);

        infolayout = root.findViewById(R.id.infolayout);
        infolayout2 = root.findViewById(R.id.infolayout2);
        infolayout3 = root.findViewById(R.id.infolayout3);


        infolayout2.setVisibility(View.GONE);
        infolayout3.setVisibility(View.GONE);


        m2 = root.findViewById(R.id.addMedic2);
        m3 = root.findViewById(R.id.addMedic3);


        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m2.getVisibility() == View.GONE) {

                    infolayout2.setVisibility(View.VISIBLE);

                } else {
                    infolayout2.setVisibility(View.VISIBLE);
                }
            }
        });


        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m3.getVisibility() == View.GONE) {

                    infolayout3.setVisibility(View.VISIBLE);

                } else {
                    infolayout3.setVisibility(View.VISIBLE);
                }
            }
        });


        /////// M1 ///////
        time2Layout = root.findViewById(R.id.time2_layout);
        time2Layout.setVisibility(View.GONE);
        time3Layout = root.findViewById(R.id.time3_layout);
        time3Layout.setVisibility(View.GONE);
        /////// M1 ///////

        /////// M2 ///////
        time2Layout2 = root.findViewById(R.id.time2_layout_m2);
        time2Layout2.setVisibility(View.GONE);
        time3Layout2 = root.findViewById(R.id.time3_layout_m2);
        time3Layout2.setVisibility(View.GONE);
        /////// M2 ///////

        /////// M3 ///////
        time2Layout3 = root.findViewById(R.id.time2_layout_m3);
        time2Layout3.setVisibility(View.GONE);
        time3Layout3 = root.findViewById(R.id.time3_layout_m3);
        time3Layout3.setVisibility(View.GONE);
        /////// M3 ///////


//        adNewExInfoUserLayout.setVisibility(View.GONE);
//        infolayout.setVisibility(View.GONE);

        ExuserName = root.findViewById(R.id.ExuserName);
        ExuserAge = root.findViewById(R.id.ExuserAge);

///////////////////////// M1 ////////////////////////////////////////////
        medicineName = root.findViewById(R.id.medicName);
        exDate = root.findViewById(R.id.date_exp0);
        amount = root.findViewById(R.id.amount);
        note = root.findViewById(R.id.note);

        time1 = root.findViewById(R.id.time_pik1);
        time2 = root.findViewById(R.id.time_pik2);
        time3 = root.findViewById(R.id.time_pik3);


        amountType = root.findViewById(R.id.amountRadio);
        localRadio1 = root.findViewById(R.id.time1Radio);
        localRadio2 = root.findViewById(R.id.time2Radio);
        localRadio3 = root.findViewById(R.id.time3Radio);
        eatRadio1 = root.findViewById(R.id.eatRadio1);
        eatRadio2 = root.findViewById(R.id.eating_Radio2);
        eatRadio3 = root.findViewById(R.id.eating_Radio3);

        amountType = root.findViewById(R.id.amountRadio);

        addTime2 = root.findViewById(R.id.addTime2);
        addTime3 = root.findViewById(R.id.addTime3);

        final int timeRadioID1 = localRadio1.getCheckedRadioButtonId();
        final int timeRadioID2 = localRadio2.getCheckedRadioButtonId();
        final int timeRadioID3 = localRadio3.getCheckedRadioButtonId();
        final int eatRadioID1 = eatRadio1.getCheckedRadioButtonId();
        final int eatRadioID2 = eatRadio2.getCheckedRadioButtonId();
        final int eatRadioID3 = eatRadio3.getCheckedRadioButtonId();

        final int amount_Type = amountType.getCheckedRadioButtonId();


///////////////////////// M1 ////////////////////////////////////////////


///////////////////////// M2 ////////////////////////////////////////////
        medicineName_m2 = root.findViewById(R.id.medicName2);
        exDate_m2 = root.findViewById(R.id.date_exp0_m2);
        amount_m2 = root.findViewById(R.id.amount_m2);
        note_m2 = root.findViewById(R.id.note_m2);

        time1_m2 = root.findViewById(R.id.time_pik1_m2);
        time2_m2 = root.findViewById(R.id.time_pik2_m2);
        time3_m2 = root.findViewById(R.id.time_pik3_m2);


        amountType_m2 = root.findViewById(R.id.amountRadio_m2);
        localRadio1_m2 = root.findViewById(R.id.time1Radio_m2);
        localRadio2_m2 = root.findViewById(R.id.time2Radio_m2);
        localRadio3_m2 = root.findViewById(R.id.time3Radio_m2);
        eatRadio1_m2 = root.findViewById(R.id.eatRadio1_m2);
        eatRadio2_m2 = root.findViewById(R.id.eating_Radio2_m2);
        eatRadio3_m2 = root.findViewById(R.id.eating_Radio3_m2);

        amountType_m2 = root.findViewById(R.id.amountRadio_m2);

        addTime2_m2 = root.findViewById(R.id.addTime2_m2);
        addTime3_m2 = root.findViewById(R.id.addTime3_m2);

        final int timeRadioID1_m2 = localRadio1_m2.getCheckedRadioButtonId();
        final int timeRadioID2_m2 = localRadio2_m2.getCheckedRadioButtonId();
        final int timeRadioID3_m2 = localRadio3_m2.getCheckedRadioButtonId();
        final int eatRadioID1_m2 = eatRadio1_m2.getCheckedRadioButtonId();
        final int eatRadioID2_m2 = eatRadio2_m2.getCheckedRadioButtonId();
        final int eatRadioID3_m2 = eatRadio3_m2.getCheckedRadioButtonId();

        final int amount_Type_m2 = amountType_m2.getCheckedRadioButtonId();


///////////////////////// M2 ////////////////////////////////////////////


///////////////////////// M3 ////////////////////////////////////////////
        medicineName_m3 = root.findViewById(R.id.medicName3);
        exDate_m3 = root.findViewById(R.id.date_exp0_m3);
        amount_m3 = root.findViewById(R.id.amount_m3);
        note_m3 = root.findViewById(R.id.note_m3);

        time1_m3 = root.findViewById(R.id.time_pik1_m3);
        time2_m3 = root.findViewById(R.id.time_pik2_m3);
        time3_m3 = root.findViewById(R.id.time_pik3_m3);


        amountType_m3 = root.findViewById(R.id.amountRadio_m3);
        localRadio1_m3 = root.findViewById(R.id.time1Radio_m3);
        localRadio2_m3 = root.findViewById(R.id.time2Radio_m3);
        localRadio3_m3 = root.findViewById(R.id.time3Radio_m3);
        eatRadio1_m3 = root.findViewById(R.id.eatRadio1_m3);
        eatRadio2_m3 = root.findViewById(R.id.eating_Radio2_m3);
        eatRadio3_m3 = root.findViewById(R.id.eating_Radio3_m3);

        amountType_m3 = root.findViewById(R.id.amountRadio_m3);

        addTime2_m3 = root.findViewById(R.id.addTime2_m3);
        addTime3_m3 = root.findViewById(R.id.addTime3_m3);

        final int timeRadioID1_m3 = localRadio1_m3.getCheckedRadioButtonId();
        final int timeRadioID2_m3 = localRadio2_m3.getCheckedRadioButtonId();
        final int timeRadioID3_m3 = localRadio3_m3.getCheckedRadioButtonId();
        final int eatRadioID1_m3 = eatRadio1_m3.getCheckedRadioButtonId();
        final int eatRadioID2_m3 = eatRadio2_m3.getCheckedRadioButtonId();
        final int eatRadioID3_m3 = eatRadio3_m3.getCheckedRadioButtonId();

        final int amount_Type_m3 = amountType_m3.getCheckedRadioButtonId();


///////////////////////// M3 ////////////////////////////////////////////


        //++++++++++++++++++++++++++++++++++++++++++ M1 ++++++++++++++++++++++++++++++++//
        addTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time2Layout.getVisibility() == View.GONE) {
                    time2Layout.setVisibility(View.VISIBLE);
                    inputTimeStatus2 = true;
                } else {
                    time2Layout.setVisibility(View.GONE);
                    inputTimeStatus2 = false;
                }
            }
        });

        addTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time3Layout.getVisibility() == View.GONE) {
                    time3Layout.setVisibility(View.VISIBLE);
                    inputTimeStatus3 = true;
                } else {
                    time3Layout.setVisibility(View.GONE);
                    inputTimeStatus3 = false;
                }
            }
        });

//        if (time2Layout.getVisibility() == View.GONE)


        localRadio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am).getId()) {
                    timeSet1 = "am";
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pm).getId()) {
                        timeSet1 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eatRadio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat).getId()) {
                    eatTime1 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat).getId()) {
                        eatTime1 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        amountType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == root.findViewById(R.id.ml).getId()) {
                    amountType1 = "ml";
                    Toast.makeText(getActivity(), "ml", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pill).getId()) {
                        amountType1 = "pill";
                        Toast.makeText(getActivity(), "pill", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


//        if (time2Layout.getVisibility() == View.VISIBLE) {

        localRadio2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am2).getId()) {
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                    timeSet2 = "am";
                } else {
                    if (checkedId == root.findViewById(R.id.pm2).getId()) {
                        timeSet2 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        eatRadio2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat2).getId()) {
                    eatTime2 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat2).getId()) {
                        eatTime2 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        }

//        if (time2Layout.getVisibility() == View.VISIBLE) {

        localRadio3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am3).getId()) {
                    timeSet3 = "am";
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pm3).getId()) {
                        timeSet3 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        eatRadio3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat3).getId()) {
                    eatTime3 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat3).getId()) {
                        eatTime3 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        }


        //++++++++++++++++++++++++++++++++++++++++++ M1 ++++++++++++++++++++++++++++++++//


        //++++++++++++++++++++++++++++++++++++++++++ M2 ++++++++++++++++++++++++++++++++//
        addTime2_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time2Layout2.getVisibility() == View.GONE) {
                    time2Layout2.setVisibility(View.VISIBLE);
                    inputTimeStatus2_m2 = true;
                } else {
                    time2Layout2.setVisibility(View.GONE);
                    inputTimeStatus2_m2 = false;
                }
            }
        });

        addTime3_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time3Layout2.getVisibility() == View.GONE) {
                    time3Layout2.setVisibility(View.VISIBLE);
                    inputTimeStatus3_m2 = true;
                } else {
                    time3Layout2.setVisibility(View.GONE);
                    inputTimeStatus3_m2 = false;
                }
            }
        });

//        if (time2Layout.getVisibility() == View.GONE)


        localRadio1_m2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am_m2).getId()) {
                    timeSet1_m2 = "am";
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pm_m2).getId()) {
                        timeSet1_m2 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eatRadio1_m2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat_m2).getId()) {
                    eatTime1_m2 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat_m2).getId()) {
                        eatTime1_m2 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        amountType_m2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == root.findViewById(R.id.ml_m2).getId()) {
                    amountType1_m2 = "ml";
                    Toast.makeText(getActivity(), "ml", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pill_m2).getId()) {
                        amountType1_m2 = "pill";
                        Toast.makeText(getActivity(), "pill", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


//        if (time2Layout.getVisibility() == View.VISIBLE) {

        localRadio2_m2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am2_m2).getId()) {
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                    timeSet2_m2 = "am";
                } else {
                    if (checkedId == root.findViewById(R.id.pm2_m2).getId()) {
                        timeSet2_m2 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        eatRadio2_m2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat2_m2).getId()) {
                    eatTime2_m2 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat2_m2).getId()) {
                        eatTime2_m2 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        }

//        if (time2Layout.getVisibility() == View.VISIBLE) {

        localRadio3_m2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am3_m2).getId()) {
                    timeSet3_m2 = "am";
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pm3_m2).getId()) {
                        timeSet3_m2 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        eatRadio3_m2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat3_m2).getId()) {
                    eatTime3_m2 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat3_m2).getId()) {
                        eatTime3_m2 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        }


        //++++++++++++++++++++++++++++++++++++++++++ M2 ++++++++++++++++++++++++++++++++//


        //++++++++++++++++++++++++++++++++++++++++++ M3 ++++++++++++++++++++++++++++++++//
        addTime2_m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time2Layout3.getVisibility() == View.GONE) {
                    time2Layout3.setVisibility(View.VISIBLE);
                    inputTimeStatus2_m3 = true;
                } else {
                    time2Layout3.setVisibility(View.GONE);
                    inputTimeStatus2_m3 = false;
                }
            }
        });

        addTime3_m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time3Layout3.getVisibility() == View.GONE) {
                    time3Layout3.setVisibility(View.VISIBLE);
                    inputTimeStatus3_m3 = true;
                } else {
                    time3Layout3.setVisibility(View.GONE);
                    inputTimeStatus3_m3 = false;
                }
            }
        });

//        if (time2Layout.getVisibility() == View.GONE)


        localRadio1_m3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am_m3).getId()) {
                    timeSet1_m3 = "am";
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pm_m3).getId()) {
                        timeSet1_m3 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eatRadio1_m3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat_m3).getId()) {
                    eatTime1_m3 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat_m3).getId()) {
                        eatTime1_m3 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        amountType_m3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == root.findViewById(R.id.ml_m3).getId()) {
                    amountType1_m3 = "ml";
                    Toast.makeText(getActivity(), "ml", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pill_m3).getId()) {
                        amountType1_m3 = "pill";
                        Toast.makeText(getActivity(), "pill", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


//        if (time2Layout.getVisibility() == View.VISIBLE) {

        localRadio2_m3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am2_m3).getId()) {
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                    timeSet2_m3 = "am";
                } else {
                    if (checkedId == root.findViewById(R.id.pm2_m3).getId()) {
                        timeSet2_m3 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        eatRadio2_m3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat2_m3).getId()) {
                    eatTime2_m3 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat2_m3).getId()) {
                        eatTime2_m3 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        }

//        if (time2Layout.getVisibility() == View.VISIBLE) {

        localRadio3_m3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.am3_m3).getId()) {
                    timeSet3_m3 = "am";
                    Toast.makeText(getActivity(), "am", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.pm3_m3).getId()) {
                        timeSet3_m3 = "pm";
                        Toast.makeText(getActivity(), "pm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        eatRadio3_m3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == root.findViewById(R.id.after_eat3_m3).getId()) {
                    eatTime3_m3 = "after";
                    Toast.makeText(getActivity(), "after", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == root.findViewById(R.id.before_eat3_m3).getId()) {
                        eatTime3_m3 = "before";
                        Toast.makeText(getActivity(), "before", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        }


        //++++++++++++++++++++++++++++++++++++++++++ M3 ++++++++++++++++++++++++++++++++//


        progressBar = root.findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressBar.setVisibility(View.VISIBLE);

                if (checkPostInputs()) {

                    CollectionReference exUserData = Ff.collection("extraUsers");

                    Map<String, Object> data = new HashMap<>();
                    data.put("name", ExuserName.getText().toString().trim());
                    data.put("age", ExuserAge.getText().toString().trim());


                    ////// M1 /////////
                    Map<String, Object> medicData = new HashMap<>();
                    medicData.put("medicineName", medicineName.getText().toString().trim());
                    medicData.put("medicineTime1", time1.getText().toString().trim());
                    medicData.put("medicineTimeLocal1", timeSet1);
                    medicData.put("medicineTimePeriod1", eatTime1);
                    medicData.put("expiryDate", exDate.getText().toString().trim());
                    medicData.put("amountSize", amount.getText().toString().trim());
                    medicData.put("amountType", amountType1);
                    if (note.toString().isEmpty()) {
                        medicData.put("userNote", "");
                    } else {
                        medicData.put("userNote", note.getText().toString().trim());
                    }


                    if (checkStatus(time2)) {

                        medicData.put("medicineTime2", time2.getText().toString().trim());
                        medicData.put("medicineTimeLocal2", timeSet2);
                        medicData.put("medicineTimePeriod2", eatTime2);
                    }

                    if (checkStatus(time3)) {

                        medicData.put("medicineTime3", time3.getText().toString().trim());
                        medicData.put("medicineTimeLocal3", timeSet3);
                        medicData.put("medicineTimePeriod3", eatTime3);
                    }

                    data.put("m1", medicData);
                    ////// M1 /////////


                    ////// M2 /////////
                    if (m2.getVisibility() == View.VISIBLE) {


                        if (checkPostInputs2()) {
                            Map<String, Object> medicData2 = new HashMap<>();
                            medicData2.put("medicineName", medicineName_m2.getText().toString().trim());
                            medicData2.put("medicineTime1", time1_m2.getText().toString().trim());
                            medicData2.put("medicineTimeLocal1", timeSet1_m2);
                            medicData2.put("medicineTimePeriod1", eatTime1_m2);
                            medicData2.put("expiryDate", exDate_m2.getText().toString().trim());
                            medicData2.put("amountSize", amount_m2.getText().toString().trim());
                            medicData2.put("amountType", amountType1_m2);
                            if (note_m2.toString().isEmpty()) {
                                medicData2.put("userNote", "");
                            } else {
                                medicData2.put("userNote", note_m2.getText().toString().trim());
                            }


                            if (checkStatus(time2_m2)) {

                                medicData2.put("medicineTime2", time2_m2.getText().toString().trim());
                                medicData2.put("medicineTimeLocal2", timeSet2_m2);
                                medicData2.put("medicineTimePeriod2", eatTime2_m2);
                            }

                            if (checkStatus(time3_m2)) {

                                medicData2.put("medicineTime3", time3_m2.getText().toString().trim());
                                medicData2.put("medicineTimeLocal3", timeSet3_m2);
                                medicData2.put("medicineTimePeriod3", eatTime3_m2);
                            }

                            data.put("m2", medicData2);
                        }
                    }
                    ////// M2 /////////



                    ////// M3 /////////
                    if (m3.getVisibility() == View.VISIBLE) {


                        if (checkPostInputs3()) {
                            Map<String, Object> medicData3 = new HashMap<>();
                            medicData3.put("medicineName", medicineName_m3.getText().toString().trim());
                            medicData3.put("medicineTime1", time1_m3.getText().toString().trim());
                            medicData3.put("medicineTimeLocal1", timeSet1_m3);
                            medicData3.put("medicineTimePeriod1", eatTime1_m3);
                            medicData3.put("expiryDate", exDate_m3.getText().toString().trim());
                            medicData3.put("amountSize", amount_m3.getText().toString().trim());
                            medicData3.put("amountType", amountType1_m3);
                            if (note_m3.toString().isEmpty()) {
                                medicData3.put("userNote", "");
                            } else {
                                medicData3.put("userNote", note_m3.getText().toString().trim());
                            }


                            if (checkStatus(time2_m3)) {

                                medicData3.put("medicineTime2", time2_m3.getText().toString().trim());
                                medicData3.put("medicineTimeLocal2", timeSet2_m3);
                                medicData3.put("medicineTimePeriod2", eatTime2_m3);
                            }

                            if (checkStatus(time3_m3)) {

                                medicData3.put("medicineTime3", time3_m3.getText().toString().trim());
                                medicData3.put("medicineTimeLocal3", timeSet3_m3);
                                medicData3.put("medicineTimePeriod3", eatTime3_m3);
                            }

                            data.put("m3", medicData3);
                        }
                    }
                    ////// M3 /////////


                    try {
                        Thread.sleep(2000);

                        exUserData.document().set(data);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }


            }
        });


//        checkUser();


        return root;
    }

    private boolean checkStatus(EditText time) {
        boolean status = false;

        if (!time.getText().toString().equals("00:00")) {
            status = true;
        }

        return status;
    }

    private boolean checkPostInputs() {

        progressBar.setVisibility(View.VISIBLE);

        boolean value = false;

        try {
            Thread.sleep(3000);
            progressBar.setVisibility(View.GONE);
            if (!ExuserName.getText().toString().isEmpty() && !ExuserAge.getText().toString().isEmpty()) {
                if (!medicineName.getText().toString().isEmpty() && !time1.toString().isEmpty()) {
                    if (!time1.toString().isEmpty()) {
                        if (!exDate.getText().toString().equals("00/00/000") && !amount.getText().toString().isEmpty()) {
                            value = true;
                        } else {
                            message();
                        }
                    } else {
                        message();
                    }
                } else {
                    message();
                }
            } else {

                message();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return value;
    }


    private boolean checkPostInputs2() {

        progressBar.setVisibility(View.VISIBLE);

        boolean value = false;

        try {
            Thread.sleep(3000);
            progressBar.setVisibility(View.GONE);

            if (!medicineName_m2.getText().toString().isEmpty() && !time1_m2.toString().isEmpty()) {
                if (!time1_m2.toString().isEmpty()) {
                    if (!exDate_m2.getText().toString().equals("00/00/000") && !amount_m2.getText().toString().isEmpty()) {
                        value = true;
                    } else {
                        message();
                    }
                } else {
                    message();
                }
            } else {
                message();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return value;
    }

    private boolean checkPostInputs3() {

        progressBar.setVisibility(View.VISIBLE);

        boolean value = false;

        try {
            Thread.sleep(3000);
            progressBar.setVisibility(View.GONE);

            if (!medicineName_m3.getText().toString().isEmpty() && !time1_m3.toString().isEmpty()) {
                if (!time1_m3.toString().isEmpty()) {
                    if (!exDate_m3.getText().toString().equals("00/00/000") && !amount_m3.getText().toString().isEmpty()) {
                        value = true;
                    } else {
                        message();
                    }
                } else {
                    message();
                }
            } else {
                message();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return value;
    }

    private void message() {

        Toast.makeText(getActivity(), "Please complete all information!", Toast.LENGTH_SHORT).show();
    }


//    public void checkUser(){
//
//       FU = FA.getCurrentUser();
//
//       if (FU != null) {
//
//
//
//           addExUser.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//
//                   if (!ExuserName.toString().isEmpty() && !ExuserAge.toString().isEmpty()){
//
//                       CollectionReference exUserData = Ff.collection("extraUsers");
//
//                       Map<String, Object> data = new HashMap<>();
//                       data.put("name", ExuserName.getText().toString().trim());
//                       data.put("age", ExuserAge.getText().toString().trim());
//                       exUserData.document().set(data);
//
////                       showUsersNames();
//
//                   }
//
//
//               }
//           });
//
//
//       }else {
//           adminUserLayout.setVisibility(View.VISIBLE);
//       }
//
//   }
//
//
//
//    private void checkPostInfo(String s, int position) {
//
//        infolayout.setVisibility(View.VISIBLE);
//
//
//        if (position == 0) {
//
//            DocumentReference docRef = Ff.collection("users").document(s);
//            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//
//
//                    if (task.isSuccessful()) {
//
//                        DocumentSnapshot document = task.getResult();
//                        if (document.exists()) {
//
//                            Object object = document.getData();
//
//                            int count = 1;
//                            if (object instanceof Map) {
//                                Map map = (Map) object;
//
//                                while (map.toString().contains("m" + count)) {
//
//                                    Toast.makeText(getActivity(), "m" + count, Toast.LENGTH_SHORT).show();
//                                    count++;
//
//                                    checkPost = true;
//                                    postCount = count;
//
//                                }
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
//        }else{
//
//            DocumentReference docRef = Ff.collection("extraUsers").document(s);
//            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if (task.isSuccessful()) {
//
//                        DocumentSnapshot document = task.getResult();
//                        if (document.exists()) {
//
//                            Object object = document.getData();
//
//                            int count = 1;
//                            if (object instanceof Map) {
//                                Map map = (Map) object;
//
//                                while (map.toString().contains("m" + count)) {
//
//
//
//                                    Toast.makeText(getActivity(), "m" + count, Toast.LENGTH_SHORT).show();
//                                    count++;
//
//                                    checkPost = true;
//                                    postCount = count;
//
//                                }
//                            }
//
//
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
//        }
//
//    }

}