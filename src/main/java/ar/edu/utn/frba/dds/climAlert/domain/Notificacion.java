package ar.edu.utn.frba.dds.climAlert.domain;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Notificacion {
    private String id;
    private String emailDestinatario;
    private String mensaje;
    private Date fecha;
    private boolean deleted;
    private boolean enviada;

    public Notificacion(String emailDestinatario, String mensaje, Date fecha) {
        this.id = UUID.randomUUID().toString();
        this.emailDestinatario = emailDestinatario;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }
}
