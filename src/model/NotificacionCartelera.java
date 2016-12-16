package model;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="notificacionCartelera")
public class NotificacionCartelera {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(optional=false)
	private Usuario usuario;
	
	private String notificacion;
	
	@ManyToOne(optional=false)
	private Cartelera cartelera;
	
	private Boolean permiso;

	public NotificacionCartelera(Usuario usuario, String notificacion, Cartelera cartelera, Boolean permiso) {
		super();
		this.usuario = usuario;
		this.notificacion = notificacion;
		this.cartelera = cartelera;
		this.permiso = permiso;

	}
	
	public NotificacionCartelera(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPermiso() {
		return permiso;
	}

	public void setPermiso(Boolean permiso) {
		this.permiso = permiso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}

	public Cartelera getCartelera() {
		return cartelera;
	}

	public void setCartelera(Cartelera cartelera) {
		this.cartelera = cartelera;
	}

}
