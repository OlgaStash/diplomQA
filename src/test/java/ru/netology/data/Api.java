package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Api {
    static String appUrl = System.getProperty("app.url");
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(appUrl)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String fillPaymentFormByDebitCard(DataHelper.CardInfo cardInfo) {
        String response = given()
                .spec(requestSpec)
                .body(cardInfo)
                .when()
                .post("/api/v1/pay")
                .then().log().all()
                .statusCode(200)
                .extract()
                .asString();
        return response;
    }

    public static String fillPaymentFormByCreditCard(DataHelper.CardInfo cardInfo) {
        String response = given()
                .spec(requestSpec)
                .body(cardInfo)
                .when()
                .post("/api/v1/credit")
                .then().log().all()
                .statusCode(200)
                .extract()
                .asString();
        return response;
    }
}
