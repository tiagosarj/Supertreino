package br.ufba.matc89.controller;

import android.app.Activity;
import br.ufba.matc89.dao.UsuarioDAO;
import br.ufba.matc89.model.Usuario;

public class Security extends Activity{
	
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
	
	private void setUsuarioLogado(Usuario usuario){
		UsuarioDAO.usuarioLogado = usuario;
	}
}
