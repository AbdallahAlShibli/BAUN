package tech.abdullah.baun;

public class UpdateMedicineDataModel {

    private String medicineName, medicineTime1, medicineTimeLocal1, medicineTimePeriod1, expiryDate, amountSize, amountType, userNote;

    public UpdateMedicineDataModel(String medicineName, String medicineTime1, String medicineTimeLocal1, String medicineTimePeriod1, String expiryDate, String amountSize, String amountType, String userNote) {
        this.medicineName = medicineName;
        this.medicineTime1 = medicineTime1;
        this.medicineTimeLocal1 = medicineTimeLocal1;
        this.medicineTimePeriod1 = medicineTimePeriod1;
        this.expiryDate = expiryDate;
        this.amountSize = amountSize;
        this.amountType = amountType;
        this.userNote = userNote;
    }

    public UpdateMedicineDataModel() {

        medicineName = "";
        medicineTime1 = "";
        medicineTimeLocal1 = "";
        medicineTimePeriod1 = "";
        expiryDate = "";
        amountSize = "";
        amountType = "";
        userNote = "";

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

    public String getMedicineTimeLocal1() {
        return medicineTimeLocal1;
    }

    public void setMedicineTimeLocal1(String medicineTimeLocal1) {
        this.medicineTimeLocal1 = medicineTimeLocal1;
    }

    public String getMedicineTimePeriod1() {
        return medicineTimePeriod1;
    }

    public void setMedicineTimePeriod1(String medicineTimePeriod1) {
        this.medicineTimePeriod1 = medicineTimePeriod1;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
