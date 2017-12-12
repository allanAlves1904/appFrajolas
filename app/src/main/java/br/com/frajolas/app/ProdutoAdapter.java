package br.com.frajolas.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 16254826 on 28/11/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto>{

    public ProdutoAdapter(Context ctx, ArrayList<Produto> lstProdutos) {
        super(ctx, 0, lstProdutos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item,null);
        }

        Produto item = getItem(position);

        TextView txt_nome = v.findViewById(R.id.txt_nome);
        TextView txt_preco = v.findViewById(R.id.txt_preco);
        TextView txt_descri = v.findViewById(R.id.txt_descri);
        ImageView img_foto= v.findViewById(R.id.img_produto);

        txt_nome.setText(item.getNome());
        txt_preco.setText(item.getPreco());
        txt_descri.setText(item.getDescricao());

        Picasso.with(getContext())
                .load("http://10.0.2.2/inf3m/TurmaA/projeto/cms/"+ item.getFoto())
                .into(img_foto);

        return v;
    }

}
