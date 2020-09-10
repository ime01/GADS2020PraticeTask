package com.flowz.gads2020praticetask.network.get;

import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.models.SkilliqModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/skilliq")
    Call<List<SkilliqModel>> getSkillIq();

    @GET("api/hours")
    Call<List<HoursModel>> getHours();
}
