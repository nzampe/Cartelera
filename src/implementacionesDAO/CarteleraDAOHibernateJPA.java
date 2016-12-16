package implementacionesDAO;

import org.springframework.stereotype.Repository;

import interfacesDAO.CarteleraDAO;
import model.Cartelera;

@Repository
public class CarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<Cartelera> implements CarteleraDAO {

	public CarteleraDAOHibernateJPA() {
		super(Cartelera.class);
	}

}
