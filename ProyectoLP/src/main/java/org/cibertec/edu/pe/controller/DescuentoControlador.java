package org.cibertec.edu.pe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.ICarritoService;
import org.cibertec.edu.pe.interfaceService.IDescuentosService;
import org.cibertec.edu.pe.modelo.Carrito;
import org.cibertec.edu.pe.modelo.Descuento;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DescuentoControlador {

	@Autowired
	private IDescuentosService descuentosService;
	
	@Autowired
	private ICarritoService carritoService;
	

	@PostMapping("/aplicarDescuento")
	public String aplicarDescuento(@RequestParam String codigoNombre, HttpSession session, Model model) {
		Integer usuario = (Integer) session.getAttribute("usuarioId");

		Descuento descuento = descuentosService.aplicarDescuento(codigoNombre);
		double descuentoCarrito = 0.0;
		double totalCarrito = 0.0;
		 double precioTotal = 0.0;
		if(descuento != null) {
			List<Carrito> listar = carritoService.listarPorId(usuario);
			
			for(Carrito c : listar) {
			     precioTotal+= c.getPrecioTotal();
				 descuentoCarrito = descuento.getPorcentajeDescuento() / 100 * precioTotal;
				 totalCarrito = precioTotal - descuentoCarrito;		 
			}
			
			session.setAttribute("cuponCodigo", descuento.getIdDescuento());
			session.setAttribute("descuentoCarrito", descuentoCarrito);
			session.setAttribute("carTotal", totalCarrito);
			session.setAttribute("cuponValido", "Cupon aplicado");
			session.removeAttribute("cuponNoValido");
		
			
		}else {
			session.setAttribute("cuponNoValido", "Cupon No valido");
			session.removeAttribute("cuponValido");
			session.removeAttribute("descuentoAplicado");
		}
		
		
		return "redirect:/tienda/carrito";
		
	}

}
