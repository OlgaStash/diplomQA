package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardData {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement holderField = $$(".input__inner").find(Condition.text("Владелец")).$(".input__control");
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement buttonContinue = $$(".button__text").find(Condition.text("Продолжить"));

    private final SelenideElement successNotification = $(withText("Успешно"));
    private final SelenideElement errorNotification = $(withText("Ошибка"));
    private final SelenideElement wrongFormat = $$(".input__sub").find(Condition.text("Неверный формат"));
    private final SelenideElement emptyField = $$(".input__sub").find(Condition.text("Поле обязательно для заполнения"));
    private final SelenideElement cardExpired = $$(".input__sub").find(Condition.text("Истёк срок действия карты"));
    private final SelenideElement wrongCardLimit = $$(".input__sub").find(Condition.text("Неверно указан срок действия карты"));

    public void inputData(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolder());
        cvcField.setValue(cardInfo.getCvc());
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
