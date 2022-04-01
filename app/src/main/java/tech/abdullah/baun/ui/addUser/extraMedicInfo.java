package tech.abdullah.baun.ui.addUser;

public class extraMedicInfo {

    private String medicineName;
    private String medicineTime;
    private String medicineTimeLocal;//am or pm
    private String medicineTimePeriod;//after or before
    private String amount;
    private String amountSet;
    private String userNote;
    private String expiryDate;


    public extraMedicInfo(String medicineName, String medicineTime, String medicineTimeLocal, String medicineTimePeriod, String amount, String amountSet, String userNote, String expiryDate) {
        this.medicineName = medicineName;
        this.medicineTime = medicineTime;
        this.medicineTimeLocal = medicineTimeLocal;
        this.medicineTimePeriod = medicineTimePeriod;
        this.amount = amount;
        this.amountSet = amountSet;
        this.userNote = userNote;
        this.expiryDate = expiryDate;
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

    public String getAmountSet() {
        return amountSet;
    }

    public void setAmountSet(String amountSet) {
        this.amountSet = amountSet;
    }
}
