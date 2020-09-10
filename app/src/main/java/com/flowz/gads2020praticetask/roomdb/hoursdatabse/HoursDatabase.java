package com.flowz.gads2020praticetask.roomdb.hoursdatabse;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {dbHoursModel.class}, version = 1)
public abstract class HoursDatabase extends RoomDatabase {

    private static HoursDatabase instance;

    public abstract HoursDao hoursDao();

    public static synchronized HoursDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HoursDatabase.class, "hours_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private HoursDao hoursDao;

        private PopulateDbAsyncTask(HoursDatabase db){
            hoursDao = db.hoursDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }



}
