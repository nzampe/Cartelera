package implementacionesDAO;

import org.springframework.stereotype.Repository;

import interfacesDAO.TemplateDAO;
import model.Template;

@Repository
public class TemplateDAOHibernateJPA extends GenericDAOHibernateJPA<Template> implements TemplateDAO {

	public TemplateDAOHibernateJPA() {
		super(Template.class);
	}

}
