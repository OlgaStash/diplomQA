package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement modePaymentHeader = $("#root > div > h3");
    private final SelenideElement buttonBuyByDebit = $(byText("Купить"));
    private final SelenideElement buttonBuyByCredit = $(byText("Купить в кредит"));

    public CardData selectByDebitCard() {
    buttonBuyByDebit.click();
    modePaymentHeader.shouldHave(exactText("Оплата по карте"));
    return new CardData();
    }
    public CardData selectByCreditCard() {
        buttonBuyByCredit.click();
        modePaymentHeader.shouldHave(exactText("Кредит по данным карты"));
        return new CardData();
    }
}
