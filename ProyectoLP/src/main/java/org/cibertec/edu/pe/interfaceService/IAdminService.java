package org.cibertec.edu.pe.interfaceService;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.modelo.Admin;


public interface IAdminService {
	
	public List<Admin> listar();
	
	public Optional<Admin>listarId(int id);
	
	public int guardar(Admin admin);
	
	public void eliminar(int id);
		
	public Admin login(String usuario, String password);
	
}
