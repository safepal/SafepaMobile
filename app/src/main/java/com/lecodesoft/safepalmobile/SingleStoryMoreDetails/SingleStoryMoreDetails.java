package com.lecodesoft.safepalmobile.SingleStoryMoreDetails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lecodesoft.safepalmobile.Incidentreportedbydialog.IncidentReportedByDialog;
import com.lecodesoft.safepalmobile.R;

public class SingleStoryMoreDetails extends AppCompatActivity {

    Intent receiveIntent;
    String receiveSSIncidentLocation;
    String receiveSSPostTime;
    String receiveSSIncidentDescription;
    String receiveSSIncidentHappenedTime;
    String receiveSSIncidentStatus;
    String receiveSSIncidentType;
    Bitmap receiveThumbnailbitmap;

    TextView tvSsMoreLocation, tvSsMoreDateTime, tvSsMoreType, tvSsMoreDescription, tvSsMoreStatus;
    ImageView ivSsMoreThumbnail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_story_more_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvSsMoreLocation = (TextView)findViewById(R.id.tv_ss_more_location);
        tvSsMoreDateTime = (TextView)findViewById(R.id.tv_ss_more_datetime);
        tvSsMoreType = (TextView)findViewById(R.id.tv_ss_more_type);
        tvSsMoreDescription=(TextView)findViewById(R.id.tv_ss_more_description);
        tvSsMoreStatus = (TextView)findViewById(R.id.tv_ss_more_status);

        ivSsMoreThumbnail = (ImageView)findViewById(R.id.iv_ss_more_thumbnail);

        //assignments
        receiveIntent = getIntent();

        receiveSSIncidentLocation = receiveIntent.getStringExtra("sendSSLocation");
        receiveSSPostTime = receiveIntent.getStringExtra("sendSSPostTime");
        receiveSSIncidentDescription = receiveIntent.getStringExtra("sendSSIncidentDescription");
        receiveSSIncidentHappenedTime = receiveIntent.getStringExtra("sendSSIncidentHappenedTime");
        receiveSSIncidentStatus = receiveIntent.getStringExtra("sendSSIncidentStatus");
        receiveSSIncidentType = receiveIntent.getStringExtra("sendSSIncidentType");
        receiveThumbnailbitmap = (Bitmap) receiveIntent.getParcelableExtra("sendSSIncidentBitmapImage");
        //set the title for single fragment
        setTitle(receiveSSIncidentType);

        //assign items to layout
        tvSsMoreLocation.setText(receiveSSIncidentLocation);
        tvSsMoreDateTime.setText(receiveSSIncidentHappenedTime);
        tvSsMoreType.setText(receiveSSIncidentType);
        tvSsMoreDescription.setText(receiveSSIncidentDescription);
        tvSsMoreStatus.setText(receiveSSIncidentStatus);

        ivSsMoreThumbnail.setImageBitmap(receiveThumbnailbitmap);
        //backwards arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_ssmd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                incidentReportedBy();
            }
        });
    }
    private void incidentReportedBy() {
        DialogFragment newFragment = new IncidentReportedByDialog();

        newFragment.show(getSupportFragmentManager(), getResources().getString(R.string.incidentreportedbydialogTitle));
    }

}
