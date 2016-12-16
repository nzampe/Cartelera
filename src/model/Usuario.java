package model;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idUsuario")
	private Integer idUsuario;

	private String usuario;
	private String clave;
	private String rol;
	
	
	@OneToMany(mappedBy = "usuario")
	private Collection<Comentario> comentarios;
	
	@ManyToMany
	@JoinTable(name="usuario_carteleras", joinColumns=@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="cartelera_id"))
	private Collection<Cartelera> carteleras;
	
	
	@OneToMany(mappedBy = "usuario")
	private Collection<NotificacionCartelera> notificaciones;

	public Usuario(String usuario, String clave) {
		super();
		this.setUsuario(usuario);
		this.setClave(clave);
	}

	public Usuario(){
		
	}
	
	public Integer getId() {
		return idUsuario;
	}

	public void setId(Integer id) {
		this.idUsuario = id;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
