package org.cibertec.edu.pe.interfaceService;

import java.util.List;


import org.cibertec.edu.pe.modelo.Mensajes;

public interface IMensajesService {

	public List<Mensajes> listar();
	
	public int guardar(Mensajes mensajes);
	
	public void eliminar(int id);
}
