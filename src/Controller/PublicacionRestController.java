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

import interfacesDAO.PublicacionDAO;
import model.Publicacion;

public class PublicacionRestController {
	@Autowired
	PublicacionDAO publicacionDAO;

	@RequestMapping(value = "/publicacions/", method = RequestMethod.GET)
	public ResponseEntity<List<Publicacion>> recuperarTodos() {
		List<Publicacion> publicacions = publicacionDAO.recuperarTodos();
		if (publicacions.isEmpty()) {
			return new ResponseEntity<List<Publicacion>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Publicacion>>(publicacions, HttpStatus.OK);
	}

	// Recupero un publicacion dado
	@RequestMapping(value = "/publicacion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Publicacion> recuperar(@PathVariable("id") int id) {

		System.out.println("Obteniendo publicacion con id " + id);
		Publicacion publicacion = publicacionDAO.recuperar(id);
		if (publicacion == null) {
			System.out.println("Publicacion con id " + id + " no encontrado");
			return new ResponseEntity<Publicacion>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Publicacion>(publicacion, HttpStatus.OK);
	}

	// Creo un publicacion

	@RequestMapping(value = "/publicacion/", method = RequestMethod.POST)
	public ResponseEntity<Void> persistir(@RequestBody Publicacion publicacion, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el publicacion " + publicacion.getId());
		if (publicacionDAO.existe(publicacion.getId())) {
			System.out.println("Ya existe" + publicacion.getId());
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		publicacionDAO.persistir(publicacion);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/publicacion/{id}").buildAndExpand(publicacion.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Actualizo un publicacion

	@RequestMapping(value = "/publicacion/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Publicacion> actualizar(@PathVariable("id") int id, @RequestBody Publicacion publicacion) {
		System.out.println("Actualizando el publicacion " + id);

		Publicacion currentpublicacion = publicacionDAO.recuperar(id);

		if (currentpublicacion == null) {
			System.out.println("publicacion with id " + id + " not found");
			return new ResponseEntity<Publicacion>(HttpStatus.NOT_FOUND);
		}
		currentpublicacion.setTitulo(publicacion.getTitulo());
		currentpublicacion.setTexto(publicacion.getTexto());
		currentpublicacion.setComentariosHabilitados(publicacion.getComentariosHabilitados());

		publicacionDAO.persistir(currentpublicacion);
		return new ResponseEntity<Publicacion>(currentpublicacion, HttpStatus.OK);
	}

	// Elimino un publicacion
	@RequestMapping(value = "/publicacion/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Publicacion> eliminar(@PathVariable("id") int id) {
		System.out.println("Obteniendo y eliminando el publicacion con id " + id);
		Publicacion publicacion = publicacionDAO.recuperar(id);
		if (publicacion == null) {
			System.out.println("No es posible eliminar, no se encuentra el publicacion con id " + id);
			return new ResponseEntity<Publicacion>(HttpStatus.NOT_FOUND);
		}
		publicacionDAO.eliminar(id);
		return new ResponseEntity<Publicacion>(HttpStatus.NO_CONTENT);
	}

}
