package br.com.frajolas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 16254836 on 28/11/2017.
 */

public class AdapterList extends ArrayAdapter<Pizza> {

    public AdapterList (Context ctx, ArrayList<Pizza> objects){
        super (ctx,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null){
            v= LayoutInflater.from(getContext()).inflate(R.layout.item_list, null);

        }

            Pizza item = getItem(position);
        ImageView img_pizza =(ImageView) v.findViewById(R.id.img_pizza);
        TextView txt_nome_pizza=(TextView) v.findViewById(R.id.txt_nome_pizza);
        RatingBar ratingBar= (RatingBar) v.findViewById(R.id.ratingBar);
        TextView txt_valor =(TextView) v.findViewById(R.id.txt_valor);

        double av = item.getAvaliacao();

        float avaliacao = (float) av;

        txt_nome_pizza.setText(item.getNome());
        ratingBar.setRating(avaliacao);
        txt_valor.setText(item.getPreco().toString());

        Picasso.with(getContext())
                .load("http://10.0.2.2/projeto_frajolas/cms/" + item.getFoto())
                .into(img_pizza);

        return v;

    }
}
