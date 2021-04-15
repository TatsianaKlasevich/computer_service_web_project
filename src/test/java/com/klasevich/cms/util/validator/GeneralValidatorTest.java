package com.klasevich.cms.util.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GeneralValidatorTest {
    @DataProvider
    public Object[][] serviceNameData() {
        return new Object[][]{
                {"коррекция", true},
                {"КОРРЕКЦИЯ", true},
                {"123дл", false},
                {"Sjmlknsdfi", false},
                {null, false},
                {"0", false},
                {"все хорошо!!!", false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] priceData() {
        return new Object[][]{
                {"0.20", true},
                {"20.20", true},
                {"15", true},
                {"Sjmlknsdfi", false},
                {null, false},
                {"0", false},
                {"все хорошо!!!", false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] dateData() {
        return new Object[][]{
                {"2021.04.10", true},
                {"1021.04.10", false},
                {"2021.13.10", false},
                {"2022-04-02", true},
                {"2022-04-55", false},
                {null, false},
                {"24", false},
                {"все хорошо!!!", false},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] addressData() {
        return new Object[][]{
                {"ул. Смолякова, 10-25", true},
                {"ул. Красная, д.11, кв.25", true},
                {"Empire str., 24", false},
                {"ул. Груздева, 24", true},
                {null, false},
                {"Хорошая улица", true},
                {"", false},
        };
    }

    @DataProvider
    public Object[][] nameData() {
        return new Object[][]{
                {"Прося", true},
                {"kirill", true},
                {null, false},
                {"", false},
                {"андрей", true},
                {"2022-04-24", false},
                {"24", false},
                {"a", false},
                {"все хорошо!!!", false},
        };
    }

    @DataProvider
    public Object[][] mailData() {
        return new Object[][]{
                {"tanya@mai.ru", true},
                {"tan@mai.ru", false},
                {"tanya_klass@mai.ru", true},
                {"", false},
                {"андрей", false},
                {"2022-04-24", false},
                {"tanya_klass@mai.rumtour", false},
                {null, false},
        };
    }

    @DataProvider
    public Object[][] phoneData() {
        return new Object[][]{
                {"+375332894566", true},
                {"+375202894566", false},
                {"+000000000000", false},
                {"", false},
                {"андрей", false},
                {"2022-04-24", false},
                {"+375291000000", true},
                {null, false},
        };
    }

    @DataProvider
    public Object[][] passwordData() {
        return new Object[][]{
                {"12345678", true},
                {"123456", false},
                {"lkj#4kjdf", true},
                {"", false},
                {"андрей", false},
                {"!-<lkjsdf", true},
                {"ыдвлоатдлва", false},
                {null, false},
        };
    }

    @Test(dataProvider = "serviceNameData")
    public void testIsValidServiceName(String serviceName, boolean expected) {
        boolean actual = GeneralValidator.isValidServiceName(serviceName);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "priceData")
    public void testIsValidPrice(String price, boolean expected) {
        boolean actual = GeneralValidator.isValidPrice(price);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dateData")
    public void testIsValidDate(String date, boolean expected) {
        boolean actual = GeneralValidator.isValidDate(date);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "addressData")
    public void testIsValidAddress(String address, boolean expected) {
        boolean actual = GeneralValidator.isValidAddress(address);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "nameData")
    public void testIsValidName(String name, boolean expected) {
        boolean actual = GeneralValidator.isValidName(name);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "mailData")
    public void testIsValidMail(String mail, boolean expected) {
        boolean actual = GeneralValidator.isValidMail(mail);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "phoneData")
    public void testIsValidPhone(String phone, boolean expected) {
        boolean actual = GeneralValidator.isValidPhone(phone);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "passwordData")
    public void testIsValidPass(String password, boolean expected) {
        boolean actual = GeneralValidator.isValidPass(password);
        assertEquals(actual, expected);
    }
}