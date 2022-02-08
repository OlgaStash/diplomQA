package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class CardData {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement holderField = $(".input__top").shouldHave(exactText("Владелец"));
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement buttonContinue = $(".button__text").shouldHave(exactText("Продолжить"));

    private final SelenideElement successNotification = $(".notification__content").shouldHave(exactText("Операция одобрена Банком."));
    private final SelenideElement errorNotification = $(".notification__content").shouldHave(exactText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement wrongFormat = $(".input__sub").shouldHave(exactText("Неверный формат"));
    private final SelenideElement emptyField = $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    private final SelenideElement cardExpired = $(".input__sub").shouldHave(exactText("Истёк срок действия карты"));
    private final SelenideElement wrongCardLimit = $(".input__sub").shouldHave(exactText("Неверно указан срок действия карты"));

    public void inputData(DataHelper.Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        holderField.setValue(card.getHolder());
        cvcField.setValue(card.getCvv());
        buttonContinue.click();
    }

    public void waitSuccessPayment() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void waitErrorPayment() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void waitErrorFormat() {
        wrongFormat.shouldBe(Condition.visible);
    }

    public void waitErrorExpired() {
        cardExpired.shouldBe(Condition.visible);
    }

    public void waitErrorEmptyField() {
        emptyField.shouldBe(Condition.visible);
    }

    public void waitErrorCardLimit() {
        wrongCardLimit.shouldBe(Condition.visible);
    }
}
