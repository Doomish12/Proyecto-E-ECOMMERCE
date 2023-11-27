package org.cibertec.edu.pe.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.cibertec.edu.pe.interfaceService.ICarritoService;
import org.cibertec.edu.pe.interfaces.ICarrito;
import org.cibertec.edu.pe.interfaces.IProductos;
import org.cibertec.edu.pe.modelo.Carrito;
import org.cibertec.edu.pe.modelo.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CarritoService implements ICarritoService {

	@Autowired
	private ICarrito data;

	@Autowired
	private IProductos dataProducto;

	@Override
	public List<Carrito> listarPorId(int idCliente) {
		return data.findByClientesIdCliente(idCliente);
	}

	@Override
	public int guardar(Carrito carrito) {

		Carrito c = data.save(carrito);
		if (c != null) {
			return 1;
		}

		return 0;
	}

	@Override
	public void aumentarCantidad(int id) {
		Optional<Carrito> carritoOptional = data.findById(id);

		if (carritoOptional.isPresent()) {
			Carrito carrito = carritoOptional.get();

			Optional<Productos> productosOptional = dataProducto.findById(carrito.getProductos().getIdProducto());
			if (productosOptional.isPresent()) {
				Productos producto = productosOptional.get();

				// Aumentar la cantidad y calcular el nuevo precio total
				carrito.setCantidad(carrito.getCantidad() + 1);
				carrito.setPrecioTotal(carrito.getCantidad() * producto.getPrecio());

				// Guardar el carrito actualizado en la base de datos
				data.save(carrito);
			}
		}
	}

	@Override
	public void disminuirCantidad(int id) {
		Optional<Carrito> carritoOptional = data.findById(id);

		if (carritoOptional.isPresent()) {
			Carrito carrito = carritoOptional.get();

			Optional<Productos> productosOptional = dataProducto.findById(carrito.getProductos().getIdProducto());
			if (productosOptional.isPresent()) {
				Productos producto = productosOptional.get();

				if (carrito.getCantidad() > 1) {
					// Disminuir la cantidad y calcular el nuevo precio total
					carrito.setCantidad(carrito.getCantidad() - 1);
					carrito.setPrecioTotal(carrito.getCantidad() * producto.getPrecio());

					// Guardar el carrito actualizado en la base de datos
					data.save(carrito);
				} else {
					eliminar(id);
				}
			}
		}

	}

	@Override
	public void eliminar(int id) {
		data.deleteById(id);
	}
	
	//PARA ELIMINAR TODO EL CARRITO ACORDE AL idCliente
	@Override
	public void eliminarIdCliente(int idCliente) {
		data.deleteByClientesIdCliente(idCliente);
		
	}





}
