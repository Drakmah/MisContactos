package com.echolima.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    // Declaramos las TextView fuera de los métodos para que sean variables globales

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto_detalle);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        // Traemos los Extras de MainActivity mediante un objeto Bundle:
        Bundle parametros = getIntent().getExtras();

        // Catchamos los parametros traidos por el objeto Bundle y los asignamos a variables :

        String nombre = parametros.getString(getResources().getString(R.string.pnombre));
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));// Tambien se puede poner asi: String telefono = parametros.getString("telefono");
        String email = parametros.getString(getResources().getString(R.string.pemail));


        // Seteamos los objetos TextView con los id que hay en los TextView del layout contacto_detalle.xml
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        // Asignamos el texto de los TextViews con el metodo setText
        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
    }


    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString(); // recuperamos el valor de tvTelefono
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono))); // el telefono se tiene que pasar al Intent mediante un recurso Uri


    }

        public void enviaremail(View v){

            String email = tvEmail.getText().toString();
            Intent emailIntent = new Intent((Intent.ACTION_SEND));
            emailIntent.setData(Uri.parse("mail to:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, email); // Enviamos como extra la direccion email del contacto
            emailIntent.setType("message/rfc822"); // le indicamos el tipo de programas que tiene que buscar para ejecutar el intent
            startActivity(Intent.createChooser(emailIntent, "Email")); // lanzamos el intent con un chooser (para elegir aplicacion con la que se envia el mail)

        }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent volver = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(volver);
        }
        return super.onKeyDown(keyCode, event);
    }
}
