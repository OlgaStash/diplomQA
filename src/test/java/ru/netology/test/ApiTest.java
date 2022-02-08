package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.Api.fillPaymentFormByCreditCard;
import static ru.netology.data.Api.fillPaymentFormByDebitCard;
import static ru.netology.data.DataHelper.*;

public class ApiTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

     @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldValidStatusCardPaymentApproved() {
        val validApprovedCard = new DataHelper.Card(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidName(), getCVC());
        val status = fillPaymentFormByDebitCard(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldValidStatusCardPaymentDeclined() {
        val validDeclinedCard = new DataHelper.Card(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidName(), getCVC());
        val status = fillPaymentFormByDebitCard(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void shouldValidStatusCardCreditRequestApproved() {
        val validApprovedCard = new DataHelper.Card(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidName(), getCVC());
        val status = fillPaymentFormByCreditCard(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldValidStatusCardCreditRequestDeclined() {
        val validDeclinedCard = new DataHelper.Card(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidName(), getCVC());
        val status = fillPaymentFormByCreditCard(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
