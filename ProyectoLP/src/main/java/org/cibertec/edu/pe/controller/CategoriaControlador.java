package org.cibertec.edu.pe.controller;




import org.cibertec.edu.pe.interfaceService.ICategoriaService;

import org.cibertec.edu.pe.modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CategoriaControlador {
	
	@Autowired
	private ICategoriaService service;



	@PostMapping("/registrarCategoria")
	public String registrar(@Validated Categoria c, Model model) {
		service.guardar(c);
		return "redirect:/productos";
	}

	@GetMapping("/eliminarCategoria/{idCategoria}")
	public String eliminarCliente(@PathVariable int idCategoria, Model model, RedirectAttributes redirectAttributes) {
		
		 try {
			  service.eliminar(idCategoria);
		} catch (Exception e) {
			   // Agregar un mensaje de error al modelo para mostrarlo en la vista
			 redirectAttributes.addAttribute("errorCategoria", "No se puede eliminar la categoría debido a restricciones de integridad referencial.");
		}
		      
		    
		return "redirect:/productos";
	}

}
