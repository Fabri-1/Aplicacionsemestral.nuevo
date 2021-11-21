package com.example.aplicacionsemestral.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionsemestral.R;
import com.example.aplicacionsemestral.objetos.NoticiaVo;

import java.util.ArrayList;

public class noticiasAdaptador extends RecyclerView.Adapter<noticiasAdaptador.ViewHolderNoticias> implements View.OnClickListener{

    ArrayList<NoticiaVo> listaNoticias;
    private View.OnClickListener listener;

    public noticiasAdaptador(ArrayList<NoticiaVo> listaNoticias) {
        this.listaNoticias = listaNoticias;
    }

    @NonNull
    @Override
    public ViewHolderNoticias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_select,null,false);

        view.setOnClickListener(this);
        return new ViewHolderNoticias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNoticias holder, int position) {
        holder.informacion.setText(listaNoticias.get(position).getInfo());
        holder.foto.setImageResource(listaNoticias.get(position).getFoto());


    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderNoticias extends RecyclerView.ViewHolder {

        TextView informacion;
        ImageView foto;

        public ViewHolderNoticias(@NonNull View itemView) {
            super(itemView);
            informacion=itemView.findViewById(R.id.noticiaDescripcion);
            foto=itemView.findViewById(R.id.noticiaIcono);
        }
    }
}
