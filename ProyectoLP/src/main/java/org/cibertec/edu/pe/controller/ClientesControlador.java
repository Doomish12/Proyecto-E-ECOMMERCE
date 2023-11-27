package org.cibertec.edu.pe.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.interfaceService.ICarritoService;
import org.cibertec.edu.pe.interfaceService.IClientesService;
import org.cibertec.edu.pe.interfaceService.IHistorialVentaService;
import org.cibertec.edu.pe.interfaceService.IListaDeseosService;
import org.cibertec.edu.pe.interfaceService.IMensajesService;
import org.cibertec.edu.pe.modelo.Carrito;
import org.cibertec.edu.pe.modelo.Clientes;
import org.cibertec.edu.pe.modelo.HistorialVenta;
import org.cibertec.edu.pe.modelo.ListaDeseos;
import org.cibertec.edu.pe.modelo.Mensajes;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ClientesControlador {

	@Autowired
	private IClientesService service;
	
	@Autowired
	private IMensajesService mensaje;
	
	@Autowired
	private ICarritoService carritoService;
	
	@Autowired
	private CarritoControlador carrito;
	
	@Autowired
	private IListaDeseosService listaDeseo;
	
	@Autowired
	private IHistorialVentaService historial;
	
	
	//PARA EL ADMIN PANEL
	@GetMapping("/clientes")
	public String listar(Model model, HttpSession session) {
		List<Clientes> clientes = service.listar();
		model.addAttribute("clientes", clientes);
		//Contador para clientes
		int contadorCliente = clientes.size();
		session.setAttribute("contarCliente", contadorCliente);
	
		//SUMA DE TOTAL DE VENTAS
		List<HistorialVenta> historialVentas = historial.listarHis();
		double sumaVentas = 0.0;
		for (HistorialVenta h : historialVentas) {
			sumaVentas+= h.getPrecioTotal();
		}
		
		session.setAttribute("sumaVentas", sumaVentas);
		
		//LLAMO AL LISTADO AQUI PARA PODER MOSTRAR Y COMENZAR LA SESSION DESDE AQUI EL CONTADOR DE MENSAJES
		List<Mensajes> mensajes = mensaje.listar();
		
		int contadorMensajes = mensajes.size();
		session.setAttribute("contadorMensaje", contadorMensajes);
		return "clientes";
	}

	@PostMapping("/agregarCliente")
	public String guardar(@Validated Clientes c, Model model) {
		service.guardar(c);
		return "redirect:/clientes";
	}

	@GetMapping("/editarCliente/{id}")
	@ResponseBody
	public ResponseEntity<Clientes> editarCliente(@PathVariable int id) {
		Optional<Clientes> clientesOptional = service.listarId(id);

		if (clientesOptional.isPresent()) {
			Clientes clientes = clientesOptional.get();
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/eliminarCliente/{id}")
	public String eliminarCliente(@PathVariable int id, Model model){
		service.eliminar(id);
		return "redirect:/clientes";
	} 
	//*****************************************************************************************
	
	//PAGINA LOGIN
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	//PAGINA TIENDA PRINCIPAL
	@GetMapping("/tienda")
	public String tienda(Model model, HttpSession session) {
		//PARA LA IMG de usuario para actualizar la ruta /
	   model.addAttribute("paginaPrincipal", true);
		return "tienda";
	}	
	
	
	//LOGIN PARA USUARIOS
	@PostMapping("/loginUsuario")
	public String login(String user, String contrasenia, Model model, HttpSession session) {
		
		Clientes clientes = service.login(user, contrasenia);


		if (clientes != null) {
			// Autenticación exitosa, redirigir a la página de inicio del usuario
			model.addAttribute("nombreUsuario", user);
			
			session.setAttribute("nombreUsuario", user);
			session.setAttribute("usuarioId", clientes.getIdCliente());

			carrito.sessionCarrito(session);
			return "redirect:/tienda";
		}
		else {
			// Autenticación fallida, mostrar un mensaje de error
			model.addAttribute("mensajeErrorLogin", "Error: Usuario/Contraseña incorrectos");
			return "login";
		}
	
	}
	
	//USUARIO CERRAR SESSION ACABA TODAS LAS SESSIONES EXISTENTE  DEL USUARIO
	
	@GetMapping("/cerrarSesionUsuario")
	public String cerraSession(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	//Para registrar usuario
	@PostMapping("/registrarCliente")
	public String registrar(@Validated Clientes c, Model model) {	
			service.guardar(c);
		return "login";
	}
	
	//VALIDAR EL USUARIO Y CORREO
	@GetMapping("/verificarUsuario")
	@ResponseBody
	public String verificarUsuario(@RequestParam("usuario") String usuario, @RequestParam("correo") String correo) {
	    List<Clientes> usuariosRegistrados = service.listar();
	    for (Clientes cliente : usuariosRegistrados) {
	        if (cliente.getUsuario().equals(usuario)) {
	            return "usuarioExiste";
	        }
	        else if(cliente.getCorreo().equals(correo)) {
	        	return "correoExiste";
	        }
	    }
	    return "no_existe";
	}

	
	
	//PARA MOSTRAR LOS DATOS DEL USUARIO EN LA PAGINA PERFIL DEL USUARIO
	@PostMapping("/tienda/perfil")
	public String editarPerfilUsuario(@RequestParam("idUsuario") int idUsuario, Model model) {
		Optional<Clientes> clientes = service.listarId(idUsuario);
		List<ListaDeseos> deseos = listaDeseo.listar(idUsuario);
		List<HistorialVenta> historialVe = historial.listar(idUsuario);
		model.addAttribute("editarPerfil", clientes);
		model.addAttribute("listaDeseo", deseos);
		model.addAttribute("historialVe", historialVe);
		return "perfil";
	}
	

	//PARA ACTUALIZAR PERFIL DEL USUARIO
	@PostMapping("/actualizarPerfil")
	public String actualizarPerfil(@Validated Clientes c, Model model) {
		service.guardar(c);
		return "redirect:/tienda";
	}
	
	//INSERTAR PRODUCTO A LISTA DE DESEO
	@PostMapping("/insertarListaDeseo")
	public ResponseEntity<?>  guardar(@Validated ListaDeseos l, Model model, HttpSession session) {		
		try {
			Integer usuario = (Integer) session.getAttribute("usuarioId");
			
			List<ListaDeseos> deseo = listaDeseo.listar(usuario);
			//Verficar si el producto ya esta en la listaDeseo
			boolean productoLista = false;
			for(ListaDeseos item : deseo) {
				if(item.getProductos().getIdProducto() == l.getProductos().getIdProducto()) {				
					productoLista = true;
				}
				
			}				
	        if (!productoLista) {
	            listaDeseo.guardarDeseo(l);
	            List<ListaDeseos> deseoC = listaDeseo.listar(usuario);	
   				int contador = deseoC.size();
   				session.setAttribute("contadorDeseo", contador);
				return ResponseEntity.ok(contador);
	        }
	        else {
	    		return ResponseEntity.ok("ProductoExiste");
	        }
	            
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	//PARA ELIMINAR UN PRODUCTO DE LA LISTA DE DESEO
	@DeleteMapping("/eliminarDeseo/{id}")
	public ResponseEntity<Integer> eliminarProducto(@PathVariable int id, HttpSession session) {

		try {
			listaDeseo.eliminar(id);
			//OBTEMOS LA LISTA POR EL IDUSUARIO PARA CONTAR LOS PRODUCTOS DE LA LISTA DESEO
			Integer usuario = (Integer) session.getAttribute("usuarioId");
			List<ListaDeseos> deseoC = listaDeseo.listar(usuario);	
			int contador = deseoC.size();
			session.setAttribute("contadorDeseo", contador);
			return ResponseEntity.ok(contador);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
	
	//INSETAR PRODUCTO DESDE LA LISTA DE DESO AL CARRITO Y ELIMINAR EL PRODUCTO DE LA LISTA DE DESEO
	@PostMapping("/insertarCarritoDeseo")
	public ResponseEntity<?> guardar(@Validated Carrito c, Model model, HttpSession session) {		
		try {
			Integer usuario = (Integer) session.getAttribute("usuarioId");
			
			List<Carrito> carrito = carritoService.listarPorId(usuario);
			//Verficar si el producto ya esta en el carrito
			boolean productoCarrito = false;
			for(Carrito item : carrito) {
				if(item.getProductos().getIdProducto() == c.getProductos().getIdProducto()) {
				
					item.setCantidad(item.getCantidad()  + 1);
					item.setPrecioTotal(item.getCantidad() * item.getProductos().getPrecio());
					carritoService.guardar(item);
					productoCarrito = true;
			
				}
				
			}	
			
	        if (!productoCarrito) {
	            carritoService.guardar(c);
	            
	         	List<Carrito> listar = carritoService.listarPorId(usuario);	
	          	List<ListaDeseos> deseoC = listaDeseo.listar(usuario);
	            
   				//RECOLECTAR VALORES
	          	double contadorDeseo = deseoC.size();
   				double contador = listar.size();
   				double totalCarrito = this.carrito.calcularNuevoTotalCarrito(session);
   				List<Double> valores = new ArrayList<>();
   				valores.add(contador);
   				valores.add(totalCarrito);
   				valores.add(contadorDeseo);		
   				
   				//CREAR SESSION DE CARRITO PARA VISUALIZAR EL CONTADOR ACUTALIZADO DEL CARRITO
   				session.setAttribute("contadorCarrito", listar.size());
				return ResponseEntity.ok(valores);
	        }
	        else {
	            double totalCarrito = this.carrito.calcularNuevoTotalCarrito(session);
	            List<Double> responseList = new ArrayList<>();
	            responseList.add(totalCarrito);
	            return ResponseEntity.ok(responseList);
	        }
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}
	
	

}
