package org.cibertec.edu.pe.service;

import java.util.List;

import org.cibertec.edu.pe.interfaceService.IHistorialVentaService;
import org.cibertec.edu.pe.interfaces.IHistorialVentas;
import org.cibertec.edu.pe.modelo.HistorialVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialVentaService implements IHistorialVentaService{

	@Autowired
	private IHistorialVentas data;
	
	@Override
	public List<HistorialVenta> listar(int idCliente) {
		return  data.findByClientesIdCliente(idCliente);
	}

	@Override
	public int guardar(HistorialVenta historialVenta) {
		HistorialVenta h = data.save(historialVenta);
			
			if(h != null) {
				return 1;
			}
			
		return 0;
	}

	@Override
	public List<HistorialVenta> listarHis() {
		return data.findAll();
	}

}
