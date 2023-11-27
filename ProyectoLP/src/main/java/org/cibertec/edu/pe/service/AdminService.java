package org.cibertec.edu.pe.service;


import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.interfaceService.IAdminService;
import org.cibertec.edu.pe.interfaces.IAdmin;
import org.cibertec.edu.pe.modelo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService implements IAdminService {

	@Autowired
	private IAdmin data;

	@Override
	public List<Admin> listar() {
		return (List<Admin>) data.findAll();
	}

	@Override
	public Optional<Admin> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardar(Admin admin) {
		
		  Admin a = data.save(admin);
	        if (a != null) {
	            return 1;
	        }
	        return 0;
	}

	@Override
	public void eliminar(int id) {
		data.deleteById(id);

	}

	@Override
    public Admin login(String usuario, String password) {
		return data.findByUsuarioAndPassword(usuario, password);
    }

}
