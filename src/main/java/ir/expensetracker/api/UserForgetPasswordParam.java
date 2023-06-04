package ir.expensetracker.api;

public class UserForgetPasswordParam {
    private String username;
    private String mobileNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
