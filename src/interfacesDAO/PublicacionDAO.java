package interfacesDAO;

import model.Comentario;
import model.Publicacion;

public interface PublicacionDAO extends GenericDAO<Publicacion>{
	public void habilitarComentarios(Publicacion unaPublicacion);
	public void deshabilitarComentarios(Publicacion unaPublicacion);
}
