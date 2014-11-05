package com.maxxindev.studia.DataBaseController.SubjectsDB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maxxindev.studia.DataBaseController.DbHelper;

import java.util.ArrayList;

/**
 * Class of Subject's Data Base Manager
 */
public class SubjectDataBaseManager
{
    private static final String DB_NAME = "subjects.sqlite";

    public static final String TABLE_NAME = "subjects";

    public static final String CN_ID = "_id";
    public static final String CN_NAME = "name";
    public static final String CN_CREDIT = "credit";
    public static final String CN_BIBLIOGRAPHY = "bibliography";
    public static final String CN_COLORMARK = "colorMark";

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement, "
            + CN_NAME + " text not null UNIQUE, "
            + CN_BIBLIOGRAPHY + " text, "
            + CN_COLORMARK + " integer, "
            + CN_CREDIT + " text);";

    public static final int COL_ROWID = 0;

    // TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
    public static final int COL_NAME = 1;
    public static final int COL_BIBLIOGRAPHY = 2;
    public static final int COL_CREDIT = 3;
    public static final int COL_COLORMARK = 4;

    private String[] columns = new String[]{CN_ID, CN_NAME, CN_BIBLIOGRAPHY, CN_COLORMARK, CN_CREDIT};

    private final Context context;
    //private DbHelperSubjects helper;
    private DbHelper helper;
    private SQLiteDatabase db;


    /**
     * void to load cursor of subjects data base
     * @return
     */
    public Cursor loadSubjectsCursor ()
    {
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    /**
     * Load cursor of a specific subject
     * @param name
     * @return cursor
     */
    public Cursor searchSubject(String name)
    {
        return db.query(TABLE_NAME, columns, CN_NAME + "=?", new String []{name}, null, null, null);

    }

    //BORRAR EN CASO DE QUE NO SIRVA

    public SubjectDataBaseManager(Context ctx) {
        this.context = ctx;
        helper = new DbHelper(context);
    }

    // Open the database connection.
    public SubjectDataBaseManager open() {
        db = helper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        helper.close();
    }

    //-------------------------------------


    /**
     * content values generator of subjects
     * @param name
     * @param bibliography
     * @param colorMark
     * @param creditUnits
     * @return
     */
    private ContentValues contentValuesGenerator(String name, String bibliography, Integer colorMark, String creditUnits)
    {
        ContentValues values = new ContentValues();
        values.put(CN_NAME, name);
        values.put(CN_BIBLIOGRAPHY, bibliography);
        values.put(CN_COLORMARK, colorMark);
        values.put(CN_CREDIT, creditUnits);

        return values;
    }


    /**
     * void to insert a subject in data base
     * @param name
     * @param bibliography
     * @param creditUnits
     */
    public void insert (String name, String bibliography,  Integer colorMark, String creditUnits)
    {
        open();
        db.insert(TABLE_NAME, null, contentValuesGenerator(name, bibliography, colorMark, creditUnits));
        close();
    }


    //delete voids

    /**
     * Void to delete a Subject by name
     * @param name
     */
    public void delete (String name)
    {
        db.delete(TABLE_NAME, CN_NAME+"=?", new String[]{name});
    }

    //NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOO

    /**
     * Void to Get a specific row (by rowId)
     */
    public Cursor getRow(long rowId) {
        String where = CN_ID + "=" + rowId;
        Cursor c = 	db.query(true, TABLE_NAME, columns,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Void to Return all data in the database.
     */
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, TABLE_NAME, columns,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Void to Change an existing row to be equal to new data.
     */
    public boolean updateRow(long rowId, String name, String bibliography, Integer colorMark, String credits) {
        String where = CN_ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(CN_NAME, name);
        newValues.put(CN_BIBLIOGRAPHY, bibliography);
        newValues.put(CN_COLORMARK, colorMark);
        newValues.put(CN_CREDIT, credits);

        // Insert it into the database.
        return db.update(TABLE_NAME, newValues, where, null) != 0;
    }

    /**
     * Void to Delete a row from the database, by rowId (primary key)
     */
    public boolean deleteRow(long rowId) {
        String where = CN_ID + "=" + rowId;
        return db.delete(DB_NAME, where, null) != 0;
    }

    /**
     * Void to delete all Subjects
     *
     */
    public void deleteAll() {
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(CN_ID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }


    //--------------------------------------------------------

    /**
     * Void to delete multiples subjects
     * @param name1
     * @param name2
     */
    public void multipleDelete (String name1, String name2)
    {
        db.delete(TABLE_NAME, CN_NAME+"IN (?,?)", new String[]{name1, name2});
    }

}
