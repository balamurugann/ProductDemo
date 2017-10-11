package com.bb.productdemo.fragment.createproduct;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

public interface CreateProductPresenter {
    void validateProductDetails(String name, String description, String actual_price,String sales_price, String color,
                                String seller_name,String seller_address, String seller_mobilenumber, String image);
    void onDestroy();
}
