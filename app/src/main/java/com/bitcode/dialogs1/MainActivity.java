package com.bitcode.dialogs1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAlertDialog, btnTimePickerDialog, btnDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initListeners() {
        btnAlertDialog.setOnClickListener(new BtnAlertDialogClickListener());
        btnDatePickerDialog.setOnClickListener(new BtnDatePickerDialogClickListener());
        btnTimePickerDialog.setOnClickListener(new BtnTimePickerDialogClickListener());
    }

    private void initViews() {
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
    }

    private class BtnTimePickerDialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(
                            MainActivity.this,
                            new MyOnTimeSetListener(),
                            23,
                            55,
                            false
                    );
            timePickerDialog.show();
        }
    }

    private class MyOnTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            btnTimePickerDialog.setText(hourOfDay + " : " + minute);
        }
    }

    private class BtnDatePickerDialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                            MainActivity.this,
                            new MyOnDateSetListener(),
                            2022,
                            4,
                            14
                    );
            datePickerDialog.show();
        }
    }

    private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            btnDatePickerDialog.setText(year + " - " + (month+1) + " - " + dayOfMonth);
        }
    }

    private class BtnAlertDialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("BitCode Pune");
            builder.setMessage("Are you sure, you want to logout?");
            builder.setIcon(R.mipmap.ic_launcher);

            /*builder.setPositiveButton("Yes", new YesClickListener());
            builder.setNegativeButton("No", new NoClickListener());
            builder.setNeutralButton("Cancel", new CancelClickListener());*/

            DialogInterface.OnClickListener listener = new AlertButtonsClickListener();
            builder.setPositiveButton("Yes", listener);
            builder.setNegativeButton("No", listener);
            builder.setNeutralButton("Cancel", listener);

            //builder.setCancelable(false);

            builder.setOnCancelListener(new LogoutDialogCancelListener());
            builder.setOnDismissListener(new LogoutDialogDismissListener());

            AlertDialog logoutDialog = builder.create();
            logoutDialog.show();
        }
    }

    class LogoutDialogDismissListener implements DialogInterface.OnDismissListener {
        @Override
        public void onDismiss(DialogInterface dialog) {
            mt("Dialog Dismissed...");
        }
    }

    class LogoutDialogCancelListener implements DialogInterface.OnCancelListener {
        @Override
        public void onCancel(DialogInterface dialog) {
            mt("Dialog Cancelled...");
        }
    }

    class AlertButtonsClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_POSITIVE) {
                mt("Yes Clicked");
            }
            if(which == DialogInterface.BUTTON_NEGATIVE) {
                mt("No Clicked");
            }
            if(which == DialogInterface.BUTTON_NEUTRAL) {
                mt("Cancel Clicked");
            }
        }
    }

    private class YesClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Yes Clicked");
        }
    }

    private class CancelClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Cancel Clicked");
        }
    }

    private class NoClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("No Clicked");
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}