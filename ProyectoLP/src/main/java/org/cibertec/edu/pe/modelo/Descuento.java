package org.cibertec.edu.pe.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="descuentos")
public class Descuento {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name ="idDescuento")
	private int idDescuento;
	private String nombre;
	private double porcentajeDescuento;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	
	public Descuento() {}


	public Descuento(int idDescuento, String nombre, double porcentajeDescuento, LocalDate fechaInicio,
			LocalDate fechaFin) {
		this.idDescuento = idDescuento;
		this.nombre = nombre;
		this.porcentajeDescuento = porcentajeDescuento;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}


	public int getIdDescuento() {
		return idDescuento;
	}


	public void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}


	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}


	public LocalDate getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public LocalDate getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	
	
}
