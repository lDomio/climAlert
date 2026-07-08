package ar.edu.utn.frba.dds.climAlert.domain;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Clima {
    private String id;
    private Date date;
    private int temperature;
    private int humidity;
    private boolean activo;
    private boolean deleted;

    public Clima(Date date, int temperature, int humidity, boolean activo) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.activo = activo;
    }
}
