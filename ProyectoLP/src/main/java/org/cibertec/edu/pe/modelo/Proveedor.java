package org.cibertec.edu.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {
	
	 @Id
	 @GeneratedValue (strategy = GenerationType.IDENTITY)
	 @Column(name ="idProveedor")
	 private int idProveedor;
	 private String razonSocial;
	 @Column(length = 11)
	 private String ruc;
	 private String telefono;
	 
	 
	 public Proveedor() {}


	public Proveedor(int idProveedor, String razonSocial, String ruc, String telefono) {
		this.idProveedor = idProveedor;
		this.razonSocial = razonSocial;
		this.ruc = ruc;
		this.telefono = telefono;
	}


	public int getIdProveedor() {
		return idProveedor;
	}


	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getRuc() {
		return ruc;
	}


	public void setRuc(String ruc) {
		this.ruc = ruc;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	 
	 
	 
}
