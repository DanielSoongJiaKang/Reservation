package sg.edu.rp.c346.id19045346.reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    TimePicker tp;
    EditText etname;
    EditText etmobile;
    EditText etpax;
    CheckBox cbsmoke;
    Button btnReserve;
    Button btnReset;





    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        etname = findViewById(R.id.etname);
        etmobile = findViewById(R.id.etmobilenumber);
        etpax = findViewById(R.id.etpax);
        cbsmoke = findViewById(R.id.cbsmoke);
        btnReserve = findViewById(R.id.btnReserve);
        btnReset = findViewById(R.id.btnReset);




        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);


        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etname.getText().toString().trim().length()!= 0 && etmobile.getText().toString().trim().length()!=0
                && etpax.getText().toString().trim().length()!=0) {

                    int month = dp.getMonth()+1;

                    if(cbsmoke.isChecked() ){
                        String output = String.format("Hi %s, Your Reservation will be on %s/%d/%s at %s:%s, Pax:%s and you will be dining " +
                                "in a smoking table area, we will contact you with your mobile number: %s",etname.getText(),dp.getDayOfMonth(),month,dp.getYear(),tp.getCurrentHour(),tp.getCurrentMinute(),
                                etpax.getText(),etmobile.getText());
                        Toast.makeText(MainActivity.this,output,Toast.LENGTH_SHORT).show();
                    }
                    else  {
                        String output = String.format("Hi %s, Your Reservation will be on %s/%d/%s at %s:%s, Pax:%s and you will be dining " +
                                        "in a non-smoking table area, we will contact you with your mobile number: %s",etname.getText(),dp.getDayOfMonth(),month,dp.getYear(),tp.getCurrentHour(),tp.getCurrentMinute(),
                                etpax.getText(),etmobile.getText());
                        Toast.makeText(MainActivity.this,output,Toast.LENGTH_SHORT).show();

                    }





                }
                else {
                    Toast.makeText(MainActivity.this,"Please fill in the empty blanks",Toast.LENGTH_SHORT).show();
                }
            }
        });


        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Add your code here to limit the time to 8am and 8pm
                if(tp.getCurrentHour() <8) {
                    tp.setCurrentHour(8);
                    Toast.makeText(MainActivity.this,"We are only open from 8AM to 8PM daily",Toast.LENGTH_SHORT).show();

                }
                else if(tp.getCurrentHour()>20) {
                    tp.setCurrentHour(20);
                    Toast.makeText(MainActivity.this,"We are only open from 8AM to 8PM daily",Toast.LENGTH_SHORT).show();

                }
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etname.setText("");
                etmobile.setText("");
                etpax.setText("");
                dp.updateDate(2020,0,1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

       dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
           @Override
           public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendarToday = Calendar.getInstance();
            dayOfMonth = calendarToday.get(Calendar.DAY_OF_MONTH);
            monthOfYear = calendarToday.get(Calendar.MONTH);
            year = calendarToday.get(Calendar.YEAR);

            if(dp.getYear() < year ) {
                dp.updateDate(year,dp.getMonth(),dp.getDayOfMonth());
            }

            else if(dp.getDayOfMonth() < dayOfMonth && dp.getMonth() < monthOfYear) {
                dp.updateDate(dp.getYear(),monthOfYear,dayOfMonth);
            }

            else {
                dp.updateDate(dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
            }


           }
       });


    }
}



