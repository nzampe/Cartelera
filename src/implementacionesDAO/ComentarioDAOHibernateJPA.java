package implementacionesDAO;

import org.springframework.stereotype.Repository;

import interfacesDAO.ComentarioDAO;
import model.Comentario;

@Repository
public class ComentarioDAOHibernateJPA extends GenericDAOHibernateJPA<Comentario> implements ComentarioDAO {

	public ComentarioDAOHibernateJPA() {
		super(Comentario.class);
	}

}
