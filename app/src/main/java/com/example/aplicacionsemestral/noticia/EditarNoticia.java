package com.example.aplicacionsemestral.noticia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.aplicacionsemestral.R;
import com.example.aplicacionsemestral.objetos.NoticiaVo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditarNoticia extends AppCompatActivity {

    private EditText noticiaEdit;
    private RadioButton economiaEdit,deporteEdit,internacionalEdit,politicaEdit;
    private String id_noticia_e,noticia_e,foto_e;

    FirebaseDatabase database;
    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_noticia);

        if(null!=this.getIntent()){
            if(null!=this.getIntent().getExtras()){
                Bundle paramEntrada = this.getIntent().getExtras();
                id_noticia_e= paramEntrada.getString("idnoticia");
                noticia_e = paramEntrada.getString("noticia");
                foto_e = paramEntrada.getString("foto");
            }
        }

        noticiaEdit = findViewById(R.id.editNoticia);
        noticiaEdit.setText(noticia_e);

        economiaEdit = findViewById(R.id.economiaEdit);
        deporteEdit = findViewById(R.id.deporteEdit);
        internacionalEdit = findViewById(R.id.internacionalEdit);
        politicaEdit = findViewById(R.id.politicaEdit);

        if(foto_e.toUpperCase().equals("ECONOMIA")){
            economiaEdit.setChecked(true);
            deporteEdit.setChecked(false);
            internacionalEdit.setChecked(false);
            politicaEdit.setChecked(false);
        }else if(foto_e.toUpperCase().equals("DEPORTE")){
            economiaEdit.setChecked(false);
            deporteEdit.setChecked(true);
            internacionalEdit.setChecked(false);
            politicaEdit.setChecked(false);
        }else if(foto_e.toUpperCase().equals("INTERNACIONAL")){
            economiaEdit.setChecked(false);
            deporteEdit.setChecked(false);
            internacionalEdit.setChecked(true);
            politicaEdit.setChecked(false);
        }else if(foto_e.toUpperCase().equals("POLITICA")){
            economiaEdit.setChecked(false);
            deporteEdit.setChecked(false);
            internacionalEdit.setChecked(false);
            politicaEdit.setChecked(true);
        }
    }

    public void editar(View v){
        try {
            FirebaseApp.initializeApp(this);
            database = FirebaseDatabase.getInstance();
            referencia = database.getReference().child("noticia");

            NoticiaVo noticia = new NoticiaVo();
            noticia.setId_noticia(id_noticia_e);
            noticia.setInfo(noticiaEdit.getText().toString());
            if (economiaEdit.isChecked()) {
                noticia.setFoto("ECONOMIA");
            }else if(deporteEdit.isChecked()){
                noticia.setFoto("DEPORTE");
            }else if(internacionalEdit.isChecked()){
                noticia.setFoto("INTERNACIONAL");
            }else if(politicaEdit.isChecked()){
                noticia.setFoto("POLITICA");
            }
            referencia.child(id_noticia_e).setValue(noticia);
            Toast.makeText(this,"Noticia actualizada",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Error actualizando: "+e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void lista(View v){
        Intent i = new Intent(this,Lista.class);
        startActivity(i);
    }
}