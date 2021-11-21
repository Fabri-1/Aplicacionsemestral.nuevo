package com.example.aplicacionsemestral;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.aplicacionsemestral.R;

public class reproductorMusica extends Thread{

    private int duracionMusica=0;
    private Context contextollamada;
    private MediaPlayer reproducirMusica;

    public reproductorMusica(Context contexto, int duracion){
        super();
        this.duracionMusica = duracion;
        this.contextollamada = contexto;
    }

    @Override
    public void run() {
        super.run();
        reproducirMusica = MediaPlayer.create(this.contextollamada, R.raw.eliminar);
        reproducirMusica.start();

        try {
            Thread.sleep(this.duracionMusica*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reproducirMusica.stop();
    }
}
