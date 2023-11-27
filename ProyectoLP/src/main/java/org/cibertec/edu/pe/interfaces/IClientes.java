package org.cibertec.edu.pe.interfaces;


import org.cibertec.edu.pe.modelo.Clientes;
import org.springframework.data.repository.CrudRepository;



public interface IClientes extends CrudRepository<Clientes, Integer>{
	 Clientes findByUsuarioAndPassword(String usuario, String password);
}
