package tech.abdullah.baun;

import java.util.ArrayList;

public class EditUserDataConst2 {

    private String name, age,amountSize,amountType, expiryDate,medicineName,
            medicineTime1, medicineTime2,medicineTime3, medicineTimeLocal1,
            medicineTimeLocal2, medicineTimeLocal3, medicineTimePeriod1,
            medicineTimePeriod2, medicineTimePeriod3,userNote;

    private ArrayList<String> medicNameList;
    private ArrayList<String> medicListName;

    public EditUserDataConst2(String name, String age, String amountSize, String amountType, String expiryDate, String medicineName, String medicineTime1, String medicineTime2, String medicineTime3, String medicineTimeLocal1, String medicineTimeLocal2, String medicineTimeLocal3, String medicineTimePeriod1, String medicineTimePeriod2, String medicineTimePeriod3, String userNote) {
        this.name = name;
        this.age = age;
        this.amountSize = amountSize;
        this.amountType = amountType;
        this.expiryDate = expiryDate;
        this.medicineName = medicineName;
        this.medicineTime1 = medicineTime1;
        this.medicineTime2 = medicineTime2;
        this.medicineTime3 = medicineTime3;
        this.medicineTimeLocal1 = medicineTimeLocal1;
        this.medicineTimeLocal2 = medicineTimeLocal2;
        this.medicineTimeLocal3 = medicineTimeLocal3;
        this.medicineTimePeriod1 = medicineTimePeriod1;
        this.medicineTimePeriod2 = medicineTimePeriod2;
        this.medicineTimePeriod3 = medicineTimePeriod3;
        this.userNote = userNote;
    }

    public EditUserDataConst2(String name, String age, String amountSize, String amountType, String expiryDate, String medicineName, String medicineTime1, String medicineTimeLocal1, String medicineTimePeriod1, String userNote) {
        this.name = name;
        this.age = age;
        this.amountSize = amountSize;
        this.amountType = amountType;
        this.expiryDate = expiryDate;
        this.medicineName = medicineName;
        this.medicineTime1 = medicineTime1;
        this.medicineTimeLocal1 = medicineTimeLocal1;
        this.medicineTimePeriod1 = medicineTimePeriod1;
        this.userNote = userNote;
    }


    public EditUserDataConst2(String name, String age, String amountSize, String amountType, String expiryDate, String medicineName, String medicineTime1, String medicineTime2, String medicineTimeLocal1, String medicineTimeLocal2, String medicineTimePeriod1, String medicineTimePeriod2, String userNote) {
        this.name = name;
        this.age = age;
        this.amountSize = amountSize;
        this.amountType = amountType;
        this.expiryDate = expiryDate;
        this.medicineName = medicineName;
        this.medicineTime1 = medicineTime1;
        this.medicineTime2 = medicineTime2;
        this.medicineTimeLocal1 = medicineTimeLocal1;
        this.medicineTimeLocal2 = medicineTimeLocal2;
        this.medicineTimePeriod1 = medicineTimePeriod1;
        this.medicineTimePeriod2 = medicineTimePeriod2;
        this.userNote = userNote;
    }

    public EditUserDataConst2(String name, String age, ArrayList<String> medicNameList, ArrayList<String> medicListName) {
        this.name = name;
        this.age = age;
        this.medicNameList = medicNameList;
        this.medicListName = medicListName;
    }


    public EditUserDataConst2(String medicineName) {
        this.medicineName = medicineName;
    }

    public EditUserDataConst2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAmountSize() {
        return amountSize;
    }

    public void setAmountSize(String amountSize) {
        this.amountSize = amountSize;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineTime1() {
        return medicineTime1;
    }

    public void setMedicineTime1(String medicineTime1) {
        this.medicineTime1 = medicineTime1;
    }

    public String getMedicineTime2() {
        return medicineTime2;
    }

    public void setMedicineTime2(String medicineTime2) {
        this.medicineTime2 = medicineTime2;
    }

    public String getMedicineTime3() {
        return medicineTime3;
    }

    public void setMedicineTime3(String medicineTime3) {
        this.medicineTime3 = medicineTime3;
    }

    public String getMedicineTimeLocal1() {
        return medicineTimeLocal1;
    }

    public void setMedicineTimeLocal1(String medicineTimeLocal1) {
        this.medicineTimeLocal1 = medicineTimeLocal1;
    }

    public String getMedicineTimeLocal2() {
        return medicineTimeLocal2;
    }

    public void setMedicineTimeLocal2(String medicineTimeLocal2) {
        this.medicineTimeLocal2 = medicineTimeLocal2;
    }

    public String getMedicineTimeLocal3() {
        return medicineTimeLocal3;
    }

    public void setMedicineTimeLocal3(String medicineTimeLocal3) {
        this.medicineTimeLocal3 = medicineTimeLocal3;
    }

    public String getMedicineTimePeriod1() {
        return medicineTimePeriod1;
    }

    public void setMedicineTimePeriod1(String medicineTimePeriod1) {
        this.medicineTimePeriod1 = medicineTimePeriod1;
    }

    public String getMedicineTimePeriod2() {
        return medicineTimePeriod2;
    }

    public void setMedicineTimePeriod2(String medicineTimePeriod2) {
        this.medicineTimePeriod2 = medicineTimePeriod2;
    }

    public String getMedicineTimePeriod3() {
        return medicineTimePeriod3;
    }

    public void setMedicineTimePeriod3(String medicineTimePeriod3) {
        this.medicineTimePeriod3 = medicineTimePeriod3;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }


    public ArrayList<String> getMedicNameList() {
        return medicNameList;
    }

    public void setMedicNameList(ArrayList<String> medicNameList) {
        this.medicNameList = medicNameList;
    }

    public ArrayList<String> getMedicListName() {
        return medicListName;
    }

    public void setMedicListName(ArrayList<String> medicListName) {
        this.medicListName = medicListName;
    }
}
