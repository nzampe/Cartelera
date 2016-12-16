package implementacionesDAO;

import org.springframework.stereotype.Repository;

import interfacesDAO.NotificacionCarteleraDAO;
import model.NotificacionCartelera;

@Repository
public class NotificacionCarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<NotificacionCartelera>
		implements NotificacionCarteleraDAO {

	public NotificacionCarteleraDAOHibernateJPA() {
		super(NotificacionCartelera.class);
	}

	public void agregarPermisoCarteleraUsuario(NotificacionCartelera notificacion) {
		notificacion.setPermiso(true);
		this.modificar(notificacion);
	}

	public void eliminarPermisoCarteleraUsuario(NotificacionCartelera notificacion) {
		notificacion.setPermiso(false);
		this.modificar(notificacion);
	}
}
