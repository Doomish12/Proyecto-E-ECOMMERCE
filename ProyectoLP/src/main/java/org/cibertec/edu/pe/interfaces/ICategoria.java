package org.cibertec.edu.pe.interfaces;

import org.cibertec.edu.pe.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoria extends JpaRepository<Categoria, Integer> {

}
