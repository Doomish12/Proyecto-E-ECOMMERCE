package org.cibertec.edu.pe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.ICarritoService;
import org.cibertec.edu.pe.interfaceService.IListaDeseosService;
import org.cibertec.edu.pe.modelo.Carrito;
import org.cibertec.edu.pe.modelo.ListaDeseos;
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


@Controller
public class CarritoControlador {

	@Autowired
	private ICarritoService service;
	
	@Autowired
	private IListaDeseosService listaDeseo;
	

	@GetMapping("/tienda/carrito")
	public String listar(Model model, HttpSession session) {
		Integer usuario = (Integer) session.getAttribute("usuarioId");
		List<Carrito> listar = service.listarPorId(usuario);
		model.addAttribute("carrito", listar);
		session.setAttribute("carrito", listar);
		sessionCarrito(session);
		return "carrito";
	}

	@PostMapping("/insertarCarrito")
	public ResponseEntity<?>  guardar(@Validated Carrito c, Model model, HttpSession session) {		
		try {
			Integer usuario = (Integer) session.getAttribute("usuarioId");
			
			List<Carrito> carrito = service.listarPorId(usuario);
			//Verficar si el producto ya esta en el carrito
			boolean productoCarrito = false;
			for(Carrito item : carrito) {
				if(item.getProductos().getIdProducto() == c.getProductos().getIdProducto()) {
					
					item.setCantidad(item.getCantidad()  + 1);
					item.setPrecioTotal(item.getCantidad() * item.getProductos().getPrecio());
					service.guardar(item);
					productoCarrito = true;
			
				}
				
			}	
			
	        if (!productoCarrito) {
	            service.guardar(c);
	          	List<Carrito> listar = service.listarPorId(usuario);	
   				double contador = listar.size();
   				double totalCarrito = calcularNuevoTotalCarrito(session);
   				List<Double> valores = new ArrayList<>();
   				valores.add(contador);
   				valores.add(totalCarrito);
   				session.setAttribute("contadorCarrito", listar.size());
				return ResponseEntity.ok(valores);
	        }
	        else {
	            double totalCarrito = calcularNuevoTotalCarrito(session);
	            List<Double> responseList = new ArrayList<>();
	            responseList.add(totalCarrito);
	            return ResponseEntity.ok(responseList);
	        }
	        
     
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}

	@GetMapping("/aumentarCantidad/{id}")
	public String aumentarCantidad(@PathVariable("id") int id, Model model) {
		service.aumentarCantidad(id);
		return "redirect:/tienda/carrito";
	}

	@GetMapping("/disminuirCantidad/{id}")
	public String disminuirCantidad(@PathVariable("id") int id, Model model) {
		service.disminuirCantidad(id);
		return "redirect:/tienda/carrito";
	}

	@DeleteMapping("/eliminarCarrito/{id}")
	public ResponseEntity<Double> eliminarProducto(@PathVariable int id, Model model, HttpSession session) {

		try {
			service.eliminar(id);
			sessionCarrito(session);
			double nuevoTotal = calcularNuevoTotalCarrito(session);
			return ResponseEntity.ok(nuevoTotal);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1.0);
		}
	}

	public double calcularNuevoTotalCarrito(HttpSession session) {
		Integer usuario = (Integer) session.getAttribute("usuarioId");
		if (usuario != null) {
			List<Carrito> listar = service.listarPorId(usuario);
			double totalCarrito = totalCarrito(listar);
			session.setAttribute("totalCarrito", totalCarrito);
			return totalCarrito;
		} else {
			return 0;
		}
	}

	// METODOS PARA EL CARRITO CALCULOS

	// TOTAL PRODUCTOS EN CARRITO
	private double totalCarrito(List<Carrito> carrito) {
		double total = 0;

		for (Carrito c : carrito) {
			total += c.getPrecioTotal();
		}
		return total;
	}

	// SESSION CARRITO CONTADOR PRODUCTOS , SUMA DE TOTAL DEL CARRITO Y CONTADOR LISTA DE DESEO 
	public void sessionCarrito(HttpSession session) {

		Integer usuario = (Integer) session.getAttribute("usuarioId");
		if (usuario != null) {
			List<Carrito> listar = service.listarPorId(usuario);
			List<ListaDeseos> deseo = listaDeseo.listar(usuario);
			session.setAttribute("contadorDeseo", deseo.size());
			session.setAttribute("contadorCarrito", listar.size());
			session.setAttribute("subTotalCarrito", totalCarrito(listar));
			session.setAttribute("totalCarrito", totalCarrito(listar));
		} else {
			System.out.println("NO HAY CUENTA  , HUBO UN ERROR");
		}

	}

}
