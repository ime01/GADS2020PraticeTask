package com.flowz.gads2020praticetask.roomdb.skilliqdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {dbSkilliqModel.class}, version = 1)
public abstract class SkillIqDatabase extends RoomDatabase {

    private static SkillIqDatabase instance;

    public abstract SkillIqDao skillIqDao();

    public static synchronized SkillIqDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), SkillIqDatabase.class, "skilliq_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private SkillIqDao skillIqDao;

        private PopulateDbAsyncTask(SkillIqDatabase db){
            skillIqDao = db.skillIqDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {

//            skillIqDao.insert();


            return null;
        }
    }



}
