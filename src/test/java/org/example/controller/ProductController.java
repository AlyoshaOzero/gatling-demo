package org.example.controller;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.http.HttpDsl;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.example.constant.BaseConstant;
import org.example.constant.EnvironmentConstant;
import org.example.constant.ProductsConstant;

public final class ProductController {

    public static ActionBuilder getAllProducts() {
        return HttpDsl
                .http(ProductsConstant.GET_ALL_PRODUCTS_API_NAME)
                .get(EnvironmentConstant.BASE_URL + ProductsConstant.PRODUCTS_ENDPOINT)
                .header(BaseConstant.AUTH_HEADER_NAME, BaseConstant.AUTH_TOKEN_PLACEHOLDER)
                .check(
                        HttpDsl
                                .status()
                                .is(HttpResponseStatus.OK.code())
                )
                .check(
                        HttpDsl
                                .status()
                                .saveAs(BaseConstant.RESPONSE_STATUS_CODE)
                );
    }
}
