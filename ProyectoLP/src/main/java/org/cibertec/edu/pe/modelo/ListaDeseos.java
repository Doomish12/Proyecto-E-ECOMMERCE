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
@Table(name = "listaDeseos")
public class ListaDeseos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idDeseo")
	private int idDeseo;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Clientes clientes;

	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Productos productos;

	public ListaDeseos() {
	}

	public ListaDeseos(int idDeseo, Clientes clientes, Productos productos) {
		this.idDeseo = idDeseo;
		this.clientes = clientes;
		this.productos = productos;
	}

	public int getIdDeseo() {
		return idDeseo;
	}

	public void setIdDeseo(int idDeseo) {
		this.idDeseo = idDeseo;
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

}
