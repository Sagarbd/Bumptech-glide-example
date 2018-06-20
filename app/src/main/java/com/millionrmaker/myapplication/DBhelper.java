package com.millionrmaker.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBhelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABSE_NAME = "user";
    public static final int DATABASE_VERSION = 1;

    public DBhelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    RuntimeExceptionDao<PHOTO, Integer> runtimeExceptionDao = null;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, PHOTO.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, PHOTO.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    RuntimeExceptionDao<PHOTO, Integer> getRuntimeExceptionDao() {

        if (runtimeExceptionDao == null) {
            runtimeExceptionDao = getRuntimeExceptionDao(PHOTO.class);
        }
        return runtimeExceptionDao;

    }
}
