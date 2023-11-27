package org.cibertec.edu.pe.modelo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensajes")
public class Mensajes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idMensajes")
	private int idMensajes;
	private String nombre;
	private String correo;
	private String mensaje;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp fechaEnvio;
	
	
	public Mensajes() {}


	public Mensajes(int idMensajes, String nombre, String correo, String mensaje, Timestamp fechaEnvio) {
		this.idMensajes = idMensajes;
		this.nombre = nombre;
		this.correo = correo;
		this.mensaje = mensaje;
		this.fechaEnvio = fechaEnvio;
	}


	public int getIdMensajes() {
		return idMensajes;
	}


	public void setIdMensajes(int idMensajes) {
		this.idMensajes = idMensajes;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Timestamp getFechaEnvio() {
		return fechaEnvio;
	}


	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
	
	
}
