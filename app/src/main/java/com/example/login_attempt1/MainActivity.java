package com.example.login_attempt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    HashMap<String,String> users = new HashMap<String,String>();
    EditText editText1 , editText2;

    public static String global_uname ;


    //LOGIN
    public void Login(View view){
        String uname = editText1.getText().toString();
        String psk = editText2.getText().toString();

        if(Check_Empty(uname,psk)) {
            if (users.containsKey(uname)) {
                if (psk.equals(users.get(uname))) {
                    Toast.makeText(this, " ACCESS GRANTED !", Toast.LENGTH_SHORT).show();
                    Intent login_intent = new Intent(getApplicationContext() , ThirdActivity.class);
                    startActivity(login_intent);
                }
                else
                    Toast.makeText(this, "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Please register first", Toast.LENGTH_SHORT).show();

            editText1.getText().clear();
            editText2.getText().clear();
        }
    }
    public boolean Check_Empty(String uname , String psk) {
        boolean flag = true; //marks any blank fields .

        if (uname.equals("") && psk.equals("")) {
            Toast.makeText(this, "Fill some username and password", Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (uname.equals("")) {
            Toast.makeText(this, "Fill some username", Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (psk.equals("")) {
            Toast.makeText(this, "Fill some password", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        return flag;
    }


    //FORGOT PASSWORD
    public void goToForgot(View view){
        String uname = editText1.getText().toString();
        if(uname.isEmpty())
            Toast.makeText(this,"Fill some username",Toast.LENGTH_SHORT).show();
        else if(users.containsKey(uname)){
            global_uname = uname;
            editText1.getText().clear();
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivityForResult(intent,1);
        }
        else
            Toast.makeText(this,"No such user exists !",Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String strEditText = data.getStringExtra("psk");
                users.put(global_uname,strEditText); //actual resetting of password
            }
        }
    }




    //REGISTER USER
    public void Register(View view){
        String uname = editText1.getText().toString();
        String psk = editText2.getText().toString();

        if(Check_Empty(uname,psk)) {
            if(users.containsKey(uname))
                Toast.makeText(this,uname+" already exists . Choose another username ",Toast.LENGTH_SHORT).show();
            else {
                users.put(uname, psk);
                Toast.makeText(this, "New user " + uname + " added with psk = " + users.get(uname), Toast.LENGTH_SHORT).show();
            }
            editText1.getText().clear();
            editText2.getText().clear();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        editText1.setGravity(Gravity.CENTER_HORIZONTAL);
        editText2.setGravity(Gravity.CENTER_HORIZONTAL);

        users.put("abc","123");
        users.put("def","456");
        users.put("ghi","456");

    }
}
