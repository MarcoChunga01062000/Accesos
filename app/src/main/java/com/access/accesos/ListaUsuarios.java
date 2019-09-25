package com.access.accesos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    ListView lv;
    ArrayList<Usuario> lista;
    private List<Usuario> listaList;
    private ListView lista1;
    //ArrayAdapter adaptador;
    private UsuarioAdapter adapter;
    private int idposi;
    private UsuarioDao usuarioDAO;
    private AlertDialog alertDialog, alertconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);
        lv = (ListView) findViewById(R.id.lista);
        /*DBHelper db = new DBHelper(getApplicationContext());
        lista = db.llenar_lv();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adaptador);*/
        usuarioDAO = new UsuarioDao(this);
        listaList = usuarioDAO.listarUsuarios();
        adapter = new UsuarioAdapter(this,listaList);
        lv = (ListView) findViewById(R.id.lista);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposi = position;
        alertDialog.show();
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = listaList.get(idposi).get_id();
        switch (which){
            case 0:
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("USUARIO_ID",id);
                startActivity(intent);
                break;
            case 1:alertconfirm.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                listaList.remove(idposi);
                usuarioDAO.eliminarUsuario(id);
                lista1.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertconfirm.dismiss();break;
        }
    }


}
