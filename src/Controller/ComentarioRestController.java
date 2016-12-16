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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import interfacesDAO.ComentarioDAO;
import model.Comentario;

@RestController
public class ComentarioRestController {
	@Autowired
	ComentarioDAO comentarioDAO;

	@RequestMapping(value = "/comentarios/", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> recuperarTodos() {
		List<Comentario> comentarios = comentarioDAO.recuperarTodos();
		if (comentarios.isEmpty()) {
			return new ResponseEntity<List<Comentario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}

	// Recupero un comentario dado
	@RequestMapping(value = "/comentario/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comentario> recuperar(@PathVariable("id") int id) {

		System.out.println("Obteniendo comentario con id " + id);
		Comentario comentario = comentarioDAO.recuperar(id);
		if (comentario == null) {
			System.out.println("Comentario con id " + id + " no encontrado");
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
	}

	// Creo un comentario

	@RequestMapping(value = "/comentario/", method = RequestMethod.POST)
	public ResponseEntity<Void> persistir(@RequestBody Comentario comentario, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el comentario " + comentario.getMensaje());
		if (comentarioDAO.existe(comentario.getId())) {
			System.out.println("Ya existe un comentario con nombre " + comentario.getMensaje());
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		comentarioDAO.persistir(comentario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/comentario/{id}").buildAndExpand(comentario.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Actualizo un comentario

	@RequestMapping(value = "/comentario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Comentario> actualizar(@PathVariable("id") int id, @RequestBody Comentario comentario) {
		System.out.println("Actualizando el comentario " + id);

		Comentario currentcomentario = comentarioDAO.recuperar(id);

		if (currentcomentario == null) {
			System.out.println("comentario with id " + id + " not found");
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		currentcomentario.setMensaje(comentario.getMensaje());


		comentarioDAO.persistir(currentcomentario);
		return new ResponseEntity<Comentario>(currentcomentario, HttpStatus.OK);
	}

	// Elimino un comentario
	@RequestMapping(value = "/comentario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Comentario> eliminar(@PathVariable("id") int id) {
		System.out.println("Obteniendo y eliminando el comentario con id " + id);
		Comentario comentario = comentarioDAO.recuperar(id);
		if (comentario == null) {
			System.out.println("No es posible eliminar, no se encuentra el comentario con id " + id);
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		comentarioDAO.eliminar(id);
		return new ResponseEntity<Comentario>(HttpStatus.NO_CONTENT);
	}

}
