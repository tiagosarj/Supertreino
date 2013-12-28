package br.ufba.matc89.dao;

import android.content.ContentValues;
import android.content.Context;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Genero;



public class AtletaDAO extends GenericDAO {
	public static Atleta atletaLogado = new Atleta("");
	static{
		TABLE_NAME = "atleta";		
	}
	
	public AtletaDAO(Context ctx){
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);				
	}
	
	public boolean save(Atleta atleta, long idUsuario){
		boolean sucess = false;
		long id = atleta.getId();
		
		if(id > 0){
			sucess = update(atleta, idUsuario);
		}else{
			sucess = insert(atleta, idUsuario);
		}
		
		return sucess;
	}
	
	public boolean insert(Atleta atleta, long idUsuario){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		
		values.put("id_usuario", idUsuario);
		
		if(atleta.getGenero() == 1){
			values.put("genero", String.valueOf(Genero.MASCULINO));
		}
		if(atleta.getGenero() == 2){
			values.put("genero", String.valueOf(Genero.FEMININO));
		}
		
		long id = db.insert(TABLE_NAME, null, values);
		
		if(id > 0){
			sucess = true;
			atletaLogado = atleta;
			atletaLogado.setId(id);
		}else{
			setErro("Erro no banco. Os dados de atleta não foram salvos", "insert");			
		}
		return sucess;
	}
	
	public boolean update(Atleta atleta, long idUsuario){
		boolean sucess = false;
		
		
		return sucess;
	}
}
