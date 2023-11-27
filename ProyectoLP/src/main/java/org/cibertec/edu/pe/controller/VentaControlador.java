package org.cibertec.edu.pe.controller;


import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.ICarritoService;
import org.cibertec.edu.pe.interfaceService.IDescuentosService;
import org.cibertec.edu.pe.interfaceService.IProductosService;
import org.cibertec.edu.pe.interfaceService.IVentaService;

import org.cibertec.edu.pe.modelo.Carrito;
import org.cibertec.edu.pe.modelo.Clientes;
import org.cibertec.edu.pe.modelo.Productos;
import org.cibertec.edu.pe.modelo.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class VentaControlador {

	@Autowired
	private IVentaService service;

	@Autowired
	private ICarritoService carritoService;
	
	@Autowired
	private IProductosService productoService;
	
	@Autowired
	private IDescuentosService descuentoService;
	
	@GetMapping("/tienda/venta")
	public String listar(Model model, HttpSession session) {
		Integer usuarioId = (Integer) session.getAttribute("usuarioId");
		List<Venta> lista = service.listar(usuarioId);
		model.addAttribute("venta", lista);

		return "venta";
	}

	@PostMapping("/venta")
	public String guardar(Model model, HttpSession session) {
	    Integer usuarioId = (Integer) session.getAttribute("usuarioId");
	    @SuppressWarnings("unchecked")
		List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");

	    for (Carrito item : carrito) {
	        Venta venta = new Venta();
	        venta.setClientes(new Clientes());
	        venta.getClientes().setIdCliente(usuarioId);
	        venta.setProductos(item.getProductos());
	        venta.setCantidad(item.getCantidad());
	        venta.setPrecioTotal(item.getPrecioTotal());
	        venta.setFechaVenta(new Timestamp(System.currentTimeMillis()));
	        
	        //CALCULO TRANSICIONAL PARA DISMINUIR LA CANTIDAD DE LOS PRODUCTOS
	        List<Productos> producto = productoService.listar();
	        for(Productos p : producto) {
	        	
	        	if(p.getIdProducto() == venta.getProductos().getIdProducto()) {
	        		 int cantidad = p.getCantidad() - venta.getCantidad();
	        		 p.setCantidad(cantidad);
	        		 productoService.guardar(p);
	        	}
	        	
	        }
  
	        int resultado = service.guardar(venta);

	        if (resultado != 1) {
	            //La venta no se pudo completar
	            model.addAttribute("error", "No se pudo completar la venta");            
	        }
	    }
	    
	    //Limpiar Carrito
	    carritoService.eliminarIdCliente(usuarioId);
	    session.setAttribute("contadorCarrito", 0);
	    session.setAttribute("totalCarrito", 0);
	    //Eliminar cupon descuento en caso se aplico a la compra
	    Integer cuponCodigo = (Integer) session.getAttribute("cuponCodigo");
	    descuentoService.eliminar(cuponCodigo);
	    
	    
	    return "redirect:/tienda/venta"; 
	}




}
