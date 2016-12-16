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

import interfacesDAO.CarteleraDAO;
import model.Cartelera;

@RestController
public class CarteleraRestController {
	@Autowired
	CarteleraDAO carteleraDAO;

	@RequestMapping(value = "/carteleras/", method = RequestMethod.GET)
	public ResponseEntity<List<Cartelera>> recuperarTodos() {
		List<Cartelera> carteleras = carteleraDAO.recuperarTodos();
		if (carteleras.isEmpty()) {
			return new ResponseEntity<List<Cartelera>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Cartelera>>(carteleras, HttpStatus.OK);
	}

	// Recupero un cartelera dado
	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cartelera> recuperar(@PathVariable("id") int id) {

		System.out.println("Obteniendo cartelera con id " + id);
		Cartelera cartelera = carteleraDAO.recuperar(id);
		if (cartelera == null) {
			System.out.println("cartelera con id " + id + " no encontrado");
			return new ResponseEntity<Cartelera>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cartelera>(cartelera, HttpStatus.OK);
	}

	// Creo un cartelera

	@RequestMapping(value = "/cartelera/", method = RequestMethod.POST)
	public ResponseEntity<Void> persistir(@RequestBody Cartelera cartelera, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando la cartelera " + cartelera.getDescripcion());
		if (carteleraDAO.existe(cartelera.getId())) {
			System.out.println("Ya existe esa cartelera");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		carteleraDAO.persistir(cartelera);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/cartelera/{id}").buildAndExpand(cartelera.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Actualizo un cartelera

	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cartelera> actualizar(@PathVariable("id") int id, @RequestBody Cartelera cartelera) {
		System.out.println("Actualizando el cartelera " + id);

		Cartelera currentCartelera = carteleraDAO.recuperar(id);

		if (currentCartelera == null) {
			System.out.println("cartelera with id " + id + " not found");
			return new ResponseEntity<Cartelera>(HttpStatus.NOT_FOUND);
		}
		currentCartelera.setTipo(cartelera.getTipo());
		currentCartelera.setDescripcion(cartelera.getDescripcion());

		carteleraDAO.persistir(currentCartelera);
		return new ResponseEntity<Cartelera>(currentCartelera, HttpStatus.OK);
	}

	// Elimino un cartelera
	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cartelera> eliminar(@PathVariable("id") int id) {
		System.out.println("Obteniendo y eliminando el cartelera con id " + id);
		Cartelera cartelera = carteleraDAO.recuperar(id);
		if (cartelera == null) {
			System.out.println("No es posible eliminar, no se encuentra el cartelera con id " + id);
			return new ResponseEntity<Cartelera>(HttpStatus.NOT_FOUND);
		}
		carteleraDAO.eliminar(id);
		return new ResponseEntity<Cartelera>(HttpStatus.NO_CONTENT);
	}

}
