package com.lecodesoft.safepalmobile.datetimepickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;


import com.lecodesoft.safepalmobile.R;

import java.util.Calendar;

/**
 * Created by root on 2/16/16.
 */
public  class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //assign the date to the button
        Button rfyButtonTime = (Button)getActivity().findViewById(R.id.rfy_button_time);
        //Get the AM or PM for current time
        String aMpM = "AM";
        if(hourOfDay >12)
        {
            aMpM = "PM";
        }
        int currentHour;
        if(hourOfDay>12)
        {
            currentHour = hourOfDay - 12;
        }
        else
        {
            currentHour = hourOfDay;
        }




        rfyButtonTime.setText( String.valueOf(currentHour) +" : "+ String.valueOf(minute)+" " +aMpM);

    }
}
