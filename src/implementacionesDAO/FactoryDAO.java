package implementacionesDAO;

import interfacesDAO.*;

public class FactoryDAO {
	public CarteleraDAO getCarteleraDAO() {
		return new CarteleraDAOHibernateJPA();
	}

	public NotificacionCarteleraDAO getNotificacionCarteleraDAO() {
		return new NotificacionCarteleraDAOHibernateJPA();
	}

	public UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOHibernateJPA();
	}

	public ComentarioDAO getComentarioDAO() {
		return new ComentarioDAOHibernateJPA();
	}

	public MediaDAO getMediaDAO() {
		return new MediaDAOHibernateJPA();
	}

	public PublicacionDAO getPublicacionDAO() {
		return new PublicacionDAOHibernateJPA();
	}

	public TemplateDAO getTemplateDAO() {
		return new TemplateDAOHibernateJPA();
	}

}
