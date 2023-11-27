package org.cibertec.edu.pe.controller;


import java.util.*;
import javax.servlet.http.HttpSession;
import org.cibertec.edu.pe.interfaceService.IAdminService;
import org.cibertec.edu.pe.modelo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class AdminControlador {

	@Autowired
	private IAdminService service;

	@GetMapping("/administrador")
	public String listar(Model model) {
		List<Admin> admin = service.listar();
		model.addAttribute("admin", admin);
		return "admin";
	}

	@PostMapping("/agregar")
	public String agregarAdmin(@Validated Admin admin, Model model) {
		service.guardar(admin);
		return "redirect:/administrador";
	}


	@GetMapping("/editar/{id}")
	@ResponseBody
	public ResponseEntity<Admin> editar(@PathVariable int id) {
		Optional<Admin> adminOptional = service.listarId(id);
		if (adminOptional.isPresent()) {
			Admin admin = adminOptional.get();
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarAdmin(@PathVariable int id, Model model) {
		service.eliminar(id);
		return "redirect:/administrador";
	}
	
	@GetMapping("/adminLogin")
	public String adminLogin() {
		return "adminLogin";
	}
	
	//LOGIN PARA ADMIN
	@PostMapping("/login")
	public String login(String usuario, String password, Model model, HttpSession session) {
		
		Admin admin = service.login(usuario, password);
		if (admin != null) {
			// Autenticación exitosa, redirigir a la página de inicio del usuario
			model.addAttribute("nombreAdmin", usuario);
			
			session.setAttribute("nombreAdmin", usuario);
			session.setAttribute("adminId", admin.getIdAdmin());
			session.setAttribute("fotoAdmin", admin.getFoto());
			return "redirect:/clientes";
		} else {
			// Autenticación fallida, mostrar un mensaje de error
			model.addAttribute("mensajeErrorLogin", "Error: Usuario/Contraseña incorrectos");
			return "adminLogin";
		}
	}
	
	//ADMIN PANEL ACABAR CERRAR SESSION ACABA TODAS LAS SESSIONES EXISTENTE  DEL ADMIN PANEL
	
	@GetMapping("/cerrarSesion")
	public String cerraSession(HttpSession session) {
		session.invalidate();
		return "redirect:/adminLogin";
	}
	
}
