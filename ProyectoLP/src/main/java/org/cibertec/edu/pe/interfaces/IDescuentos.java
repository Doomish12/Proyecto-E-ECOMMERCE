package org.cibertec.edu.pe.interfaces;

import org.cibertec.edu.pe.modelo.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDescuentos  extends JpaRepository<Descuento, Integer>{

	Descuento findByNombre(String codigoNombre);

}
