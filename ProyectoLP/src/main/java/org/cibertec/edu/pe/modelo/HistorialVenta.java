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
@Table(name = "historialVentas")
public class HistorialVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idHistorialVenta")
	private int idHistorialVenta;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Clientes clientes;
	
	@Column(name = "nomPro")
	private String nomPro;
	private int cantidad;
	@Column(name = "precioTotal")
	private double precioTotal;
	private String estado;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" ,name = "fechaVenta")
	private Timestamp fechaVenta;
	
	public HistorialVenta() {}

	public HistorialVenta(int idHistorialVenta, Clientes clientes, String nomPro, int cantidad, double precioTotal,
			String estado, Timestamp fechaVenta) {
		this.idHistorialVenta = idHistorialVenta;
		this.clientes = clientes;
		this.nomPro = nomPro;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.estado = estado;
		this.fechaVenta = fechaVenta;
	}
	
	//OBTENER NOMBRE PROPIEDAD
	public String getNombres() {
		return clientes.getNombres();
	}
	
	public int getIdHistorialVenta() {
		return idHistorialVenta;
	}

	public void setIdHistorialVenta(int idHistorialVenta) {
		this.idHistorialVenta = idHistorialVenta;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public String getNomPro() {
		return nomPro;
	}

	public void setNomPro(String nomPro) {
		this.nomPro = nomPro;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Timestamp fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	
	
	
	
	
}
