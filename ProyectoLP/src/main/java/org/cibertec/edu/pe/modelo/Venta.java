package org.cibertec.edu.pe.modelo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idVenta")
	private int idVenta;
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Clientes clientes;
	
	@ManyToOne
	@JoinColumn(name ="idProducto")
	private Productos productos;
	
	private int cantidad;
	private double precioTotal;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp fechaVenta;

	
	public Venta() {}


	public Venta(int idVenta, Clientes clientes, Productos productos, int cantidad, double precioTotal,
			Timestamp fechaVenta) {
		this.idVenta = idVenta;
		this.clientes = clientes;
		this.productos = productos;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.fechaVenta = fechaVenta;
	}

	
	//NOMBRE 
	public String getNombres() {
		return clientes.getNombres();
	}
	
	//Producto
	public String getNombre() {
		return productos.getNombre();
	}
		
	//Cliente
	public int getIdCliente() {
		return clientes.getIdCliente();
	}

	public int getIdVenta() {
		return idVenta;
	}


	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}


	public Clientes getClientes() {
		return clientes;
	}


	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}


	public Productos getProductos() {
		return productos;
	}


	public void setProductos(Productos productos) {
		this.productos = productos;
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


	public Timestamp getFechaVenta() {
		return fechaVenta;
	}


	public void setFechaVenta(Timestamp fechaVenta) {
		this.fechaVenta = fechaVenta;
	}



	
	
}
