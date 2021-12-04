package com.example.aplicacionsemestral.adaptadores;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionsemestral.R;
import com.example.aplicacionsemestral.noticia.EditarNoticia;
import com.example.aplicacionsemestral.objetos.NoticiaVo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;

public class noticiasAdaptador extends RecyclerView.Adapter<noticiasAdaptador.ViewHolderNoticias>{

    ArrayList<NoticiaVo> listaNoticias;
    FirebaseDatabase database;
    DatabaseReference referencia;


    public noticiasAdaptador(ArrayList<NoticiaVo> listaNoticias) {
        this.listaNoticias = listaNoticias;
    }

    @NonNull
    @Override
    public ViewHolderNoticias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_select,parent,false);

        ViewHolderNoticias holder=new ViewHolderNoticias(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNoticias holder, int position) {
        NoticiaVo item = listaNoticias.get(position);

        holder.informacion.setText(item.getInfo());
        if(item.getFoto().toUpperCase().equals("ECONOMIA")){
            holder.foto.setImageResource(R.drawable.economiafoto);
            holder.cardView.setCardBackgroundColor(Color.rgb(237,255,63));
        }else if(item.getFoto().toUpperCase().equals("DEPORTE")){
            holder.foto.setImageResource(R.drawable.noticiadeporte);
            holder.cardView.setCardBackgroundColor(Color.rgb(119,255,147));
        }else if(item.getFoto().toUpperCase().equals("INTERNACIONAL")){
            holder.foto.setImageResource(R.drawable.noticiainternacional);
            holder.cardView.setCardBackgroundColor(Color.rgb(46,137,228));
        }else if(item.getFoto().toUpperCase().equals("POLITICA")){
            holder.foto.setImageResource(R.drawable.noticiapolitica);
            holder.cardView.setCardBackgroundColor(Color.rgb(255,94,240));
        }
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editarNoticia = new Intent(v.getContext(), EditarNoticia.class);
                editarNoticia.putExtra("idnoticia",item.getId_noticia().toString());
                editarNoticia.putExtra("noticia",item.getInfo().toString());
                editarNoticia.putExtra("foto",item.getFoto().toString());

                v.getContext().startActivity(editarNoticia);
            }
        });
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseApp.initializeApp(v.getContext());
                database = FirebaseDatabase.getInstance();
                referencia = database.getReference().child("noticia");
                referencia.child(item.getId_noticia()).removeValue();
                noticiasAdaptador.this.listaNoticias.remove(holder.getAdapterPosition());
                noticiasAdaptador.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }


    public class ViewHolderNoticias extends RecyclerView.ViewHolder {

        public TextView informacion;
        public ImageView foto;
        public CardView cardView;
        public ImageButton eliminar,editar;


        public ViewHolderNoticias(@NonNull View itemView) {
            super(itemView);
            informacion=itemView.findViewById(R.id.noticiaDescripcion);
            foto=itemView.findViewById(R.id.noticiaIcono);
            cardView=itemView.findViewById(R.id.cv);
            eliminar=itemView.findViewById(R.id.eliminar);
            editar=itemView.findViewById(R.id.editar);
        }
    }
}
