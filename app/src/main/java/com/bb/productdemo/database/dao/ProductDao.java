package com.bb.productdemo.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bb.productdemo.database.domain.Product;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by bala.natarajan on 10/11/2017.
 */
@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM product")
    Flowable<List<Product>> getAllProduct();

    @Query("SELECT * FROM product")
    List<Product> getAllProductForTest();

}
