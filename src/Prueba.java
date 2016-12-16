import java.util.List;

import org.springframework.http.ResponseEntity;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.UsuarioDAO;
import model.Usuario;

public class Prueba {
	public static void main() {
		FactoryDAO f = new FactoryDAO();
		UsuarioDAO usuario = f.getUsuarioDAO();
		ResponseEntity<List<Usuario>> usuarios = (ResponseEntity<List<Usuario>>) usuario.recuperarTodos();
		for (Usuario u : usuarios.getBody()) {
			u.getUsuario();

		}
	}
}
