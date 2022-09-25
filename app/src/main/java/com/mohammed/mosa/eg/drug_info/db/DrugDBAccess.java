package com.mohammed.mosa.eg.drug_info.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mohammed.mosa.eg.drug_info.utility.Drug;

import java.util.ArrayList;

public class DrugDBAccess {

    public static final String DRUG_TABLE_NAME = "drugs";
    public static final String DRUG_COLM_ID = "ID";
    public static final String DRUG_COLM_FIRST_NAME = "FirstName";
    public static final String DRUG_COLM_LAST_NAME = "LastName";
    public static final String DRUG_COLM_PRICE = "price";
    public static final String DRUG_COLM_COMPANY = "Company";
    public static final String DRUG_COLM_PHARMACOLOGY = "Pharmacology";
    public static final String DRUG_COLM_SRDE = "SRDE";
    public static final String DRUG_COLM_GI = "GI";
    public static final String DRUG_COLM_ROUTE = "Route";

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private static DrugDBAccess instance;

    private DrugDBAccess(Context context){
        this.sqLiteOpenHelper = new DrugDB(context);
    }

    public static DrugDBAccess getInstance(Context context){
        if(instance == null)
            instance = new DrugDBAccess(context);
        return instance;
    }

    public void open(){
        this.database = this.sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        if(this.database != null)
            this.database.close();
    }


    @SuppressLint("Range")
    public ArrayList<Drug> searchAlter(String text, String com){
        String[] values = { text, com};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE "
                + DRUG_COLM_PHARMACOLOGY +  " = ? AND " + DRUG_COLM_COMPANY + " != ? " ;
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }



    @SuppressLint("Range")
    public ArrayList<Drug> searchSmiler(String text){
        String[] values = {text};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE "
                + DRUG_COLM_PHARMACOLOGY +  " = ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }

    @SuppressLint("Range")
    public ArrayList<Drug> searchCostLess(String text, float _price){
        String[] values = { text, _price+""};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE "
                + DRUG_COLM_PHARMACOLOGY +  " = ? AND " + DRUG_COLM_PRICE + " <= ? " ;
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }


    @SuppressLint("Range")
    public ArrayList<Drug> searchByEffect(String text){
        String[] values = {"%" + text + "%"};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE " + DRUG_COLM_PHARMACOLOGY +  " LIKE ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }

    @SuppressLint("Range")
    public ArrayList<Drug> searchByFirstName(String text){
        String[] values = {"%" + text + "%"};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE " + DRUG_COLM_FIRST_NAME +  " LIKE ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }

    @SuppressLint("Range")
    public ArrayList<Drug> searchByPriceAndName(String price_, String text){
        int minus = (int) Float.parseFloat(price_) -1;
        int plus =(int) Float.parseFloat(price_) +1;
        String[] values = {"%" + text+ "%" ,minus+"", plus+""};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME +
                " WHERE " + DRUG_COLM_FIRST_NAME +
                " LIKE ? AND " + DRUG_COLM_PRICE + " BETWEEN ? AND ?";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }

    @SuppressLint("Range")
    public ArrayList<Drug> searchByFirstNameAnyPosition(String text){
        String[] values = {text.replaceAll("\\s+", "%")};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE " + DRUG_COLM_FIRST_NAME +  " LIKE ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }

    @SuppressLint("Range")
    public ArrayList<Drug> searchByCompany(String text){
        String[] values = {"%" + text + "%"};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE " + DRUG_COLM_COMPANY +  " LIKE ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;
        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }

    // TODO: add search and get function
    @SuppressLint("Range")
    public ArrayList<Drug> searchByPrice(float drugPrice){
        int minus = (int) drugPrice -1;
        int plus = (int) drugPrice +1;
        String[] values = {minus+"", plus+""};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE " + DRUG_COLM_PRICE +  " BETWEEN ? AND ?";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;

        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }

    // TODO
    @SuppressLint("Range")
    public ArrayList<Drug> searchByGoogle(String  text){
        String[] values = {"%" + text + "%"};
        String query = "SELECT * FROM " + DRUG_TABLE_NAME + " WHERE " + DRUG_COLM_COMPANY +  " LIKE ? ";
        Cursor cursor = database.rawQuery(query  , values);
        ArrayList<Drug> drugs = new ArrayList<>();
        int id;
        String firstName, lastName, rout, srde, company, gi, pharmacology;
        float price;

        Drug drug;
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(DRUG_COLM_ID));
                firstName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_FIRST_NAME));
                lastName = cursor.getString(cursor.getColumnIndex(DRUG_COLM_LAST_NAME));
                company = cursor.getString(cursor.getColumnIndex(DRUG_COLM_COMPANY));
                price = cursor.getFloat(cursor.getColumnIndex(DRUG_COLM_PRICE));
                rout = cursor.getString(cursor.getColumnIndex(DRUG_COLM_ROUTE));
                srde = cursor.getString(cursor.getColumnIndex(DRUG_COLM_SRDE));
                pharmacology = cursor.getString(cursor.getColumnIndex(DRUG_COLM_PHARMACOLOGY));
                gi = cursor.getString(cursor.getColumnIndex(DRUG_COLM_GI));
                drug = new Drug(id, price, firstName, lastName, company, pharmacology, srde, gi, rout);
                drugs.add(drug);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return drugs;
    }


}
