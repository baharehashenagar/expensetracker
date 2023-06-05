package ir.expensetracker.api;

public class UserChangePasswordParam {
    private String newPassword;
    private String newPasswordConfirm;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    @Override
    public String toString() {
        return "{" +
                ", newPassword='" + newPassword + '\'' +
                ", newPasswordConfirm='" + newPasswordConfirm + '\'' +
                '}';
    }
}
