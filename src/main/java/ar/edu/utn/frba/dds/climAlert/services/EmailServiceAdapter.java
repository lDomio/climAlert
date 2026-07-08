package ar.edu.utn.frba.dds.climAlert.services;

public interface EmailServiceAdapter {
    void enviarMail(String destinatario, String asunto, String cuerpo);
}
