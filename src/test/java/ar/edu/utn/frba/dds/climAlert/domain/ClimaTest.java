package ar.edu.utn.frba.dds.climAlert.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClimaTest {

    @Test
    void constructorDebeInicializarCampos() {
        Date now = new Date();
        Clima clima = new Clima(now, 30, 70, true);

        assertNotNull(clima.getId());
        assertEquals(now, clima.getDate());
        assertEquals(30, clima.getTemperature());
        assertEquals(70, clima.getHumidity());
        assertTrue(clima.isActivo());
        assertFalse(clima.isDeleted());
    }

    @Test
    void constructorDebeGenerarIdDistintoParaCadaInstancia() {
        Clima c1 = new Clima(new Date(), 25, 50, true);
        Clima c2 = new Clima(new Date(), 25, 50, true);

        assertNotEquals(c1.getId(), c2.getId());
    }

    @Test
    void settersDebenActualizarValores() {
        Clima clima = new Clima(new Date(), 20, 40, true);
        clima.setTemperature(35);
        clima.setHumidity(80);
        clima.setActivo(false);
        clima.setDeleted(true);

        assertEquals(35, clima.getTemperature());
        assertEquals(80, clima.getHumidity());
        assertFalse(clima.isActivo());
        assertTrue(clima.isDeleted());
    }
}
