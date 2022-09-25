package com.mohammed.mosa.eg.drug_info.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DrugDB extends SQLiteAssetHelper {

    public static final int DrugDBVersion = 1;
    public static final String DrugDBName = "drugs.db";

    public DrugDB(Context context) {
        super(context, DrugDBName, null, DrugDBVersion);
    }
}
