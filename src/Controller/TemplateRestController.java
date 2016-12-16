package Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import interfacesDAO.TemplateDAO;
import model.Template;

public class TemplateRestController {
	@Autowired
	TemplateDAO templateDAO;

	@RequestMapping(value = "/templates/", method = RequestMethod.GET)
	public ResponseEntity<List<Template>> recuperarTodos() {
		List<Template> templates = templateDAO.recuperarTodos();
		if (templates.isEmpty()) {
			return new ResponseEntity<List<Template>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Template>>(templates, HttpStatus.OK);
	}

	// Recupero un template dado
	@RequestMapping(value = "/template/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Template> recuperar(@PathVariable("id") int id) {

		System.out.println("Obteniendo template con id " + id);
		Template template = templateDAO.recuperar(id);
		if (template == null) {
			System.out.println("Template con id " + id + " no encontrado");
			return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Template>(template, HttpStatus.OK);
	}

	// Creo un template

	@RequestMapping(value = "/template/", method = RequestMethod.POST)
	public ResponseEntity<Void> persistir(@RequestBody Template template, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el template " + template.getId());
		if (templateDAO.existe(template.getId())) {
			System.out.println("Ya existe" + template.getId());
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		templateDAO.persistir(template);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/template/{id}").buildAndExpand(template.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Actualizo un template

	@RequestMapping(value = "/template/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Template> actualizar(@PathVariable("id") int id, @RequestBody Template template) {
		System.out.println("Actualizando el template " + id);

		Template currenttemplate = templateDAO.recuperar(id);

		if (currenttemplate == null) {
			System.out.println("template with id " + id + " not found");
			return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
		}
		currenttemplate.setNombre(template.getNombre());

		templateDAO.persistir(currenttemplate);
		return new ResponseEntity<Template>(currenttemplate, HttpStatus.OK);
	}

	// Elimino un template
	@RequestMapping(value = "/template/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Template> eliminar(@PathVariable("id") int id) {
		System.out.println("Obteniendo y eliminando el template con id " + id);
		Template template = templateDAO.recuperar(id);
		if (template == null) {
			System.out.println("No es posible eliminar, no se encuentra el template con id " + id);
			return new ResponseEntity<Template>(HttpStatus.NOT_FOUND);
		}
		templateDAO.eliminar(id);
		return new ResponseEntity<Template>(HttpStatus.NO_CONTENT);
	}

}
