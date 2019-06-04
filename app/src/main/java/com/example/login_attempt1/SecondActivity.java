package com.example.login_attempt1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText editText3;

    public void ResetPassword(View view){
        String new_psk = editText3.getText().toString();
        if(new_psk.isEmpty())
            Toast.makeText(this,"Fill some new password",Toast.LENGTH_SHORT).show();
        else {
            Intent intent1 = new Intent();
            intent1.putExtra("psk",new_psk);
            setResult(RESULT_OK,intent1);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText3 = findViewById(R.id.editText3);
        editText3.setGravity(Gravity.CENTER_HORIZONTAL);
    }
}
