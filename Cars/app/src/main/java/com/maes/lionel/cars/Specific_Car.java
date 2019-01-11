package com.maes.lionel.cars;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Specific_Car extends AppCompatActivity {

    TextView carDescription;
    TextView carName;
    TextView carMerk;
    TextView carBuild;
    ImageView carImg;
    View background;
    Button btnLike;


    AppDatabase carDB;
    AppDatabase userLikeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific__car);

        Intent previousIntent = getIntent();
        final int carId = previousIntent.getIntExtra("carId",0);
        final int userId = previousIntent.getIntExtra("userId",0);
        Log.d("userId", String.valueOf(userId));

        carDescription = findViewById(R.id.txtCarDescriptionSpecific);
        carBuild = findViewById(R.id.txtCarBuildYearSpecific);
        carImg = findViewById(R.id.imgSpecificCar);
        carMerk = findViewById(R.id.txtMerkSpecific);
        carName = findViewById(R.id.txtCarNameSpecific);
        background = findViewById(R.id.specificBackground);
        btnLike = findViewById(R.id.btnLikeSpecific);

        carDB = Room.databaseBuilder(this,AppDatabase.class,"Cars").allowMainThreadQueries().build();
        userLikeDB = Room.databaseBuilder(this,AppDatabase.class,"UserLike").allowMainThreadQueries().build();

        Cars theCar = carDB.carDao().getSpecificCar(carId+1);

        carDescription.setText(theCar.getDescription());
        carName.setText(theCar.getName());
        carMerk.setText(theCar.getMerk());
        carImg.setImageResource(theCar.getPicture());
        carBuild.setText(theCar.getDate());

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLike userLike = new UserLike();
                userLike.setUserId(userId);
                userLike.setCarId(carId);

                userLikeDB.userLikeDao().insertLike(userLike);
                background.setBackgroundColor(Color.parseColor("#41fa78"));
            }
        });

        List<UserLike> allLikes = userLikeDB.userLikeDao().getAllUserLike();


        for (UserLike ul : allLikes) {
            Log.d(String.valueOf(ul.getCarId()), String.valueOf(ul.getUserId()));
            Log.d("pd", "onCreate: ");
            int userIdFromDb = ul.getUserId();
            int carIdFromDb = ul.getCarId();

            if (userId == userIdFromDb && carId == carIdFromDb){
                background.setBackgroundColor(Color.parseColor("#41fa78"));
            }
        }
    }
}
