package com.serezka.client.io.format.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.serezka.client.object.Color;
import com.serezka.client.object.Person;

public class PersonConverter extends AbstractBeanField<Person, String> {
    @Override
    protected Person convert(String value) throws CsvDataTypeMismatchException {
        if (value == null || value.isEmpty())
            return null;

        String[] parts = value.split(",");
        if (parts.length != 4)
            throw new CsvDataTypeMismatchException("Неверный формат данных о человеке");

        try {
            String name = parts[0];
            Long height = Long.parseLong(parts[1]);
            Color eyeColor = Color.valueOf(parts[2]);
            Color hairColor = Color.valueOf(parts[3]);

            return Person.builder().name(name).height(height).eyeColor(eyeColor).hairColor(hairColor).build();
        } catch (Exception e) {
            throw new CsvDataTypeMismatchException("Неверный формат данных о человеке" + e.getMessage());
        }
    }

    @Override
    public String convertToWrite(Object value) {
        Person person = (Person) value;
        if (person == null)
            return "";

        return person.getName() + "," + person.getHeight() + "," + person.getEyeColor() + "," + person.getHairColor();
    }
}
