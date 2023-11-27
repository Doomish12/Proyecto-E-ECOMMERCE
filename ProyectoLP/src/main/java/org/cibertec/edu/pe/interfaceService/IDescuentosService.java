package org.cibertec.edu.pe.interfaceService;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.modelo.Descuento;

public interface IDescuentosService {

	public List<Descuento> listar();
	
	public Optional<Descuento> listarId(int id);
	
	public int guardarDescuento(Descuento descuento);
	
	public Descuento aplicarDescuento(String nombre);
	
	public void eliminar(int id);
	
	
}
