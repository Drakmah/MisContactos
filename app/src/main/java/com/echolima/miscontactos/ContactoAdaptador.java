package com.echolima.miscontactos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Fernando on 31/08/2016.
 */
public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder>{

    // Declaramos un arraylist de una coleccion de datos (clase Contacto) - como es una coleccion de datos se pone entre <>
    ArrayList<Contacto> contactos;
    Activity activity;


    // Metodo constructor, al cual se le pasa una lista de contactos que declaramos justo arriba de él
    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity = activity;
    }




    @Override
    // Infla el layout  y lo pasará al viewholder para que éste obtenga los views
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Aqui asociamos el layout cardview_contacto.xml al RecyclerView
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override
    //Asocia cada elemento de la vista con cada view
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        // Aqui seteamos los valores de cada objeto de la lista
        final Contacto contacto = contactos.get(position); // Obtenemos cada objeto que se está iterando
        holder.imgfotoCV.setImageResource(contacto.getFoto());
        holder.tvNombreCV.setText(contacto.getNombre());
        holder.tvTelefonoCV.setText(contacto.getTelefono());

        holder.imgfotoCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aqui ponemos el código que se ejecuta al clickear
                Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleContacto.class);
                intent.putExtra("nombre", contacto.getNombre());
                intent.putExtra("telefono", contacto.getTelefono());
                intent.putExtra("email", contacto.getEmail());
                activity.startActivity(intent);

            }
        });

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Código a ejecutar
                Toast.makeText(activity, "Diste like a " + contacto.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {// Cantidad de elementos que contiene la lista de contactos
        return contactos.size();
    }

    // Desde aqui se maneja los elementos VewHolder que hemos seteado abajo


    public static class ContactoViewHolder extends RecyclerView.ViewHolder{ // Al ser una clase "static" , para accesar a ella tendremos que escribir el nombre de la clase padre ---ver arriba


        // Aqui se declaran todos los Views de la tarjeta (cardView_contacto.xml)

        private ImageView imgfotoCV;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;
        private ImageButton btnLike;


            public ContactoViewHolder(View itemView){ // Método Constructor de la clase
                super(itemView);

                // Aquí seteamos las vistas de los Views de la tarjeta
                imgfotoCV       =   (ImageView) itemView.findViewById(R.id.ivfotoCard);
                tvNombreCV      =   (TextView) itemView.findViewById(R.id.tvNombreCard);
                tvTelefonoCV    =   (TextView) itemView.findViewById(R.id.tvTelefonoCard);
                btnLike         =   (ImageButton) itemView.findViewById(R.id.btnLike);

            }


    }
}
