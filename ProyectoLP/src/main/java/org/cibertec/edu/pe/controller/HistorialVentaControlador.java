package org.cibertec.edu.pe.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.IHistorialVentaService;
import org.cibertec.edu.pe.interfaceService.IVentaService;
import org.cibertec.edu.pe.modelo.Clientes;
import org.cibertec.edu.pe.modelo.HistorialVenta;
import org.cibertec.edu.pe.modelo.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistorialVentaControlador {

	@Autowired
	private IVentaService service;
	
	@Autowired
	private IHistorialVentaService serviceVenta;
	
	@PostMapping("/insertarHistorialV")
	public String insertarHistorialVenta(HttpSession session) {
	    Integer usuarioId = (Integer) session.getAttribute("usuarioId");

		List<Venta> venta = service.listar(usuarioId);
		
		for(Venta  item : venta) {
			HistorialVenta historial = new HistorialVenta();
			historial.setClientes(new Clientes());
			historial.getClientes().setIdCliente(usuarioId);
			historial.setNomPro(item.getProductos().getNombre());
			historial.setCantidad(item.getCantidad());
			historial.setPrecioTotal(item.getPrecioTotal());
			historial.setFechaVenta(new Timestamp(System.currentTimeMillis()));
			historial.setEstado("Entregado");
			int resultado = serviceVenta.guardar(historial);
			
			if(resultado != 1) {
			    System.out.println("NO SE PUDO PROCESAR , ERROR");       
			}
		}
		
		//LIMPIAR TABLA DE VENTA / VISTA DEL DETALLE VENTA
		service.eliminar(usuarioId);
		
		//REINICIAR LOS VALORES DEL CARRITO LOS CALCULOS
	    session.setAttribute("descuentoCarrito", null);
	    session.setAttribute("subTotalCarrito", null);
	    session.setAttribute("carTotal", null);
	    session.removeAttribute("cuponValido");
	    session.removeAttribute("cuponNoValido");
		return  "redirect:/tienda";
	}
	
	
	
	@GetMapping("/historial")
	public String historial(Model model) {
	    List<Venta> lista = service.listarPrueba();
	    model.addAttribute("venta",lista);
	    model.addAttribute("contador",0);
		return "listadoVenta";
	}
	
	@GetMapping("/filtrarFechas")
	public String filtrar(@RequestParam String fechaInicio, @RequestParam String fechaFin ,Model model) throws ParseException  {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedFechaInicio = dateFormat.parse(fechaInicio);
        Date parsedFechaFin = dateFormat.parse(fechaFin);
        
        // Convertir las fechas a Timestamp
        Timestamp timestampInicio = new Timestamp(parsedFechaInicio.getTime());
        Timestamp timestampFin = new Timestamp(parsedFechaFin.getTime());
        
        
		List<Venta> lista = service.listarFecha(timestampInicio, timestampFin);
		model.addAttribute("venta", lista);
		

		//CONTAR CUANTAS VENTAS ENCONTRO Y MOSTRAR MENSAJE
		String mensaje = "VENTAS ENCONTRADAS";
		int contador = 0;
		for(Venta v : lista) {
			  v.getIdVenta();
			contador++;
		}
			if(contador == 0) {
			String error = "NO SE ENCONTRO NINGUNA VENTA";
			model.addAttribute("conError", 0);
			model.addAttribute("error", error);
			}
			
			model.addAttribute("contador",contador);
			model.addAttribute("mensaje", mensaje);
		
		return "listadoVenta";
	}
}
