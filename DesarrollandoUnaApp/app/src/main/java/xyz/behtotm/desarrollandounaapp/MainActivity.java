package xyz.behtotm.desarrollandounaapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button next;
    private Date birthdayFormat = new Date();
    private DatePicker birthday;
    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText description;
    private SimpleDateFormat input = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button)findViewById(R.id.next);
        birthday = (DatePicker)findViewById(R.id.birthday);
        name =  (TextInputEditText)findViewById(R.id.name);
        phone =  (TextInputEditText)findViewById(R.id.phone);
        email =  (TextInputEditText)findViewById(R.id.email);
        description =  (TextInputEditText)findViewById(R.id.description);

        next.setOnClickListener(this);
        
        Bundle args = getIntent().getBundleExtra("args");
        if(args != null){
            name.setText(args.getString("name"));
            phone.setText(args.getString("phone"));
            email.setText(args.getString("email"));
            description.setText(args.getString("description"));

            try {
                Date updateBirthday = new Date(output.parse(args.getString("birthday")).getTime());
                String day = (String) DateFormat.format("dd",updateBirthday);
                String month = (String) DateFormat.format("MM",updateBirthday);
                String year = (String) DateFormat.format("yyyy",updateBirthday);

                birthday.init(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        try {
                            birthdayFormat = input.parse("" + String.valueOf(year) + String.valueOf(monthOfYear) + String.valueOf(dayOfMonth));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            birthday.init(year, month, day, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    try {
                        birthdayFormat = input.parse("" + String.valueOf(year) + String.valueOf(monthOfYear) + String.valueOf(dayOfMonth));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        if(name.getText().toString().trim().equals("") && phone.getText().toString().trim().equals("") && email.getText().toString().trim().equals("")){
            name.setError("*Ingresa tu nombre");
            phone.setError("*Ingresa tu número de teléfono");
            email.setError("*Ingresa tu correo electrónico");
        } else if(name.getText().toString().trim().equals("")){
            name.setError("*Ingresa tu nombre");
        } else if(phone.getText().toString().trim().equals("")){
            phone.setError("*Ingresa tu número de teléfono");
        } else if(email.getText().toString().trim().equals("")) {
            email.setError("*Ingresa tu correo electrónico");
        } else {
            Intent intent = new Intent(MainActivity.this, UpdateDataActivity.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("birthday", output.format(birthdayFormat));
            intent.putExtra("phone", phone.getText().toString());
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("description", description.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}
