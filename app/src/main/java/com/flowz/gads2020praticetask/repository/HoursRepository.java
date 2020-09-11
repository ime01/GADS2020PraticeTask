package com.flowz.gads2020praticetask.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.flowz.gads2020praticetask.roomdb.hoursdatabse.HoursDao;
import com.flowz.gads2020praticetask.roomdb.hoursdatabse.HoursDatabase;
import com.flowz.gads2020praticetask.roomdb.hoursdatabse.dbHoursModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class HoursRepository {

    private HoursDao hoursDao;
    private LiveData<List<dbHoursModel>> hoursScores;

    public HoursRepository(Application application) {

       HoursDatabase hoursDatabase = HoursDatabase.getInstance(application);

        hoursDao = hoursDatabase.hoursDao();

        hoursScores = hoursDao.getAllIhours();

    }

    public void insert(dbHoursModel hoursModel) {
        new InsertHoursqAsyncTask(hoursDao).execute(hoursModel);
    }

    public void delete(dbHoursModel hoursModel) {

        new InsertHoursqAsyncTask.DeleteHoursAsyncTask(hoursDao).execute(hoursModel);
    }

    public LiveData<List<dbHoursModel>> getAllHours() {
        return hoursScores;
    }




    private static class InsertHoursqAsyncTask extends AsyncTask<dbHoursModel, Void, Void> {

        private HoursDao hoursDao;

        public InsertHoursqAsyncTask(HoursDao hoursDao) {
            this.hoursDao = hoursDao;
        }

        @Override
        protected Void doInBackground(dbHoursModel... dbHoursModels) {

            hoursDao.insert(dbHoursModels[0]);
            return null;
        }


        private static class DeleteHoursAsyncTask extends AsyncTask<dbHoursModel, Void, Void> {

            private HoursDao hoursDao;

            public DeleteHoursAsyncTask(HoursDao hoursDao) {
                this.hoursDao = hoursDao;
            }

            @Override
            protected Void doInBackground(dbHoursModel... dbHoursModels) {

                hoursDao.delete(dbHoursModels[0]);
                return null;
            }

        }

    }

}