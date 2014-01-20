package br.ufba.matc89.controller;

import java.util.List;

import android.content.Context;
import br.ufba.matc89.dao.AlimentoDAO;
import br.ufba.matc89.model.Alimento;

public class AlimentoController implements Controller<Alimento>{
    public static Alimento alimento = new Alimento();
    
    public AlimentoController(){
    	alimento.setNome("Frango grelhado");
    	alimento.setFonte("prote�na");
    	alimento.setProteina(22.0);
    	alimento.setCarboidrato(31.0);
    	alimento.setGordura(9.0);
    }
	@Override
	public boolean save(Context ctx) {
		AlimentoDAO dAlimento = new AlimentoDAO(ctx);
		return dAlimento.save(alimento);		
	}

	@Override
	public Alimento get(long id, Context context) {
		
		return null;
	}

	@Override
	public Alimento get(Alimento entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alimento> getList(Alimento entity, Context ctx) {
		AlimentoDAO dAlimento = new AlimentoDAO(ctx);
		List<Alimento> alimentos = dAlimento.getAll();
		
		if(alimentos == null || alimentos.size() < 1){
			dAlimento.save(alimento);
			alimentos = dAlimento.getAll();
		}
		return alimentos;	
	}

}
