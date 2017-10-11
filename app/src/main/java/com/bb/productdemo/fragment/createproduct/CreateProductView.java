package com.bb.productdemo.fragment.createproduct;

/**
 * Created by bala.natarajan on 10/11/2017.
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
