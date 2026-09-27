package com.example.phonebook;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Простая телефонная книга.
 *
 * Хранит данные в двух картах, чтобы оба поиска выполнялись без полного
 * перебора:
 *  - nameToNumber (TreeMap) — ключи (имена) всегда отсортированы,
 *    поэтому printAllNames не требует явной сортировки;
 *  - numberToName (HashMap) — обратный индекс для быстрого поиска по номеру.
 */
public class PhoneBook {

    private final Map<String, String> nameToNumber = new TreeMap<>();
    private final Map<String, String> numberToName = new HashMap<>();

    /**
     * Добавляет контакт. Гарантируется, что повторяющиеся имена не передаются.
     *
     * @return количество контактов в книге после добавления
     */
    public int add(String name, String number) {
        nameToNumber.put(name, number);
        numberToName.put(number, name);
        return nameToNumber.size();
    }

    /** Находит имя по номеру телефона без полного перебора. */
    public String findByNumber(String number) {
        return numberToName.get(number);
    }

    /** Находит номер телефона по имени без полного перебора. */
    public String findByName(String name) {
        return nameToNumber.get(name);
    }

    /** Печатает все имена в алфавитном порядке (без явной сортировки). */
    public void printAllNames() {
        for (String name : nameToNumber.keySet()) {
            System.out.println(name);
        }
    }
}
