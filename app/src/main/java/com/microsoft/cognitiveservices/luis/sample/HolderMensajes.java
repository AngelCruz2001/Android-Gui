package com.microsoft.cognitiveservices.luis.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class HolderMensajes extends RecyclerView.ViewHolder {
    private TextView Nombre;
    private TextView Mensaje;

    public HolderMensajes(View itemView) {
        super(itemView);
        Nombre=(TextView)itemView.findViewById(R.id.tvNombre);
        Mensaje=(TextView)itemView.findViewById(R.id.tvMensaje);

    }
    public TextView getNombre() {
        return Nombre;
    }

    public void setNombre(TextView nombre) {
        Nombre = nombre;
    }

    public TextView getMensaje() {
        return Mensaje;
    }

    public void setMensaje(TextView mensaje) {
        Mensaje = mensaje;
    }
}
