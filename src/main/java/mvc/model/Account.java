package mvc.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private StringProperty accountName;
    private StringProperty username;
    private StringProperty hashedPassword;
    private StringProperty website;
    private StringProperty email;

    public Account(String userName, String accountName, String hashedPassword, String website, String email) {
        this.username = new SimpleStringProperty(userName);
        this.accountName = new SimpleStringProperty(accountName);
        this.hashedPassword = new SimpleStringProperty(hashedPassword);
        this.website = new SimpleStringProperty(website);
        this.email = new SimpleStringProperty(email);
    }

    public String getAccountName() {
        return accountName.get();
    }

    public void setAccountName(String accountName) {
        this.accountName = new SimpleStringProperty(accountName);
    }

    public String getHashedPassword() {
        return hashedPassword.get();
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = new SimpleStringProperty(hashedPassword);
    }

    public String getWebsite() {
        return website.get();
    }

    public void setWebsite(String website) {
        this.website = new SimpleStringProperty(website);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public StringProperty accountNameProperty() {
        return accountName;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty hashedPasswordProperty() {
        return hashedPassword;
    }

    public StringProperty websiteProperty() {
        return website;
    }

    public StringProperty emailProperty() {
        return email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName=" + accountName +
                ", username=" + username +
                ", hashedPassword=" + hashedPassword +
                ", website=" + website +
                ", email=" + email +
                '}';
    }
}
