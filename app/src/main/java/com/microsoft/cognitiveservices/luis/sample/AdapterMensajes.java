package com.android.exemple.james;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel E. Retana on 11/07/2018.
 */

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensajes>{

    private List<Mensaje> listaMensajes = new ArrayList<>();
    private Context c;

    public AdapterMensajes(Context c) {
        this.c = c;
    }

    public void addMensaje(Mensaje m){
        listaMensajes.add(m);
        notifyItemInserted(listaMensajes.size());
    }
    @Override
    public HolderMensajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes,parent,false);
        return new HolderMensajes(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensajes holder, int position) {
        holder.getNombre().setText(listaMensajes.get(position).getNombre());
        holder.getMensaje().setText(listaMensajes.get(position).getMensaje());
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }
}
