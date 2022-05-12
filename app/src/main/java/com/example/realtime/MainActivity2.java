package com.example.realtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {


    EditText Name;
    EditText City;
    EditText Contact;
    Spinner Blood;
    Button btn4;

    boolean valid = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Name=findViewById(R.id.editTextTextPersonName);
        City=findViewById(R.id.editTextTextPersonName2);
        Contact=findViewById(R.id.editTextPhone);
        Blood=findViewById(R.id.sp_blood);
        btn4=findViewById(R.id.button4);

        String[] blood = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, blood);
        Blood.setAdapter(adapter2);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                checkField(Name);
                checkField(City);
                checkField(Contact);

                if(valid) {

                    HashMap<String, Object> m = new HashMap<String , Object>();
                    m. put ("Name" , Name.getText ().toString());
                    m.put ("City" ,City.getText ().toString());
                    m.put ("Contact" ,Contact.getText ().toString());
                    m.put ("blood group",Blood.getSelectedItem ().toString());
                    FirebaseDatabase.getInstance().getReference().child("User").push().setValue(m);




                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });
    }
    private boolean checkField(EditText text) {
        if (text.getText().toString().isEmpty())
        {
            text.setError("Fill all Fields");

            valid = false;
        }
        else
        {
            valid = true;
        }
        return valid;
    }
}