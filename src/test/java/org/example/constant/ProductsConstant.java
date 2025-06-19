package org.example.constant;

public final class ProductsConstant {

    // API Names
    public static final String GET_ALL_PRODUCTS_API_NAME = "[GET] Get All Products";
    public static final String ADD_PRODUCT_API_NAME = "[POST] Add Product";
    public static final String UPDATE_PRODUCT_API_NAME = "[PUT] Update Product";

    // API Endpoints
    public static final String PRODUCTS_ENDPOINT = "/auth/products";
    public static final String ADD_PRODUCT_ENDPOINT = PRODUCTS_ENDPOINT + "/add";

    // CSV File Paths
    public static final String PRODUCT_NAMES_CSV_FILE_PATH = "data/product_names.csv";
}
