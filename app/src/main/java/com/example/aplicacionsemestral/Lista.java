package com.example.aplicacionsemestral;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.aplicacionsemestral.adaptadores.noticiasAdaptador;
import com.example.aplicacionsemestral.objetos.NoticiaVo;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    public Switch mode;
    ArrayList<NoticiaVo> listaNoticias;
    RecyclerView recyclerNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNoticias=new ArrayList<>();
        recyclerNoticias=(RecyclerView) findViewById(R.id.listRecyclerView);
        recyclerNoticias.setLayoutManager(new LinearLayoutManager(this));
        llenarNoticias();
        noticiasAdaptador adaptador=new noticiasAdaptador(listaNoticias);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        recyclerNoticias.setAdapter(adaptador);

        mode = findViewById(R.id.modoOscuro);
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mode.isChecked()){
                    nightMode(0);
                }else{
                    nightMode(1);
                }
            }
        });
    }

    public void nightMode(int mode){
        if(mode==0){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void llenarNoticias(){
        listaNoticias.add(new NoticiaVo("La enfermedad delta a llegado a Chile",R.drawable.noticia1));
        listaNoticias.add(new NoticiaVo("Votantes estadounidenses quieren frenar la influencia de las grandes empresas tecnológicas",R.drawable.noticia2));
    }

    public void noticia1(View v){
        Intent noticia = new Intent(this, noticia1.class);
        startActivity(noticia);
    }



}