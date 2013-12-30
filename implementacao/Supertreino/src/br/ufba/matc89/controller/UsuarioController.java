package br.ufba.matc89.controller;

import android.app.Activity;
import android.content.Context;
import br.ufba.matc89.dao.UsuarioDAO;
import br.ufba.matc89.model.Usuario;

public class UsuarioController extends Activity implements Controller{

	public static Usuario usuario = new Usuario();	
	
	@Override
	public boolean save(Context ctx){
		boolean sucess = false;
		UsuarioDAO dUsuario = new UsuarioDAO(ctx);
		
		if(dUsuario.save(usuario)){
			AtletaController atletaControl = new AtletaController();
			sucess = atletaControl.save(ctx);
		}
		return sucess;
	}

	@Override
	public void get(long id) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void getList(String[] where) {
		// TODO Auto-generated method stub		
	}	
}
