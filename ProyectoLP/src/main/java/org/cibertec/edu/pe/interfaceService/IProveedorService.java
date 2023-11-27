package org.cibertec.edu.pe.interfaceService;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.modelo.Proveedor;

public interface IProveedorService {

	public List<Proveedor> listar();
	
	public Optional<Proveedor> listarPorID(int id);
	
	public int guardar(Proveedor proveedor);
	
	public void eliminar(int id);
}
