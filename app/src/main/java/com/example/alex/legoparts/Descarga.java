package com.example.alex.legoparts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class Descarga extends AsyncTask<String, Void, ArrayList<components>> {

        private Context context;
        private String partes;
        public Descarga(Context context) {this.context = context;}
        private GsonBuilder builder ;
        private Gson gson;

    public Descarga(Context context, String partes, GsonBuilder builder, Gson gson) {
        this.context = context;
        this.partes = partes;
        this.builder = new GsonBuilder();
        this.gson = builder.create();
    }



    ArrayList <components> lista = new ArrayList<>();


    private ProgressDialog pDialog;

    @Override protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.setTitle(R.string.espera);
        String msg = context.getResources().getString(R.string.buscando_piezas);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.show();
    }



    @Override protected ArrayList<components> doInBackground(String... params) {
        int count;
        try {
            URL url = new URL("https://stucom.flx.cat/lego/get_set_parts.php?key=sm3ttsuA3i&format=tsv&set=42066-1");
            URLConnection connection = url.openConnection();
            connection.connect();
            int lengthOfFile = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress("" + (int) ((total * 100) / lengthOfFile));
                output.write(data, 0, count);
            }
            input.close();
            output.flush();
            String xml = new String(output.toByteArray());

            File dir = context.getExternalFilesDir(null);

            File f = new File(dir, "brick.csv");
            f.delete();

            String parrafo[] = xml.split("\n");

            for (String p :parrafo) {

                String columna[] = p.split("\t");
                {
                    components component = new components(columna[0],columna[1],columna[2],columna[3],columna[4]);
                    lista.add(component);

                    //logd per veure que esta fent
                }

            }



        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());

        }

        return lista;
    }



    protected void onProgressUpdate(String... progress) {
        pDialog.setProgress(Integer.parseInt(progress[0]));
    }

    @Override public void onPostExecute(Boolean result) {
        pDialog.dismiss();
        if (listener != null) listener.BrickloadedListener(result);
   }

}