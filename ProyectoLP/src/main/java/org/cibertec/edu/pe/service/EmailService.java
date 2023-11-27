package org.cibertec.edu.pe.service;

import org.cibertec.edu.pe.interfaceService.IEmailService;
import org.cibertec.edu.pe.modelo.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
    private  JavaMailSender javaMailSender;

    @Override
    public void enviarEmail(Mensajes mensajes) {
        SimpleMailMessage mailMensaje = new SimpleMailMessage();
        mailMensaje.setFrom("InnovaTechX <InnovaTechX@gmail.com>");
        mailMensaje.setTo("innovatechxcorp@gmail.com");
        mailMensaje.setSubject("Formulario Contacto");
        mailMensaje.setText("Nombres: \n"+ mensajes.getNombre()+"\n\n" + "Correo:\n"+ mensajes.getCorreo()+ "\n\n" + "Mensaje:\n" + mensajes.getMensaje());
        javaMailSender.send(mailMensaje);
    }
}
