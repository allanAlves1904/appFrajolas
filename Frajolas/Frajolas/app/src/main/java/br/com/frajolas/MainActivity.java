package br.com.frajolas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView list_view;
    AdapterList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view=(GridView) findViewById(R.id.gridview);
        adapter=new AdapterList(this,new ArrayList<Pizza>());
        list_view.setAdapter(adapter);

        conectar();

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Pizza item = adapter.getItem(i);

                Intent intent = new Intent(getApplicationContext(),
                        visualizar_produtos_Activity.class);

                intent.putExtra("idProduto", item.getId());
                intent.putExtra("nomeProduto", item.getNome());
                intent.putExtra("preco", item.getPreco());
                intent.putExtra("descricaoProduto", item.getDescricao());
                intent.putExtra("imagen1", item.getFoto());
                intent.putExtra("subCategoria", item.getCategoria());
                intent.putExtra("percentual", item.getAvaliacao());
                intent.putExtra("total_pessoas", item.getTotalAvaliacao());
                startActivity(intent);
            }
        });

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
