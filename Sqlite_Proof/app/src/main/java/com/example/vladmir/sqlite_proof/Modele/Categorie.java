package com.example.vladmir.sqlite_proof.Modele;

/**
 * Created by vladmir on 05/11/17.
 */

public class Categorie {
    private long mId;
    private  int mStock;
    private long mDistributeurID;

    public static final String TABLE_NAME = "Categorie";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Stock";
    public static final String COL_3 = "Distributeur_ID";


    public static final String CATEGORIE_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_3 + " INTEGER NOT NULL, "
            + COL_2 + " INTEGER NOT NULL, FOREIGN KEY(Distributeur_ID) REFERENCES Distributeur(ID));";

    public Categorie() {
    }

    public Categorie( int mStock, long mDistributeurID) {
        this.mStock = mStock;
        this.mDistributeurID = mDistributeurID;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public int getmStock() {
        return mStock;
    }

    public long getmDistributeurID() {
        return mDistributeurID;
    }

    public void setmDistributeurID(long mDistributeurID) {
        this.mDistributeurID = mDistributeurID;
    }

    public void setmStock(int mStock) {
        this.mStock = mStock;
    }
}
