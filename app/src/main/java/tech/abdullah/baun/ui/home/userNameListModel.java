package tech.abdullah.baun.ui.home;

public class userNameListModel {

    private String uName, uNumber;

    userNameListModel(String uName, String uNumber){

        this.uName = uName;
        this.uNumber = uNumber;

    }

    public String getuName() {
        if (uName == null)
            return " ";
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuNumber() {
        if (uName == null)
            return " ";
        return uNumber;
    }

    public void setuNumber(String uNumber) {
        this.uNumber = uNumber;
    }
}
