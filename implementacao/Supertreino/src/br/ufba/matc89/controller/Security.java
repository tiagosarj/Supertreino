package br.ufba.matc89.controller;

import android.app.Activity;
import br.ufba.matc89.dao.UsuarioDAO;
import br.ufba.matc89.model.Usuario;

public class Security extends Activity{
	private static Usuario usuarioLogado;

	public boolean logar(Usuario usuario){
		boolean validado = false;
		
		UsuarioDAO dUsuario = new UsuarioDAO(this);
		dUsuario.get(usuario);
		
		if(usuario.getId() > 0){
			validado = true;
			setUsuarioLogado(usuario);
		}
		
		return validado;
	}
	
	public static void setUsuarioLogado(Usuario usuario){
		usuarioLogado = usuario;
	}
	
	public static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}
}
