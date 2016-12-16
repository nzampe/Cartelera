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

import interfacesDAO.MediaDAO;
import model.Media;

public class MediaRestController {
	@Autowired
	MediaDAO mediaDAO;

	@RequestMapping(value = "/medias/", method = RequestMethod.GET)
	public ResponseEntity<List<Media>> recuperarTodos() {
		List<Media> medias = mediaDAO.recuperarTodos();
		if (medias.isEmpty()) {
			return new ResponseEntity<List<Media>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Media>>(medias, HttpStatus.OK);
	}

	// Recupero un media dado
	@RequestMapping(value = "/media/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Media> recuperar(@PathVariable("id") int id) {

		System.out.println("Obteniendo media con id " + id);
		Media media = mediaDAO.recuperar(id);
		if (media == null) {
			System.out.println("Media con id " + id + " no encontrado");
			return new ResponseEntity<Media>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Media>(media, HttpStatus.OK);
	}

	// Creo un media

	@RequestMapping(value = "/media/", method = RequestMethod.POST)
	public ResponseEntity<Void> persistir(@RequestBody Media media, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el media " + media.getId());
		if (mediaDAO.existe(media.getId())) {
			System.out.println("Ya existe" + media.getId());
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		mediaDAO.persistir(media);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/media/{id}").buildAndExpand(media.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Actualizo un media

	@RequestMapping(value = "/media/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Media> actualizar(@PathVariable("id") int id, @RequestBody Media media) {
		System.out.println("Actualizando el media " + id);

		Media currentmedia = mediaDAO.recuperar(id);

		if (currentmedia == null) {
			System.out.println("media with id " + id + " not found");
			return new ResponseEntity<Media>(HttpStatus.NOT_FOUND);
		}
		currentmedia.setTipo(media.getTipo());
		currentmedia.setUrl(media.getUrl());

		mediaDAO.persistir(currentmedia);
		return new ResponseEntity<Media>(currentmedia, HttpStatus.OK);
	}

	// Elimino un media
	@RequestMapping(value = "/media/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Media> eliminar(@PathVariable("id") int id) {
		System.out.println("Obteniendo y eliminando el media con id " + id);
		Media media = mediaDAO.recuperar(id);
		if (media == null) {
			System.out.println("No es posible eliminar, no se encuentra el media con id " + id);
			return new ResponseEntity<Media>(HttpStatus.NOT_FOUND);
		}
		mediaDAO.eliminar(id);
		return new ResponseEntity<Media>(HttpStatus.NO_CONTENT);
	}

}
