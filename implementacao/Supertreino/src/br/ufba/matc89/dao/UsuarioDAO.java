package br.ufba.matc89.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufba.matc89.model.Usuario;

public class UsuarioDAO extends GenericDAO{
	
	public static Usuario usuarioLogado =  new Usuario();
	
	static{ 
		//add breakpoint aqui
		TABLE_NAME = "usuario";
		SQL_COMMAND_DELETE = "drop table if exists usuario";
		SQL_COMMAND_CREATE = new String[]{"create table usuario ( "
							+"id integer primary key autoincrement,"	
							+"nome text not null,"
							+"login text not null,"
							+"senha text not null,"
							+"email text not null"
							+")"};
		
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
		
		//caso o usuário seja adicionado na tabela, passará a ser o usuário logado.
		if(id > 0){
			usuarioLogado = usuario;
			usuarioLogado.setSenha("");
			usuarioLogado.setId(id);
			sucess = true;
		}else{
			setErro("Erro no banco. Os dados de usuario não foram salvos", "insert");
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
