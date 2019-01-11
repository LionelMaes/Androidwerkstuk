package com.maes.lionel.cars;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    public void insertCar(Cars cars);

    @Query("select * from Cars")
    public List<Cars> getCars();

    @Query("select * from Cars where carId = :carId ")
    public Cars getSpecificCar(int carId);
}
