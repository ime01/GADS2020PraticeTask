package com.flowz.gads2020praticetask.display;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flowz.gads2020praticetask.R;
import com.flowz.gads2020praticetask.network.post.PostApiClient;
import com.flowz.gads2020praticetask.network.post.PostApiInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

public class SubmitActivity extends AppCompatActivity {

    TextInputEditText firstName, lastName, email, link;
    Button submit;
    Boolean networkCall = false;
    String state;
    String myTag = "MYTAG";
    MaterialAlertDialogBuilder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.submit_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email_adress);
        link = findViewById(R.id.github_link);
        submit = findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((!firstName.getEditableText().toString().isEmpty()&& !lastName.getEditableText().toString().isEmpty() && !email.getEditableText().toString().isEmpty() && !link.getEditableText().toString().isEmpty())){

                    dialog = new MaterialAlertDialogBuilder(SubmitActivity.this, R.style.AlertDialogTheme);

                    LayoutInflater inflater = LayoutInflater.from(SubmitActivity.this);
                    View alertView = inflater.inflate(R.layout.alert_dialog, null);

                    Button yesButton = alertView.findViewById(R.id.yes);
                    ImageView closeDialog = alertView.findViewById(R.id.image_close);
                    TextView sure = alertView.findViewById(R.id.sure);


                    dialog.setView(alertView);
//                    dialog.setCancelable(false);

                    AlertDialog alertDialog = dialog.create();

                    alertDialog.show();

                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            submitTask(email.getEditableText().toString().trim(), firstName.getEditableText().toString().trim(), lastName.getEditableText().toString().trim(), link.getEditableText().toString().trim());

                            alertDialog.dismiss();

                        }
                    });

                    closeDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            alertDialog.dismiss();
                            Toast.makeText(SubmitActivity.this, "Closed Pressed", Toast.LENGTH_LONG).show();
                        }
                    });



                }else if (firstName.getEditableText().toString().isEmpty()||lastName.getEditableText().toString().isEmpty()||email.getEditableText().toString().isEmpty()||link.getEditableText().toString().isEmpty()){

                    Toast.makeText(SubmitActivity.this, "Ensure you have Entered the Required Details in All Fields", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

//    public void showSuccess(){
//
//
//        LayoutInflater inflater = LayoutInflater.from(SubmitActivity.this);
//        View alertView = inflater.inflate(R.layout.alert_dialog, null);
//
//        Button yesButton = alertView.findViewById(R.id.yes);
//        ImageView closeDialog = alertView.findViewById(R.id.image_close);
//        TextView sure = alertView.findViewById(R.id.sure);
//
//        yesButton.setVisibility(View.INVISIBLE);
//        closeDialog.setVisibility(View.INVISIBLE);
//        sure.setVisibility(View.INVISIBLE);
//        alertView.setBackground(getResources().getDrawable(R.drawable.succesicon));
//
//        dialog.setView(alertView);
////                    dialog.setCancelable(false);
//
//        AlertDialog alertDialog = dialog.create();
//
//        alertDialog.show();
//
//    }

    public void showStatus(Drawable statusIndicator){

//        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(SubmitActivity.this, R.style.AlertDialogTheme);
        dialog = new MaterialAlertDialogBuilder(SubmitActivity.this, R.style.AlertDialogTheme);

        LayoutInflater inflater = LayoutInflater.from(SubmitActivity.this);
        View alertView = inflater.inflate(R.layout.alert_dialog, null);


        Button yesButton = alertView.findViewById(R.id.yes);
        ImageView closeDialog = alertView.findViewById(R.id.image_close);
        TextView sure = alertView.findViewById(R.id.sure);

        yesButton.setVisibility(View.INVISIBLE);
        closeDialog.setVisibility(View.INVISIBLE);
        sure.setVisibility(View.INVISIBLE);
//        alertView.setBackground(getResources().getDrawable(R.drawable.failureicon));
        alertView.setBackground(statusIndicator);

        dialog.setView(alertView);

        AlertDialog alertDialog = dialog.create();

        alertDialog.show();

    }


    public void submitTask(String userEmail, String userfirstName, String userLastName, String userGithubLink ){

        PostApiInterface postService = PostApiClient.getApiClient().create(PostApiInterface.class);

        Call<Void> call = postService.postValues(userEmail, userfirstName, userLastName, userGithubLink);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                int responseCode = response.code();
                if (response.isSuccessful()) {

                    networkCall = true;
                    state = "success";
                    Toast.makeText(SubmitActivity.this, "POST REQUEST SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Log.e(myTag, "POST REQUEST SUCCESSFUL" + response.body());
                    showStatus(getResources().getDrawable(R.drawable.succesicon));



                }else {
                    Toast.makeText(SubmitActivity.this, "Request failed", Toast.LENGTH_LONG).show();
                    Log.e(myTag, "failed" + response.body());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                networkCall = false;
                state = "failure";
                String errorMessage = t.getMessage().toString();
                Toast.makeText(SubmitActivity.this, "Unable to send the POST request" + errorMessage, Toast.LENGTH_LONG).show();
                Log.e(myTag, "POST REQUEST FAILED" + t.getMessage());

              showStatus(getResources().getDrawable(R.drawable.failureicon));

            }
        });
    }

}
