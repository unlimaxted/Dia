package com.maxxindev.studia.DataBaseController.TestDB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.maxxindev.studia.DataBaseController.DbHelper;

/**
 * Class of Test's Data Base manager
 */
public class TestDataBaseManager
{
    private static final String DB_NAME = "subjects.sqlite";

    public static final String TABLE_NAME = "tests";
    public static final String TABLE_SUBJECT = "subjects";

    public static final String CN_ID = "_id";
    public static final String CN_NAME = "name";
    public static final String CN_DATE = "date";
    public static final String CN_DAYOFWEEK = "dayOfWeek";
    public static final String CN_HOUR = "hour";
    public static final String CN_SALON = "salon";
    public static final String CN_DESCRIPTION = "description";
    public static final String CN_QUALIFICATION = "qualification";
    public static final String CN_PERCENT = "percent";

    public static final String FK_SUBJECT_ID = "subjectID";
    public static final String FK_SUBJECT_CNID = "_id";

    public static final int COL_ROWID = 0;
    public static final int COL_DATE = 2;



    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_DATE + " text, "
            + CN_DAYOFWEEK + " text, "
            + CN_HOUR + " text, "
            + CN_SALON + " text, "
            + CN_DESCRIPTION + " text, "
            + CN_QUALIFICATION + " integer, "
            + CN_PERCENT + " integer, "
            + FK_SUBJECT_ID + " integer, "
            + "FOREIGN KEY (" + FK_SUBJECT_ID + ") REFERENCES " + TABLE_SUBJECT + "("+ FK_SUBJECT_CNID + "));";

    private String[] columns = new String[]{CN_ID, CN_NAME, CN_DATE, CN_DAYOFWEEK, CN_HOUR, CN_SALON, CN_DESCRIPTION, CN_QUALIFICATION, CN_PERCENT};

    private DbHelper helper;
    private SQLiteDatabase db;
    private final Context context;


    /**
     * data base manager of test
     * @param ctx
     */
    public TestDataBaseManager(Context ctx) {
        this.context = ctx;
        helper = new DbHelper(context);
    }
    // Open the database connection.
    public TestDataBaseManager open() {
        db = helper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        helper.close();
    }

    /**
     * content values generator of Tests
     * @param name
     * @param date
     * @param dayOfWeek
     * @param hour
     * @param salon
     * @param description
     * @param qualification
     * @param percent
     * @param subjectID
     * @return
     */
    private ContentValues contentValuesGenerator(String name, String date, String dayOfWeek, String hour, String salon, String description, Integer qualification, Integer percent, Integer subjectID)
    {
        ContentValues values = new ContentValues();
        values.put(CN_NAME, name);
        values.put(CN_DATE, date);
        values.put(CN_DAYOFWEEK, dayOfWeek);
        values.put(CN_HOUR, hour);
        values.put(CN_SALON, salon);
        values.put(CN_DESCRIPTION, description);
        values.put(CN_QUALIFICATION, qualification);
        values.put(CN_PERCENT, percent);
        values.put(FK_SUBJECT_ID, subjectID);

        return values;
    }

    /**
     * void to insert a Test in data base
     * @param name
     * @param date
     * @param dayOfWeek
     * @param hour
     * @param salon
     * @param description
     * @param qualification
     * @param percent
     * @param subjectID
     */
    public void insert (String name, String date, String dayOfWeek, String hour, String salon, String description, Integer qualification, Integer percent, Integer subjectID)
    {
        open();
        db.insert(TABLE_NAME, null, contentValuesGenerator(name, date, dayOfWeek, hour, salon, description, qualification, percent, subjectID));
        close();
    }


    /**
     * Void to Get a specific row (by rowId)
     */
    public Cursor getRow(long rowId) {
        String where = CN_ID + "=" + rowId;
        String orderBy = CN_DATE;
        Cursor c = 	db.query(true, TABLE_NAME, columns,
                where, null, null, null, orderBy, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Void to Get a specific row by subject (by rowId)
     */
    public Cursor getRowBySubject(long rowId) {
        String where = FK_SUBJECT_ID + "=" + rowId;
        String orderBy = CN_DATE;
        Cursor c = 	db.query(true, TABLE_NAME, columns,
                where, null, null, null, orderBy, null);
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
        String orderBy = CN_DATE;
        Cursor c = 	db.query(true, TABLE_NAME, columns,
                where, null, null, null, orderBy, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * Void to Change an existing row to be equal to new data.
     * @param rowId
     * @param name
     * @param date
     * @param hour
     * @param salon
     * @param qualification
     * @param percent
     * @param subjectID
     * @return
     */
    public boolean updateRow(long rowId, String name, String date, String dayOfWeek, String hour, String salon, String description, Integer qualification, Integer percent, Integer subjectID) {
        String where = CN_ID + "=" + rowId;

        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(CN_NAME, name);
        newValues.put(CN_DATE, date);
        newValues.put(CN_DAYOFWEEK, dayOfWeek);
        newValues.put(CN_HOUR, hour);
        newValues.put(CN_SALON, salon);
        newValues.put(CN_DESCRIPTION, description);
        newValues.put(CN_QUALIFICATION, qualification);
        newValues.put(CN_PERCENT, percent);
        newValues.put(FK_SUBJECT_ID, subjectID);

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

}