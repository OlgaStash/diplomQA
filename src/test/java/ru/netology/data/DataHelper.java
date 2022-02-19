package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    static Faker faker = new Faker(new Locale("en"));
    //    static Random random = new Random();
    //    static int cvc = (int) (100 + Math.random() * 899);
    static String correctCVC = Integer.toString((int) (100 + Math.random() * 899));
    //    static int monthNumber = (int) (1 + Math.random()*8);
    static String wrongMonthNumber = Integer.toString((int) (1 + Math.random() * 8));
    //    static int year = (int) (15 + Math.random() * 6);
    static String wrongYear = Integer.toString((int) (15 + Math.random() * 6));

    public DataHelper() {
    }

    public static CardInfo getValidCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "22", "08", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getInvalidCardInfo() {
        return new CardInfo("4444 4444 4444 4442", "22", "08", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getCardInfoWithWrongDifferentCardNumber() {

        return new CardInfo("1111 1111 1111 1111", "22", "08", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getCardInfoWithEmptyCardNumber() {

        return new CardInfo("", "22", "08", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getCardInfoWithShortestCardNumber() {

        return new CardInfo(wrongMonthNumber, "22", "11", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getCardInfoWithWrongMonth() {
        return new CardInfo("4444 4444 4444 4441", "22", wrongMonthNumber, faker.name().fullName(), correctCVC);
    }

    public static CardInfo getValidCardInfoWithEmptyMonth() {
        return new CardInfo("4444 4444 4444 4441", "22", "", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getCardInfoWithWrongYear() {
        return new CardInfo("4444 4444 4444 4441", wrongYear, "12", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getValidCardInfoWithEmptyYear() {
        return new CardInfo("4444 4444 4444 4441", "", "08", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getCardInfoWithWrongYearMoreLimit() {
        return new CardInfo("4444 4444 4444 4441", "30", "12", faker.name().fullName(), correctCVC);
    }

    public static CardInfo getCardInfoWithWrongCvc() {
        return new CardInfo("4444 4444 4444 4441", "22", "11", faker.name().fullName(), wrongYear);
    }

    public static CardInfo getValidCardInfoWithEmptyCvc() {
        return new CardInfo("4444 4444 4444 4441", "22", "09", faker.name().fullName(), "");
    }

    public static CardInfo getCardInfoWithWrongHolderNameDigit() {
        return new CardInfo("4444 4444 4444 4441", "22", "11", "000", correctCVC);
    }

    public static CardInfo getCardInfoEmptyName() {
        return new CardInfo("4444 4444 4444 4441", "22", "11", " ", correctCVC);
    }

    public static CardInfo getCardInfoWithWrongHolderNameSpecialSymbol() {
        return new CardInfo("4444 4444 4444 4441", "22", "11", "!%#*", correctCVC);
    }

    public static CardInfo getCardInfoWithWrongHolderNameRussianLetters() {
        return new CardInfo("4444 4444 4444 4441", "22", "11", "Оля Сташ", correctCVC);
    }

    public static CardInfo getCardInfoWithWrongHolderNameOneWord() {
        return new CardInfo("4444 4444 4444 4441", "22", "11", "Olga", correctCVC);
    }

    @Value
    public static class CardInfo {
        private String number, year, month, holder, cvc;

    }
}
