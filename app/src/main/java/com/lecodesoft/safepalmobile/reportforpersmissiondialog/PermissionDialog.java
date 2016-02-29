package com.lecodesoft.safepalmobile.reportforpersmissiondialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.lecodesoft.safepalmobile.R;


/**
 * Created by root on 2/22/16.
 */
public class PermissionDialog extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.permission_message)
                .setSingleChoiceItems(R.array.rfy_permission_acceptance, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                               // startActivity(new Intent(getActivity(), ReferralPathway.class));
                                break;
                            case 1:
                                //startActivity(new Intent(getActivity(), ReferralPathway.class));
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
