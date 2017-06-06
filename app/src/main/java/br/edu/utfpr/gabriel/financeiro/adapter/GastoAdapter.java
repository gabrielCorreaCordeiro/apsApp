package br.edu.utfpr.gabriel.financeiro.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.Normalizer;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.Gasto;
import br.edu.utfpr.gabriel.financeiro.util.FormatAll;

/**
 * Created by gabriel on 27/11/16.
 */

/// referencio a classe genérica AbstractAdapter e implemento seus metodos
public class GastoAdapter extends AbstractAdapter<GastoAdapter.ViewHolder,Gasto>{
    public GastoAdapter(Context context) {
        super(context);
    }

    @Override
    /// onde seto cada item e adiciono os textos
    protected void onBindViewHolder(ViewHolder holder, int position, Gasto item) {
        ///set data a partir de minha nova classe de utilidades :D
        holder.textDia.setText(FormatAll.formatData(item.getData(),FormatAll.FORMATO_DIA));
        holder.textTipoGasto.setText(item.getTipo().getDescricao());
        holder.textValorGasto.setText(FormatAll.formatValor(item.getValor()));

        int mes = Integer.parseInt(FormatAll.formatData(item.getData(),FormatAll.FORMATO_MES));
        holder.textMes.setText(FormatAll.MES[mes-1]);
    }

    @Override
    /// onde inflo o layout de cada item da lista
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.card_gasto,parent,false);
        return new ViewHolder(v);
    }

    class ViewHolder extends AbstractAdapter.AbstractViewHolder {
        TextView textDia,
                textMes,
                textTipoGasto,
                textValorGasto;
        public ViewHolder(View v) {
            super(v);
            textDia=(TextView) v.findViewById(R.id.text_dia);
            textMes=(TextView) v.findViewById(R.id.text_mes);
            textTipoGasto=(TextView) v.findViewById(R.id.text_tipo_gasto);
            textValorGasto=(TextView) v.findViewById(R.id.text_valor_gasto);

        }
    }

}
