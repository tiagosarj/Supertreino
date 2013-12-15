package br.ufba.matc89;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.ufba.matc89.model.Alimento;

public class AlimentoAdapter extends ArrayAdapter<Alimento>{

    private final LayoutInflater inflater;
    private final int resourceId;

    public AlimentoAdapter(Context context, int resource, List<Alimento> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Alimento alimento = getItem(position);

        convertView = inflater.inflate(resourceId, parent, false);

        TextView nome = (TextView) convertView.findViewById(R.id.alimento_nome);
        TextView fonte = (TextView) convertView.findViewById(R.id.alimento_fonte);

        nome.setText(alimento.getNome());
        fonte.setText(alimento.getFonte());

        return convertView;
    } 
}
