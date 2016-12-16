import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.*;
import javassist.bytecode.Descriptor.Iterator;
import model.*;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FactoryDAO f = new FactoryDAO();
		UsuarioDAO usuario = f.getUsuarioDAO();
		ResponseEntity<List<Usuario>> usuarios = (ResponseEntity<List<Usuario>>) usuario.recuperarTodos();
		for (Usuario u : usuarios.getBody()) {
			u.getUsuario();

		}
		/*
		 * //Creo 2 usuarios UsuarioDAO user = f.getUsuarioDAO(); Usuario u =
		 * new Usuario("grupo18","1234"); u = user.persistir(u); Usuario u2 =
		 * new Usuario("otro","1234"); u2 = user.persistir(u2);
		 * 
		 * //Verifico que los datos del usuario sean correctos al loguearse
		 * 
		 * if((user.chequearAutenticacion("grupo18", "1234")).equals(null)){
		 * System.out.print("Username o passwrod incorrecto/s"); } else{
		 * System.out.println("Correcto"); } //Creo dos carteleras CarteleraDAO
		 * cartelera = f.getCarteleraDAO(); Cartelera c = new
		 * Cartelera("empleo","empleo1"); c = cartelera.persistir(c); Cartelera
		 * c2 = new Cartelera("materia","materia1"); c2 =
		 * cartelera.persistir(c2);
		 * 
		 * //Creo dos publicaciones PublicacionDAO publicacion =
		 * f.getPublicacionDAO(); Publicacion p = new
		 * Publicacion("java","entrega5",true,c); p = publicacion.persistir(p);
		 * Publicacion p2 = new Publicacion("java","entrega6",true,c2); p2 =
		 * publicacion.persistir(p2); c2 = cartelera.recuperar(c2.getId());
		 * 
		 * 
		 * 
		 * ComentarioDAO comentario = f.getComentarioDAO();
		 * 
		 * MediaDAO media = f.getMediaDAO();
		 * 
		 * //Elimino en cascada las publicaciones, con sus comentarios y medias.
		 * for(Publicacion publi : c2.getPublicaciones()){ for(Comentario coment
		 * : publi.getComentarios()){ comentario.eliminar(coment); } for(Media
		 * med : publi.getMedias()){ media.eliminar(med); }
		 * publicacion.eliminar(publi); } //Elimino cartelera
		 * cartelera.eliminar(c2);
		 * 
		 * //Creo dos comentarios p = publicacion.recuperar(p.getId());
		 * Comentario com = new Comentario("primer comentario",p, u); com =
		 * comentario.persistir(com); Comentario com2 = new
		 * Comentario("primer comentario",p, u2); com2 =
		 * comentario.persistir(com2); p = publicacion.recuperar(p.getId());
		 * 
		 * //Elimino en cascada las publicaciones, con sus comentarios y medias.
		 * for(Comentario coment : p.getComentarios()){
		 * comentario.eliminar(coment); } for(Media med : p.getMedias()){
		 * media.eliminar(med); } //Elimino publicacion publicacion.eliminar(p);
		 * 
		 * //Creo dos notificaciones NotificacionCarteleraDAO notificacion =
		 * f.getNotificacionCarteleraDAO(); NotificacionCartelera n = new
		 * NotificacionCartelera(u,"facebook", c, true);
		 * notificacion.persistir(n); NotificacionCartelera n2 = new
		 * NotificacionCartelera(u,"email", c, true);
		 * notificacion.persistir(n2);
		 * 
		 * //Modifico comentario Publicacion p3 = new
		 * Publicacion("java","entrega6",true,c); p3 =
		 * publicacion.persistir(p3); Comentario com5 = new
		 * Comentario("primer comentario",p3, u2); com5 =
		 * comentario.persistir(com5); com5.setMensaje("Comentario modificado");
		 * comentario.modificar(com5);
		 */

	}

}
