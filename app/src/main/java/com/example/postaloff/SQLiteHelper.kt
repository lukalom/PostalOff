package com.example.postaloff

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "PostalOff.db"
        const val TABLE_NAME = "Items"
        const val COLUMN_ID = "id"
        const val COLUMN_TRACKING = "tracking"
        const val COLUMN_PRICE = "price"
        const val COLUMN_WEBPAGE = "webpage"
        const val COLUMN_IMAGE_URL = "imageUrl"
        const val COLUMN_DESTINATION = "destination"
        const val COLUMN_COMMENTS = "comments"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TRACKING + " TEXT," + COLUMN_PRICE + " TEXT," + COLUMN_WEBPAGE + " TEXT," + COLUMN_IMAGE_URL + " TEXT," + COLUMN_DESTINATION + " TEXT," + COLUMN_COMMENTS + " TEXT" + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(item: ItemModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(COLUMN_TRACKING, item.tracking)
        contentValues.put(COLUMN_PRICE, item.price)
        contentValues.put(COLUMN_WEBPAGE, item.webpage)
        contentValues.put(COLUMN_IMAGE_URL, item.imageUrl)
        contentValues.put(COLUMN_DESTINATION, item.destination)
        contentValues.put(COLUMN_COMMENTS, item.comments)

        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close();

        return success
    }

    @SuppressLint("Range")
    fun getAllItems(): List<ItemModel> {
        val items = mutableListOf<ItemModel>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return emptyList()
        }

//        var id: Int
        var tracking: String
        var price: String
        var webPage: String
        var imageUrl: String
        var destination: String
        var comments: String

        if (cursor.moveToFirst()) {
            do {
                tracking = cursor.getString(cursor.getColumnIndex("tracking"))
                price = cursor.getString(cursor.getColumnIndex("price"))
                webPage = cursor.getString(cursor.getColumnIndex("webpage"))
                imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"))
                destination = cursor.getString(cursor.getColumnIndex("destination"))
                comments = cursor.getString(cursor.getColumnIndex("comments"))

                val item = ItemModel(
                    tracking = tracking,
                    price = price,
                    webpage = webPage,
                    imageUrl = imageUrl,
                    destination = destination,
                    comments = comments
                )
                items.add(item)

            } while (cursor.moveToNext())
        }
        cursor.close()

        return items
    }
}