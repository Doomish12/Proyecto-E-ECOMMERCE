package org.cibertec.edu.pe.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.IEmailService;
import org.cibertec.edu.pe.interfaceService.IMensajesService;

import org.cibertec.edu.pe.modelo.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class MensajesControlador {

	@Autowired
	private IMensajesService service;
	
	@Autowired
	private IEmailService emailService;
	
	@GetMapping("/mensajes")
	public String listar(Model model, HttpSession session) {
		List<Mensajes> lista = service.listar();
		Collections.reverse(lista);
		model.addAttribute("mensaje", lista);
		//CONTADOR MENSAJES
		int contadorMensajes = lista.size();
		session.setAttribute("contadorMensaje", contadorMensajes);

		return "mensajes";
	}

	@GetMapping("/tienda/contacto")
	public String contacto() {

		return "/contacto";
	}

	@PostMapping("/mensajeNuevo")
	public String save(@Validated Mensajes m, Model model) {
		service.guardar(m);
		emailService.enviarEmail(m);
		return "redirect:/tienda/contacto";
	}

	@PostMapping("/eliminarMensaje")
	public String eliminar(@RequestParam("id") int id, Model model) {
		service.eliminar(id);
		return "redirect:/mensajes";
	}

}
