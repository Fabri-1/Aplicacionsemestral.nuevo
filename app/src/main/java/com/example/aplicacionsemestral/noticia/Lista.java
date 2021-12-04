package com.example.aplicacionsemestral.noticia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.aplicacionsemestral.R;
import com.example.aplicacionsemestral.adaptadores.noticiasAdaptador;
import com.example.aplicacionsemestral.objetos.NoticiaVo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    public Switch mode;
    private ArrayList<NoticiaVo> listaNoticias;
    private RecyclerView recyclerNoticias;
    private noticiasAdaptador adaptador;

    FirebaseDatabase miBD;
    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        miBD = FirebaseDatabase.getInstance();
        referencia = miBD.getReference().child("noticia");

        recyclerNoticias = findViewById(R.id.listRecyclerView);
        recyclerNoticias.setHasFixedSize(true);
        recyclerNoticias.setLayoutManager(new LinearLayoutManager(this));

        listaNoticias = new ArrayList<>();
        adaptador = new noticiasAdaptador(listaNoticias);
        recyclerNoticias.setAdapter(adaptador);
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaNoticias.clear();

                if(snapshot.exists()){
                    for(DataSnapshot auxiliar:snapshot.getChildren()){
                        NoticiaVo objeto = auxiliar.getValue(NoticiaVo.class);
                        listaNoticias.add(objeto);
                    }
                    adaptador.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
    }

    public void crearnoticia(View v){
        Intent noticia = new Intent(this, CrearNoticia.class);
        startActivity(noticia);
    }




}