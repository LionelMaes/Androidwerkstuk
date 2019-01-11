package com.maes.lionel.cars;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Users.class,Cars.class,UserLike.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();

    public abstract CarDao carDao();

    public abstract UserLikeDao userLikeDao();
}
