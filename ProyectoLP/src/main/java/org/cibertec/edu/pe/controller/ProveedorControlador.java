package org.cibertec.edu.pe.controller;


import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.interfaceService.IProveedorService;
import org.cibertec.edu.pe.modelo.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ProveedorControlador {

	@Autowired
	private IProveedorService service;

	@GetMapping("/proveedor")
	public String listarProveedor(Model model) {
		List<Proveedor> lista = service.listar();
		model.addAttribute("proveedor", lista);
		return "proveedor";

	}

	@PostMapping("/agregarProveedor")
	public String guardarProveedor(@Validated Proveedor p, Model model) {
		service.guardar(p);
		return "redirect:/proveedor";
	}

	@GetMapping("/editarProveedor/{idProveedor}")
	@ResponseBody
	public ResponseEntity<Proveedor> editarProveedor(@PathVariable int idProveedor) {
		Optional<Proveedor> optionalProveedor = service.listarPorID(idProveedor);

		if (optionalProveedor.isPresent()) {
			Proveedor p = optionalProveedor.get();
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	 @DeleteMapping("/eliminarProveedor/{idProveedor}")
	    public ResponseEntity<String> eliminarProveedor(@PathVariable int idProveedor) {
	        try {
	            service.eliminar(idProveedor);
	            return ResponseEntity.ok("Proveedor eliminado con éxito");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el proveedor");
	        }
	    }

}
