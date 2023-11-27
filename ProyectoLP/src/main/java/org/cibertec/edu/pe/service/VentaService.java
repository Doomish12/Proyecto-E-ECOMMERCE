package org.cibertec.edu.pe.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.cibertec.edu.pe.interfaceService.IVentaService;

import org.cibertec.edu.pe.interfaces.IVenta;

import org.cibertec.edu.pe.modelo.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VentaService  implements IVentaService{
	
	@Autowired
	private IVenta data;
	
	
	@Override
	public List<Venta> listar(int idCliente) {
		return data.findByClientesIdCliente(idCliente);
	}

	@Override
	public int guardar(Venta venta) {

		Venta v = data.save(venta);
		if(v != null) {
			return 1;
		}	
		return 0;
	}

	
	@Override
	public void eliminar(int idCliente) {
		data.deleteByClientesIdCliente(idCliente);
	}

	//PRUEBAS
	@Override
	public List<Venta> listarFecha(Timestamp fechaInicio, Timestamp fechaFin) {
		return data.findByFechaVentaBetween(fechaInicio, fechaFin);
	}

	@Override
	public List<Venta> listarPrueba() {
		return data.findAll();
	}




	
}
