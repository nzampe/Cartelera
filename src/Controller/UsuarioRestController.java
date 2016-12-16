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

import interfacesDAO.UsuarioDAO;
import model.Usuario;

@RestController
public class UsuarioRestController {
	@Autowired
	UsuarioDAO usuarioDAO;

	@RequestMapping(value = "/usuarios/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> recuperarTodos() {
		List<Usuario> usuarios = usuarioDAO.recuperarTodos();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	// Recupero un usuario dado
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> recuperar(@PathVariable("id") int id) {

		System.out.println("Obteniendo usuario con id " + id);
		Usuario user = usuarioDAO.recuperar(id);
		if (user == null) {
			System.out.println("Usuario con id " + id + " no encontrado");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	// Creo un usuario

	@RequestMapping(value = "/usuario/", method = RequestMethod.POST)
	public ResponseEntity<Void> persistir(@RequestBody Usuario user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el usuario " + user.getUsuario());
		if (usuarioDAO.existe(user.getId())) {
			System.out.println("Ya existe un usuario con nombre " + user.getUsuario());
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		usuarioDAO.persistir(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Actualizo un usuario

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> actualizar(@PathVariable("id") int id, @RequestBody Usuario user) {
		System.out.println("Actualizando el usuario " + id);

		Usuario currentUser = usuarioDAO.recuperar(id);

		if (currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		currentUser.setUsuario(user.getUsuario());
		currentUser.setClave(user.getClave());
		currentUser.setRol(user.getRol());

		usuarioDAO.persistir(currentUser);
		return new ResponseEntity<Usuario>(currentUser, HttpStatus.OK);
	}

	// Elimino un usuario
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> eliminar(@PathVariable("id") int id) {
		System.out.println("Obteniendo y eliminando el usuario con id " + id);
		Usuario user = usuarioDAO.recuperar(id);
		if (user == null) {
			System.out.println("No es posible eliminar, no se encuentra el usuario con id " + id);
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		usuarioDAO.eliminar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
	
	//Chequeo loguin
	@RequestMapping(value = "/usuarios/chequearloguin", method = RequestMethod.POST)
	public ResponseEntity<Usuario> chequearLoguinAlumno(@RequestBody Usuario user, @RequestBody String clave) {
		Usuario usuario = usuarioDAO.recuperar(user.getId());
		if (usuario == null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.UNAUTHORIZED);
		}
		else if(user.getClave().equals(clave)){
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
		else return new ResponseEntity<Usuario>(usuario, HttpStatus.UNAUTHORIZED);
	}
	
	
}
