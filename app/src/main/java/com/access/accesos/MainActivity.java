package com.access.accesos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsuario, edtClave;
    private UsuarioDao usuarioDAO;
    private RolDao rolDao;
    private int idposi;
    List<Usuario> listaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtClave = (EditText) findViewById(R.id.edtClave);
        usuarioDAO = new UsuarioDao(this);
        rolDao = new RolDao(this);
    }
    public void ingresar(View view){
        String usuario = edtUsuario.getText().toString();
        String clave = edtClave.getText().toString();
        boolean valida = true;
        if(usuario == null || usuario.equals("")){
            valida = false;
            edtUsuario.setError(getString(R.string.Login_validaUsuario));
        }
        if(clave == null || clave.equals("")){
            valida = false;
            edtClave.setError(getString(R.string.Login_validaClave));
        }
        if(valida){
            if (usuarioDAO.logueoUser(usuario, clave) && usuario.equals("marco")) {
                startActivity(new Intent(this, Menu1Activity.class));
                finish();
            }
            else if (usuarioDAO.logueoUser(usuario, clave) && usuario.equals("mija")){
                startActivity(new Intent(this, Menu2Activity.class));
                finish();
            }
            else if (usuarioDAO.logueoUser(usuario, clave) && usuario.equals("gerson")){
                startActivity(new Intent(this, Menu3Activity.class));
                finish();
            }
            else if (usuarioDAO.logueoUser(usuario, clave) && usuario.equals("juan")){
                startActivity(new Intent(this, Menu2Activity.class));
                finish();
            }
            else{
                Mensajes.Msg(this, getString(R.string.msg_login_incorrecto));
            }
            }
        }
    }

