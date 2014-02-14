package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.controller.AtletaController;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Dieta;

public class DietaDAO extends GenericDAO {
	 
	static final String TABLE_NAME = "dieta";
	
	public DietaDAO(Context ctx){
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);		
	}

	public long save(Dieta dieta){
		//add breakpoint aqui
		boolean sucess = false;
		long id = dieta.getId();
		
		if(id > 0){
			sucess = update(dieta);
		}else{
			
			return insert(dieta);
				
			
		}
		
		return -1;
	}
	
	private long insert(Dieta dieta) {
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		values = getValues(dieta);
		values.put("id_atleta", AtletaController.atleta.getId());
		Long id = db.insert(TABLE_NAME, null, values);
		if(id > 0){
			dieta.setId(id);
			sucess = true;
		}else{
			setErro("Erro no banco. Os dados de dieta não foram salvos", "insert");
		}		
		return id;
	}

	public boolean update(Dieta dieta){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		
		values = getValues(dieta);
		String where = "id=?";
		String[] whereArgs = new String[]{String.valueOf(dieta.getId())};
		
		if(db.update(TABLE_NAME, values, where, whereArgs)>0){
			sucess = true;
		}else{
			setErro("Nenhum registro foi atualizado", "Dieta.update");			
		}
		
		return sucess;
	}
	
	public List<Dieta> getList(Atleta atleta){
		
		List<Dieta> dietas = new ArrayList<Dieta>();
		String where = "id_atleta="+atleta.getId();
		dietas = getList(where);
				
		return dietas;
	}	
	
	public Dieta getById(long id){
		Dieta dieta = new Dieta();
		Cursor cursor = db.query(TABLE_NAME, null, "id="+String.valueOf(id), null, null, null, null);

		if (cursor.getCount() > 0){
			cursor.moveToFirst();
			dieta = getDietaByRegistro(cursor);
		}
		// Make sure to close the cursor
		cursor.close();
		//close();
		return dieta;
	}
	
	public List<Dieta> getList(String where){
		
		List<Dieta> dietas = new ArrayList<Dieta>();
		
		Cursor c = db.query(TABLE_NAME, null, where, null, null, null, null);
				
		if(c.getCount() > 0){
			c.moveToFirst();
			for(int i = 0; i < c.getCount(); i++){
				dietas.add(getDietaByRegistro(c));
				c.moveToNext();
			}
			c.close();
		//	close();
		}	
		return dietas;
	}
	
	private Dieta getDietaByRegistro(Cursor c){
		Dieta dieta = new Dieta();
				
		int indexColId = c.getColumnIndex("id");
		int indexColNome = c.getColumnIndex("nome");
		int indexColIdAtleta = c.getColumnIndex("id_atleta");		
		
		dieta.setId(c.getInt(indexColId));
		
		HashMap<String, Long> foreignKey = new HashMap<String, Long>();
		foreignKey.put("id_atleta", c.getLong(indexColIdAtleta));
		
		dieta.setId_other(foreignKey);
		dieta.setNome(c.getString(indexColNome));
			
		return dieta;
	}
	
	private ContentValues getValues(Dieta dieta){
		ContentValues values = new ContentValues();
		
		values.put("nome", dieta.getNome());
		
		return values;
	}
	
	/*public void close(){
		db.close();
	}*/
}
