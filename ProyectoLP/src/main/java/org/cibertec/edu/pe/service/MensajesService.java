package org.cibertec.edu.pe.service;


import java.util.List;

import org.cibertec.edu.pe.interfaceService.IMensajesService;
import org.cibertec.edu.pe.interfaces.IMensajes;
import org.cibertec.edu.pe.modelo.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajesService implements IMensajesService{
	
	@Autowired
	private IMensajes data;
	
	@Override
	public List<Mensajes> listar() {
		return (List<Mensajes>) data.findAll();
	}

	@Override
	public int guardar(Mensajes mensajes) {
	     Mensajes m = data.save(mensajes);
	     if(m != null) {
	    	 return 1;
	     }
	     return 0;
	}

	@Override
	public void eliminar(int id) {
	   data.deleteById(id);
	}

}
