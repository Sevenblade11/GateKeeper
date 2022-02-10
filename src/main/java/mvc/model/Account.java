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
    @Transient
    @Column(name = "account_name", nullable = false)
    private StringProperty accountName;
    @Transient
    @Column(name = "username", nullable = false)
    private StringProperty username;
    @Transient
    @Column(name = "password", nullable = false)
    private StringProperty encryptPassword;
    @Transient
    @Column(name = "website")
    private StringProperty website;
    @Transient
    @Column(name = "email")
    private StringProperty email;

    public Account() {

    }

    public Account(String userName, String accountName, String hashedPassword, String website, String email) {
        this.username = new SimpleStringProperty(userName);
        this.accountName = new SimpleStringProperty(accountName);
        this.encryptPassword = new SimpleStringProperty(hashedPassword);
        this.website = new SimpleStringProperty(website);
        this.email = new SimpleStringProperty(email);
    }

    public String getAccountName() {
        return accountName.get();
    }

    public void setAccountName(String accountName) {
        this.accountName = new SimpleStringProperty(accountName);
    }

    public String getEncryptPassword() {
        return encryptPassword.get();
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = new SimpleStringProperty(encryptPassword);
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

    public StringProperty encryptPasswordProperty() {
        return encryptPassword;
    }

    public StringProperty websiteProperty() {
        return website;
    }

    public StringProperty emailProperty() {
        return email;
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
