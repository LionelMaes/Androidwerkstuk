package com.maes.lionel.cars;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserLikeDao {

    @Insert
    public void insertLike(UserLike userLike);

    @Query("select * from UserLike")
    public List<UserLike> getAllUserLike();

}
