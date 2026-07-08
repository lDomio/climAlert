package ar.edu.utn.frba.dds.climAlert.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AlertaTest {

    @Test
    void constructorDebeInicializarCampos() {
        Date now = new Date();
        Alerta alerta = new Alerta(35, 80, now);

        assertNotNull(alerta.getId());
        assertEquals(35, alerta.getRiskTemperature());
        assertEquals(80, alerta.getRiskHumidity());
        assertEquals(now, alerta.getTs());
        assertFalse(alerta.isDeleted());
    }

    @Test
    void isAlertClimateDebeRetornarFalseCuandoClimaActivoYNoSuperaLimites() {
        Alerta alerta = new Alerta(35, 80, new Date());
        Clima clima = new Clima(new Date(), 30, 70, true);

        assertFalse(alerta.isAlertClimate(clima));
    }

    @Test
    void isAlertClimateDebeRetornarFalseCuandoClimaInactivo() {
        Alerta alerta = new Alerta(35, 80, new Date());
        Clima clima = new Clima(new Date(), 30, 70, false);

        assertFalse(alerta.isAlertClimate(clima));
    }

    @Test
    void isAlertClimateDebeRetornarFalseCuandoSoloTemperaturaSuperaLimite() {
        Alerta alerta = new Alerta(35, 80, new Date());
        Clima clima = new Clima(new Date(), 40, 70, true);

        assertFalse(alerta.isAlertClimate(clima));
    }

    @Test
    void isAlertClimateDebeRetornarFalseCuandoSoloHumedadSuperaLimite() {
        Alerta alerta = new Alerta(35, 80, new Date());
        Clima clima = new Clima(new Date(), 30, 90, true);

        assertFalse(alerta.isAlertClimate(clima));
    }

    @Test
    void isAlertClimateDebeRetornarTrueCuandoAmbosSuperanLimites() {
        Alerta alerta = new Alerta(35, 80, new Date());
        Clima clima = new Clima(new Date(), 40, 90, true);

        assertTrue(alerta.isAlertClimate(clima));
    }

    @Test
    void isAlertClimateDebeRetornarFalseCuandoValoresIgualesALimites() {
        Alerta alerta = new Alerta(35, 80, new Date());
        Clima clima = new Clima(new Date(), 35, 80, true);

        assertFalse(alerta.isAlertClimate(clima));
    }
}
