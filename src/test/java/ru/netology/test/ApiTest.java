package ru.netology.test;


import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.Api;
import ru.netology.data.DataHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ApiTest {

    @Test
    void shouldValidStatusCardPaymentApproved() {
        val validApprovedCard = DataHelper.getValidCardInfo();
        final String response = Api.fillPaymentFormByDebitCard(validApprovedCard);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldValidStatusCardPaymentDeclined() {
        val invalidDeclinedCard = DataHelper.getInvalidCardInfo();
        final String response = Api.fillPaymentFormByDebitCard(invalidDeclinedCard);
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    void shouldValidStatusCardCreditRequestApproved() {
        val validApprovedCard = DataHelper.getValidCardInfo();
        final String response  = Api.fillPaymentFormByCreditCard(validApprovedCard);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldInvalidStatusCardCreditRequestDeclined() {
        val invalidDeclinedCard = DataHelper.getInvalidCardInfo();
        final String response  = Api.fillPaymentFormByCreditCard(invalidDeclinedCard);
        assertTrue(response.contains("DECLINED"));
    }
}
