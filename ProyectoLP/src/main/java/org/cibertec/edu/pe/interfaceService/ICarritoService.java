package org.cibertec.edu.pe.interfaceService;

import java.util.List;
import org.cibertec.edu.pe.modelo.Carrito;

public interface ICarritoService {
	
	public List<Carrito> listarPorId(int id);
		
	public int guardar(Carrito carrito);
	
	public void aumentarCantidad(int id);
	
	public void disminuirCantidad(int id);
	
	public void eliminar(int id);
	
	public void eliminarIdCliente(int idCliente);
}
