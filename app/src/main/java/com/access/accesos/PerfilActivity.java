package com.access.accesos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {
    UsuarioDao dAO = new UsuarioDao(this);
    List<String> listaRoles = new ArrayList<>();
    List<Rol> rolesLists;
    ListView listt;
    ArrayList<Usuario> lista;
    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        listt = (ListView) findViewById(R.id.lista);
        /*SBHelper db = new DBHelper(getApplicationContext());
        Usuario user = new Usuario()
        lista = db.misdats(user);
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        listt.setAdapter(adaptador);*/
    }
}