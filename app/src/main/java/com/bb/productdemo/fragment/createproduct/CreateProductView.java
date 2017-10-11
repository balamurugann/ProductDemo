package com.bb.productdemo.fragment.createproduct;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

/**
 * To set the error message for corresponding text fields and navigate the product list page after all the validation is done.
 */
public interface CreateProductView {
    void setProductNameError();
    void setProductDescriptionError();
    void setProductActualPriceError();
    void setProductSalesPriceError();
    void setProductColorsError();
    void setProductSellerNameError();
    void setProductSellerAddressError();
    void setProductSellerMobileNumberError();
    void setProductImageError();

    void navigateToProductList();
}
