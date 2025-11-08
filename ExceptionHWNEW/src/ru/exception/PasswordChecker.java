package ru.exception;

public class PasswordChecker {
    private int passwordLength = -1;
    private int maxRepeats = -1;


    public void setMinLength(int passwordLength) throws IllegalArgumentException {
        if (passwordLength < 0) {
            throw new IllegalArgumentException("Длина пароля не может быть отрицательной");
        }

        this.passwordLength = passwordLength;
    }

    public void setMaxRepeats(int maxRepeats) throws IllegalArgumentException {
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException("Количество повторений символа не может быть отрицательным или 0");
        }
        this.maxRepeats = maxRepeats;
    }

    public boolean verify(String password) throws IllegalStateException {
        if (passwordLength == -1 || maxRepeats == -1) {
            throw new IllegalStateException("Необходимо установить настройки пароля");
        }
        if (password.length() < passwordLength) {
            return false;
        }

        int repeatCount = 1;
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                repeatCount++;
                if (repeatCount > maxRepeats) {
                    return false;
                }
            } else {
                repeatCount = 1;
            }
        }

        return true;
    }
}

