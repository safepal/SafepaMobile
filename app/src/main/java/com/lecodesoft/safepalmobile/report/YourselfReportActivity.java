package com.lecodesoft.safepalmobile.report;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lecodesoft.safepalmobile.R;
import com.lecodesoft.safepalmobile.ReferralPathway.ReferralPathway;
import com.lecodesoft.safepalmobile.datetimepickers.DatePickerFragment;
import com.lecodesoft.safepalmobile.datetimepickers.TimePickerFragment;
import com.lecodesoft.safepalmobile.jsonreport.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YourselfReportActivity extends AppCompatActivity {
    //Progress Dialog;
    private ProgressDialog pDialog;
    private EditText rfyEtPhonenumber, rfyEtIncidentDescription;
    private Button rfyButtonDate, rfyButtonTime;
    private AppCompatSpinner rfyspinnerAge,rfyspinnerGender, rfyspinnerIncidentType, rfyspinnerActionTaken;

    //private AutoCompleteTextView rfyIncidentLocation;

    ///pick current time
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
    String currentDateandTime = sdf.format(new Date());
    //api aint picking post time .brain to work onit


    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    //php add a comment script
    //testing on Emulator:
    private static final String POST_COMMENT_URL = "http://www.thinkitlimited.com/safepal/api/addreport.php";
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourself_report);

        //toolbar  rfy
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //spinners
        //backwards arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rfyEtPhonenumber = (EditText)findViewById(R.id.rfy_et_phonenumber);
        rfyEtIncidentDescription=(EditText)findViewById(R.id.rfy_et_incident_description);

        rfyButtonDate=(Button)findViewById(R.id.rfy_button_date);
        rfyButtonTime=(Button)findViewById(R.id.rfy_button_time);

//        rfyIncidentLocation = (AutoCompleteTextView)findViewById(R.id.rfy_cactv_location);
  //      rfyIncidentLocation.setThreshold(1);


        /**        * gender spinner */
        rfyspinnerAge = (AppCompatSpinner) findViewById(R.id.rfy_spinner_age);
        rfyspinnerGender = (AppCompatSpinner) findViewById(R.id.rfy_spinner_gender);
        rfyspinnerIncidentType = (AppCompatSpinner) findViewById(R.id.rfy_spinner_incident_type);
        rfyspinnerActionTaken = (AppCompatSpinner) findViewById(R.id.rfy_spinner_actiontaken);

        //ageAdapter
        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(this,
                R.array.hint_age_array, R.layout.spinner_layout);
        //genderAdapter
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.hint_gender_array, R.layout.spinner_layout);
        //genderAdapter
        ArrayAdapter<CharSequence> adapterIncidentType = ArrayAdapter.createFromResource(this,
                R.array.rfy_incident_type, R.layout.spinner_layout);
        //actionTakenAdapter
        ArrayAdapter<CharSequence> adapterActionTaken = ArrayAdapter.createFromResource(this,
                R.array.rfy_hint_actiontaken, R.layout.spinner_layout);

        /** Specify the layout to use when the list of choices appears*/
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /** Specify the layout to use when the list of choices appears*/
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /** Specify the layout to use when the list of choices appears*/
        adapterIncidentType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /** Specify the layout to use when the list of choices appears*/
        adapterActionTaken.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rfyspinnerAge.setAdapter(adapterAge);
        rfyspinnerGender.setAdapter(adapterGender);
        rfyspinnerIncidentType.setAdapter(adapterIncidentType);
        rfyspinnerActionTaken.setAdapter(adapterActionTaken);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_rfy);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check whether the gender is selected
                if(rfyspinnerGender.getSelectedItemPosition()!=0){
                    //check whether the age is selected
                    if(rfyspinnerAge.getSelectedItemPosition()!=0){
                        //check whether the incident type is selected
                        if(rfyspinnerIncidentType.getSelectedItemPosition()!=0){
                            //check whether the incident type is selected
                            if(rfyspinnerActionTaken.getSelectedItemPosition()!=0){
                                if(rfyEtIncidentDescription.getText().toString().length()>10){


                                    new PostIncident(rfyEtPhonenumber.getText().toString(),String.valueOf(rfyspinnerGender.getSelectedItem()),
                                            String.valueOf(rfyspinnerAge.getSelectedItem()),"bunamyaya",rfyButtonDate.getText().toString(),
                                            rfyButtonTime.getText().toString(),rfyEtIncidentDescription.getText().toString(),String.valueOf(rfyspinnerIncidentType.getSelectedItem()),
                                            String.valueOf(rfyspinnerActionTaken.getSelectedItem()),"Yes").execute();


                                }else{
                                    Snackbar.make(view, "Your Story is too short. ", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }else{
                                Snackbar.make(view, "Tell us the action u've taken so far.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }

                        }else {
                            Snackbar.make(view, "Select the type of incident.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                    //feed back when age is not selected
                    else{
                        Snackbar.make(view, "Select your Age. ", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                //feed back when gender is not selected
                else {
                    Snackbar.make(view, "What's your gender. ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                /*
                new PostIncident(rfyEtPhonenumber.getText().toString(),String.valueOf(rfyspinnerGender.getSelectedItem()),
                        String.valueOf(rfyspinnerAge.getSelectedItem()),rfyButtonLocation.getText().toString(),rfyButtonDate.getText().toString(),
                        rfyButtonTime.getText().toString(),rfyEtIncidentDescription.getText().toString(),String.valueOf(rfyspinnerIncidentType.getSelectedItem()),
                        String.valueOf(rfyspinnerActionTaken.getSelectedItem())).execute();
                                */

            }
        });


    }
    //end of onCreate method


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    class PostIncident extends AsyncTask<String, String, String> {


        String post_ei_phoneNumber;
        String post_ei_gender;
        String post_ei_age ;
        String post_ti_location ;
        String post_ti_date  ;
        String post_ti_time ;
        String post_ti_description ;
        String post_ti_type ;
        String post_ti_action ;
        String post_ti_permission;
        public PostIncident(String post_ei_phoneNumber,String post_ei_gender,   String post_ei_age,String post_ti_location , String post_ti_date,
                            String post_ti_time, String post_ti_description,String post_ti_type,
                            String post_ti_action,
                            String post_ti_permission){

            this.post_ei_phoneNumber = post_ei_phoneNumber;
            this.post_ei_gender = post_ei_gender;
            this.post_ei_age = post_ei_age;
            this.post_ti_location=post_ti_location;
            this.post_ti_date=post_ti_date;
            this.post_ti_time=post_ti_time;
            this.post_ti_description=post_ti_description;
            this.post_ti_type=post_ti_type;
            this.post_ti_action=post_ti_action;
            this.post_ti_permission = post_ti_permission;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(YourselfReportActivity.this);
            pDialog.setMessage("Posting Comment...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;


            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("ei_phoneNumber", post_ei_phoneNumber));
                params.add(new BasicNameValuePair("ei_gender", post_ei_gender));
                params.add(new BasicNameValuePair("ei_age", post_ei_age));

                params.add(new BasicNameValuePair("ti_location", post_ti_location));
                params.add(new BasicNameValuePair("ti_date", post_ti_date));
                params.add(new BasicNameValuePair("ti_time", post_ti_time));
                params.add(new BasicNameValuePair("ti_description", post_ti_description));
                params.add(new BasicNameValuePair("ti_type", post_ti_type));
                params.add(new BasicNameValuePair("ti_action", post_ti_action));

                params.add(new BasicNameValuePair("permission", post_ti_permission));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        POST_COMMENT_URL, "POST", params);

                // full json response
                Log.d("Post Comment attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Comment Added!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Comment Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(YourselfReportActivity.this, file_url, Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ReferralPathway.class));

            }

        }

    }//end of class for sending data to safepal



}
