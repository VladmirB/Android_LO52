package com.example.vladmir.sqlite_proof.Controlleur;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.vladmir.sqlite_proof.Modele.Categorie;
import com.example.vladmir.sqlite_proof.Modele.Client;
import com.example.vladmir.sqlite_proof.Modele.Distributeur;
import com.example.vladmir.sqlite_proof.Modele.Facture;
import com.example.vladmir.sqlite_proof.Modele.Tube_Volant;
import com.example.vladmir.sqlite_proof.R;

public class AchatsActivity extends AppCompatActivity {

    private MySQLiteHelper db;

    ListView list ;
    Context context;
    EditText PrenomInput,RefrenceInput,quantiteInput,prixInput;
    final String EXTRA_NOM_CLIENT = "Nom";
    final String EXTRA_ID_FACTURE = "ID";
    final String EXTRA_PRENOM_CLIENT = "Prenom";
    final String EXTRA_REFERENCE_TUBE = "Reference";
    final String EXTRA_QUANTITE_FACTURE = "Quantite";
    final String EXTRA_PAYE_FACTURE = "paye";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);
        context = this;
        db = new MySQLiteHelper(this);


        list = (ListView)findViewById(R.id.tab_achat_listview);

        viewAll();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Cursor cursor= (Cursor)parent.getItemAtPosition(position);
                Intent intent = new Intent(AchatsActivity.this, FormulaireActivity.class);
                intent.addFlags(40);
                intent.putExtra(EXTRA_NOM_CLIENT, cursor.getString(2));
                intent.putExtra(EXTRA_ID_FACTURE, cursor.getString(0));
                intent.putExtra(EXTRA_PRENOM_CLIENT, cursor.getString(1));
                intent.putExtra(EXTRA_REFERENCE_TUBE, cursor.getString(4));
                intent.putExtra(EXTRA_QUANTITE_FACTURE, cursor.getString(3));
                intent.putExtra(EXTRA_PAYE_FACTURE, cursor.getString(6));
                startActivity(intent);
            }
        });

    }






    private void viewAll()
    {

        Cursor data = db.getAllAchatsData();

        if(data.getCount()==0)
        {
            showMessage("Error","aucun achat n'a été effectué");
            return;

        }

        ListView lv = (ListView) findViewById(R.id.tab_achat_listview);
        ToDoCursorAdapter adapter = new ToDoCursorAdapter(this, data);
       lv.setAdapter(adapter);



    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        viewAll();

    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();

    }
}
