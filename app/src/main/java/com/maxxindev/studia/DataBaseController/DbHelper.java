package com.maxxindev.studia.DataBaseController;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.maxxindev.studia.DataBaseController.ProfessorDB.ProfessorDataBaseManager;
import com.maxxindev.studia.DataBaseController.SubjectsDB.SubjectDataBaseManager;
import com.maxxindev.studia.DataBaseController.TestDB.TestDataBaseManager;

/**
 * Data Base helper of Professor
 */
public class DbHelper extends SQLiteOpenHelper
{

    private static final String TAG = "ProfessorDataBaseManager";
    private static final String DB_NAME = "subjects.sqlite";

    public static final String TABLE_NAME_SUBJECTS = "subjects";
    public static final String TABLE_NAME_PROFESSORS = "professors";
    public static final String TABLE_NAME_TESTS = "tests";

    private static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ProfessorDataBaseManager.CREATE_TABLE);
        db.execSQL(TestDataBaseManager.CREATE_TABLE);
        db.execSQL(SubjectDataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
    {
        Log.w(TAG, "Upgrading application's database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data!");

        // Destroy old database:
        _db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PROFESSORS);
        _db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SUBJECTS);
        _db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TESTS);

        // Recreate new database:
        onCreate(_db);
    }


}
