package cl.ihov.project.common.vo;

import java.util.Date;

public class Fechas {

   
    private Date fechaInicio;
    private Date fechaTermino;
  
    public Fechas() {
       
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
}
