package org.cibertec.edu.pe.service;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.interfaceService.IProveedorService;
import org.cibertec.edu.pe.interfaces.IProveedor;
import org.cibertec.edu.pe.modelo.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService implements IProveedorService{

	@Autowired
	private IProveedor data;

	@Override
	public List<Proveedor> listar() {
		return  data.findAll();
	}

	@Override
	public Optional<Proveedor> listarPorID(int id) {
		return data.findById(id);
	}

	@Override
	public int guardar(Proveedor proveedor) {
		
		Proveedor p = data.save(proveedor);
		if(p != null) {
			return 1;
		}	
		return 0;
	}

	@Override
	public void eliminar(int id) {
		data.deleteById(id);
		
	}
	
	
}
