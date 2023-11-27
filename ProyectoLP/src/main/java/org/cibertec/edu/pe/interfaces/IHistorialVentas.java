package org.cibertec.edu.pe.interfaces;

import java.util.List;

import org.cibertec.edu.pe.modelo.HistorialVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistorialVentas extends JpaRepository<HistorialVenta, Integer>{
	
	List<HistorialVenta> findByClientesIdCliente(int idCliente);
	
}
