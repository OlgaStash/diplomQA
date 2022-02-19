package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlUtil;
import ru.netology.page.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String appUrl = System.getProperty("app.url");
        open(appUrl);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldCheckSuccessValidDebitCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByDebitCard();
        val validCardInfo = DataHelper.getValidCardInfo();
        CardData.inputData(validCardInfo);
        CardData.waitSuccessPayment();
        val paymentId = SqlUtil.getPaymentId();
        val statusForPaymentByDebitCard = SqlUtil.getStatusDebitCard(paymentId);
        val paymentAmount = SqlUtil.getStatusDebitCard(paymentId);
        assertEquals("APPROVED", statusForPaymentByDebitCard);
        assertEquals("4500000", paymentAmount);

    }

    @Test
    void shouldCheckSuccessValidCreditCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val validCardInfo = DataHelper.getValidCardInfo();
        CardData.inputData(validCardInfo);
        CardData.waitSuccessPayment();
        val paymentId = SqlUtil.getPaymentId();
        val statusForPaymentByCreditCard = SqlUtil.getStatusCreditCard(paymentId);
        assertEquals("APPROVED", statusForPaymentByCreditCard);
    }

    @Test
    void shouldCheckErrorWithInvalidDebitCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByDebitCard();
        val invalidCardInfo = DataHelper.getInvalidCardInfo();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorPayment();
        val paymentId = SqlUtil.getPaymentId();
        val statusForPaymentByDebitCard = SqlUtil.getStatusDebitCard(paymentId);
        assertThat(statusForPaymentByDebitCard, equalTo("DECLINED"));
    }

    @Test
    void shouldCheckErrorWithInvalidCreditCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getInvalidCardInfo();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorPayment();
        val paymentId = SqlUtil.getPaymentId();
        val statusForPaymentByCreditCard = SqlUtil.getStatusCreditCard(paymentId);
        assertThat(statusForPaymentByCreditCard, equalTo("DECLINED"));
    }

    @Test
    void shouldCheckErrorFormatDifferentCardNumber() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongDifferentCardNumber();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorPayment();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorPayment();
    }

    @Test
    void shouldCheckErrorFormatWithShortCardNumber() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithShortestCardNumber();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorEmptyFieldCardNumber() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithEmptyCardNumber();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorFormatWithWrongMonth() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongMonth();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorEmptyFieldMonth() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getValidCardInfoWithEmptyMonth();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorFormatWithWrongYear() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongYear();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorExpired();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorExpired();
    }

    @Test
    void shouldCheckErrorFormatWhereYearMoreLimit30() { //Проверка при установке года больше, чем возможный срок карты +5 лет от настоящего
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongYearMoreLimit();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorCardLimit();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorCardLimit();
    }

    @Test
    void shouldCheckErrorEmptyFieldYear() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getValidCardInfoWithEmptyYear();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorFormatWithWrongCVC() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongCvc();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorEmptyFieldCVC() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getValidCardInfoWithEmptyCvc();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorFormatDigitName() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongHolderNameDigit();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorEmptyFieldName() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoEmptyName();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorEmptyField();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorEmptyField();
    }

    @Test
    void shouldCheckErrorSpecialSymbolFieldName() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongHolderNameSpecialSymbol();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorRussianLettersFieldName() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongHolderNameRussianLetters();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }

    @Test
    void shouldCheckErrorOneWordFieldName() {
        val mainPage = new MainPage();
        val CardData = mainPage.selectByCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongHolderNameOneWord();
        CardData.inputData(invalidCardInfo);
        CardData.waitErrorFormat();
        val CardData2 = mainPage.selectByDebitCard();
        CardData2.inputData(invalidCardInfo);
        CardData2.waitErrorFormat();
    }
}
