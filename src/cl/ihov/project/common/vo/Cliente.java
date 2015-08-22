package cl.ihov.project.common.vo;

import javafx.beans.property.SimpleStringProperty;

public class Cliente {

    private final SimpleStringProperty rutCliente;
    private final SimpleStringProperty rutAnterior;
    private final SimpleStringProperty nombres;
    private final SimpleStringProperty paterno;
    private final SimpleStringProperty materno;
    private final SimpleStringProperty email;
    private final SimpleStringProperty telFijo;
    private final SimpleStringProperty celular;
    private final SimpleStringProperty activo;

    public Cliente() {
        this.rutCliente = new SimpleStringProperty();
        this.rutAnterior = new SimpleStringProperty();
        this.nombres = new SimpleStringProperty();
        this.paterno = new SimpleStringProperty();
        this.materno = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.telFijo = new SimpleStringProperty();
        this.celular = new SimpleStringProperty();
        this.activo = new SimpleStringProperty();
    }

    public Cliente(String rutCliente, String rutAnterior, String nombres, String paterno, String materno, String email, String telFijo, String celular, String activo) {
        this.rutCliente = new SimpleStringProperty(rutCliente);
        this.rutAnterior = new SimpleStringProperty(rutAnterior);
        this.nombres = new SimpleStringProperty(nombres);
        this.paterno = new SimpleStringProperty(paterno);
        this.materno = new SimpleStringProperty(materno);
        this.email = new SimpleStringProperty(email);
        this.telFijo = new SimpleStringProperty(telFijo);
        this.celular = new SimpleStringProperty(celular);
        this.activo = new SimpleStringProperty(activo);
    }

    public String getRutCliente() {
        return rutCliente.get();
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente.set(rutCliente != null ? rutCliente.trim() : "");
    }

    public String getNombres() {
        return nombres.get();
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres != null ? nombres.trim() : "");
    }

    public String getPaterno() {
        return paterno.get();
    }

    public void setPaterno(String paterno) {
        this.paterno.set(paterno != null ? paterno.trim() : "");
    }

    public String getMaterno() {
        return materno.get();
    }

    public void setMaterno(String materno) {
        this.materno.set(materno != null ? materno.trim() : "");
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email != null ? email.trim() : "");
    }

    public String getTelFijo() {
        return telFijo.get();
    }

    public void setTelFijo(String telFijo) {
        this.telFijo.set(telFijo != null ? telFijo.trim() : "");
    }

    public String getCelular() {
        return celular.get();
    }

    public void setCelular(String celular) {
        this.celular.set(celular != null ? celular.trim() : "");
    }

    public String getRutAnterior() {
        return rutAnterior.get();
    }

    public void setRutAnterior(String rutAnterior) {
        this.rutAnterior.set(rutAnterior != null ? rutAnterior.trim() : "");
    }

    public String getActivo() {
        return activo.get();
    }

    public void setActivo(String activo) {
        this.activo.set(activo != null ? activo.trim() : "");
    }
}
