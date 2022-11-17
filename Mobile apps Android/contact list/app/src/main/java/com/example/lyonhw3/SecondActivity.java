package com.example.lyonhw3;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.example.lyonhw3.databinding.ActivitySecondBinding;
import android.net.Uri;

public class SecondActivity extends Activity {
    DBAdapter db = new DBAdapter(this);
    protected boolean validate(String name, String phoneNumber, String emailAddress){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "";
        if(name.equals("")||phoneNumber.equals("")||emailAddress.equals("")) {
            if (name.equals("")) {
                text += "Please enter a name!\n";
            }
            if (phoneNumber.equals("")) {
                text += "Please enter a phone number!\n";
            }
            if (emailAddress.equals("")) {
                text += "Please enter an email!\n";
            }
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return(false);
        }
//        else if(emailAddress.contains("@") || !emailAddress.contains(".com") ||
//                !emailAddress.contains(".org") || !emailAddress.contains(".edu")){
//                    CharSequence ttext = "email must be formatted as \'@domain.com or .org or .edu\'";
//                    Toast toast = Toast.makeText(context, emailAddress, duration);
//                    toast.show();
//                    return(false);}
        else return (true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding bind = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }

    public void submit(View form){
        db.open();
        EditText fullName = (EditText) findViewById(R.id.editTextTextPersonName);
        String name= fullName.getText().toString();
        EditText phone = (EditText) findViewById(R.id.phoneText);
        String phoneNumber= phone.getText().toString();
        EditText email = (EditText) findViewById(R.id.emailText);
        String emailAddress= email.getText().toString();

        boolean valid = validate(name, phoneNumber, emailAddress);
        if(!valid) return;

        long id = db.insertContact(name, emailAddress, phoneNumber);
        db.close();
//        Intent data = new Intent();
//        data.putExtra("Name",name);
//        data.putExtra("Number", phoneNumber);
//        data.putExtra("Email", emailAddress);
//        data.setData(Uri.parse(name));

//        setResult(RESULT_OK, data);
        finish();
    }

}
