package upb.edu.AuthMicroservice.models;

public class LogoutRequest {
    private String email;
    private String password;
    private String session;

    public LogoutRequest() {
    }

    public LogoutRequest(String email, String password, String session) {
        this.email = email;
        this.password = password;
        this.session = session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
