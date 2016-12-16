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

import interfacesDAO.NotificacionCarteleraDAO;
import model.NotificacionCartelera;

public class NotificacionCarteleraRestController {
	@Autowired
	NotificacionCarteleraDAO notificacionCarteleraDAO;

	@RequestMapping(value = "/notificacionCarteleras/", method = RequestMethod.GET)
	public ResponseEntity<List<NotificacionCartelera>> recuperarTodos() {
	  List<NotificacionCartelera> notificacionCarteleras = notificacionCarteleraDAO.recuperarTodos();
	  if (notificacionCarteleras.isEmpty()) {
	    return new ResponseEntity<List<NotificacionCartelera>>(HttpStatus.NO_CONTENT);
	  }
	  return new ResponseEntity<List<NotificacionCartelera>>(notificacionCarteleras, HttpStatus.OK);
	}

	// Recupero un notificacionCartelera dado
	@RequestMapping(value = "/notificacionCartelera/{id}", method = RequestMethod.GET, produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NotificacionCartelera> recuperar(@PathVariable("id") int id) {

	  System.out.println("Obteniendo notificacionCartelera con id " + id);
	  NotificacionCartelera notificacionCartelera = notificacionCarteleraDAO.recuperar(id);
	  if (notificacionCartelera == null) {
	    System.out.println("NotificacionCartelera con id " + id + " no encontrado");
	    return new ResponseEntity<NotificacionCartelera>(HttpStatus.NOT_FOUND);
	  }
	  return new ResponseEntity<NotificacionCartelera>(notificacionCartelera, HttpStatus.OK);
	}

	// Creo un notificacionCartelera

	@RequestMapping(value = "/notificacionCartelera/", method = RequestMethod.POST)
	public ResponseEntity<Void> persistir(@RequestBody NotificacionCartelera notificacionCartelera, UriComponentsBuilder ucBuilder) {
	  System.out.println("Creando el notificacionCartelera " + notificacionCartelera.getId());
	  if (notificacionCarteleraDAO.existe(notificacionCartelera.getId())) {
	    System.out.println("Ya existe" + notificacionCartelera.getId());
	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	  }
	  notificacionCarteleraDAO.persistir(notificacionCartelera);
	  HttpHeaders headers = new HttpHeaders();
	  headers.setLocation(ucBuilder.path("/notificacionCartelera/{id}").buildAndExpand(notificacionCartelera.getId()).toUri());
	  return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Actualizo un notificacionCartelera

	@RequestMapping(value = "/notificacionCartelera/{id}", method = RequestMethod.PUT)
	public ResponseEntity<NotificacionCartelera> actualizar(@PathVariable("id") int id, @RequestBody NotificacionCartelera notificacionCartelera) {
	  System.out.println("Actualizando el notificacionCartelera " + id);

	  NotificacionCartelera currentnotificacionCartelera = notificacionCarteleraDAO.recuperar(id);

	  if (currentnotificacionCartelera == null) {
	    System.out.println("notificacionCartelera with id " + id + " not found");
	    return new ResponseEntity<NotificacionCartelera>(HttpStatus.NOT_FOUND);
	  }
	  currentnotificacionCartelera.setNotificacion(notificacionCartelera.getNotificacion());
	  currentnotificacionCartelera.setPermiso(notificacionCartelera.getPermiso());

	  notificacionCarteleraDAO.persistir(currentnotificacionCartelera);
	  return new ResponseEntity<NotificacionCartelera>(currentnotificacionCartelera, HttpStatus.OK);
	}

	// Elimino un notificacionCartelera
	@RequestMapping(value = "/notificacionCartelera/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<NotificacionCartelera> eliminar(@PathVariable("id") int id) {
	  System.out.println("Obteniendo y eliminando el notificacionCartelera con id " + id);
	  NotificacionCartelera notificacionCartelera = notificacionCarteleraDAO.recuperar(id);
	  if (notificacionCartelera == null) {
	    System.out.println("No es posible eliminar, no se encuentra el notificacionCartelera con id " + id);
	    return new ResponseEntity<NotificacionCartelera>(HttpStatus.NOT_FOUND);
	  }
	  notificacionCarteleraDAO.eliminar(id);
	  return new ResponseEntity<NotificacionCartelera>(HttpStatus.NO_CONTENT);
	}


}
