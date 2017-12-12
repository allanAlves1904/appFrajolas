package br.com.frajolas.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list_view;
    ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        list_view = (ListView)findViewById(R.id.list_view);

        adapter = new ProdutoAdapter(this, new ArrayList<Produto>());

        list_view.setAdapter(adapter);

        new AsyncTask<Void, Void, Void>(){

            ArrayList<Produto> lstProdutos = new ArrayList<Produto>();

            @Override
            protected Void doInBackground(Void... voids) {


                String retornoJson = Http.get("http://10.0.2.2/inf3m/TurmaA/projeto/cms/selecionar.php");

                Log.d("TAG:", retornoJson);


                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for(int i=0 ; i < jsonArray.length(); i++){

                        JSONObject item = jsonArray.getJSONObject(i);

                        Produto c = Produto.create(
                                item.getInt("id"),
                                item.getString("nome"),
                                item.getString("preco"),
                                item.getString("descricao"),
                                item.getString("foto")
                        );
                        lstProdutos.add(c);
                    }

                    Log.d("TAG", lstProdutos.size()+"");
                }catch (Exception ex){
                    Log.e("ERRO:",ex.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.addAll(lstProdutos);
            }
        }.execute();

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PizzaStatic.pizza = adapter.getItem(i);
                startActivity(new Intent(MainActivity.this, ScrollingActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.primeira) {
            startActivity(new Intent(this, MapsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
