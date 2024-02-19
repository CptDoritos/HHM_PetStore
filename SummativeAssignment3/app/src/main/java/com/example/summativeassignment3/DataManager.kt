package com.example.summativeassignment3

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataManager(context: Context){

    private val db: SQLiteDatabase

    init {

        val helper = CustomSQLiteOpenHelper(context)

        db = helper.writableDatabase
    }

    companion object {
        const val TABLE_ROW_ID = "_id"
        const val TABLE_ROW_SPECIES = "species"
        const val TABLE_ROW_NAME = "name"
        const val TABLE_ROW_AGE = "age"
        const val TABLE_ROW_FOOD = "food"

        const val TABLE_CLIENTS_ROW_ID = "_id"
        const val TABLE_CLIENTS_ROW_FIRST_NAME = "firstName"
        const val TABLE_CLIENTS_ROW_LAST_NAME = "lastName"
        const val TABLE_CLIENTS_PAYMENT_METHOD = "paymentMethod"

        const val TABLE_PURCHASES_ROW_ID = "_id"
        const val TABLE_PURCHASES_PET_ID = "petId"
        const val TABLE_PURCHASES_CLIENT_ID = "clientId"
        const val TABLE_PURCHASES_PET_NAME = "petName"
        const val TABLE_PURCHASES_CLIENT_FNAME = "clientFirstName"
        const val TABLE_PURCHASES_CLIENT_LNAME = "clientLastName"

        private const val DB_NAME = "pet_store"
        private const val DB_VERSION = 1
        private const val TABLE_PETS = "pets"
        private const val TABLE_CLIENTS = "clients"
        private const val TABLE_PURCHASES = "purchases"
    }

    fun insertPet(species: String, name: String, age: Int, food: String){
        val query = "INSERT INTO " + TABLE_PETS + " (" +
                TABLE_ROW_SPECIES + ", " +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_AGE + ", " +
                TABLE_ROW_FOOD +
                ") " +
                "VALUES (" +
                "'" + species + "'" + ", " +
                "'" + name + "'" + ", " +
                "'" + age + "'" + ", " +
                "'" + food + "'" +
                ");"
        Log.i("insert() = ", query)
        db.execSQL(query)
    }

    fun insertClient(firstName: String, lastName: String, paymentMethod: String){
        val query = "INSERT INTO " + TABLE_CLIENTS + " (" +
                TABLE_CLIENTS_ROW_FIRST_NAME + ", " +
                TABLE_CLIENTS_ROW_LAST_NAME + ", " +
                TABLE_CLIENTS_PAYMENT_METHOD +
                ") " +
                "VALUES (" +
                "'" + firstName + "'" + ", " +
                "'" + lastName + "'" + ", " +
                "'" + paymentMethod + "'" +
                ");"
        Log.i("insert() = ", query)
        db.execSQL(query)
    }

    fun insertPurchase(petId: Int, clientId: Int, petName: String, clientFirstName: String, clientLastName: String){
        val query = "INSERT INTO " + TABLE_PURCHASES + " (" +
                TABLE_PURCHASES_PET_ID + ", " +
                TABLE_PURCHASES_CLIENT_ID + ", " +
                TABLE_PURCHASES_PET_NAME + ", " +
                TABLE_PURCHASES_CLIENT_FNAME + "," +
                TABLE_PURCHASES_CLIENT_LNAME +
                ") " +
                "VALUES (" +
                "'" + petId + "'" + ", " +
                "'" + clientId + "'" + ", " +
                "'" + petName + "'" + ", " +
                "'" + clientFirstName + "'" + ", " +
                "'" + clientLastName + "'" +
                ");"
        Log.i("insert()", query)
        db.execSQL(query)
    }

    fun deletePet(name: String){
        val query = "DELETE FROM " + TABLE_PETS +
                " WHERE " + TABLE_ROW_NAME +
                " = '" + name + "';"
        Log.i("delete() = ", query)
        db.execSQL(query)
    }

    fun deleteClient(firstName: String, lastName: String){
        val query = "DELETE FROM " + TABLE_CLIENTS +
                " WHERE " + TABLE_CLIENTS_ROW_FIRST_NAME +
                " = '" + firstName + "'" +
                " AND " + TABLE_CLIENTS_ROW_LAST_NAME +
                " = '" + lastName + "';"
        Log.i("delete() = ", query)
        db.execSQL(query)
    }

    fun deletePurchase(petId: Int, clientId: Int){
        val query = "DELETE FROM " + TABLE_PURCHASES +
                " WHERE " + TABLE_PURCHASES_PET_ID +
                " = '" + petId + "'" +
                " AND " + TABLE_PURCHASES_CLIENT_ID +
                " = '" + clientId + "';"
        Log.i("delete()", query)
        db.execSQL(query)
    }

    fun updatePet(id: Int, species: String, name: String, age: String, food: String){
        val query = "UPDATE " + TABLE_PETS +
                " SET " +
                TABLE_ROW_SPECIES + " = '" + species + "', " +
                TABLE_ROW_NAME + " = '" + name + "', " +
                TABLE_ROW_AGE + " = '" + age + "', " +
                TABLE_ROW_FOOD + " = '" + food + "'" +
                " WHERE " + TABLE_ROW_ID + " = '" + id + "';"
        Log.i("update() = ", query)
        db.execSQL(query)
    }

    fun updateClient(id: Int, firstName: String, lastName: String, paymentMethod: String){
        val query1 = "UPDATE " + TABLE_CLIENTS +
                " SET " +
                TABLE_CLIENTS_ROW_FIRST_NAME + " = '" + firstName + "', " +
                TABLE_CLIENTS_ROW_LAST_NAME + " = '" + lastName + "', " +
                TABLE_CLIENTS_PAYMENT_METHOD + " = '" + paymentMethod + "'" +
                " WHERE " + TABLE_CLIENTS_ROW_ID + " = '" + id + "';"
        Log.i("update() = ", query1)
        db.execSQL(query1)
    }

    fun updatePurchase(id: Int, petId: Int, clientId: Int, petName: String, clientFirstName: String, clientLastName: String){
        val query = "UPDATE " + TABLE_PURCHASES +
                " SET " +
                TABLE_PURCHASES_PET_ID + " = '" + petId + "', " +
                TABLE_PURCHASES_CLIENT_ID + " = '" + clientId + "', " +
                TABLE_PURCHASES_PET_NAME + " = '" + petName + "', " +
                TABLE_PURCHASES_CLIENT_FNAME + " = '" + clientFirstName + "', " +
                TABLE_PURCHASES_CLIENT_LNAME + " = '" + clientLastName + "'" +
                " WHERE " + TABLE_PURCHASES_ROW_ID + " = '" + id + "';"
        Log.i("update() = ", query)
        db.execSQL(query)
    }

    fun selectAllPets(): Cursor{
        return db.rawQuery("SELECT *" + " from " +
                            TABLE_PETS, null)
    }

    fun selectAllClients(): Cursor{
        return db.rawQuery("SELECT *" + " from " +
                TABLE_CLIENTS, null)
    }

    fun selectAllPurchases(): Cursor{
        return db.rawQuery("SELECT *" + " from " +
                TABLE_PURCHASES, null)
    }

    fun searchNamePet(name: String): Cursor{
        val query= "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_SPECIES + ", " +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_AGE + ", " +
                TABLE_ROW_FOOD +
                " from " +
                TABLE_PETS + " WHERE " +
                TABLE_ROW_NAME + " = '" + name + "';"
        Log.i("searchName() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchNameClient(firstName: String, lastName: String): Cursor{
        val query= "SELECT " +
                TABLE_CLIENTS_ROW_ID + ", " +
                TABLE_CLIENTS_ROW_FIRST_NAME + ", " +
                TABLE_CLIENTS_ROW_LAST_NAME + ", " +
                TABLE_CLIENTS_PAYMENT_METHOD +
                " from " +
                TABLE_CLIENTS + " WHERE " +
                TABLE_CLIENTS_ROW_FIRST_NAME + " = '" + firstName + "'" +
                " AND " + TABLE_CLIENTS_ROW_LAST_NAME + " = '" + lastName + "';"
        Log.i("searchName() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchPurchaseNamePet(petName: String): Cursor{
        val query = "SELECT " +
                TABLE_PURCHASES_ROW_ID + ", " +
                TABLE_PURCHASES_PET_ID + ", " +
                TABLE_PURCHASES_CLIENT_ID + ", " +
                TABLE_PURCHASES_PET_NAME + ", " +
                TABLE_PURCHASES_CLIENT_FNAME + ", " +
                TABLE_PURCHASES_CLIENT_LNAME +
                " from " +
                TABLE_PURCHASES + " WHERE " +
                TABLE_PURCHASES_PET_NAME + " = '" + petName + "';"
        Log.i("searchName() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchPurchaseFirstAndLastNameClient(clientFirstName: String, clientLastName: String): Cursor{
        val query = "SELECT " +
                TABLE_PURCHASES_ROW_ID + ", " +
                TABLE_PURCHASES_PET_ID + ", " +
                TABLE_PURCHASES_CLIENT_ID + ", " +
                TABLE_PURCHASES_PET_NAME + ", " +
                TABLE_PURCHASES_CLIENT_FNAME + ", " +
                TABLE_PURCHASES_CLIENT_LNAME +
                " from " +
                TABLE_PURCHASES + " WHERE " +
                TABLE_PURCHASES_CLIENT_FNAME + " = '" + clientFirstName + "'" +
                " AND " + TABLE_PURCHASES_CLIENT_LNAME + " = '" + clientLastName + "';"
        Log.i("searchName() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchIdPet(id: Int): Cursor{
        val query= "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_SPECIES + ", " +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_AGE + ", " +
                TABLE_ROW_FOOD +
                " from " +
                TABLE_PETS + " WHERE " +
                TABLE_ROW_ID + " = '" + id + "';"
        Log.i("searchId() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchIdClient(id: Int): Cursor{
        val query= "SELECT " +
                TABLE_CLIENTS_ROW_ID + ", " +
                TABLE_CLIENTS_ROW_FIRST_NAME + ", " +
                TABLE_CLIENTS_ROW_LAST_NAME + ", " +
                TABLE_CLIENTS_PAYMENT_METHOD +
                " from " +
                TABLE_CLIENTS + " WHERE " +
                TABLE_CLIENTS_ROW_ID + " = '" + id + "';"
        Log.i("searchId() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchIdPurchase(id: Int): Cursor{
        val query = "SELECT " +
                TABLE_PURCHASES_ROW_ID + ", " +
                TABLE_PURCHASES_PET_ID + ", " +
                TABLE_PURCHASES_CLIENT_ID + ", " +
                TABLE_PURCHASES_PET_NAME + ", " +
                TABLE_PURCHASES_CLIENT_FNAME + ", " +
                TABLE_PURCHASES_CLIENT_LNAME +
                " from " +
                TABLE_PURCHASES + " WHERE " +
                TABLE_PURCHASES_ROW_ID + " = '" + id + "';"
        Log.i("searchId() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchPurchasePetId(petId: Int): Cursor{
        val query = "SELECT " +
                TABLE_PURCHASES_ROW_ID + ", " +
                TABLE_PURCHASES_PET_ID + ", " +
                TABLE_PURCHASES_CLIENT_ID + ", " +
                TABLE_PURCHASES_PET_NAME + ", " +
                TABLE_PURCHASES_CLIENT_FNAME + ", " +
                TABLE_PURCHASES_CLIENT_LNAME +
                " from " +
                TABLE_PURCHASES + " WHERE " +
                TABLE_PURCHASES_PET_ID + " = '" + petId + "';"
        Log.i("searchId() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchPurchaseClientId(clientId: Int): Cursor{
        val query = "SELECT " +
                TABLE_PURCHASES_ROW_ID + ", " +
                TABLE_PURCHASES_PET_ID + ", " +
                TABLE_PURCHASES_CLIENT_ID + ", " +
                TABLE_PURCHASES_PET_NAME + ", " +
                TABLE_PURCHASES_CLIENT_FNAME + ", " +
                TABLE_PURCHASES_CLIENT_LNAME +
                " from " +
                TABLE_PURCHASES + " WHERE " +
                TABLE_PURCHASES_CLIENT_ID + " = '" + clientId + "';"
        Log.i("searchId() = ", query)
        return db.rawQuery(query, null)
    }

    fun resetTablePets(){
        val query = "DROP TABLE " + TABLE_PETS + ";"
        db.execSQL(query)

        val query1 = ("CREATE TABLE "
                + TABLE_PETS + " ("
                + TABLE_ROW_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + TABLE_ROW_SPECIES
                + " TEXT NOT NULL,"
                + TABLE_ROW_NAME
                + " TEXT NOT NULL,"
                + TABLE_ROW_AGE
                + " INTEGER NOT NULL,"
                + TABLE_ROW_FOOD
                + " TEXT NOT NULL);")
        db.execSQL(query1)
    }

    fun resetTableClients(){
        val query = "DROP TABLE " + TABLE_CLIENTS + ";"
        db.execSQL(query)

        val query1 = ("CREATE TABLE "
                + TABLE_CLIENTS + " ("
                + TABLE_CLIENTS_ROW_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + TABLE_CLIENTS_ROW_FIRST_NAME
                + " TEXT NOT NULL,"
                + TABLE_CLIENTS_ROW_LAST_NAME
                + " TEXT NOT NULL,"
                + TABLE_CLIENTS_PAYMENT_METHOD
                + " TEXT NOT NULL);")
        db.execSQL(query1)
    }

    fun resetTablePurchases(){
        val query = "DROP TABLE " + TABLE_PURCHASES + ";"
        db.execSQL(query)

        val query1 = ("CREATE TABLE "
                + TABLE_PURCHASES + " ("
                + TABLE_PURCHASES_ROW_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + TABLE_PURCHASES_PET_ID
                + " INTEGER NOT NULL,"
                + TABLE_PURCHASES_CLIENT_ID
                + " INTEGER NOT NULL,"
                + TABLE_PURCHASES_PET_NAME
                + " TEXT NOT NULL,"
                + TABLE_PURCHASES_CLIENT_FNAME
                + " TEXT NOT NULL,"
                + TABLE_PURCHASES_CLIENT_LNAME
                + " TEXT NOT NULL);")
        db.execSQL(query1)
    }

    private inner class CustomSQLiteOpenHelper(
        context: Context
    ) : SQLiteOpenHelper(
        context, DB_NAME,
        null, DB_VERSION
    ) {
        override fun onCreate(db: SQLiteDatabase) {
            val newTableQueryStringPets = ("CREATE TABLE "
                    + TABLE_PETS + " ("
                    + TABLE_ROW_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + TABLE_ROW_SPECIES
                    + " TEXT NOT NULL,"
                    + TABLE_ROW_NAME
                    + " TEXT NOT NULL,"
                    + TABLE_ROW_AGE
                    + " INTEGER NOT NULL,"
                    + TABLE_ROW_FOOD
                    + " TEXT NOT NULL);")
            db.execSQL(newTableQueryStringPets)

            val newTableQueryStringClients = ("CREATE TABLE "
                    + TABLE_CLIENTS + " ("
                    + TABLE_CLIENTS_ROW_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + TABLE_CLIENTS_ROW_FIRST_NAME
                    + " TEXT NOT NULL,"
                    + TABLE_CLIENTS_ROW_LAST_NAME
                    + " TEXT NOT NULL,"
                    + TABLE_CLIENTS_PAYMENT_METHOD
                    + " TEXT NOT NULL);")
            db.execSQL(newTableQueryStringClients)

            val newTableQueryStringPurchases = ("CREATE TABLE "
                    + TABLE_PURCHASES + " ("
                    + TABLE_PURCHASES_ROW_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + TABLE_PURCHASES_PET_ID
                    + " INTEGER NOT NULL,"
                    + TABLE_PURCHASES_CLIENT_ID
                    + " INTEGER NOT NULL,"
                    + TABLE_PURCHASES_PET_NAME
                    + " TEXT NOT NULL,"
                    + TABLE_PURCHASES_CLIENT_FNAME
                    + " TEXT NOT NULL,"
                    + TABLE_PURCHASES_CLIENT_LNAME
                    + " TEXT NOT NULL);")
            db.execSQL(newTableQueryStringPurchases)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        }
    }
}