package model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="template")
public class Template {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idTemplate")
	private Integer idTemplate;

	private String nombre;

	@ManyToMany(mappedBy = "templates")
	private Collection<Cartelera> carteleras;

	public Template(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Template(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return idTemplate;
	}

	public void setId(Integer id) {
		this.idTemplate = id;
	}

	public Collection<Cartelera> getCarteleras() {
		return carteleras;
	}

	public void setCarteleras(Collection<Cartelera> carteleras) {
		this.carteleras = carteleras;
	}

}
