package org.cibertec.edu.pe.interfaceService;

import java.sql.Timestamp;
import java.util.List;

import org.cibertec.edu.pe.modelo.Venta;

public interface IVentaService {

	public List<Venta> listar(int idCliente);
	
	public int guardar(Venta venta);
		
	public void eliminar(int idCliente);
	
	//PRUEBAS
	public List<Venta> listarFecha(Timestamp fechaInicio , Timestamp fechaFin);
	
	public List<Venta> listarPrueba();
}
