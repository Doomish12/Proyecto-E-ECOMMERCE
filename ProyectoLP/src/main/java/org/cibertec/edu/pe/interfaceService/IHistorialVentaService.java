package org.cibertec.edu.pe.interfaceService;

import java.util.List;

import org.cibertec.edu.pe.modelo.HistorialVenta;

public interface IHistorialVentaService {

	public List<HistorialVenta> listarHis();
	
	public List<HistorialVenta> listar(int idCliente);
	
	public int guardar(HistorialVenta historialVenta);
	
	
}
