package br.ufba.matc89.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufba.matc89.controller.Security;
import br.ufba.matc89.model.Usuario;

public class UsuarioDAO extends GenericDAO{
	
	static{ 
		TABLE_NAME = "usuario";		
	}
	
	public UsuarioDAO(Context ctx){
		//add breakpoint aqui
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);			
	}
	
	public boolean save(Usuario usuario){
		//add breakpoint aqui
		boolean sucess = false;
		long id = usuario.getId();
		
		if(id > 0){
			sucess = update(usuario);
		}else{
			
			sucess = insert(usuario);
		}
		
		return sucess;
	}
	
	public boolean insert(Usuario usuario){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
				
		values.put("nome",  usuario.getNome());
		values.put("login", usuario.getLogin());
		values.put("senha", usuario.getSenha());
		values.put("email", usuario.getEmail());
		
		long id = db.insert(TABLE_NAME, null, values);
		
		//caso o usu�rio seja adicionado na tabela, passar� a ser o usu�rio logado.
		if(id > 0){
			Usuario usuarioLogado = new Usuario();
			
			usuarioLogado = usuario;
			usuarioLogado.setSenha("");
			usuarioLogado.setId(id);
			Security.setUsuarioLogado(usuarioLogado);
			sucess = true;
		}else{
			setErro("Erro no banco. Os dados de usuario n�o foram salvos", "insert");
		}
		return sucess;
	}
	
	
	
	public boolean update(Usuario usuario){
		boolean sucess = false;
		
		
		return sucess;
	}
	
	public Usuario get(Usuario usuario){
		
		
		//TODO - Buscar usuario pelo login e senha na camada de controller, se o id 
		
		
		return usuario;
	}
}
