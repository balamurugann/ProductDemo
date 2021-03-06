package com.bb.productdemo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bb.productdemo.database.dao.ProductDao;
import com.bb.productdemo.database.domain.Product;



/**
 * Created by bala.natarajan on 10/11/2017.
 *
 * Get the database object to perform insert,update,select and delete opearation
 */
@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public static final String DB_NAME = "bbproduct_db";

    public abstract ProductDao getProductDao();

    /**
     * To handle single database object throughtout the application
     * @param context
     * @return
     */

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
