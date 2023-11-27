package org.cibertec.edu.pe.service;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.interfaceService.IDescuentosService;
import org.cibertec.edu.pe.interfaces.IDescuentos;
import org.cibertec.edu.pe.modelo.Descuento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescuentosService implements IDescuentosService {

	@Autowired
	private IDescuentos data;

	@Override
	public List<Descuento> listar() {
		return data.findAll();
	}

	@Override
	public Optional<Descuento> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardarDescuento(Descuento descuento) {

		return 0;
	}

	@Override
	public void eliminar(int id) {
		data.deleteById(id);

	}

	@Override
	public Descuento aplicarDescuento(String codigoNombre) {	
		return  data.findByNombre(codigoNombre);
	}
}