package xyz.behtotm.desarrollandounaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateDataActivity extends AppCompatActivity implements View.OnClickListener {

    private Button update;
    private TextView name;
    private TextView birthday;
    private TextView phone;
    private TextView email;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        update = (Button)findViewById(R.id.update);
        name = (TextView)findViewById(R.id.confirm_name);
        birthday = (TextView)findViewById(R.id.confirm_birthday);
        phone = (TextView)findViewById(R.id.confirm_phone);
        email = (TextView)findViewById(R.id.confirm_email);
        description = (TextView)findViewById(R.id.confirm_description);

        update.setOnClickListener(this);

        if(getIntent().getStringExtra("name") != null){
            name.setText(getIntent().getStringExtra("name"));
        } else {
            name.setText("");
        }

        if(getIntent().getStringExtra("birthday") != null){
            birthday.setText(getIntent().getStringExtra("birthday"));
        } else {
            birthday.setText("");
        }

        if(getIntent().getStringExtra("phone") != null){
            phone.setText(getIntent().getStringExtra("phone"));
        } else {
            phone.setText("");
        }

        if(getIntent().getStringExtra("email") != null){
            email.setText(getIntent().getStringExtra("email"));
        } else {
            email.setText("");
        }

        if(getIntent().getStringExtra("description") != null){
            description.setText(getIntent().getStringExtra("description"));
        } else {
            description.setText("");
        }
    }

    @Override
    public void onClick(View v) {

        Bundle args = new Bundle();
        args.putString("name",name.getText().toString());
        args.putString("birthday",birthday.getText().toString());
        args.putString("phone",phone.getText().toString());
        args.putString("email",email.getText().toString());
        args.putString("description",description.getText().toString());

        Intent intent = new Intent(UpdateDataActivity.this,MainActivity.class);
        intent.putExtra("args",args);
        startActivity(intent);
        finish();
    }
}
