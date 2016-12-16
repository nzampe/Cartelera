package implementacionesDAO;

import org.springframework.stereotype.Repository;

import interfacesDAO.PublicacionDAO;

import model.Publicacion;

@Repository
public class PublicacionDAOHibernateJPA extends GenericDAOHibernateJPA<Publicacion> implements PublicacionDAO {

	public PublicacionDAOHibernateJPA() {
		super(Publicacion.class);
	}

	public void habilitarComentarios(Publicacion unaPublicacion) {
		unaPublicacion.setComentariosHabilitados(true);
		this.modificar(unaPublicacion);
	}

	public void deshabilitarComentarios(Publicacion unaPublicacion) {
		unaPublicacion.setComentariosHabilitados(false);
		this.modificar(unaPublicacion);
	}
}
