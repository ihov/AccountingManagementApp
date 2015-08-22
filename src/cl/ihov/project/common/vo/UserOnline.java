package cl.ihov.project.common.vo;

public class UserOnline {

    private String userId;
    private String password;
    private String nombres;
    private String apellidos;
    private String lastLogin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s", nombres, apellidos, lastLogin);
    }
}
