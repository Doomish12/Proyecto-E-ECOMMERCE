package org.cibertec.edu.pe.service;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.interfaceService.IProductosService;
import org.cibertec.edu.pe.interfaces.IProductos;
import org.cibertec.edu.pe.modelo.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductosService implements IProductosService{
	
	@Autowired
	private IProductos data;
	
	@Override
	public List<Productos> listar() {
		return (List<Productos>) data.findAll();
	}

	@Override
    public List<Productos> listarPorCategoria(int idCategoria) {
        return data.findByCategoriaIdCategoria(idCategoria);
    }
	
	@Override
	public Optional<Productos> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardar(Productos productos) {
		Productos p = data.save(productos);
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
