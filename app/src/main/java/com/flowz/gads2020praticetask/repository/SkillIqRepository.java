package com.flowz.gads2020praticetask.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.SkillIqDao;
import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.SkillIqDatabase;
import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.dbSkilliqModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SkillIqRepository {

    private SkillIqDao skillIqDao;
    private LiveData<List<dbSkilliqModel>> skillIqScores;

    public SkillIqRepository(Application application) {

        SkillIqDatabase skillIqDatabase = SkillIqDatabase.getInstance(application);

        skillIqDao = skillIqDatabase.skillIqDao();

        skillIqScores = skillIqDao.getAllIqScores();

    }

    public void insert(dbSkilliqModel skilliqModel) {
        new InsertSkillIqAsyncTask(skillIqDao).execute(skilliqModel);
    }

    public void delete(dbSkilliqModel skilliqModel) {

        new InsertSkillIqAsyncTask.DeleteSkillIqAsyncTask(skillIqDao).execute(skilliqModel);
    }

    public LiveData<List<dbSkilliqModel>> getSkillIqScores() {
        return skillIqScores;
    }


    private static class InsertSkillIqAsyncTask extends AsyncTask<dbSkilliqModel, Void, Void> {

        private SkillIqDao skillIqDao;

        public InsertSkillIqAsyncTask(SkillIqDao skillIqDao) {
            this.skillIqDao = skillIqDao;
        }

        @Override
        protected Void doInBackground(dbSkilliqModel... dbSkilliqModels) {
            skillIqDao.insert(dbSkilliqModels[0]);

            return null;
        }


        private static class DeleteSkillIqAsyncTask extends AsyncTask<dbSkilliqModel, Void, Void> {

            private SkillIqDao skillIqDao;

            public DeleteSkillIqAsyncTask(SkillIqDao skillIqDao) {
                this.skillIqDao = skillIqDao;
            }

            @Override
            protected Void doInBackground(dbSkilliqModel... dbSkilliqModels) {

                skillIqDao.delete(dbSkilliqModels[0]);
                return null;
            }


        }

    }

}