package cl.ihov.project.common.vo;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

public class Abono {

    private final SimpleStringProperty rutCliente;
    private final SimpleStringProperty rutEmpresa;
    private final SimpleStringProperty idAbono;
    private final SimpleStringProperty monto;
    private final SimpleStringProperty observacion;
    private final SimpleStringProperty nrecibo;
//    private final SimpleStringProperty mes;
//    private final SimpleStringProperty anno;
    private final SimpleStringProperty fecha;
    private Date fechaDate;
    private int montoInt;
    private int idAbonoInt;
    private int valorMensual;

    public Abono() {
        this.rutCliente = new SimpleStringProperty();
        this.rutEmpresa = new SimpleStringProperty();
        this.idAbono = new SimpleStringProperty();
        this.monto = new SimpleStringProperty();
        this.observacion = new SimpleStringProperty();
        this.nrecibo = new SimpleStringProperty();
//        this.anno = new SimpleStringProperty();
//        this.mes = new SimpleStringProperty();
        this.fecha = new SimpleStringProperty();
    }

    public Abono(String rutCliente, String rutEmpresa, String idAbono, String monto, String observacion, String nrecibo, /*String mes, String anno,*/ String fecha) {
        this.rutCliente = new SimpleStringProperty(rutCliente);
        this.rutEmpresa = new SimpleStringProperty(rutEmpresa);
        this.idAbono = new SimpleStringProperty(idAbono);
        this.monto = new SimpleStringProperty(monto);
        this.observacion = new SimpleStringProperty(observacion);
        this.nrecibo = new SimpleStringProperty(nrecibo);
//        this.mes = new SimpleStringProperty(mes);
//        this.anno = new SimpleStringProperty(anno);
        this.fecha = new SimpleStringProperty(fecha);
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

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha != null ? fecha.trim() : "");
    }

    public Date getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }
    
    public String getObservacion() {
        return observacion.get();
    }

    public void setObservacion(String observacion) {
        this.observacion.set(observacion != null ? observacion.trim() : "");
    }

    public String getNrecibo() {
        return nrecibo.get();
    }

    public void setNrecibo(String nrecibo) {
        this.nrecibo.set(nrecibo != null ? nrecibo.trim() : "");
    }

//    public String getAnno() {
//        return anno.get();
//    }
//
//    public void setAnno(String anno) {
//        this.anno.set(anno != null ? anno.trim() : "");
//    }
//
//    public String getMes() {
//        return mes.get();
//    }
//
//    public void setMes(String mes) {
//        this.mes.set(mes != null ? mes.trim() : "");
//    }

//    public int getMesInt() {
//        return Integer.valueOf(getMes());
//    }
//
//    public int getAnnoInt() {
//        return Integer.valueOf(getAnno());
//    }

    public String getIdAbono() {
        return idAbono.get();
    }

    public void setIdAbono(String idAbono) {
        this.idAbono.set(idAbono != null ? idAbono.trim() : "");
    }

    public int getIdAbonoInt() {
        return idAbonoInt;
    }

    public void setIdAbonoInt(int idAbonoInt) {
        this.idAbonoInt = idAbonoInt;
    }

    public String getMonto() {
        return monto.get();
    }

    public void setMonto(String monto) {
        this.monto.set(monto != null ? monto.trim() : "");
    }

    public int getMontoInt() {
        return montoInt;
    }

    public void setMontoInt(int montoInt) {
        this.montoInt = montoInt;
    }

    public int getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(int valorMensual) {
        this.valorMensual = valorMensual;
    }
}
