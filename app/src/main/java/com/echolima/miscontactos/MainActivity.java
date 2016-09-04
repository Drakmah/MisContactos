package com.echolima.miscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        listaContactos = (RecyclerView) findViewById(R.id.rvContactos);
        LinearLayoutManager llm = new LinearLayoutManager(this); // Esta clase es la encargada de darle forma al RecyclerView. En este caso una lista
        llm.setOrientation(LinearLayoutManager.VERTICAL); // Definimos la orientacion Vertical
        listaContactos.setLayoutManager(llm);// Aqui les estamos diciendo al RecyclerView que se comporte como un linear layout

        inicializarListaContactos(); // Llamamos al método que inicializa los contactos (abajo)
        inicializarAdaptador(); // Llamamos al método que inicializa el Adaptador (abajo)





            



        /*
        -----Con este foreach llenamos un Arraylist con los nombres de los contactos para poder mostrarlos luego en la ListView
        ArrayList<String> nombresContacto = new ArrayList<>();
        for (Contacto contacto :contactos) {
            nombresContacto.add(contacto.getNombre());

        ListView lstContactos = (ListView) findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));

        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                intent.putExtra(getResources().getString(R.string.pnombre), contactos.get(i).getNombre());
                intent.putExtra(getResources().getString(R.string.ptelefono), contactos.get(i).getTelefono());   // Tambien se puede poner así : intent.putExtra("telefono", contactos.get(i).getTelefono());
                intent.putExtra(getResources().getString(R.string.pemail), contactos.get(i).getEmail());
                startActivity(intent);
                finish();
            }
        });
        */




    }
    /*
    public void inicializarAdaptador(){
        //Este método sólamente trabaja la instanciacion de ContactoAdaptador;
        // Es decir, creamos un objeto de la clase ContactoAdaptador y lo llenamos con los elementos de la lista

        ContactoAdaptador adaptador = new ContactoAdaptador(contactos);
        listaContactos.setAdapter(adaptador); // Aqui al RecyclerView (listaContactos) se le setea el adaptador
    }*/

    // Métodod inicializarAdaptador añadiendo la Activity activity para hacer clickeables los CardViews

    public ContactoAdaptador adaptador;
    private void inicializarAdaptador(){
        adaptador = new ContactoAdaptador(contactos, this);
        listaContactos.setAdapter(adaptador);
    }

    public void inicializarListaContactos() {
        contactos = new ArrayList<Contacto>();

        contactos.add(new Contacto(R.drawable.gazpacho, "Anahi Salgado", "687791385", "anncode@gmail.com"));
        contactos.add(new Contacto(R.drawable.naranjito, "Pedro Sanchez", "44444444", "pedrosa@gmail.com"));
        contactos.add(new Contacto(R.drawable.pincho, "Mireya Martinez", "33333333", "Mireyama@gmail.com"));
        contactos.add(new Contacto(R.drawable.platanito, "Juan Lopez", "88888888", "Juanlo@gmail.com"));
    }
}
