package com.example.aplicacionsemestral;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class menuPrincipal extends AppCompatActivity {

    private TextView mensaje;
    private SensorManager manejadorSensores;
    private Sensor sensorTemperatura;
    private ProgressBar termometro;
    private TextView mostrar;
    private Button admin,reg;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        mensaje = findViewById(R.id.mensajeMenu);
        termometro = findViewById(R.id.progressBar);
        admin = findViewById(R.id.users);
        reg = findViewById(R.id.registro);
        termometro.setMin(-273);
        termometro.setMax(100);
        termometro.incrementProgressBy(1);

        mostrar = findViewById(R.id.temperatura);

        manejadorSensores = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorTemperatura = manejadorSensores.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if(sensorTemperatura == null){
            System.out.println("No existe el sensor de temperatura");
            finish();
        }

        String datos = getIntent().getStringExtra("datos");

        if(datos == null){
            mensaje.setText("Bienvenido ");
            admin.setVisibility(View.INVISIBLE);
        }else{
            mensaje.setText("Bienvenido "+datos);
            admin.setVisibility(View.VISIBLE);
            reg.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onPause() {
        manejadorSensores.unregisterListener(capturaTemperatura);
        super.onPause();
    }

    @Override
    protected void onResume() {
        manejadorSensores.registerListener(capturaTemperatura, sensorTemperatura, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    public void registro(View v){
        Intent intent = new Intent(this,registroCuenta.class);
        startActivity(intent);
    }

    public void lista(View v){
        Intent intent=new Intent(this, Lista.class);
        startActivity(intent);
    }
    public void menuNoticia(View v){
        Intent intent = new Intent(this,noticiaMenu.class);
        startActivity(intent);
    }
    public void listaUsuarios(View v){
        Intent intent = new Intent(this,ListaUsuarios.class);
        startActivity(intent);
    }
    public void mapa(View v){
        Intent intent = new Intent(this, com.example.aplicacionsemestral.mapa.mapaGlobal.class);
        startActivity(intent);
    }

    private SensorEventListener capturaTemperatura= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            System.out.println(""+sensorEvent.values[0]);
            mostrar.setText(""+sensorEvent.values[0]+" °C");
            termometro.setProgress((int)sensorEvent.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}