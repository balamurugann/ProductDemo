package com.bb.productdemo.fragment.createproduct;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

/**
 * Listener to indicate validation error and success after validation
 */
public interface CreateProductInteractor {

    interface OnCreateProductFinishedListener {
        void onProductNameError();
        void onProductDescriptionError();
        void onProductActualPriceError();
        void onProductSalesPriceError();
        void onProductColorsError();
        void onProductSellerNameError();
        void onProductSellerAddressError();
        void onProductSellerMobileNumberError();
        void onProductImageError();

        void onSuccess();
    }

    /**
     *  To validate all the text fields and invoke the database insert opeartion
     * @param name
     * @param description
     * @param actual_price
     * @param sales_price
     * @param color
     * @param seller_name
     * @param seller_address
     * @param seller_mobilenumber
     * @param image
     * @param listener
     */
    void createProduct(String name, String description, String actual_price,String sales_price, String color,
                       String seller_name,String seller_address, String seller_mobilenumber,String image,OnCreateProductFinishedListener listener);
}
