package com.example.alumnedam.horario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Basededatos bd =   new Basededatos(this, "Il horario", null, 2);
        //SQLiteDatabase slq= bd.getWritableDatabase();

        Button btn = (Button) findViewById(R.id.btnHorari);
        btn.setOnClickListener(this);

    }

    /**
     *
     * En este metodo haremos el cambio de layout y pasaremos los datos que consideramos relevantes
     * como el nombre el usuario, el color de fuente y el grupo
     * @param view
     */
    @Override
    public void onClick(View view) {
        intent = new Intent(this, horario.class);
        TextView tvNom = (TextView) findViewById(R.id.etNom);
        Spinner spinner = (Spinner) findViewById(R.id.spGrup);
        preferencesGuardar(tvNom, color,spinner);
        intent.putExtra("nom", tvNom.toString());
        intent.putExtra("grup", spinner.getSelectedItem().toString());
        intent.putExtra("color", color);
        startActivity(this.intent);

    }

    /**
     *
     * Este metodo guarda en las Shared preferences el color,nombre y spinner  para ejecutarlo automaticamente
     * la siguiente vez que se inicie
     *
     * @param tvNom
     * @param color
     * @param spinner
     */
    private void preferencesGuardar(TextView tvNom, String color, Spinner spinner) {

        SharedPreferences sp = getSharedPreferences("Config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nom", tvNom.getText().toString());
        editor.putString("color", color);
        editor.putString("spinner", spinner.getSelectedItem().toString());
        editor.commit();

    }

    /**
     * Metodo que sirve para cambiar de color de fuente la informacion que se pasara a la tabla siguiente en
     * el layoute de hoarario
     *
     * @param view
     */
    public void canviColor (View view){

        switch (view.getId())
        {

            case R.id.red_color:
                this.color = "#FC0116";
                break;
            case R.id.blue_color:
                this.color = "#0810F5";
                break;
            case R.id.green_color:
                this.color = "#03FF20";
                break;
        }

    }
}
