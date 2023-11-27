package org.cibertec.edu.pe.service;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.interfaceService.IClientesService;
import org.cibertec.edu.pe.interfaces.IClientes;
import org.cibertec.edu.pe.modelo.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService implements IClientesService{
	
	@Autowired
	private IClientes data;
	
	
	@Override
	public List<Clientes> listar() {
		return  (List<Clientes>) data.findAll();
	}

	@Override
	public Optional<Clientes> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardar(Clientes clientes) {
		
		Clientes c = data.save(clientes);
		if(c != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public void eliminar(int id) {
		 data.deleteById(id);		
	}

	@Override
	public Clientes login(String usuario, String password) {
		return data.findByUsuarioAndPassword(usuario, password);
	}

}
