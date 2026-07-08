package ar.edu.utn.frba.dds.climAlert.domain;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Alerta {
    private String id;
    private int riskTemperature;
    private int riskHumidity;
    private Date ts;
    private boolean deleted;

    public Alerta(int riskTemperature, int riskHumidity, Date ts) {
        this.id = UUID.randomUUID().toString();
        this.riskTemperature = riskTemperature;
        this.riskHumidity = riskHumidity;
        this.ts = ts;
    }

    public boolean isAlertClimate(Clima clima) {
        return clima.isActivo() && clima.getHumidity() > riskHumidity && clima.getTemperature() > riskTemperature;
    }
}
