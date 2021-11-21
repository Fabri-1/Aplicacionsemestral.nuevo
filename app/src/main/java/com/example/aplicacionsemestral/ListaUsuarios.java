package com.example.aplicacionsemestral;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplicacionsemestral.adaptadores.userAdaptador;
import com.example.aplicacionsemestral.esquemas.OperacionesCRUD;
import com.example.aplicacionsemestral.esquemas.User;
import com.example.aplicacionsemestral.objetos.UserVO;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity {

    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        OperacionesCRUD instancia= new OperacionesCRUD(this,"BDTEST",null,11);

        String columnasObtener[] = User.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String valores[] = {};

        List<ContentValues> userObtenidos = instancia.obtenerDatos(columnasObtener, condicion, valores, User.Esquema.TABLA_NAME);

        ArrayList<UserVO> listaUserObtenidos = new ArrayList<>();

        if(null == userObtenidos){
            Toast.makeText(this,"Error al botener usuarios", Toast.LENGTH_SHORT).show();
        }else{
            for(int i=0;i < userObtenidos.size();i++){
                ContentValues auxiliar = userObtenidos.get(i);
                UserVO usuario=new UserVO();

                for(String key: auxiliar.keySet()){
                    switch (key.toString()){
                        case User.Esquema.ID:
                            usuario.setId_usuario(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.NOMBRE:
                            usuario.setNombre((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.APELLIDO:
                            usuario.setApellido((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.CONTRASEÑA:
                            usuario.setContraseña((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.EMAIL:
                            usuario.setEmail((auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.GENERO:
                            usuario.setGenero((auxiliar.get(key).toString()));
                            break;
                    }
                }
                listaUserObtenidos.add(usuario);
            }
            RecyclerView lista = findViewById(R.id.listUser);
            lista.setHasFixedSize(true);

            manejador = new LinearLayoutManager(this);
            adaptador = new userAdaptador(listaUserObtenidos);
            lista.setLayoutManager(manejador);
            lista.setAdapter(adaptador);
        }
    }
}