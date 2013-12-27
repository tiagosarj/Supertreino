package br.ufba.matc89.dao;

import br.ufba.matc89.util.ErroUtil;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class GenericDAO extends SQLiteOpenHelper{
	
	protected static final int DB_VERSION = 5;
	
	protected static final String DB_NAME = "supertreino";
	
	protected static String SQL_COMMAND_DELETE;
	protected static String[] SQL_COMMAND_CREATE; 
	protected static String TABLE_NAME;
	
	
	
	protected SQLiteDatabase db;
	 
	
	public GenericDAO(Context context, String name, int version, String[] sqlCreate, String sqlDelete) {
		
		super(context, name, null, version);
		
		GenericDAO.SQL_COMMAND_DELETE = sqlDelete;
		GenericDAO.SQL_COMMAND_CREATE = sqlCreate;
		//add breakpoint aqui
		//Cria base de dados		
		db = getWritableDatabase();				
	}
	
	
	public void createDB(Context ctx){
		
		db = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);	 
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Generic", "Criacao do banco");
		int qntCommandsSql = SQL_COMMAND_CREATE.length;
		
		for(int i = 0; i < qntCommandsSql; i++){
			String cmd = SQL_COMMAND_CREATE[i];
			
			Log.i("Generic", cmd);
			db.execSQL(cmd);
		}
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String txtLog = "Atualizando da versao "+oldVersion+ " para a versao "+newVersion;
		Log.w("Generic", txtLog);
		Log.i("Generic", SQL_COMMAND_DELETE);
		
		db.execSQL(SQL_COMMAND_DELETE);
		
		onCreate(db);
	}
	
	protected void setErro(String msg, String local){
		ErroUtil.erroMensagem = msg;
		ErroUtil.erroLocal = local;
	}
	
	
}
