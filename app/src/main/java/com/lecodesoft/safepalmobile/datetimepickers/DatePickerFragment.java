package com.lecodesoft.safepalmobile.datetimepickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;


import com.lecodesoft.safepalmobile.R;

import java.util.Calendar;

/**
 * Created by root on 2/16/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //assign the date to the button
       Button rfyButtonDate = (Button)getActivity().findViewById(R.id.rfy_button_date);
        rfyButtonDate.setText( String.valueOf(month)+" / "+ String.valueOf(day)+" / "+ String.valueOf(year));
    }
}