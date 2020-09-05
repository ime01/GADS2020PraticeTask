package com.flowz.gads2020praticetask.network.post;

import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.models.UserModel;
import com.flowz.gads2020praticetask.models.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostApiInterface {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded()
    Call<Void> postValues( // Entry ID of the response field for each of the question
                           @Field("entry.1824927963") String emailAddress,
                           @Field("entry.1877115667") String firstName,
                           @Field("entry.2006916086") String lastName,
                           @Field("entry.284483984") String linkToProject
    );


}
