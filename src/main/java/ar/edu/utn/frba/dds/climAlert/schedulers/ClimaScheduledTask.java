package ar.edu.utn.frba.dds.climAlert.schedulers;

import ar.edu.utn.frba.dds.climAlert.services.ClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduledTask {
    private final ClimaService climaService;

    public ClimaScheduledTask(ClimaService climaService) {
        this.climaService = climaService;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void procesarClimaActual() {
        climaService.procesarDatosClimaActual();
    }
}
