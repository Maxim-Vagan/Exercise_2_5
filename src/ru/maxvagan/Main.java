package ru.maxvagan;

import ru.maxvagan.exceptions.WrongLoginException;
import ru.maxvagan.exceptions.WrongPasswordException;

import java.util.Random;

public class Main {

    private static Boolean checkBoundaryIlligalSymbals(String inpStr) {
        boolean isValid = true;
        for (char symb : inpStr.toCharArray()) {
            isValid = Character.isLetterOrDigit(symb) || symb == '_';
            if (!isValid) {
                //System.out.println(symb);
                break;
            }
        }
        return isValid;
    }

    private static Boolean checkLogin(String inpLogin) {
        boolean isLoginValid = false;

        if (inpLogin.length() >= 21)
            throw new WrongLoginException("Длинна Логина превышает 20 символов!");
        else if (!checkBoundaryIlligalSymbals(inpLogin))
            throw new WrongLoginException("Логин содержит недопустимые символы!");
        else isLoginValid = true;
        return isLoginValid;
    }

    private static Boolean checkPasswords(String inpPassword, String inpConfPassword) {
        boolean isLoginValid = false;
        if (inpPassword.length() >= 21)
            throw new WrongPasswordException("Длинна Пароля превышает 20 символов!");
        else if (!checkBoundaryIlligalSymbals(inpPassword))
            throw new WrongPasswordException("Пароль содержит недопустимые символы!");
        else if (!inpPassword.equals(inpConfPassword))
            throw new WrongPasswordException("Пароль не совпадает с Подтверждением!");
        else isLoginValid = true;
        return isLoginValid;
    }

    private static Boolean checkingUsersAccountParam(String login, String password, String confirmPassword) {
        boolean isAllSatisfied = false; boolean isLoginValid = false; boolean isPassValid = false;

        try {
            isLoginValid = checkLogin(login);
            isPassValid = checkPasswords(password, confirmPassword);
        } catch (WrongLoginException e) {
            e.printStackTrace();
        } catch (WrongPasswordException e) {
            e.printStackTrace();
        } finally {
            isAllSatisfied = isLoginValid && isPassValid;
            return isAllSatisfied;
        }
    }

    private static final String DICTIONARY_STRING = genDictionaryString();
    
    private static String genDictionaryString() {
        StringBuilder strDict = new StringBuilder();
        for (int i = 48; i < 58; i++) strDict.append((char) i);
        strDict.append('-');
        for (int i = 97; i < 123; i++) strDict.append((char) i);
        strDict.append('.');
        for (int i = 64; i < 91; i++) strDict.append((char) i);
        strDict.append('_');
        return strDict.toString();
    }
    
    private static String generatorRandomString(String dict) {
        Random generator = new Random();
        int lenOfString = 5 + generator.nextInt(25);
        StringBuilder resultStr = new StringBuilder(lenOfString);
        while (resultStr.length() < lenOfString) {
            resultStr.append((char) dict.charAt(generator.nextInt(dict.length())));
        }
        return resultStr.toString();
    }

    public static void main(String[] args) {
	    // Task 1
        String usersLogin = generatorRandomString(DICTIONARY_STRING);
        String usersPassword = generatorRandomString(DICTIONARY_STRING);
        String[] varietyConfirmPwd = {usersPassword, generatorRandomString(DICTIONARY_STRING)};
        String usersConfirmPassword = varietyConfirmPwd[(int) Math.round(Math.random())];
        System.out.printf("User enters Login (length is %s): %s\r\n", usersLogin.length(), usersLogin);
        System.out.printf("User  enters  Password (length is %s): %s\r\n", usersPassword.length(), usersPassword);
        System.out.printf("User confirms Password (length is %s): %s\r\n", usersConfirmPassword.length(), usersConfirmPassword);
        System.out.println("Do All parameters satisfy? - " + checkingUsersAccountParam(usersLogin, usersPassword, usersConfirmPassword));
    }
}
