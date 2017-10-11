package com.bb.productdemo.fragment.createproduct;

import android.text.TextUtils;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

/**
 * Validate the text fields in create product page
 */
public class CreateProductInteractorImpl implements CreateProductInteractor {
    @Override
    public void createProduct(String name, String description, String actual_price, String sales_price,
                              String color, String seller_name, String seller_address, String seller_mobilenumber,String image,final OnCreateProductFinishedListener listener) {
        boolean error = false;
        if (TextUtils.isEmpty(name)){
            listener.onProductNameError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(description)){
            listener.onProductDescriptionError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(actual_price)){
            listener.onProductActualPriceError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(sales_price)){
            listener.onProductSalesPriceError();
            error = true;
            return;
        }

        if (TextUtils.isEmpty(color)){
            listener.onProductColorsError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(seller_name)){
            listener.onProductSellerNameError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(seller_address)){
            listener.onProductSellerAddressError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(seller_mobilenumber)){
            listener.onProductSellerMobileNumberError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(image)){
            listener.onProductImageError();
            error = true;
            return;
        }
        if (!error){
            listener.onSuccess();
        }
    }
}
