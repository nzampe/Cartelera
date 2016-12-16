package model;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="cartelera")
public class Cartelera {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private String tipo;
	
	@ManyToMany
	 private Collection<Template> templates;

	@OneToMany( mappedBy = "cartelera", cascade={CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.MERGE})
	private Collection<Publicacion> publicaciones;
	
	
	@OneToMany(mappedBy="cartelera")
	private Collection<NotificacionCartelera> notificacion;
	
	private String descripcion;
	
	@ManyToMany(mappedBy = "carteleras")
	private Collection<Usuario> usuarios;

	public Cartelera(String tipo, String descripcion) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public Cartelera(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setAlumnos(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Collection<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(Collection<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
}
