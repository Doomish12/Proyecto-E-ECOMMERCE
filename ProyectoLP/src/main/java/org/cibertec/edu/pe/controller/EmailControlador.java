package org.cibertec.edu.pe.controller;


import org.cibertec.edu.pe.modelo.Mensajes;
import org.cibertec.edu.pe.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailControlador {
		
	    @Autowired
	 	private EmailService emailService;

	
	    @GetMapping("/email-form")
	    public String showEmailForm(Model model) {
	        return "email";
	    }

	    @GetMapping("/email-enviado")
	    public String vista(Model model) {
	        return "enviado";
	    }
	    
	    @PostMapping("/enviar-email")
	    public String enviarEmail(Mensajes mensajes) {
	         emailService.enviarEmail(mensajes);
	        return "redirect:/email-enviado";
	    }
	    
}
