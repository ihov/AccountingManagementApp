package cl.ihov.project.common.vo;

import javafx.beans.property.SimpleStringProperty;

public class Empresa {

    private final SimpleStringProperty rutCliente;
    private final SimpleStringProperty rutEmpresa;
    private final SimpleStringProperty rutAnterior;
    private final SimpleStringProperty giroComercial;
    private final SimpleStringProperty direccion;
    private final SimpleStringProperty comuna;
    private final SimpleStringProperty idContabilidad;
    private final SimpleStringProperty idBanco;
    private final SimpleStringProperty nroCuenta;
    private final SimpleStringProperty idCuenta;
    private final SimpleStringProperty claveSii;
    private final SimpleStringProperty clavePrevired;
    private final SimpleStringProperty claveOtro;
    private final SimpleStringProperty valorMensual;
    private final SimpleStringProperty activo;
    private final SimpleStringProperty razonSocial;
    private int idContabilidadInt;
    private int idBancoInt;
    private int idCuentaInt;
    private int valorMensualInt;

    public Empresa() {
        this.rutCliente = new SimpleStringProperty();
        this.rutEmpresa = new SimpleStringProperty();
        this.rutAnterior = new SimpleStringProperty();
        this.giroComercial = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.comuna = new SimpleStringProperty();
        this.idContabilidad = new SimpleStringProperty();
        this.idBanco = new SimpleStringProperty();
        this.nroCuenta = new SimpleStringProperty();
        this.idCuenta = new SimpleStringProperty();
        this.claveSii = new SimpleStringProperty();
        this.clavePrevired = new SimpleStringProperty();
        this.claveOtro = new SimpleStringProperty();
        this.valorMensual = new SimpleStringProperty();
        this.activo = new SimpleStringProperty();
        this.razonSocial = new SimpleStringProperty();
    }

    public Empresa(String rutCliente, String rutEmpresa, String rutAnterior, String giroComercial, String direccion, String comuna, String idContabilidad, String idBanco, String nroCuenta,
            String idCuenta, String claveSii, String clavePrevired, String claveOtro, String valorMensual, String activo, String razonSocial) {
        this.rutCliente = new SimpleStringProperty(rutCliente);
        this.rutEmpresa = new SimpleStringProperty(rutEmpresa);
        this.rutAnterior = new SimpleStringProperty(rutAnterior);
        this.giroComercial = new SimpleStringProperty(giroComercial);
        this.direccion = new SimpleStringProperty(direccion);
        this.comuna = new SimpleStringProperty(comuna);
        this.idContabilidad = new SimpleStringProperty(idContabilidad);
        this.idBanco = new SimpleStringProperty(idBanco);
        this.nroCuenta = new SimpleStringProperty(nroCuenta);
        this.idCuenta = new SimpleStringProperty(idCuenta);
        this.claveSii = new SimpleStringProperty(claveSii);
        this.clavePrevired = new SimpleStringProperty(clavePrevired);
        this.claveOtro = new SimpleStringProperty(claveOtro);
        this.valorMensual = new SimpleStringProperty(valorMensual);
        this.activo = new SimpleStringProperty(activo);
        this.razonSocial = new SimpleStringProperty();
    }

    public String getRutCliente() {
         return rutCliente.get();
    }

    public void setRutCliente(String rutCliente) {
       this.rutCliente.set(rutCliente != null ? rutCliente.trim() : "");
    }

    public String getRutEmpresa() {
        return rutEmpresa.get();
    }

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa.set(rutEmpresa != null ? rutEmpresa.trim() : "");
    }

    public String getGiroComercial() {
        return giroComercial.get();
    }

    public void setGiroComercial(String giroComercial) {
        this.giroComercial.set(giroComercial != null ? giroComercial.trim() : "");
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion != null ? direccion.trim() : "");
    }

    public String getComuna() {
        return comuna.get();
    }

    public void setComuna(String comuna) {
        this.comuna.set(comuna != null ? comuna.trim() : "");
    }

    public String getIdContabilidad() {
        return idContabilidad.get();
    }

    public void setIdContabilidad(String idContabilidad) {
        this.idContabilidad.set(idContabilidad != null ? idContabilidad.trim() : "");
    }

    public String getIdBanco() {
        return idBanco.get();
    }

    public void setIdBanco(String idBanco) {
        this.idBanco.set(idBanco != null ? idBanco.trim() : "");
    }

    public String getNroCuenta() {
        return nroCuenta.get();
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta.set(nroCuenta != null ? nroCuenta.trim() : "");
    }

    public String getIdCuenta() {
        return idCuenta.get();
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta.set(idCuenta != null ? idCuenta.trim() : "" );
    }

    public String getClaveSii() {
        return claveSii.get();
    }

    public void setClaveSii(String claveSii) {
        this.claveSii.set(claveSii != null ? claveSii.trim() : "");
    }

    public String getClavePrevired() {
        return clavePrevired.get();
    }

    public void setClavePrevired(String clavePrevired) {
        this.clavePrevired.set(clavePrevired != null ? clavePrevired.trim() : "");
    }

    public String getClaveOtro() {
        return claveOtro.get();
    }

    public void setClaveOtro(String claveOtro) {
        this.claveOtro.set(claveOtro != null ? claveOtro.trim() : "");
    }

    public String getValorMensual() {
        return valorMensual.get();
    }

    public void setValorMensual(String valorMensual) {
        this.valorMensual.set(valorMensual != null ? valorMensual.trim() : "");
    }

    public String getActivo() {
        return activo.get();
    }

    public void setActivo(String activo) {
        this.activo.set(activo != null ? activo.trim() : "");
    }

    public String getRutAnterior() {
        return rutAnterior.get();
    }

    public void setRutAnterior(String rutAnterior) {
        this.rutAnterior.set( rutAnterior != null ? rutAnterior.trim() : "");
    }
    
    public String getRazonSocial(){
        return razonSocial.get();
    }
    
    public void setRazonSocial(String razonSocial){
        this.razonSocial.set(razonSocial != null ? razonSocial.trim(): "");
    }

    public int getIdContabilidadInt() {
        return idContabilidadInt;
    }

    public void setIdContabilidadInt(int idContabilidadInt) {
        this.idContabilidadInt = idContabilidadInt;
    }

    public int getIdBancoInt() {
        return idBancoInt;
    }

    public void setIdBancoInt(int idBancoInt) {
        this.idBancoInt = idBancoInt;
    }

    public int getIdCuentaInt() {
        return idCuentaInt;
    }

    public void setIdCuentaInt(int idCuentaInt) {
        this.idCuentaInt = idCuentaInt;
    }

    public int getValorMensualInt() {
        return valorMensualInt;
    }

    public void setValorMensualInt(int valorMensualInt) {
        this.valorMensualInt = valorMensualInt;
    }
}
