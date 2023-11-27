package org.cibertec.edu.pe.interfaces;

import java.util.List;

import org.cibertec.edu.pe.modelo.Productos;
import org.springframework.data.repository.CrudRepository;


public interface IProductos extends CrudRepository<Productos, Integer>{
	 List<Productos> findByCategoriaIdCategoria(int idCategoria);
}
