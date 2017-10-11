package com.bb.productdemo.fragment.createproduct;

/**
 * Created by bala.natarajan on 10/11/2017.
 */

public class CreateProductPresenterImpl implements CreateProductPresenter,CreateProductInteractor.OnCreateProductFinishedListener {

    private CreateProductView createProductView;
    private CreateProductInteractor createProductInteractor;

    public CreateProductPresenterImpl(CreateProductView createProductView) {
        this.createProductView = createProductView;
        this.createProductInteractor = new CreateProductInteractorImpl();
    }


    @Override
    public void validateProductDetails(String name, String description, String actual_price, String sales_price,
                                       String color, String seller_name, String seller_address, String seller_mobilenumber,String image) {
        createProductInteractor.createProduct(name, description,actual_price,
                sales_price,color,seller_name,seller_address,seller_mobilenumber,image,this);
    }

    @Override
    public void onDestroy() {
        createProductView = null;
    }

    @Override
    public void onProductNameError() {
        if (createProductView != null) {
            createProductView.setProductNameError();
        }
    }

    @Override
    public void onProductDescriptionError() {
        if (createProductView != null) {
            createProductView.setProductDescriptionError();
        }
    }

    @Override
    public void onProductActualPriceError() {
        if (createProductView != null) {
            createProductView.setProductActualPriceError();
        }
    }

    @Override
    public void onProductSalesPriceError() {
        if (createProductView != null) {
            createProductView.setProductSalesPriceError();
        }
    }

    @Override
    public void onProductColorsError() {
        if (createProductView != null) {
            createProductView.setProductColorsError();
        }
    }

    @Override
    public void onProductSellerNameError() {
        if (createProductView != null) {
            createProductView.setProductSellerNameError();
        }
    }

    @Override
    public void onProductSellerAddressError() {
        if (createProductView != null) {
            createProductView.setProductSellerAddressError();
        }
    }

    @Override
    public void onProductSellerMobileNumberError() {
        if (createProductView != null) {
            createProductView.setProductSellerMobileNumberError();
        }
    }

    @Override
    public void onProductImageError() {
        if (createProductView != null) {
            createProductView.setProductImageError();
        }
    }

    @Override
    public void onSuccess() {
        if (createProductView != null) {
            createProductView.navigateToProductList();
        }
    }
}
