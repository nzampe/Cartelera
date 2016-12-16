package interfacesDAO;
import model.NotificacionCartelera;
public interface NotificacionCarteleraDAO extends GenericDAO<NotificacionCartelera>{
	public void agregarPermisoCarteleraUsuario(NotificacionCartelera notificacion);
	public void eliminarPermisoCarteleraUsuario(NotificacionCartelera notificacion);
}
