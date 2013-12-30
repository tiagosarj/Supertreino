package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.model.Medida;
import br.ufba.matc89.model.Usuario;
import br.ufba.matc89.util.DateUtil;

public class MedidaDAO extends GenericDAO{
	static{
		TABLE_NAME = "medida";		
	}
	public MedidaDAO(Context ctx){
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);						
	}
	
	public boolean save(Medida medida, long idAtleta){
		boolean sucess = false;
		long id = medida.getId();
		
		if(id > 0){
			sucess = update(medida);
		}else{
			sucess = insert(medida, idAtleta);
		}
		
		return sucess;
	}
	
	public boolean insert(Medida medida, long idAtleta){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		values.put("id_atleta", idAtleta);
		values.put("peso", medida.getPeso());
		values.put("altura", medida.getAltura());
		values.put("cintura", medida.getCintura());
		values.put("quadril", medida.getQuadril());
		if(medida.getDataAfericao() != null){
			values.put("data_afericao", DateUtil.getDataSimples(medida.getDataAfericao()));
		}else{
			values.put("data_afericao", DateUtil.getDataSimples(new Date()));
		}
		
		long id = db.insert(TABLE_NAME, null, values);
		
		if(id > 0){
			
			sucess = true;
		}else{
			setErro("Erro no banco. Os dados de medida não foram salvos", "insert");
		}
		return sucess;
	}
	
	public boolean update(Medida medida){
		boolean sucess = false;
		
		
		return sucess;
	}
	
	public List<Medida> getList(String where){
		
		List<Medida> medidas = new ArrayList<Medida>();
		
		Cursor c = db.query(TABLE_NAME, null, where, null, null, null, null);
				
		if(c.getCount() > 0){
			c.moveToFirst();
			for(int i = 0; i < c.getCount(); i++){
				medidas.add(getMedidaByRegistro(c));
				c.moveToNext();
			}
			c.close();
		}	
		return medidas;
	}
	
	
	private Medida getMedidaByRegistro(Cursor c){
		Medida medida = new Medida(0,0);
		
		return medida;
	}
}
