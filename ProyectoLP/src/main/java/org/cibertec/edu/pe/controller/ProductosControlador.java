package org.cibertec.edu.pe.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.ICategoriaService;
import org.cibertec.edu.pe.interfaceService.IProductosService;
import org.cibertec.edu.pe.interfaceService.IProveedorService;
import org.cibertec.edu.pe.modelo.Categoria;
import org.cibertec.edu.pe.modelo.Productos;
import org.cibertec.edu.pe.modelo.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class ProductosControlador {

	@Autowired
	private IProductosService service;

	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IProveedorService proveedorService;


	// LISTARA TODOS LOS PRODUCTOS Y CATEGORIAS
	@GetMapping("/productos")
	public String listar(Model model, @RequestParam(name = "idCategoria", required = false) Integer idCategoria,
			HttpSession session) {
		obtenerProductosPorCategoria(model, idCategoria);
		session.setAttribute("idCategoria", idCategoria);
		return "productos";
	}

	@PostMapping("/agregarProducto")
	public String agregarProducto(@Validated Productos p, Model model) {
		service.guardar(p);
		return "redirect:/productos";
	}

	@GetMapping("/editarProducto/{id}")
	@ResponseBody
	public ResponseEntity<Productos> editar(@PathVariable int id) {
		Optional<Productos> productoOptional = service.listarId(id);
		if (productoOptional.isPresent()) {
			Productos productos = productoOptional.get();
			return new ResponseEntity<>(productos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/eliminarProducto/{id}")
	public ResponseEntity<String> eliminarProducto(@PathVariable int id, Model model) {
	
		try {
			service.eliminar(id);
			return ResponseEntity.ok("Producto Eliminado Correctamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("HUBO UN ERROR");
		}
	}
	
	
	
	// VISTA DE LA PAGINA PRODUCTOS
	@GetMapping("/tienda/Productos")
	public String paginaProductos(Model model, @RequestParam(name = "idCategoria", required = false) Integer idCategoria, HttpSession session) {
		obtenerProductosPorCategoria(model, idCategoria);
		session.setAttribute("idCategoria", idCategoria);
		return "tiendaPro";
	}
	
	
	//CREO ESTE METODO VOID PORQUE VAMOS A REUTILIZAR EL CODIGO PARA 2 PAGINAS DIFERENTES
	private void obtenerProductosPorCategoria(Model model, Integer idCategoria) {
		List<Categoria> categorias = categoriaService.listar();
		List<Proveedor> proveedor = proveedorService.listar();
		List<Productos> productos;

		if (idCategoria != null) {
			// Filtrar productos por categoría si se proporciona un ID de categoría
			productos = service.listarPorCategoria(idCategoria);
		} else {
			// Obtener todos los productos si no se proporciona un ID de categoría
			productos = service.listar();
		}
		
		model.addAttribute("proveedor",proveedor);
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", categorias);
	}

}
