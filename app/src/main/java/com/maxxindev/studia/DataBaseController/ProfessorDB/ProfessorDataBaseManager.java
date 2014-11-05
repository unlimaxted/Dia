package com.maxxindev.studia.DataBaseController.ProfessorDB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maxxindev.studia.DataBaseController.DbHelper;

/**
 * Class of Professor's data base manager
 */
public class ProfessorDataBaseManager
{
    private static final String DB_NAME = "subjects.sqlite";

    public static final String TABLE_NAME = "professors";
    public static final String TABLE_SUBJECT = "subjects";


    public static final String CN_ID = "_id";
    public static final String CN_NAME = "name";
    public static final String CN_EMAIL = "email";
    public static final String CN_PHONENUMBER = "phoneNumber";
    public static final String CN_OFFICE = "office";

    public static final String CN_MONDAY = "Monday";
    public static final String CN_TUESDAY = "Tuesday";
    public static final String CN_WEDNESDAY = "Wednesday";
    public static final String CN_THURSDAY = "Thursday";
    public static final String CN_FRIDAY = "Friday";
    public static final String CN_SATURDAY = "Saturday";
    public static final String CN_SUNDAY = "Sunday";

    public static final String CN_MONDAY_HOUR = "MondayHour";
    public static final String CN_TUESDAY_HOUR = "TuesdayHour";
    public static final String CN_WEDNESDAY_HOUR = "WednesdayHour";
    public static final String CN_THURSDAY_HOUR = "ThursdayHour";
    public static final String CN_FRIDAY_HOUR = "FridayHour";
    public static final String CN_SATURDAY_HOUR = "SaturdayHour";
    public static final String CN_SUNDAY_HOUR = "SundayHour";

    public static final String FK_SUBJECT_ID = "subjectID";
    public static final String FK_SUBJECT_CNID = "_id";


    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_EMAIL + " text, "
            + CN_PHONENUMBER + " text, "
            + CN_OFFICE + " text, "
            + CN_MONDAY + " bit, "
            + CN_TUESDAY + " bit, "
            + CN_WEDNESDAY + " bit, "
            + CN_THURSDAY + " bit, "
            + CN_FRIDAY + " bit, "
            + CN_SATURDAY + " bit, "
            + CN_SUNDAY + " bit, "
            + CN_MONDAY_HOUR + " text, "
            + CN_TUESDAY_HOUR + " text, "
            + CN_WEDNESDAY_HOUR + " text, "
            + CN_THURSDAY_HOUR + " text, "
            + CN_FRIDAY_HOUR + " text, "
            + CN_SATURDAY_HOUR + " text, "
            + CN_SUNDAY_HOUR + " text, "
            + FK_SUBJECT_ID + " integer, "
            + "FOREIGN KEY (" + FK_SUBJECT_ID + ") REFERENCES " + TABLE_SUBJECT + "("+ FK_SUBJECT_CNID + "));";


    // TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
    public static final int COL_ROWID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_EMAIL = 2;
    public static final int COL_PHONENUMBER = 3;
    public static final int COL_OFFICE = 4;
    public static final int COL_MONDAY = 5;
    public static final int COL_TUESDAY = 6;
    public static final int COL_WEDNESDAY = 7;
    public static final int COL_THURSDAY = 8;
    public static final int COL_FRIDAY = 9;
    public static final int COL_SATURDAY = 10;
    public static final int COL_SUNDAY = 11;
    public static final int COL_MONDAY_HOUR = 12;
    public static final int COL_TUESDAY_HOUR = 13;
    public static final int COL_WEDNESDAY_HOUR = 14;
    public static final int COL_THURSDAY_HOUR = 15;
    public static final int COL_FRIDAY_HOUR= 16;
    public static final int COL_SATURDAY_HOUR = 17;
    public static final int COL_SUNDAY_HOUR = 18;


    private String[] columns = new String[]{CN_ID, CN_NAME, CN_EMAIL, CN_PHONENUMBER, CN_OFFICE, CN_MONDAY,
            CN_TUESDAY, CN_WEDNESDAY, CN_THURSDAY, CN_FRIDAY, CN_SATURDAY, CN_SUNDAY, CN_MONDAY_HOUR, CN_TUESDAY_HOUR,
            CN_WEDNESDAY_HOUR, CN_THURSDAY_HOUR, CN_FRIDAY_HOUR, CN_SATURDAY_HOUR, CN_SUNDAY_HOUR};

    private DbHelper helper;
    private SQLiteDatabase db;
    private final Context context;

    /**
     * void to load cursor of professor's data base
     * @return
     */
    public Cursor loadProfessorsCursor ()
    {
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    /**
     * Load cursor of a specific professor
     * @param name
     * @return
     */
    public Cursor searchProfessor(String name)
    {
        return db.query(TABLE_NAME, columns, CN_NAME + "=?", new String []{name}, null, null, null);

    }


    public ProfessorDataBaseManager(Context ctx) {
        this.context = ctx;
        helper = new DbHelper(context);
    }

    // Open the database connection.
    public ProfessorDataBaseManager open() {
        db = helper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        helper.close();
    }

    /**
     * content values generator of professor
     * @param name
     * @param email
     * @param phoneNumber
     * @param office
     * @param monday
     * @param tuesday
     * @param wednesday
     * @param thursday
     * @param friday
     * @param saturday
     * @param sunday
     * @param mondayHour
     * @param tuesdayHour
     * @param wednesdayHour
     * @param thursdayHour
     * @param fridayHour
     * @param saturdayHour
     * @param sundayHour
     * @return
     */
    private ContentValues contentValuesGenerator(String name, String email, String phoneNumber, String office,
                                                 Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday,
                                                 Boolean saturday, Boolean sunday, String mondayHour, String tuesdayHour, String wednesdayHour,
                                                 String thursdayHour, String fridayHour, String saturdayHour, String sundayHour, Integer subjectID)
    {
        ContentValues values = new ContentValues();
        values.put(CN_NAME, name);
        values.put(CN_EMAIL, email);
        values.put(CN_PHONENUMBER, phoneNumber);
        values.put(CN_OFFICE, office);
        values.put(CN_MONDAY, monday);
        values.put(CN_TUESDAY, tuesday);
        values.put(CN_WEDNESDAY, wednesday);
        values.put(CN_THURSDAY, thursday);
        values.put(CN_FRIDAY, friday);
        values.put(CN_SATURDAY, saturday);
        values.put(CN_SUNDAY, sunday);
        values.put(CN_MONDAY_HOUR, mondayHour);
        values.put(CN_TUESDAY_HOUR, tuesdayHour);
        values.put(CN_WEDNESDAY_HOUR, wednesdayHour);
        values.put(CN_THURSDAY_HOUR, thursdayHour);
        values.put(CN_FRIDAY_HOUR, fridayHour);
        values.put(CN_SATURDAY_HOUR, saturdayHour);
        values.put(CN_SUNDAY_HOUR, sundayHour);
        values.put(FK_SUBJECT_ID, subjectID);


        return values;
    }

    /**
     * void to insert a professor in data base
     * @param name
     * @param email
     * @param phoneNumber
     * @param office
     * @param monday
     * @param tuesday
     * @param wednesday
     * @param thursday
     * @param friday
     * @param saturday
     * @param sunday
     * @param mondayHour
     * @param tuesdayHour
     * @param wednesdayHour
     * @param thursdayHour
     * @param fridayHour
     * @param saturdayHour
     * @param sundayHour
     */
    public void insert (String name, String email, String phoneNumber, String office,
                        Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday,
                        Boolean saturday, Boolean sunday, String mondayHour, String tuesdayHour, String wednesdayHour,
                        String thursdayHour, String fridayHour, String saturdayHour, String sundayHour, Integer subjectID)
    {
        open();
        db.insert(TABLE_NAME, null, contentValuesGenerator(name, email, phoneNumber, office, monday, tuesday, wednesday, thursday, friday,
                saturday, sunday, mondayHour, tuesdayHour, wednesdayHour, thursdayHour, fridayHour, saturdayHour, sundayHour, subjectID));
        close();
    }


    //delete voids

    /**
     * Void to delete a professor by name
     * @param name
     */
    public void delete (String name)
    {

        db.delete(TABLE_NAME, CN_NAME+"=?", new String[]{name});
    }


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
     * Void to Get a specific row by subject (by rowId)
     */
    public Cursor getRowBySubject(long rowId) {
        String where = FK_SUBJECT_ID + "=" + rowId;
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
     * @param rowId
     * @param name
     * @param email
     * @param phoneNumber
     * @param office
     * @param monday
     * @param tuesday
     * @param wednesday
     * @param thursday
     * @param friday
     * @param saturday
     * @param sunday
     * @param mondayHour
     * @param tuesdayHour
     * @param wednesdayHour
     * @param thursdayHour
     * @param fridayHour
     * @param saturdayHour
     * @param sundayHour
     * @return
     */
    public boolean updateRow(long rowId, String name, String email, String phoneNumber, String office,
                             Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday,
                             Boolean saturday, Boolean sunday, String mondayHour, String tuesdayHour, String wednesdayHour,
                             String thursdayHour, String fridayHour, String saturdayHour, String sundayHour, Integer subjectID) {
        String where = CN_ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(CN_NAME, name);
        newValues.put(CN_EMAIL, email);
        newValues.put(CN_PHONENUMBER, phoneNumber);
        newValues.put(CN_OFFICE, office);
        newValues.put(CN_MONDAY, monday);
        newValues.put(CN_TUESDAY, tuesday);
        newValues.put(CN_WEDNESDAY, wednesday);
        newValues.put(CN_THURSDAY, thursday);
        newValues.put(CN_FRIDAY, friday);
        newValues.put(CN_SATURDAY, saturday);
        newValues.put(CN_SUNDAY, sunday);
        newValues.put(CN_MONDAY_HOUR, mondayHour);
        newValues.put(CN_TUESDAY_HOUR, tuesdayHour);
        newValues.put(CN_WEDNESDAY_HOUR, wednesdayHour);
        newValues.put(CN_THURSDAY_HOUR, thursdayHour);
        newValues.put(CN_FRIDAY_HOUR, fridayHour);
        newValues.put(CN_SATURDAY_HOUR, saturdayHour);
        newValues.put(CN_SUNDAY_HOUR, sundayHour);
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
