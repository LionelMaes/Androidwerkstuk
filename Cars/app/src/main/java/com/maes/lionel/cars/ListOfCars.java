package com.maes.lionel.cars;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListOfCars extends AppCompatActivity {


    public static AppDatabase carsDB;

    ListView listOfCars;
    EditText search;
    customAdapter mcustomadapter;

    List<Cars> carsGetFromDB;
    List<Cars> ALLCARS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_cars);

        carsDB = Room.databaseBuilder(this,AppDatabase.class,"Cars").allowMainThreadQueries().build();
        carThread runnable = new carThread(carsDB);
        new Thread(runnable).start();

        mcustomadapter = new customAdapter();

        listOfCars = findViewById(R.id.listOfCars);

        listOfCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Specific_Car.class);
                intent.putExtra("carId",position);
                Intent previousIntent = getIntent();
                int userId = previousIntent.getIntExtra("userId",0);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        search = findViewById(R.id.txtSearch);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text){

        ArrayList<Cars> filtredList = new ArrayList<Cars>();
        carsGetFromDB = ALLCARS;

        for (Cars car: carsGetFromDB  ) {
            if (car.getName().toLowerCase().contains(text.toLowerCase())){
                filtredList.add(car);
            }
        }
        listOfCars.setAdapter(mcustomadapter);
        mcustomadapter.filterList(filtredList);
    }

//    https://www.youtube.com/watch?v=FKUlw7mFXRM&t=110s
    class customAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return carsGetFromDB.size();
        }

        public void filterList(List<Cars> filterList){
            carsGetFromDB = filterList;
            notifyDataSetChanged();
            Log.d("filter", String.valueOf(carsGetFromDB.size()));
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

//            List<Cars> allCars = carsDB.carDao().getCars();

            convertView = getLayoutInflater().inflate(R.layout.custom_cel,null);
            ImageView imageView = convertView.findViewById(R.id.lstImg);
            TextView merk = convertView.findViewById(R.id.lstMerk);
            TextView naam = convertView.findViewById(R.id.lstNaam);


            imageView.setImageResource(carsGetFromDB.get(position).getPicture());
            merk.setText(carsGetFromDB.get(position).getMerk());
            naam.setText(carsGetFromDB.get(position).getName());
            return convertView;
        }
    }

    class carThread implements Runnable{

        AppDatabase carsDB;
        carThread(AppDatabase database){
            this.carsDB = database;
        }

        @Override
        public void run() {
            carsGetFromDB = carsDB.carDao().getCars();
            ALLCARS = carsDB.carDao().getCars();

            ListView listOfCars = findViewById(R.id.listOfCars);

//            ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, dummy);
            /*ArrayAdapter

            listOfCars.setAdapter(arrayAdapter);*/

//            customAdapter customAdapter = new customAdapter();
            listOfCars.setAdapter(mcustomadapter);
        }
    }
}
