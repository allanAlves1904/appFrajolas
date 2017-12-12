package br.com.frajolas.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity {

    ImageView ft ;
    TextView preco;
    TextView desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ft= (ImageView)findViewById(R.id.ft);
        preco =(TextView)findViewById(R.id.preco);
        desc =(TextView)findViewById(R.id.descri);



        this.setTitle(PizzaStatic.pizza.getNome());
        preco.setText(PizzaStatic.pizza.getPreco());
        desc.setText(PizzaStatic.pizza.getDescricao());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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


        Picasso.with(ScrollingActivity.this)
                .load("http://10.0.2.2/inf3m/TurmaA/projeto/cms/"+ PizzaStatic.pizza.getFoto())
                .into(ft);
    }
}
