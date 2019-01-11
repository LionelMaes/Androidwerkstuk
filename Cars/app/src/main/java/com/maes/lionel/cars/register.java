package com.maes.lionel.cars;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPhone;
    private EditText txtName;
    private EditText txtPassword1;
    private EditText txtPassword2;
    private CheckBox agreed;

    public static AppDatabase myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEmail = findViewById(R.id.txtEmailRegister);
        txtPhone = findViewById(R.id.txtPhoneRegister);
        txtName = findViewById(R.id.txtNameRegister);
        txtPassword1 = findViewById(R.id.txtPasswordRegister);
        txtPassword2 = findViewById(R.id.txtReapeatPasswordRegister);
        agreed = findViewById(R.id.checkBox);

        Button btnRegister = findViewById(R.id.btnRegisterRegister);

        myDB = Room.databaseBuilder(this,AppDatabase.class,"Users").allowMainThreadQueries().build();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterNewUser();
            }
        });
    }

    private void RegisterNewUser(){
        String mail = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String name = txtName.getText().toString();
        String password1 = txtPassword1.getText().toString();
        String password2 = txtPassword2.getText().toString();
        Boolean isChekboxCheked = agreed.isChecked();


        if (!mail.isEmpty() && !name.isEmpty()&& !password1.isEmpty() && password1.equals(password2) && isChekboxCheked){
            Users users = new Users();
            users.setMail(mail);
            users.setPhone(phone);
            users.setName(name);
            users.setPassword(password1);

            myDB.usersDao().insertUser(users);
            Log.d(users.toString(), "create");
            Intent intent = new Intent(this,Login.class);
            finish();
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "pleasse check your register credentials", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
