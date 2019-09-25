package com.access.accesos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);
    }
    public void gotoperfil(View view){
        Intent i = new Intent(this, PerfilActivity.class);
        startActivity(i);
    }

    public void salir(View view){
        Mensajes.MsgConfirm(this, "Salir", "Desea Salir?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainfin2();
            }
        });
    }
    private void mainfin2() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
