package com.maes.lionel.cars;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert
    public void insertUser(Users user);

    @Query("select * from Users")
    public List<Users> getUsers();

}
