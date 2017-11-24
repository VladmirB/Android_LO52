package com.example.vladmir.sqlite_proof.Controlleur;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladmir.sqlite_proof.Modele.Client;
import com.example.vladmir.sqlite_proof.Modele.Facture;
import com.example.vladmir.sqlite_proof.R;

public class FormulaireActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText prenomInput,nomInput,quantiteInput;
    TextView prenomView,nomView,quantiteView,tubeView;
    CheckBox paye;
    Spinner tubeReference;
    Button  ajoutFacture;
    private MySQLiteHelper db;
    boolean payeAchat;
    long idTube,idFacture;
    Client cli = new Client();
    final String EXTRA_NOM_CLIENT = "Nom";
    final String EXTRA_ID_FACTURE = "ID";
    final String EXTRA_PRENOM_CLIENT = "Prenom";
    final String EXTRA_REFERENCE_TUBE = "Reference";
    final String EXTRA_QUANTITE_FACTURE = "Quantite";
    final String EXTRA_PAYE_FACTURE = "paye";
    boolean modifFacture = false;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        Intent intent = getIntent();
        context = this;
        db = new MySQLiteHelper(this);


        paye = (CheckBox)findViewById(R.id.payeFormulaire_checkbox);
        prenomInput = (EditText)findViewById(R.id.prenomFormulaire_edittext);
        quantiteInput = (EditText)findViewById(R.id.quantiteFormulaire_edittext);
        nomInput = (EditText)findViewById(R.id.nomFormulaire_edittext);
        ajoutFacture =(Button) findViewById(R.id.ajouterFormulaire_btn);
        prenomView = (TextView)findViewById(R.id.prenomFormulaire_textview);
        quantiteView = (TextView)findViewById(R.id.quantiteFormulaire_textview);
        nomView = (TextView)findViewById(R.id.nomFormulaire_textview);
        tubeView = (TextView)findViewById(R.id.tubeFormulaire_textview);
        tubeReference= (Spinner) findViewById(R.id.tubeFormulaire_Spinner);
        tubeReference.setOnItemSelectedListener(this);

        if (intent != null && intent.getFlags()==40) {
          ///  showMessage("Test",intent.getStringExtra(EXTRA_NOM_CLIENT));
            modifFacture = true;
            nomInput.setText(intent.getStringExtra(EXTRA_NOM_CLIENT));
            prenomInput.setText(intent.getStringExtra(EXTRA_PRENOM_CLIENT));
            quantiteInput.setText(intent.getStringExtra(EXTRA_QUANTITE_FACTURE));
            idFacture= Long.parseLong(intent.getStringExtra(EXTRA_ID_FACTURE));
            viewTubes(Long.parseLong(intent.getStringExtra(EXTRA_REFERENCE_TUBE)));
            if (Integer.parseInt(intent.getStringExtra(EXTRA_PAYE_FACTURE))== 0)
            { paye.setChecked(false);
            }

            else
            {
                paye.setChecked(true);
                ajoutFacture.setEnabled(false);
            }

            nomInput.setEnabled(false);
            prenomInput.setEnabled(false);
            quantiteInput.setEnabled(false);
            tubeReference.setEnabled(false);
           // cli.setmPrenom(prenomInput.getText().toString());
           // cli.setmNom(nomInput.getText().toString());

          // tubeReference.(intent.getStringExtra(EXTRA_REFERENCE_TUBE));

        }
        else
        {
            getPrenomInputText();
            getNomInputText();
            viewTubes();
        }



            addFactureData();


    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();


        switch(view.getId()) {
            case R.id.payeFormulaire_checkbox:
                if (checked)
                    payeAchat = true;

            else

                payeAchat = false;
                break;

        }
    }
    private void addFactureData() {
        ajoutFacture.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (modifFacture )
                        {
                            int a = db.updateFacture(idFacture,true);
                            if (a== -1)
                            {
                                showMessage("Erreur", "Données incorrectes");
                                return;
                            }
                            else
                                showMessage("Succès", "Modification de la facture effectuée");
                            ajoutFacture.setEnabled(false);


                        }
                        else
                        {
                            cli.setmPrenom(prenomInput.getText().toString());
                            cli.setmNom(nomInput.getText().toString());

                            Cursor de = db.getClientData(cli);

                            // Cursor h = d.get
                            if(de.getCount()==0)
                            {
                                showMessage("Erreur","Le nom ou le prénom ne correspond à aucun client dans la base de donnée");
                                return;
                            }
                            else
                            {
                                if (de.moveToFirst())
                                {
                                    if (quantiteInput.getText().toString().length()== 0)
                                    {
                                        showMessage("Oups!!","Veuillez indiquer une quantité");
                                        return;
                                    }
                                    else {
                                        Facture f = new Facture(de.getLong(0), Integer.parseInt(quantiteInput.getText().toString()), payeAchat, idTube);
                                        // insertion des données du formulaire dans la table facture
                                        boolean b = db.insertFactureData(f);
                                        if (b) {
                                            showMessage("Succès", "Création de la nouvelle facture effectuée");
                                            nomInput.setText("");
                                            prenomInput.setText("");
                                            quantiteInput.setText("");
                                            paye.setChecked(false);
                                        }
                                    }
                                }
                                else
                                    showMessage("Erreur","Données incorrectes");

                            }
                        }







                    }

                }
        );
    }

    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
        //Récuperation de l'ID après selection de tube

        Cursor cursor= (Cursor)parent.getItemAtPosition(pos);
        idTube = cursor.getLong(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void viewTubes()
    {

        Cursor data = db.getAllStockData();

        if(data.getCount()==0)
        {
            showMessage("Error","aucun achat n'a été effectué");
            return;

        }
        String[] from = new String[] { "Nom","Reference"};

        int[] to = new int[] { R.id.categorie_formulaireCol_textview,
                R.id.reference_formulaireCol_textview};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                R.layout.activity_formulaire_spinner_lignes, data, from, to, 0);
        //adapter.setDropDownViewResource(android.R.layout.act);
        tubeReference.setAdapter(adapter);



    }

    private void viewTubes(long reference)
    {

        Cursor data = db.getAllStockData(reference);

        if(data.getCount()==0)
        {
            showMessage("Error","aucun achat n'a été effectué");
            return;

        }
        String[] from = new String[] { "Nom","Reference"};

        int[] to = new int[] { R.id.categorie_formulaireCol_textview,
                R.id.reference_formulaireCol_textview};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                R.layout.activity_formulaire_spinner_lignes, data, from, to, 0);
        //adapter.setDropDownViewResource(android.R.layout.act);
        tubeReference.setAdapter(adapter);



    }
    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();

    }

    public void getNomInputText()
    {
        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
            cli.setmNom(nomInput.getText().toString());
            }
        });
    }

    public void getPrenomInputText()
    {
        prenomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
