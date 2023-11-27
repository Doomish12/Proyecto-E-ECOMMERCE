package org.cibertec.edu.pe.service;

import java.util.List;

import org.cibertec.edu.pe.interfaceService.IListaDeseosService;
import org.cibertec.edu.pe.interfaces.IListaDeseos;
import org.cibertec.edu.pe.modelo.ListaDeseos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListaDeseosService implements IListaDeseosService{

	@Autowired
	private IListaDeseos data;
	
	
	@Override
	public List<ListaDeseos> listar(int idUsuario) {
		return data.findByClientesIdCliente(idUsuario);
	}


	@Override
	public void eliminar(int id) {
		data.deleteById(id);
	}


	@Override
	public int guardarDeseo(ListaDeseos listaDeseos) {
		ListaDeseos l = data.save(listaDeseos);
		
		if(l != null) {
			return 1;
		}
		return 0;
	}




}
