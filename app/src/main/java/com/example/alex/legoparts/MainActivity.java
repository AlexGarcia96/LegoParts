package com.example.alex.legoparts;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    protected Button buscar;
    protected TextView editar;
    protected ListView listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editar = (EditText) findViewById(R.id.editar);
        buscar = (Button) findViewById(R.id.buscar);
        listar = (ListView) findViewById(R.id.listar);

        Button buscar = (Button) findViewById(R.id.buscar);
        buscar.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Descarga.class);
                startActivity(intent);
            }

        });



    }

    public class Lego {

        private long id;
        private String name;
        private float price;
        private boolean inStock;
        private int image;

        public Lego(long id, String name, float price, boolean inStock, int image) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.inStock = inStock;
            this.image = image;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public boolean isInStock() {
            return inStock;
        }

        public void setInStock(boolean inStock) {
            this.inStock = inStock;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);

        ListView llista10 = (ListView) findViewById(R.id.llista10);
        List<Lego> dades = new ArrayList<>();


        CatalogAdapter adapter = new CatalogAdapter(this, dades);
        llista10.setAdapter(adapter);
        //esdevenimet creat quan fas click
        llista10.setOnItemClickListener(new AdapterView.OnItemClickListener() {



})

    ;}

    public class CatalogAdapter extends BaseAdapter {

        //accedir als recursos
        private Context context;
        private List<Lego> catalog;

        public CatalogAdapter(Context context, List<Lego> catalog){
            this.context = context;
            this.catalog = catalog;
        }

        @Override
        public int getCount() {
            return catalog.size();
        }

        @Override
        public Object getItem(int position) {
            return catalog.get(position);
        }

        @Override
        public long getItemId(int position) {
            Lego l = catalog.get(position);
            long id = l.getId();
            return id;
        }

        public class ViewHolder {
            public TextView tvNom;
            public TextView tvPrice;
            public ImageView imImage;
            public TextView tvStock;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                //moviment llista mes suau
                LayoutInflater inflater =
                        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.activity_10, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.tvNom = (TextView) myView.findViewById(R.id.nom);
                holder.tvPrice = (TextView) myView.findViewById(R.id.preu);
                holder.imImage = (ImageView) myView.findViewById(R.id.imatge);
                holder.tvStock = (TextView) myView.findViewById(R.id.stock);
                //adjuntar
                myView.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) myView.getTag();


            Lego lego = catalog.get(position);

            String nom = lego.getName();
            holder.tvNom.setText(nom);
            float price = lego.getPrice();
            holder.tvPrice.setText(price + "€");
            int image = lego.getImage();
            holder.imImage.setImageResource(image);
            if (lego.isInStock()){
                holder.tvStock.setText("EN STOCK");
                holder.tvStock.setTextColor(Color.GREEN);
            }
            else {
                holder.tvStock.setText("NO DISPONIBLE");
                holder.tvStock.setTextColor(Color.RED);
            }

            return myView;
        }

    }




}
