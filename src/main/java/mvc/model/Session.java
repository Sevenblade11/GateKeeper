package mvc.model;

public class Session {

    private String sessionId;
    private long userId;
    private String userName;
    private String url;
    private User user;

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public Session(String sessionId, long userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Session(String sessionId, User user){
        this.sessionId = sessionId;
        this.user = user;
    }

    public Session() {
        sessionId = "";
        userId = 0;
        userName = "";
        url = "";
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
