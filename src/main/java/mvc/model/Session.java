package mvc.model;

import javax.persistence.*;


@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @Column(name = "id")
    private long userId;

    @Column(name = "token")
    private String sessionToken;

    @Transient
    private String url;

    public Session(String sessionId) {
        this.sessionToken = sessionId;
    }

    public Session(String sessionId, long userId) {
        this.sessionToken = sessionId;
        this.userId = userId;
    }

    public Session() {
        sessionToken = "";
        userId = 0;
        url = "";
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
