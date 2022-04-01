package tech.abdullah.baun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import tech.abdullah.baun.ui.home.HomeFragment;

import static android.content.Context.MODE_PRIVATE;

public class ReadWriteData extends AppCompatActivity {

    private  String dataSet = "";
    static  String[] sss;

    Context context;

    private String userName;
    private String userAge;
    private String medicineName;
    private String medicineTime;
    private String medicineTimeLocal;//am or pm
    private String medicineTimePeriod;//after or before
    private String amount;//pill or ml
    private String userNote;
    private String expiryDate;

    private String batteryStatus;
    private String batteryPercentage;
    private String heatDegreeFirstPart;
    private String heatDegreeSecondPart;
    private String firstPartPOSITION;
    private String secondPartPOSITION;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public ReadWriteData(){


        userName = "";
        userAge = "";
        medicineName = "";
        medicineTime = "";
        medicineTimeLocal = "";//am or pm
        medicineTimePeriod = "";//after or before
        amount = "";//pill or ml
        userNote = "";
        expiryDate = "";

        batteryStatus = "";
        batteryPercentage = "";
        heatDegreeFirstPart = "";
        heatDegreeSecondPart = "";
        firstPartPOSITION = "";
        secondPartPOSITION = "";

        getDatabaseData("");

    }




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(String medicineTime) {
        this.medicineTime = medicineTime;
    }

    public String getMedicineTimeLocal() {
        return medicineTimeLocal;
    }

    public void setMedicineTimeLocal(String medicineTimeLocal) {
        this.medicineTimeLocal = medicineTimeLocal;
    }

    public String getMedicineTimePeriod() {
        return medicineTimePeriod;
    }

    public void setMedicineTimePeriod(String medicineTimePeriod) {
        this.medicineTimePeriod = medicineTimePeriod;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBatteryStatus() {

        getDatabaseData("batteryStatus");
        return result();
    }


    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public String getBatteryPercentage() {

        getDatabaseData("chargerPercentage");
        return result();
    }

    public void setBatteryPercentage(String batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public String getHeatDegreeFirstPart() {
        getDatabaseData("heatDegreeFirstPart");
        return dataSet;
    }

    public void setHeatDegreeFirstPart(String heatDegreeFirstPart) {
        this.heatDegreeFirstPart = heatDegreeFirstPart;
    }

    public String getHeatDegreeSecondPart() {
        getDatabaseData("heatDegreeSecondPart");
        return dataSet;
    }

    public void setHeatDegreeSecondPart(String heatDegreeSecondPart) {
        this.heatDegreeSecondPart = heatDegreeSecondPart;
    }

    public String getFirstPartPOSITION() {
        getDatabaseData("firstPartPOSITION");
        return dataSet;
    }

    public void setFirstPartPOSITION(String firstPartPOSITION) {
        this.firstPartPOSITION = firstPartPOSITION;
    }

    public String getSecondPartPOSITION() {

        getDatabaseData("secondPartPOSITION");
        return dataSet;
    }

    public void setSecondPartPOSITION(String secondPartPOSITION) {
        this.secondPartPOSITION = secondPartPOSITION;
    }



    private static String result = "";

    public String result(){


        return result;
    }


    private void getDatabaseData(String par){



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again

                result = Objects.requireNonNull(dataSnapshot.child(par).getValue()).toString();



                Log.d("LOG0", result);
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }



        });




    }

}
