package com.example.contact_form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.Calendar;


public class ContactForm extends AppCompatActivity {

    AwesomeValidation awesomeValidation;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        Database g = new Database(this);
        EditText et1  = (EditText) findViewById(R.id.et1);
        EditText et2  = (EditText) findViewById(R.id.et2);
        EditText et3 = (EditText) findViewById(R.id.et3);
        EditText address = (EditText) findViewById(R.id.address);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datepicker);
        Button submit = (Button) findViewById(R.id.submit_bttn);
         submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String Username = et1.getText().toString();
               String Email = et2.getText().toString();
               String Address = address.getText().toString();
               String Number = et3.getText().toString();
               int currentYear = Calendar.getInstance().get(Calendar.YEAR);
               int userAge = datePicker.getYear();
               int isageVaild = currentYear - userAge;
               if(isageVaild<18){
                   Toast.makeText(ContactForm.this,"You are not eligible to apply",Toast.LENGTH_SHORT).show();
                   return;
               }

                awesomeValidation.addValidation(ContactForm.this, R.id.et1, "[a-zA-Z]+\\.?",R.string.username);

                awesomeValidation.addValidation(ContactForm.this,R.id.et2,Patterns.EMAIL_ADDRESS,R.string.email);
                awesomeValidation.addValidation(ContactForm.this,R.id.et3,"^[2-9]{2}[0-9]{8}$",R.string.contact);
                awesomeValidation.addValidation(ContactForm.this,R.id.address,"^(\\w*\\s*[\\#\\-\\,\\/\\.\\(\\)\\&]*)+",R.string.address);
                if(awesomeValidation.validate()){
                    Toast.makeText(ContactForm.this,"Data feed Succesfully",Toast.LENGTH_SHORT).show();
                }else if (Username.equals("") || Email.equals("") || Address.equals(""))
                {
                    Toast.makeText(ContactForm.this,"Enter the Fields",Toast.LENGTH_SHORT).show();
                    return;

                }else
                    return;
                {
                   Boolean i = g.insert_data(Username,Email);
                    if (i==true)
                    {
                        Toast.makeText(ContactForm.this,"Successfull",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ContactForm.this,"Successfull",Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });


    }
}