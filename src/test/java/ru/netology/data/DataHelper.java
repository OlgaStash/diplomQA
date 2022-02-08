package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.Year;
import java.util.Locale;

public class DataHelper {

    public DataHelper() {
    }

    @Value
    @RequiredArgsConstructor
    public static class Card {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvv;
    }

    private static
    Faker faker = new Faker(new Locale("en"));

    //Номер карты
    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    public static String getWrongCardNumber() {
        return ("1111 1111 1111 1111 1111");
    }

    public static String getWithZerosCardNumber() {
        return ("0000 0000 0000 0000");
    }

    public static String getCardNumberWithEmptyField() {
        return ("");
    }

    //Месяц
    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getInvalidMonth() {
        return ("13");
    }

    public static String getMonthOneDigit() {
        return ("8");
    }

    public static String getMonthWithZeros() {
        return ("00");
    }

    public static String getMonthWithEmptyField() {
        return ("");
    }

    //Год
    public static String getValidYear() {
        return String.format("%ty", Year.now());
    }

    public static String getPastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.minusYears(1));
    }

    public static String getYeahOneDigit() {
        return ("2");
    }

    public static String getLastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(1));
    }

    public static String get25Year() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(3));
    }

    public static String getYearEmptyField() {
        return ("");
    }

    //Владелец
    public static String getValidName() {
        return faker.name().fullName();
    }

    public static String getRussianName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getNameWithDigits() {
        return "55555555";
    }

    public static String getNameWithSigns() {
        return "***&*^*^&^%^^&";
    }

    public static String getNameOne() {
        return faker.name().firstName();
    }

    public static String getOneLetter() {
        return "W";
    }

    public static String getOwnerNameLong() {
        return "Verylonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglong";
    }

    public static String getNameEmptyField() {
        return "";
    }

    //CVC/CVV

    public static String getCVC() {
        return "999";
    }

    public static String getCVCOneDigit() {
        return "2";
    }

    public static String getCVCTwoDigits() {
        return "12";
    }

    public static String getCVCEmptyField() {
        return "";
    }
}
