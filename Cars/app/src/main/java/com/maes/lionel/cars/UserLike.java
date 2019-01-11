package com.maes.lionel.cars;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserLike {
    @PrimaryKey(autoGenerate = true)
    private int userLikeId;
    private int userId;
    private int carId;

    public int getUserLikeId() {
        return userLikeId;
    }

    public void setUserLikeId(int userLikeId) {
        this.userLikeId = userLikeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
