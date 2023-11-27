package org.cibertec.edu.pe.interfaces;

import org.cibertec.edu.pe.modelo.Admin;
import org.springframework.data.repository.CrudRepository;



public interface IAdmin extends CrudRepository<Admin, Integer>{
	 Admin findByUsuarioAndPassword(String usuario, String password);
}
