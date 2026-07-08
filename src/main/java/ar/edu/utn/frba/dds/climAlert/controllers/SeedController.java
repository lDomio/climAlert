package ar.edu.utn.frba.dds.climAlert.controllers;

import ar.edu.utn.frba.dds.climAlert.domain.Alerta;
import ar.edu.utn.frba.dds.climAlert.services.AlertaService;
import ar.edu.utn.frba.dds.climAlert.services.EntidadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SeedController {
    private final AlertaService alertaService;
    private final EntidadService entidadService;

    public SeedController(AlertaService alertaService, EntidadService entidadService) {
        this.alertaService = alertaService;
        this.entidadService = entidadService;
    }

    @PostMapping("/seed")
    public String seed() {
        alertaService.crearAlerta(new Alerta(35, 60, new Date()));

        entidadService.crearEntidad("admin@clima.com");
        entidadService.crearEntidad("emergencias@clima.com");
        entidadService.crearEntidad("meteorologia@clima.com");
        entidadService.crearEntidad("ldomio@frba.utn.edu.ar");

        return "Seed ejecutado correctamente";
    }
}
