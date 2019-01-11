package com.maes.lionel.cars;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.Cursor;
import android.os.Debug;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;

    public static AppDatabase usersDB;
    public static AppDatabase carsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);

        txtEmail = findViewById(R.id.emailInput);
        txtPassword = findViewById(R.id.passwordInput);

        usersDB = Room.databaseBuilder(this, AppDatabase.class, "Users").allowMainThreadQueries().build();
//        carsDB = Room.databaseBuilder(this, AppDatabase.class, "Cars").allowMainThreadQueries().build();
        carsDB = Room.databaseBuilder(this,AppDatabase.class,"Cars").allowMainThreadQueries().build();

        putDefaultValuesinDB();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });


    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    private void logIn() {
        String emailToString = txtEmail.getText().toString();
        String passwordToString = txtPassword.getText().toString();

        List<Users> users = usersDB.usersDao().getUsers();
        for (Users user : users) {
            String userName = user.getName();
            String userEmail = user.getMail();
            String userPassword = user.getPassword();
            if (userName.equals(emailToString) && userPassword.equals(passwordToString) || userEmail.equals(emailToString) && userPassword.equals(passwordToString)) {
                Intent intent = new Intent(this, ListOfCars.class);
                intent.putExtra("userId", user.getId());
                finish();
                startActivity(intent);
            }
        }
        Toast toast = Toast.makeText(this, "wrong user or password", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void putDefaultValuesinDB() {

        List<Users> users = usersDB.usersDao().getUsers();

        if (users.size() <= 0) {
            Users user = new Users();
            user.setName("Lionel");
            user.setPhone("024683666");
            user.setPassword("azerty");
            user.setMail("lionel.maes@hotmail.fr");
            usersDB.usersDao().insertUser(user);

            user.setName("George");
            user.setPhone("0489571258");
            user.setMail("george@hotmail.fr");
            user.setPassword("azerty");
            usersDB.usersDao().insertUser(user);
        }

        List<Cars> cars = carsDB.carDao().getCars();

        if(cars.size() <= 0){
            Log.d("INPUT:", "put Cars in the database");

            Cars car = new Cars();
            car.setName("Veyron");
            car.setMerk("Bughatti");
            car.setDescription("A super fast car!!");
            car.setDate("2005-2015");
            car.setPicture(R.drawable.bughatti_veron);
            carsDB.carDao().insertCar(car);

            car.setName("Enzo");
            car.setMerk("Ferrari");
            car.setDescription("The mythological red Ferrari car!");
            car.setDate("2002-2004");
            car.setPicture(R.drawable.ferrari_enzo);
            carsDB.carDao().insertCar(car);

            car.setName("Venom");
            car.setMerk("Hennesey");
            car.setDescription("A car who is also going super ");
            car.setDate("2011-2017");
            car.setPicture(R.drawable.hennessey_venom);
            carsDB.carDao().insertCar(car);

            car.setName("K2000");
            car.setMerk("/");
            car.setDescription("the popular car of the telivision serie");
            car.setDate("1982-1986");
            car.setPicture(R.drawable.k2000);
            carsDB.carDao().insertCar(car);

            car.setName("Urus");
            car.setMerk("lamborghini");
            car.setDescription("A just new made 4x4 car who can bring you every where");
            car.setDate("vanaf 2018");
            car.setPicture(R.drawable.lambo_urus);
            carsDB.carDao().insertCar(car);

            car.setName("Exige");
            car.setMerk("Lotus");
            car.setDescription("dsmqkfjdqsmkfj");
            car.setDate("2000-know");
            car.setPicture(R.drawable.lotus_exige);
            carsDB.carDao().insertCar(car);

            car.setName("1998 Mustang");
            car.setMerk("Mustang");
            car.setDescription("qdkfjqdmkf");
            car.setDate("1998");
            car.setPicture(R.drawable.mustang_68);
            carsDB.carDao().insertCar(car);

            car.setName("Clio");
            car.setMerk("Renaud");
            car.setDescription("qdmsklfj");
            car.setDate("1990-know");
            car.setPicture(R.drawable.renaud_clio);
            carsDB.carDao().insertCar(car);
        }
    }
}
