/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ihov.project.common.vo;

import javafx.beans.property.SimpleStringProperty;

public class Deudor {
    
    private final SimpleStringProperty rutEmpresa;
    private final SimpleStringProperty razonSocial;
    private final SimpleStringProperty giroComercial;
    private final SimpleStringProperty montoAbono;
    private final SimpleStringProperty montoDebe;
    private final SimpleStringProperty mes;
    private final SimpleStringProperty anno;
    private final SimpleStringProperty valorMensual;
    private int valorMensualInt;
    
     public Deudor() {
        this.rutEmpresa = new SimpleStringProperty();
        this.razonSocial = new SimpleStringProperty();
        this.giroComercial = new SimpleStringProperty();
        this.montoAbono = new SimpleStringProperty();
        this.montoDebe = new SimpleStringProperty();
        this.mes = new SimpleStringProperty();
        this.anno = new SimpleStringProperty();
        this.valorMensual = new SimpleStringProperty();
    }
    
     public Deudor(String rutString, String razonSocial, String giroComercial, String montoAbono, String montoDebe, String mes, String anno, String valorMensual){
       this.rutEmpresa=new SimpleStringProperty(rutString);
       this.razonSocial=new SimpleStringProperty(razonSocial);
       this.giroComercial=new SimpleStringProperty(giroComercial);
       this.montoAbono=new SimpleStringProperty(montoAbono);
       this.montoDebe=new SimpleStringProperty(montoDebe);
       this.mes=new SimpleStringProperty(mes);
       this.anno=new SimpleStringProperty(anno);
       this.valorMensual=new SimpleStringProperty(valorMensual);
     }


    public String getRutEmpresa() {
        return rutEmpresa.get();
    }

    public void setRutEmpresa(String rutEmpresa) {
         this.rutEmpresa.set(rutEmpresa != null ? rutEmpresa.trim() : "");
    }

    public String getRazonSocial() {
        return razonSocial.get();
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial.set(razonSocial != null ? razonSocial.trim() : "");
    }

    public String getGiroComercial() {
        return giroComercial.get();
    }

    public void setGiroComercial(String giroComercial) {
         this.giroComercial.set(giroComercial != null ? giroComercial.trim() : "");
    }

    public String getMontoAbono() {
        return montoAbono.get();
    }

    public void setMontoAbono(String montoAbono) {
        this.montoAbono.set(montoAbono != null ? montoAbono.trim() : "");
    }

    public String getMontoDebe() {
        return montoDebe.get();
    }

    public void setMontoDebe(String montoDebe) {
        this.montoDebe.set(montoDebe != null ? montoDebe.trim() : "");
    }

    public String getMes() {
        return mes.get();
    }

    public void setMes(String mes) {
        this.mes.set(mes != null ? mes.trim() : "");
    }

    public String getAnno() {
        return anno.get();
    }

    public void setAnno(String anno) {
        this.anno.set(anno != null ? anno.trim() : "");
    }

    public String getValorMensual() {
        return valorMensual.get();
    }

    public void setValorMensual(String valorMensual) {
        this.valorMensual.set(valorMensual != null ? valorMensual.trim() : "");
    }

    public int getValorMensualInt() {
        return valorMensualInt;
    }

    public void setValorMensualInt(int valorMensualInt) {
        this.valorMensualInt = valorMensualInt;
    }

    public int getMesInt() {
        return Integer.parseInt(mes.get());
    }

    public int getAnnoInt() {
        return Integer.parseInt(anno.get());
    }

    public void setMontoInt(int montoInt){
        this.montoAbono.set(String.valueOf(montoInt));
    }
}
