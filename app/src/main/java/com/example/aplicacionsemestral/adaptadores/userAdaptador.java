package com.example.aplicacionsemestral.adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionsemestral.R;
import com.example.aplicacionsemestral.esquemas.OperacionesCRUD;
import com.example.aplicacionsemestral.esquemas.User;
import com.example.aplicacionsemestral.objetos.UserVO;
import com.example.aplicacionsemestral.reproductorMusica;

import java.util.ArrayList;

public class userAdaptador extends RecyclerView.Adapter<userAdaptador.UsuarioHolder> {

    private ArrayList<UserVO> listaUser;

    public userAdaptador(ArrayList<UserVO> listaUsuariosDesplegar) {
        listaUser = listaUsuariosDesplegar;
    }

    @NonNull
    @Override
    public userAdaptador.UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);

        UsuarioHolder holder = new UsuarioHolder(item);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull userAdaptador.UsuarioHolder holder, int position) {
        UserVO item = listaUser.get(position);

        holder.correo.setText("Correo: "+item.getEmail());
        holder.nombre.setText("Usuario número "+item.getId_usuario()+": "+item.getNombre()+" "+item.getApellido());
        holder.editar.setId(item.getId_usuario());
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editarUsuario = new Intent(v.getContext(), com.example.aplicacionsemestral.editarUsuario.class);

                editarUsuario.putExtra("id",item.getId_usuario());
                editarUsuario.putExtra("nom",item.getNombre().toString());
                editarUsuario.putExtra("ape",item.getApellido().toString());
                editarUsuario.putExtra("con",item.getContraseña().toString());
                editarUsuario.putExtra("mail",item.getEmail().toString());
                editarUsuario.putExtra("gen",item.getGenero().toString());
                v.getContext().startActivity(editarUsuario);
            }
        });
        holder.eliminar.setId(item.getId_usuario());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproductorMusica hiloSegundoPlano=new reproductorMusica(v.getContext(),1);
                hiloSegundoPlano.start();
                String condicion = "id_usuario=?";
                String valores[] = {""+item.getId_usuario()};
                int cant_regs_eliminados=0;

                OperacionesCRUD instancia = new OperacionesCRUD(v.getContext(), "BDTEST",null,11);
                cant_regs_eliminados = instancia.borrarRegistro(User.Esquema.TABLA_NAME,condicion,valores);

                if(cant_regs_eliminados>0){
                    Toast.makeText(v.getContext(),"El usuario a sido eliminado",Toast.LENGTH_SHORT).show();
                    userAdaptador.this.listaUser.remove(holder.getAdapterPosition());
                    userAdaptador.this.notifyDataSetChanged();
                }else{
                    Toast.makeText(v.getContext(),"Error al eliminar el usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaUser.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder{

        public TextView nombre,correo;
        public ImageButton eliminar,detalle,editar;

        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre2);
            correo = itemView.findViewById(R.id.correo2);
            eliminar = itemView.findViewById(R.id.btnBorrar);
            detalle = itemView.findViewById(R.id.btnDetalles);
            editar = itemView.findViewById(R.id.btnEditar);
        }
    }
}
