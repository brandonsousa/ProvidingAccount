package br.com.seasyc.providingaccount.models.auth;

public class Login {
    private boolean login;
    private String token;

    public Login() {
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
