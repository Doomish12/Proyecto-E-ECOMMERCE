package org.cibertec.edu.pe.interfaces;

import java.util.List;

import org.cibertec.edu.pe.modelo.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IListaDeseos extends JpaRepository<ListaDeseos, Integer>{
	
	List<ListaDeseos> findByClientesIdCliente(int idUsuario);
	

}
