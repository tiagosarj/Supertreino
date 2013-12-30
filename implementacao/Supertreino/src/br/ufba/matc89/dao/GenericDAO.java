package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufba.matc89.util.ErroUtil;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class GenericDAO extends SQLiteOpenHelper{
	
	protected static final int DB_VERSION = 9;
	
	protected static final String DB_NAME = "supertreino";
	
	protected static List<String> SQL_COMMAND_DELETE;
	protected static List<String> SQL_COMMAND_CREATE; 
	protected static String TABLE_NAME;	
	
	
	protected SQLiteDatabase db;
	
	static{
		SQL_COMMAND_DELETE = new ArrayList<String>();
		SQL_COMMAND_CREATE = new ArrayList<String>();
		
		SQL_COMMAND_CREATE.add("create table usuario ( "
							+"id integer primary key autoincrement,"	
							+"nome text not null,"
							+"login text not null,"
							+"senha text not null,"
							+"email text not null"
							+")");
		
		SQL_COMMAND_CREATE.add("create table atleta("
							+"id integer primary key autoincrement,"
							+"id_usuario integer not null,"
							+"genero text not null,"
							+"foreign key(id_usuario) references usuario(id)"							
							+")");
		
		SQL_COMMAND_CREATE.add("create table medida ("
							+"id integer primary key autoincrement,"
							+"id_atleta integer not null,"
							+"peso integer,"
							+"altura integer,"
							+"cintura integer,"
							+"quadril integer,"
							+"data_afericao date,"
							+"foreign key(id_atleta) references atleta(id)"
							+")");
		
		SQL_COMMAND_DELETE.add("drop table if exists medida");
		SQL_COMMAND_DELETE.add("drop table if exists atleta");
		SQL_COMMAND_DELETE.add("drop table if exists usuario");
	}
	
	public GenericDAO(Context context, String name, int version, List<String> sqlCreate, List<String> sqlDelete) {
		
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
		
		for(int i = 0; i < SQL_COMMAND_CREATE.size(); i++){
			String cmd = SQL_COMMAND_CREATE.get(i);
			
			Log.i("Generic", cmd);
			db.execSQL(cmd);
		}		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String txtLog = "Atualizando da versao "+oldVersion+ " para a versao "+newVersion;
		
		for (int j = 0; j < SQL_COMMAND_DELETE.size(); j++) {
			Log.w("Generic", txtLog);
			Log.i("Generic", SQL_COMMAND_DELETE.get(j));
			db.execSQL(SQL_COMMAND_DELETE.get(j));
		}
		onCreate(db);		
	}
	
	protected void setErro(String msg, String local){
		ErroUtil.erroMensagem = msg;
		ErroUtil.erroLocal = local;
	}
	
	
	
	
	
}
