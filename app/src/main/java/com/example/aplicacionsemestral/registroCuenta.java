package com.example.aplicacionsemestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionsemestral.esquemas.OperacionesCRUD;
import com.example.aplicacionsemestral.esquemas.User;
import com.google.android.material.textfield.TextInputLayout;

public class registroCuenta extends AppCompatActivity {

    private EditText nombre, apellido, contraseña, correo;
    private RadioButton fem,mas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);
        nombre = findViewById(R.id.textNombre);
        apellido = findViewById(R.id.textApellido);
        contraseña = findViewById(R.id.textContraseña);
        correo = findViewById(R.id.correo);
        fem = findViewById(R.id.botonFemenino);
        mas = findViewById(R.id.botonMasculino);

    }

    public void crearCuenta(View v){
        if(validar()){
            OperacionesCRUD instacia = new OperacionesCRUD(this, "BDTEST", null, 11);

            ContentValues datosUsuario = new ContentValues();
            datosUsuario.put(User.Esquema.NOMBRE, nombre.getText().toString());
            datosUsuario.put(User.Esquema.APELLIDO, apellido.getText().toString());
            datosUsuario.put(User.Esquema.CONTRASEÑA, contraseña.getText().toString());
            datosUsuario.put(User.Esquema.EMAIL, correo.getText().toString());
            if(fem.isChecked()){
                datosUsuario.put(User.Esquema.GENERO,fem.getText().toString());
            }else if (mas.isChecked()){
                datosUsuario.put(User.Esquema.GENERO,mas.getText().toString());
            }

            long id_insertada = instacia.insertTabla(datosUsuario, User.Esquema.TABLA_NAME);

            Toast.makeText(this,"Cuenta creada",Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,menuPrincipal.class);
            i.putExtra("datos",nombre.getText().toString());
            startActivity(i);
        }else{
            Toast.makeText(this,"Revise los datos obligatorios",Toast.LENGTH_LONG).show();
        }
    }

    public boolean validar(){
        boolean retorno=true;
        String c1=nombre.getText().toString();
        String c2=apellido.getText().toString();
        String c3=contraseña.getText().toString();
        String c4=correo.getText().toString();


        if(c1.isEmpty()){
            nombre.setError("Debe ingresar un nombre");
            retorno=false;
        }
        if(c2.isEmpty()){
            apellido.setError("Debe ingresar un apellido");
            retorno=false;
        }
        if(c3.isEmpty()){
            contraseña.setError("Debe ingresar una contraseña");
            retorno=false;
        }
        if(c4.isEmpty()){
            correo.setError("Debe ingresar un correo electronico");
            retorno=false;
        }

        return retorno;
    }

}