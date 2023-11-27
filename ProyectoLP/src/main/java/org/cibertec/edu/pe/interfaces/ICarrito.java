package org.cibertec.edu.pe.interfaces;

import java.util.List;

import org.cibertec.edu.pe.modelo.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarrito extends JpaRepository<Carrito, Integer>{
	
	//Obtenemos el idCliente para listar solo en el carrito  por idCliente
	List<Carrito> findByClientesIdCliente(int idCliente);
	
	void deleteByClientesIdCliente(int idCliente);
	
	
}
