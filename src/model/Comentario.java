package model;

import javax.persistence.*;

@Entity
@Table(name="comentario")
public class Comentario {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(optional=false, cascade={CascadeType.MERGE})
	 @JoinColumn(name="idPublicacion")
	private Publicacion publicacion;
	
	@ManyToOne(optional=false, cascade={CascadeType.MERGE})
	 @JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	private String mensaje;

	public Comentario(String mensaje, Publicacion publicacion, Usuario usuario) {
		super();
		this.mensaje = mensaje;
		this.publicacion = publicacion;
		this.usuario = usuario;
	}

	public Comentario(){
		
	}
	
	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}