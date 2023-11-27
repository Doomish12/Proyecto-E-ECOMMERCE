package org.cibertec.edu.pe.interfaceService;

import java.util.List;

import org.cibertec.edu.pe.modelo.Categoria;

public interface ICategoriaService {
 
	public List<Categoria> listar();
	
	public int guardar(Categoria categoria);	
	
	public void eliminar(int id);
}
