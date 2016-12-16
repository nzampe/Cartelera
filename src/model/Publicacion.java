package model;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="publicacion")
public class Publicacion {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	private String titulo;
	private String texto;
	
	@ManyToOne(optional = false)
	 @JoinColumn(name="idCartelera")
	 private Cartelera cartelera;

	@OneToMany(mappedBy = "publicacion")
	private Collection<Media> medias;

	@OneToMany(mappedBy = "publicacion")
	private Collection<Comentario> comentarios;
	
	private Boolean comentariosHabilitados;

	public Publicacion(String titulo, String texto, Boolean comentarios, Cartelera cartelera) {
		this.titulo = titulo;
		this.texto = texto;
		this.comentariosHabilitados = comentarios;
		this.cartelera = cartelera;
	}
	
	public Publicacion(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public static void agregarMedia(Media media) {

	}

	public static void eliminarMedia(Media media) {

	}

	public Collection<Media> getMedias() {
		return medias;
	}

	public void setMedias(Collection<Media> medias) {
		this.medias = medias;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Boolean getComentariosHabilitados() {
		return comentariosHabilitados;
	}

	public void setComentariosHabilitados(Boolean comentariosHabilitados) {
		this.comentariosHabilitados = comentariosHabilitados;
	}
}
