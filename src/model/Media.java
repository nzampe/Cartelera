package model;

import javax.persistence.*;

@Entity
@Table(name="media")
public class Media {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(optional = false, cascade={CascadeType.MERGE})
	 @JoinColumn(name="idPublicacion")
	 private Publicacion publicacion;
	
	private String tipo;
	private String url;

	public Media(String tipo, String url, Publicacion publicacion) {
		this.tipo = tipo;
		this.url = url;
		this.publicacion = publicacion;
	}
	
	public Media(){
		
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
