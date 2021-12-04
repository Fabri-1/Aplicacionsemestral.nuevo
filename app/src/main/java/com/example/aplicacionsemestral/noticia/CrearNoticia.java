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

import java.util.UUID;

public class CrearNoticia extends AppCompatActivity {

    private EditText noticia;
    private RadioButton economia,deporte,internacional,politica;

    FirebaseDatabase miBaseDatos;
    DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_noticia);

        noticia = findViewById(R.id.noticiaText);
        economia = findViewById(R.id.tipoEconomia);
        deporte = findViewById(R.id.tipoDeporte);
        internacional = findViewById(R.id.tipoInternacional);
        politica = findViewById(R.id.tipoPolitica);

        FirebaseApp.initializeApp(this);
        miBaseDatos = FirebaseDatabase.getInstance();
        referencia = miBaseDatos.getReference().child("noticia");
    }

    public void insertar(View v){
        try{
            NoticiaVo nuevaNoticia = new NoticiaVo();
            String id_registro = UUID.randomUUID().toString();
            nuevaNoticia.setInfo(noticia.getText().toString());
            if(economia.isChecked()){
                nuevaNoticia.setFoto(economia.getText().toString());
            }else if(deporte.isChecked()){
                nuevaNoticia.setFoto(deporte.getText().toString());
            }else if(internacional.isChecked()){
                nuevaNoticia.setFoto(internacional.getText().toString());
            }else if(politica.isChecked()){
                nuevaNoticia.setFoto(politica.getText().toString());
            }
            nuevaNoticia.setId_noticia(id_registro);

            referencia.child(id_registro).setValue(nuevaNoticia);
            Toast.makeText(this,"Noticia creada, regrese para visualizarla",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void lista(View v){
        Intent i = new Intent(this,Lista.class);
        startActivity(i);
    }
}