package br.ufba.matc89.controller;

import android.app.Activity;
import android.content.Context;
import br.ufba.matc89.dao.AtletaDAO;
import br.ufba.matc89.model.Atleta;

public class AtletaController extends Activity implements Controller{
	public static Atleta atleta = new Atleta("");
	
	public boolean save(Context ctx){
		boolean sucess = false;
		AtletaDAO dAtleta = new AtletaDAO(ctx);
		
		if(dAtleta.save(atleta, Security.getUsuarioLogado().getId())){
			
			MedidaController medidaControl = new MedidaController();
			sucess = medidaControl.save(ctx);
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
	
	public Atleta get(Context ctx){
		
		AtletaDAO dAtleta = new AtletaDAO(ctx);		
		Atleta atleta = dAtleta.getByIdUsuario();
		
		return atleta;
	}
	/**
	 * 
	 * @return um atleta com todos os atributos setados. Esse método pode resolver todos os seus
	 * problemas = )
	 */
	public Atleta montar(Context ctx){
		
		Atleta atleta = get(ctx);
		
		MedidaController medidaControl = new MedidaController();
		
		atleta.setHistoricoMedidas(medidaControl.get(atleta, ctx));	
		
		
		return atleta;
	}
}
