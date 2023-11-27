package org.cibertec.edu.pe.interfaceService;

import java.util.List;

import org.cibertec.edu.pe.modelo.ListaDeseos;

public interface IListaDeseosService {

	public List<ListaDeseos> listar(int idUsuario);
	
	public int guardarDeseo(ListaDeseos listaDeseos);
		
	public void eliminar(int id);
	

}
