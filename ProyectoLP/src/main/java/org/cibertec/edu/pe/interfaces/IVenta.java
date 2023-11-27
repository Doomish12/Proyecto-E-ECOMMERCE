package org.cibertec.edu.pe.interfaces;

import java.sql.Timestamp;
import java.util.List;

import org.cibertec.edu.pe.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVenta extends JpaRepository<Venta, Integer>{
	
	void deleteByClientesIdCliente(int idCliente);

	//Listar por toda la venta acorde al idCliente
	List<Venta> findByClientesIdCliente(int idCliente);

	//FILTRAR POR FECHAS RANGOS
	List<Venta> findByFechaVentaBetween(Timestamp fechaInicio, Timestamp fechaFin);

}
