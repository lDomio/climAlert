package ar.edu.utn.frba.dds.climAlert.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NotificacionTest {

    @Test
    void constructorDebeInicializarCampos() {
        Date now = new Date();
        Notificacion notificacion = new Notificacion("destino@example.com", "mensaje de prueba", now);

        assertNotNull(notificacion.getId());
        assertEquals("destino@example.com", notificacion.getEmailDestinatario());
        assertEquals("mensaje de prueba", notificacion.getMensaje());
        assertEquals(now, notificacion.getFecha());
        assertFalse(notificacion.isDeleted());
        assertFalse(notificacion.isEnviada());
    }

    @Test
    void settersDebenActualizarValores() {
        Notificacion notificacion = new Notificacion("a@a.com", "mensaje", new Date());
        notificacion.setMensaje("nuevo mensaje");
        notificacion.setDeleted(true);
        notificacion.setEnviada(true);

        assertEquals("nuevo mensaje", notificacion.getMensaje());
        assertTrue(notificacion.isDeleted());
        assertTrue(notificacion.isEnviada());
    }

    @Test
    void constructorDebeGenerarIdDistintoParaCadaInstancia() {
        Notificacion n1 = new Notificacion("a@a.com", "msg1", new Date());
        Notificacion n2 = new Notificacion("b@b.com", "msg2", new Date());

        assertNotEquals(n1.getId(), n2.getId());
    }
}
