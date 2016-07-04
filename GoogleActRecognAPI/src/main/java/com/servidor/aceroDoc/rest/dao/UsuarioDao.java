package com.servidor.aceroDoc.rest.dao;

import java.util.List;

import com.servidor.aceroDoc.rest.entidades.Rol;
import com.servidor.aceroDoc.rest.entidades.Usuario;

public interface UsuarioDao {
	//Read operations
	public List<Usuario> get_users();	
	public Usuario get_user_by_mail(String mail);
	//Write operations
	public Long new_user(Usuario user);
	public Long new_role(Rol rol);
	//Update operations
	public int update_user(Usuario user);
	//Delete operations
	public Long delete_user_by_mail(String mail);

}
