package com.example.vladmir.sqlite_proof.Modele;

/**
 * Created by vladmir on 05/11/17.
 * client data
 */

public class Client {
    private long mId;
    private String mPrenom;
    private String mNom;
    private String mType;
    private String mAdresse;
    private long mTelephone;

    public static final String TABLE_NAME = "Client";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Prenom";
    public static final String COL_3 = "Nom";
    public static final String COL_4 = "Type";
    public static final String COL_5 = "Adresse";
    public static final String COL_6 = "Telephone";



    public static final String CLIENT_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, "
            + COL_3 + " TEXT NOT NULL, " + COL_4 + " TEXT NOT NULL, "
            + COL_5 + " TEXT NOT NULL, " + COL_6 + " INTEGER NOT NULL);";

    public Client() {
    }

    public Client(String mPrenom, String mNom, String mType, String mAdresse, long mTelephone) {
        this.mPrenom = mPrenom;
        this.mNom = mNom;
        this.mType = mType;
        this.mAdresse = mAdresse;
        this.mTelephone = mTelephone;
    }

    public Client(String mPrenom, String mNom) {
        this.mPrenom = mPrenom;
        this.mNom = mNom;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmPrenom() {
        return mPrenom;
    }

    public void setmPrenom(String mPrenom) {
        this.mPrenom = mPrenom;
    }

    public String getmNom() {
        return mNom;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmAdresse() {
        return mAdresse;
    }

    public void setmAdresse(String mAdresse) {
        this.mAdresse = mAdresse;
    }

    public long getmTelephone() {
        return mTelephone;
    }

    public void setmTelephone(long mTelephone) {
        this.mTelephone = mTelephone;
    }
}
