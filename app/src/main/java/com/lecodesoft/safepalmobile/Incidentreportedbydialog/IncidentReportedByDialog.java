package com.lecodesoft.safepalmobile.Incidentreportedbydialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.lecodesoft.safepalmobile.R;
import com.lecodesoft.safepalmobile.report.AnotherPersonReportActivity;
import com.lecodesoft.safepalmobile.report.YourselfReportActivity;

/**
 * Created by root on 11/17/15.
 */
public class IncidentReportedByDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.incidentreportedbydialogTitle)
                .setSingleChoiceItems(R.array.pick_incident_type, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                startActivity(new Intent(getActivity(), YourselfReportActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(getActivity(), AnotherPersonReportActivity.class));
                                break;

                            default:
                               /* st*/
                                break;

                        }
                        dialog.dismiss();

                    }
                });

        return builder.create();
    }
}
