package com.access.accesos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private EditText edtNombre, edtUser, edtClave;
    private Spinner comboRol;
    private UsuarioDao usuarioDAO;
    private Usuario usuario;
    private int iduser;
    UsuarioDao dAO = new UsuarioDao(this);
    RolDao rolDao = new RolDao(this);
    List<String> listaUsuarios = new ArrayList<>();
    List<Usuario> usuariosList;
    List<String> listaRoles = new ArrayList<>();
    List<Rol> rolesLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edtNombre = (EditText) findViewById(R.id.txtNombre);
        edtUser = (EditText) findViewById(R.id.txtUser);
        edtClave = (EditText) findViewById(R.id.txtClave);
        comboRol = (Spinner) findViewById(R.id.comborol);

        rolesLists = dAO.listarRoles();
        for (int i=0; i<rolesLists.size(); i++){
            listaRoles.add(rolesLists.get(i).getNombre());
            //Toast.makeText(this, ""+rolesLists.get(i).getNombre(), Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaRoles);
        comboRol.setAdapter(adaptador);

        iduser = getIntent().getIntExtra("USUARIO_ID",0);
        if(iduser > 0){
            Usuario model = dAO.buscarUsuarioPorID(iduser);
            edtNombre.setText(model.getNombres());
            edtUser.setText(model.getUser());
            edtClave.setText(model.getClave());
            setTitle("Actualizar Usuario");
        }
    }
    public void registrarbb(View view){
        String nombre = edtNombre.getText().toString();
        String login = edtUser.getText().toString();
        String clave = edtClave.getText().toString();
        int spinner_pos = comboRol.getSelectedItemPosition()+1;
        DBHelper db = new DBHelper(getApplicationContext());
        String result = db.guardaruser(nombre,login,clave, spinner_pos);
        if(result.equals(null)){
            Mensajes.Msg(this, getString(R.string.msg_user_error));
        }else{
            if(iduser > 0) {
                Mensajes.Msg(this, getString(R.string.msg_user_modificado));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_user_guardado));
            }
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void registrarfe(View view){
        boolean validar = true;
        String nombre = edtNombre.getText().toString();
        String login = edtUser.getText().toString();
        String clave = edtClave.getText().toString();
        int spinner_pos = comboRol.getSelectedItemPosition()+1;
        if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Login_validaNombre));
        }
        if(login == null || login.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Login_validaUsuario));
        }
        if(clave == null || clave.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Login_validaClave));
        }
        if(validar){
            usuario = new Usuario();
            usuario.setNombres(nombre);
            usuario.setUser(login);
            usuario.setClave(clave);
            usuario.setIdrol(spinner_pos);
            if(iduser > 0){
                usuario.set_id(iduser);
            }
            long resultado = dAO.modificarUsuario(usuario);
            if(resultado != -1){
                if(iduser > 0) {
                    Mensajes.Msg(this, getString(R.string.msg_user_modificado));
                }else{
                    Mensajes.Msg(this, getString(R.string.msg_user_guardado));
                }
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_user_error));
            }
        }
    }
}
