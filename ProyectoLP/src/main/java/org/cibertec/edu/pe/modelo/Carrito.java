package org.cibertec.edu.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carritoCompras")
public class Carrito {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name ="idCarrito")
	private int idCarrito;
	
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Productos productos;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Clientes clientes;
	private int cantidad;
	private double precioTotal;
	
		
	public Carrito() {}


	public Carrito(int idCarrito, Productos productos, Clientes clientes, int cantidad, double precioTotal) {
		this.idCarrito = idCarrito;
		this.productos = productos;
		this.clientes = clientes;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
	}


	public int getIdCarrito() {
		return idCarrito;
	}


	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}


	public Productos getProductos() {
		return productos;
	}


	public void setProductos(Productos productos) {
		this.productos = productos;
	}


	public Clientes getClientes() {
		return clientes;
	}


	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getPrecioTotal() {
		return precioTotal;
	}


	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}



	
	
	
	
}
