package com.example.jmarcel.shuttlesmgmt.Modele;

/**
 * Created by vladmir on 05/11/17.
 */


public class Distributeur {
    private long mId;
    private String mNom;
    private String mAdresse;
    private String mContact;
    private long mTelephone;
    private String mEmail;

    public static final String TABLE_NAME = "Distributeur";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Nom";
    public static final String COL_3 = "Adresse";
    public static final String COL_4 = "Contact";
    public static final String COL_5 = "Telephone";
    public static final String COL_6 = "Email";



    public static final String DISTRIBUTEUR_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, "
            + COL_3 + " TEXT NOT NULL, " + COL_4 + " TEXT NOT NULL, " + COL_5 + " INTEGER NOT NULL, "
            + COL_6 + " TEXT NOT NULL);";

    public Distributeur() {
    }

    public Distributeur(String mNom, String mAdresse, String mContact, long mTelephone, String mEmail) {
        this.mNom = mNom;
        this.mAdresse = mAdresse;
        this.mContact = mContact;
        this.mTelephone = mTelephone;
        this.mEmail = mEmail;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmNom() {
        return mNom;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
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

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
