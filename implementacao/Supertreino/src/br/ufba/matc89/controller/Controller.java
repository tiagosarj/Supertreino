package br.ufba.matc89.controller;

import android.content.Context;

public interface Controller {
	
	public boolean save(Context ctx);
	public void get(long id);
	public void getList(String[] where);

}
