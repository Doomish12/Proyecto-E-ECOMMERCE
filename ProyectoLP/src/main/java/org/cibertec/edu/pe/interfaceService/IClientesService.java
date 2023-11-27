package org.cibertec.edu.pe.interfaceService;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.modelo.Clientes;

public interface IClientesService {

	public List<Clientes> listar();
	
	public Optional<Clientes> listarId(int idCliente);
	
	public int guardar(Clientes clientes);
	
	public void eliminar(int id);
	
	public Clientes login(String usuario, String password);
	
}
