package com.example.vladmir.sqlite_proof.Modele;

/**
 * Created by vladmir on 08/11/17.
 */

public class Facture {
    private long mId;
    private long mClientID;
    private int mQuantite;
    private boolean mPaye;
    private long mTubeVolantID;

    public static final String TABLE_NAME = "Facture";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ClientID";
    public static final String COL_3 = "TubeVolantID";
    public static final String COL_4 = "Quantite";
    public static final String COL_5 = "Paye";

    public static final String FACTURE_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_2 + " INTEGER NOT NULL, " + COL_3 + " INTEGER NOT NULL, "
            + COL_4 + " INTEGER, " + COL_5 + " INTEGER DEFAULT 0, " +
            "FOREIGN KEY(ClientID) REFERENCES Client(ID)," +
            "FOREIGN KEY(TubeVolantID) REFERENCES Tube_Volant(ID));";

    public Facture() {
    }

    public Facture(long mClientID, int mQuantite, boolean mPaye, long mTubeVolantID) {
        this.mClientID = mClientID;
        this.mQuantite = mQuantite;
        this.mPaye = mPaye;
        this.mTubeVolantID = mTubeVolantID;
    }

    public long getmClientID() {
        return mClientID;
    }

    public void setmClientID(long mClientID) {
        this.mClientID = mClientID;
    }

    public int getmQuantite() {
        return mQuantite;
    }

    public void setmQuantite(int mQuantite) {
        this.mQuantite = mQuantite;
    }

    public boolean ismPaye() {
        return mPaye;
    }

    public void setmPaye(boolean mPaye) {
        this.mPaye = mPaye;
    }

    public long getmTubeVolantID() {
        return mTubeVolantID;
    }

    public void setmTubeVolantID(long mTubeVolantID) {
        this.mTubeVolantID = mTubeVolantID;
    }
}
