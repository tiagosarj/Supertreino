package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.model.Dieta;
import br.ufba.matc89.model.Refeicao;

public class RefeicaoDAO extends GenericDAO{
	
	static final String TABLE_NAME = "refeicao";
	
	public RefeicaoDAO(Context ctx) {
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);		
	}
	
	public long save(Refeicao refeicao){
		//add breakpoint aqui
		boolean sucess = false;
		long id = refeicao.getId();
		
		if(id > 0){
			sucess = update(refeicao);
		}else{
			
			id = insert(refeicao);
		}
		
		return id;
	}
	
	private long insert(Refeicao refeicao) {
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		values = getValues(refeicao);
		//values.put("id_atleta", AtletaController.atleta.getId());
		long id = db.insert(TABLE_NAME, null, values);
		if(id == 0){
			setErro("Erro no banco. Os dados de dieta n�o foram salvos", "insert");
		}	
		return id;
	}

	public boolean update(Refeicao refeicao){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		
		values = getValues(refeicao);
		String where = "id=?";
		String[] whereArgs = new String[]{String.valueOf(refeicao.getId())};
		
		if(db.update(TABLE_NAME, values, where, whereArgs)>0){
			sucess = true;
		}else{
			setErro("Nenhum registro foi atualizado", "Refeicao.update");			
		}
		
		return sucess;
	}
	
	public List<Refeicao> getList(Long dietaId){
		
		List<Refeicao> refeicoes = new ArrayList<Refeicao>();
		
		List<Long> ids = getIdsRefeicoesPorDieta(dietaId);
		
		for(int i = 0;i < ids.size(); i++){
			String where = "id="+ids.get(i);
			refeicoes.add(get(where));			
		}		
				
		return refeicoes;
	}
	
	public Refeicao get(Long id){
		return get("id="+id);
	}
	
	public Refeicao get(String where){
		
		Refeicao refeicao = new Refeicao();
		
		Cursor c = db.query("refeicao", null, where, null, null, null, null);
		
		if (c.getCount() > 0) {
			c.moveToFirst();			
			
			refeicao = getRefeicaoByRegistro(c);
					
			c.close();
		}
		return refeicao;
	}
	
	private Refeicao getRefeicaoByRegistro(Cursor c){
		Refeicao refeicao = new Refeicao();
				
		int indexColId = c.getColumnIndex("id");
		int indexColNome = c.getColumnIndex("nome");
		
		refeicao.setId(c.getLong(indexColId));
			
		refeicao.setNome(c.getString(indexColNome));
			
		return refeicao;
	}
	
	private Long getIdRefeicaoByRegistro(Cursor c){
						
		int indexColId = c.getColumnIndex("id_refeicao");
					
		return c.getLong(indexColId);
	}
	
	private ContentValues getValues(Refeicao refeicao){
		ContentValues values = new ContentValues();
		
		values.put("nome", refeicao.getNome());
		
		return values;
	}
	
	public boolean saveRefeicaoDieta(long idRefeicao, long idDieta){
		ContentValues values = new ContentValues();
		values.put("id_refeicao", idRefeicao);
		values.put("id_dieta", idDieta);
		
		if(db.insert("dieta_refeicao", null, values)>0){
			return true;
		}else
			return false;
		
	}
	
	public boolean saveAlimentoRefeicao(long idAlimento, long idRefeicao){
		ContentValues values = new ContentValues();
		
		values.put("id_Alimento", idAlimento);
		values.put("id_refeicao", idRefeicao);		
		
		if(db.insert("refeicao_alimento", null, values)>0){
			return true;
		}else
			return false;
	}
	
	public List<Long> getIdsRefeicoesPorDieta(Long dietaId){
		List<Long> idsRefeicoes =  new ArrayList<Long>();
		String where = "id_dieta="+dietaId;
		Cursor c = db.query("dieta_refeicao", null, where, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			for (int i = 0; i < c.getCount(); i++) {

				idsRefeicoes.add(getIdRefeicaoByRegistro(c));
				c.moveToNext();
			}
			c.close();
		}
		return idsRefeicoes;
		
	}
	/*public void close(){
		db.close();
	}*/

	public boolean removerAlimentos(long refeicaoId) {
		
		String where = "id_refeicao=?";
		String[] whereArgs = new String[]{String.valueOf(refeicaoId)};
		
		if(db.delete("refeicao_alimento", where, whereArgs) > 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean remover(long refeicaoId) {
		
		String where = "id=?";
		String[] whereArgs = new String[]{String.valueOf(refeicaoId)};
		
		if(db.delete("refeicao", where, whereArgs) > 0){
			return true;
		}else{
			return false;
		}
	}
}