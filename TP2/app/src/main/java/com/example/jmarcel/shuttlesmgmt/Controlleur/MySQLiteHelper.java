package com.example.jmarcel.shuttlesmgmt.Controlleur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.jmarcel.shuttlesmgmt.Modele.Categorie;
import com.example.jmarcel.shuttlesmgmt.Modele.Client;
import com.example.jmarcel.shuttlesmgmt.Modele.Distributeur;
import com.example.jmarcel.shuttlesmgmt.Modele.Facture;
import com.example.jmarcel.shuttlesmgmt.Modele.Tube_Volant;

import static android.content.ContentValues.TAG;


/**
 * Created by vladmir on 04/11/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {


    Categorie categorie = new Categorie();
    Client clientf = new Client();
    Distributeur distributeur = new Distributeur();
    Tube_Volant tube_volant = new Tube_Volant();
    Facture facture = new Facture();
    private static final String DATABASE_NAME = "FFBa.db";
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }


    // Creation de toutes les tables de la basse de donnée
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(clientf.CLIENT_CREATE_TABLE);
        db.execSQL(distributeur.DISTRIBUTEUR_CREATE_TABLE);
        db.execSQL(categorie.CATEGORIE_CREATE_TABLE);
        db.execSQL(tube_volant.TUBE_CREATE_TABLE);
        db.execSQL(facture.FACTURE_CREATE_TABLE);
        ajoutData(db);
        ajoutAchatData(db);


    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + clientf.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + distributeur.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + categorie.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + tube_volant.TABLE_NAME );
        //db.execSQL("DROP TABLE IF EXISTS " + facture.TABLE_NAME + ";");
        onCreate(db);
    }

    //INSERTION
    //Distributeurs
    public void insertDistributeurData(SQLiteDatabase db,Distributeur dist)
    {

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dist.COL_2,dist.getmNom());
        contentValues.put(dist.COL_3,dist.getmAdresse());
        contentValues.put(dist.COL_4,dist.getmContact());
        contentValues.put(dist.COL_5,dist.getmTelephone());
        contentValues.put(dist.COL_6,dist.getmEmail());
        long result = db.insert(dist.TABLE_NAME,null,contentValues);

    }

    //Categorie
    public void insertCategorieData(SQLiteDatabase db,Categorie cat)
    {

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cat.COL_2,cat.getmStock());
        contentValues.put(cat.COL_3,cat.getmDistributeurID());
        long result = db.insert(cat.TABLE_NAME,null,contentValues);

    }

    //Tube volant
    public void insertTube_VolantData(SQLiteDatabase db,Tube_Volant tube)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tube.COL_2,tube.getmReference());
        contentValues.put(tube.COL_3,tube.getmPrix());
        contentValues.put(tube.COL_4,tube.getmSaison());
        contentValues.put(tube.COL_5,tube.getmClassement());
        contentValues.put(tube.COL_6,tube.getmCategorieID());
        long result = db.insert(tube.TABLE_NAME,null,contentValues);

    }

    //client
    public void insertClientData(SQLiteDatabase db,Client cl)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cl.COL_2,cl.getmPrenom());
        contentValues.put(cl.COL_3,cl.getmNom());
        contentValues.put(cl.COL_4,cl.getmType());
        contentValues.put(cl.COL_5,cl.getmAdresse());
        contentValues.put(cl.COL_6,cl.getmTelephone());
        long result = db.insert(cl.TABLE_NAME,null,contentValues);

    }

    //facture
    public boolean insertFactureData(Facture fact)
    {
        int paye;
        if (fact.ismPaye()== true)
            paye = 1;
        else
            paye = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(fact.COL_2,fact.getmClientID());
        contentValues.put(fact.COL_3,fact.getmTubeVolantID());
        contentValues.put(fact.COL_4,fact.getmQuantite());
        contentValues.put(fact.COL_5,paye);
        long result = db.insert(fact.TABLE_NAME,null,contentValues);
        if (result == - 1)
            return  false;
        else
            return  true;

    }

    public boolean insertFactureData(SQLiteDatabase db,Facture fact)
    {
        int paye;
        if (fact.ismPaye()== true)
            paye = 1;
        else
            paye = 0;

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(fact.COL_2,fact.getmClientID());
        contentValues.put(fact.COL_3,fact.getmTubeVolantID());
        contentValues.put(fact.COL_4,fact.getmQuantite());
        contentValues.put(fact.COL_5,paye);
        long result = db.insert(fact.TABLE_NAME,null,contentValues);
        if (result == - 1)
            return  false;
        else
            return  true;

    }


    public Cursor getClientData(Client c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select C.ID as _id, C."+c.COL_2+", C."+ c.COL_3+
                ", C."+ c.COL_4+", C."+ c.COL_5+", C."+c.COL_6+" from "
                +c.TABLE_NAME+ " C WHERE C."+c.COL_2+ "= '"+c.getmPrenom()+"'"+" AND C."
                +c.COL_3+" = '"+c.getmNom()+"'",null);
        return result;
    }
    //Récuperation du stock
    public Cursor getAllStockData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select T.ID as _id, D.Nom, T.Reference, C.Stock from "+categorie.TABLE_NAME+" C, "
                +tube_volant.TABLE_NAME+ " T, " +distributeur.TABLE_NAME+
                " D WHERE D.ID = C.Distributeur_ID AND C.ID = T.Categorie_ID",null);
        return result;
    }
    public Cursor getAllStockData( String reference)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result = db.rawQuery("select T.ID as _id, D.Nom, T.Reference, C.Stock from "+categorie.TABLE_NAME+" C, "
                +tube_volant.TABLE_NAME+ " T, " +distributeur.TABLE_NAME+
                " D WHERE D.ID = C.Distributeur_ID AND C.ID = T.Categorie_ID AND T.Reference= '"+reference+"'",null);
        return result;
    }

    public Cursor getAllAchatsData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select F.ID as _id, C." + clientf.COL_2+
                ", C."+ clientf.COL_3+ ",F."+ facture.COL_4+ "," +
                " T."+ tube_volant.COL_2+ ",T." + tube_volant.COL_3+ ",F."+ facture.COL_5+ " from "+ clientf.TABLE_NAME+" C, "
                +tube_volant.TABLE_NAME+ " T, " + facture.TABLE_NAME+
                " F WHERE F."+ facture.COL_2+" = C."+clientf.COL_1+" AND" +
                " F."+facture.COL_3+" = T."+tube_volant.COL_1,null);
        return result;
    }

    //Mise à jour de la facture(col de signalisation de l'état paye ou non

    public int updateFacture(long ID,boolean p)
    {
        int colPaye;
        if (p == true)
            colPaye = 1;
        else
            colPaye = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Paye", colPaye);
        return db.update("Facture", values, "ID = " +ID, null);

    }


    public void ajoutData(SQLiteDatabase db){

        // Données statiques à inserer
        Distributeur d = new Distributeur("Yonex","8 rue des acacias","contact",457896575L,"lo52@utbm.fr");
        this.insertDistributeurData(db,d);
        this.insertDistributeurData(db,new Distributeur("RSL","9 rue des acacias","contact2",607789575L,"lo53@utbm.fr"));
        this.insertDistributeurData(db,new Distributeur("RSL","9 rue des acacias","contact2",607789575L,"lo53@utbm.fr"));
        this.insertDistributeurData(db,new Distributeur("RSL","9 rue des acacias","contact2",607789575L,"lo53@utbm.fr"));
        this.insertCategorieData(db,new Categorie(500,1));
        this.insertCategorieData(db,new Categorie(5000,2));
        this.insertCategorieData(db,new Categorie(10000,3));
        this.insertCategorieData(db,new Categorie(6000,4));
        //db.insertCategorieData();
        this.insertTube_VolantData(db,new Tube_Volant("AS30",27,"autonne","2017",1));
        this.insertTube_VolantData(db,new Tube_Volant("Grade 3",16.70f,"printemps","2017",2));
        this.insertTube_VolantData(db,new Tube_Volant("Grade A9",13.70f,"hiver","2017",3));
        this.insertTube_VolantData(db,new Tube_Volant("Grade A1",21,"ete","2017",4));

    }

    public void ajoutAchatData(SQLiteDatabase db){

        // Données statiques à inserer
        Client d = new Client("Oscar","MAEL","Asso","Maison du quartier",1478596314);
        this.insertClientData(db,d);
        this.insertClientData(db,new Client("Vlad","BOTON","sport","les forges",987452163));
        this.insertClientData(db,new Client("Marcel","SANI","Galaxy","avengers",89454));
        this.insertClientData(db,new Client("Tony","WILSON","Galaxy","avengers",89454));
        this.insertFactureData(db,new Facture(1,12,true,2));
        this.insertFactureData(db,new Facture(2,20,false,1));
        this.insertFactureData(db,new Facture(3,8,true,4));
        this.insertFactureData(db,new Facture(4,10,false,3));

    }


    public Cursor getStockData(String marq)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result = db.rawQuery("select T.ID as _id, D.Nom, T.Reference, C.Stock from "+categorie.TABLE_NAME+" C, "
                +tube_volant.TABLE_NAME+ " T, " +distributeur.TABLE_NAME+
                " D WHERE D.ID = C.Distributeur_ID AND C.ID = T.Categorie_ID AND D.Nom= '"+marq+"'",null);
        return result;
    }

    public Cursor getMarqueData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result = db.rawQuery("select ID as _id, Nom from "+distributeur.TABLE_NAME,null);
        return result;
    }




}
