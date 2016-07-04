package com.servidor.aceroDoc.rest.controlador;
 
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.servidor.aceroDoc.rest.dao.UsuarioDao;
import com.servidor.aceroDoc.rest.entidades.Mail;
import com.servidor.aceroDoc.rest.entidades.Rol;
import com.servidor.aceroDoc.rest.entidades.Usuario;

@Controller
@Path("/acerodoc")
public class ControladorUsuario {
    // Add SQL session member
//    @Autowired
//    private SqlSession m_SqlSession;
    @Autowired
    private UsuarioDao usuarioDao;
	@Autowired
    private Mail mail;
	@Autowired
	private Rol rol;
    
	@GET
	@Path("{mail}")
    @Produces({MediaType.APPLICATION_JSON})
	public Response get_user_mail(@PathParam("mail") String mail){
		Usuario user = usuarioDao.get_user_by_mail(mail);
		if(user != null){
			return Response.status(200).entity(user).build();
		}else{
			return Response.status(404).entity("No se ha encontrado coincidencia").build();
		}
		
	}
	
	@GET
	@Path("all")
	@Produces({MediaType.APPLICATION_JSON})
	public Response get_users(){
		List<Usuario> user_list = usuarioDao.get_users();
		if (user_list != null){
			return Response.status(200).entity(user_list).build();
		}else{
			return Response.status(404).entity("No hay ningún usuario en la base de datos.").build();
		}
	}
	
	
    @POST 
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response new_user(Usuario usuario){
    	rol.setRol("ROL_USER");
    	rol.setMail(usuario.getMail());
    	usuarioDao.new_user(usuario);
    	usuarioDao.new_role(rol);
    	String sender="jruizrdgz@gmail.com";//write here sender gmail id  
        String receiver=usuario.getMail();//write here receiver id  
        mail.sendMail(sender,receiver,"Bienvenido","El registro se ha completado con exito.");  
    	
    	return Response.status(201).entity("Nuevo usuario creado").build();
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response update_user(Usuario user){
    	if(usuarioDao.update_user(user) == 1){
    		return Response.status(200).entity("Usuario actualizado").build();
    	}else{
    		return Response.status(404).entity("El usuario no solicidato no está en la base de datos.").build();
    	}
    }
    
    @DELETE
    @Path("{mail}")
    @Produces({MediaType.TEXT_HTML})
    public Response delete_user(@PathParam("mail") String mail){
    	if(usuarioDao.delete_user_by_mail(mail)==1){

        	return Response.status(200).entity("Usuario eliminido").build();
    	}else{

        	return Response.status(404).entity("El usuario no solicidato no está en la base de datos.").build();
    	}
    	
    	
    }
    
}