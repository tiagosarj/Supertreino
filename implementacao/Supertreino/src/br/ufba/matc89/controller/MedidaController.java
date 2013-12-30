package br.ufba.matc89.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import br.ufba.matc89.dao.AtletaDAO;
import br.ufba.matc89.dao.MedidaDAO;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Medida;

public class MedidaController extends Activity implements Controller{
	public static Medida medida = new Medida(0,0);

	@Override
	public boolean save(Context ctx) {
		boolean sucess = false;
		
		MedidaDAO dMedida = new MedidaDAO(ctx);
		if(dMedida.save(medida, AtletaDAO.atletaLogado.getId())){
			sucess = true;		
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
	public List<Medida> get(Atleta atleta, Context ctx) {
		
		MedidaDAO daoMedida = new MedidaDAO(ctx);
		List<Medida> medidas = daoMedida.getList(atleta);
		return medidas;
	}
}
