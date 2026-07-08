package ar.edu.utn.frba.dds.climAlert.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntidadTest {

    @Test
    void constructorDebeInicializarCampos() {
        Entidad entidad = new Entidad("test@example.com");

        assertNotNull(entidad.getId());
        assertEquals("test@example.com", entidad.getEmail());
        assertFalse(entidad.isEstaHabilitado());
        assertNull(entidad.getTs());
    }

    @Test
    void settersDebenActualizarValores() {
        Entidad entidad = new Entidad("test@example.com");
        entidad.setEmail("otro@example.com");
        entidad.setEstaHabilitado(true);

        assertEquals("otro@example.com", entidad.getEmail());
        assertTrue(entidad.isEstaHabilitado());
    }

    @Test
    void constructorDebeGenerarIdDistintoParaCadaInstancia() {
        Entidad e1 = new Entidad("a@a.com");
        Entidad e2 = new Entidad("b@b.com");

        assertNotEquals(e1.getId(), e2.getId());
    }
}
