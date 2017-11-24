package com.example.jmarcel.shuttlesmgmt.Modele;

/**
 * Created by vladmir on 05/11/17.
 */

public class Tube_Volant {
    private long mId;
    private String mReference;
    private float mPrix;
    private String mSaison;
    private String mClassement;
    private long mCategorieID;

    public static final String TABLE_NAME = "Tube_Volant";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Reference";
    public static final String COL_3 = "Prix";
    public static final String COL_4 = "Saison";
    public static final String COL_5 = "Classement";
    public static final String COL_6 = "Categorie_ID";



    public static final String TUBE_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_6 + " INTEGER NOT NULL, "
            + COL_2 + " INTEGER, " + COL_3 + " REAL NOT NULL, "
            + COL_4 + " TEXT NOT NULL, " + COL_5 + " TEXT NOT NULL, " +
            "FOREIGN KEY(Categorie_ID) REFERENCES Categorie(ID));";

    public Tube_Volant() {
    }

    public Tube_Volant(String mReference, float mPrix, String mSaison, String mClassement, long mCategorieID) {
        this.mReference = mReference;
        this.mPrix = mPrix;
        this.mSaison = mSaison;
        this.mClassement = mClassement;
        this.mCategorieID = mCategorieID;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmReference() {
        return mReference;
    }

    public void setmReference(String mReference) {
        this.mReference = mReference;
    }

    public float getmPrix() {
        return mPrix;
    }

    public void setmPrix(float mPrix) {
        this.mPrix = mPrix;
    }

    public String getmSaison() {
        return mSaison;
    }

    public void setmSaison(String mSaison) {
        this.mSaison = mSaison;
    }

    public String getmClassement() {
        return mClassement;
    }

    public void setmClassement(String mClassement) {
        this.mClassement = mClassement;
    }

    public long getmCategorieID() {
        return mCategorieID;
    }

    public void setmCategorieID(long mCategorieID) {
        this.mCategorieID = mCategorieID;
    }
}
