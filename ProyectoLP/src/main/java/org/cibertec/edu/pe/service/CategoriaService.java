package org.cibertec.edu.pe.service;

import java.util.List;

import org.cibertec.edu.pe.interfaceService.ICategoriaService;
import org.cibertec.edu.pe.interfaces.ICategoria;
import org.cibertec.edu.pe.modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService  implements ICategoriaService{

	@Autowired
	private ICategoria data;
	
	@Override
	public List<Categoria> listar() {
		return  data.findAll();
	}

	@Override
	public int guardar(Categoria categoria) {
		  Categoria  c = data.save(categoria);
		  	if(c !=null) {
		  		return 1;
		  	}
		  
		return 0;
	}

	@Override
	public void eliminar(int id) {
		 data.deleteById(id);
	}

}
