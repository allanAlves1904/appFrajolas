package br.com.frajolas;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class visualizar_produtos_Activity extends AppCompatActivity {
    TextView txtDescricao,percentual_avaliacaos,total_avaliacaos,preco_promo,preco,categoria;
    RatingBar ratingBar,ratingBarAvaliacao;

    String nomeProduto,descricaoProduto,subCategoria,imagen1;
    Double preco_produto;
    Float avaliacao;
    ImageView img_pizza;
    int idProduto,total;

    AdapterList adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_produtos);

        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        percentual_avaliacaos = (TextView) findViewById(R.id.percentual_avaliacaos);
        total_avaliacaos = (TextView) findViewById(R.id.total_avaliacaos);
        preco_promo = (TextView) findViewById(R.id.preco_promo);
        categoria = (TextView) findViewById(R.id.categoria);
        preco = (TextView) findViewById(R.id.preco);
       img_pizza = (ImageView) findViewById(R.id.img_pizza);
        ratingBarAvaliacao=(RatingBar) findViewById(R.id.ratingBarAvaliacao);



        Intent intent = getIntent();

        if(intent != null){
            nomeProduto = intent.getStringExtra("nomeProduto");
            descricaoProduto = intent.getStringExtra("descricaoProduto");
            subCategoria = intent.getStringExtra("subCategoria");
            imagen1 = intent.getStringExtra("imagen1");
            preco_produto = intent.getDoubleExtra("preco",0);
            avaliacao = (float) intent.getDoubleExtra("percentual",0);
            idProduto = intent.getIntExtra("idProduto",0);
            total = intent.getIntExtra("total_pessoas",0);



            percentual_avaliacaos.setText(avaliacao.toString());
            ratingBar.setRating(avaliacao);
            total_avaliacaos.setText("("+total+")");

            txtDescricao.setText(descricaoProduto);
            categoria.setText(subCategoria);
            preco.setText(preco_produto.toString());

          Picasso.with(this)
                    .load("http://10.0.2.2/projeto_frajolas/cms/" + imagen1)
                    .into(img_pizza);





        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.ligarcao);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url="tel:40028922";

                if (url.startsWith("tel:"))
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
    }

    public void salvar(View view) {

                final String url = "http://10.0.2.2/projeto_frajolas/cms/API/insert.php";

        final HashMap<String, String> valores = new HashMap<>();
        valores.put("idProduto", String.valueOf(idProduto));
        valores.put("avaliacao", String.valueOf(ratingBarAvaliacao.getRating()));

        new AsyncTask<Void, Void, Void>(){
            Boolean sucesso = false;


            @Override
            protected Void doInBackground(Void... voids) {
                String resultado = Http.post(url, valores);

                try {
                    //transformação json para objeto
                    JSONObject jsonObject = new JSONObject(resultado);
                    sucesso = jsonObject.getBoolean("sucesso");
                }catch (Exception ex){
                    ex.printStackTrace();
                    sucesso= false;
                }
                return null;

            }

        }.execute();

        float teste =  ratingBarAvaliacao.getRating();

        Toast.makeText(this,idProduto+" valor estrelas " + teste,Toast.LENGTH_LONG).show();
//        finish();
        startActivity(new Intent(visualizar_produtos_Activity.this, MainActivity.class));


    }

    public void conectar(){
        // conexao com a api

        new AsyncTask<Void,Void,Void>(){

            ArrayList<Pizza> lstpizza = new ArrayList<Pizza>();

            @Override
            protected Void doInBackground(Void... voids) {

                String selectJson = Http.get("http://10.0.2.2/projeto_frajolas/cms/API/produtos.php");
                Log.d("TAG",selectJson);

                try {
                    JSONArray jsonArray = new JSONArray(selectJson);

                    for (int i=0;i < jsonArray.length();i++){
                        JSONObject item = jsonArray.getJSONObject(i);

                        Pizza p= Pizza.create(
                                item.getInt("idProduto"),
                                item.getString("nomeProduto"),
                                item.getDouble("preco"),
                                item.getString("descricaoProduto"),
                                item.getString("imagen1"),
                                item.getString("subCategoria"),
                                item.getDouble("percentual"),
                                item.getInt("total_pessoas")

                        );
                        lstpizza.add(p);
                    }
                } catch (JSONException e) {
                    Log.e("erro",e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                adapter.addAll(lstpizza);
            }
        }.execute();
    }
}
