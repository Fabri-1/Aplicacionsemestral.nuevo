package com.example.aplicacionsemestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.aplicacionsemestral.esquemas.OperacionesCRUD;
import com.example.aplicacionsemestral.esquemas.User;

public class editarUsuario extends AppCompatActivity {

    private EditText nom,ape,con,mail;
    private RadioButton mas,fem;

    private int id_user_entrada=0;
    private String nom_user_entrada="";
    private String ape_user_entrada="";
    private String con_user_entrada="";
    private String email_user_entrada="";
    private String genero_user_entrada="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        if(null!=this.getIntent()){
            if(null!=this.getIntent().getExtras()){
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_user_entrada = parametrosEntrada.getInt("id");
                nom_user_entrada = parametrosEntrada.getString("nom");
                ape_user_entrada = parametrosEntrada.getString("ape");
                con_user_entrada = parametrosEntrada.getString("con");
                email_user_entrada = parametrosEntrada.getString("mail");
                genero_user_entrada = parametrosEntrada.getString("gen");

            }
        }

        nom = findViewById(R.id.nom);
        nom.setText(nom_user_entrada);

        ape = findViewById(R.id.ape);
        ape.setText(ape_user_entrada);

        con = findViewById(R.id.pass);
        con.setText(con_user_entrada);

        mail = findViewById(R.id.email);
        mail.setText(email_user_entrada);

        mas = findViewById(R.id.mas);
        fem = findViewById(R.id.fem);

        if(genero_user_entrada.toUpperCase().equals("MASCULINO")){
            mas.setChecked(true);
            fem.setChecked(false);
        }else{
            mas.setChecked(false);
            fem.setChecked(true);
        }

    }

    public void editarUsuario(View v){
        OperacionesCRUD instancia = new OperacionesCRUD(this,"BDTEST",null,11);
        ContentValues datosNuevosUsuarios = new ContentValues();
        datosNuevosUsuarios.put("nombre",nom.getText().toString());
        datosNuevosUsuarios.put("apellido",ape.getText().toString());
        datosNuevosUsuarios.put("contraseña",con.getText().toString());
        datosNuevosUsuarios.put("email",mail.getText().toString());

        if(mas.isChecked()){
            datosNuevosUsuarios.put("genero",mas.getText().toString());
        }
        if(fem.isChecked()){
            datosNuevosUsuarios.put("genero",fem.getText().toString());
        }

        String codicion = "id_usuario=?";
        String valores[] = {id_user_entrada+""};
        int cantidad_actualizados = 0;
        cantidad_actualizados = instancia.actualizarRegistro(datosNuevosUsuarios,
                codicion,valores, User.Esquema.TABLA_NAME);

        if(cantidad_actualizados > 0){
            Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Error actualizando usuario", Toast.LENGTH_LONG).show();
        }

        Intent i=new Intent(this,ListaUsuarios.class);
        startActivity(i);

    }















}