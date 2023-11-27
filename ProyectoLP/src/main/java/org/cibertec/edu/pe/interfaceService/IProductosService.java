package org.cibertec.edu.pe.interfaceService;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.modelo.Productos;

public interface IProductosService {
	
	public List<Productos> listar();
	
	public List<Productos> listarPorCategoria(int idCategoria);
	
	public Optional<Productos> listarId(int id);
	
	public int guardar(Productos productos);
	
	public void eliminar(int id);
	
	
	
	
}
