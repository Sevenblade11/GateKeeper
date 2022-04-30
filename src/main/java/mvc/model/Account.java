package mvc.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Table(name="Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Column(name = "account_name", nullable = false)
    private String accountName;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String encryptPassword;
    @Column(name = "website")
    private String website;
    @Column(name = "email")
    private String email;

    public Account() {

    }

    public Account(String userName, String accountName, String hashedPassword, String website, String email) {
        this.username = userName;
        this.accountName = accountName;
        this.encryptPassword = hashedPassword;
        this.website = website;
        this.email = email;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StringProperty accountNameProperty() {
        return new SimpleStringProperty(accountName);
    }

    public String getUsername() {
        return username;
    }

    public StringProperty usernameProperty() {
        return new SimpleStringProperty(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StringProperty encryptPasswordProperty() {
        return new SimpleStringProperty(encryptPassword);
    }

    public StringProperty websiteProperty() {
        return new SimpleStringProperty(website);
    }

    public StringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName=" + accountName +
                ", username=" + username +
                ", hashedPassword=" + encryptPassword +
                ", website=" + website +
                ", email=" + email +
                '}';
    }
}
