package ar.edu.utn.frba.dds.climAlert.domain;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Entidad {
    private String id;
    private String email;
    private boolean estaHabilitado;
    private Date ts;

    public Entidad(String email) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
    }
}
