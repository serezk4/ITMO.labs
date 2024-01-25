package com.serezka.lab6server.handler.io.format.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.serezka.lab5.chat.object.Coordinates;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CoordinatesConverter extends AbstractBeanField<Coordinates, String> {
    @Override
    protected Coordinates convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String[] parts = value.split(",");
        if (parts.length != 2) {
            throw new CsvDataTypeMismatchException("Неверный формат координат");
        }
        try {
            Float x = Float.parseFloat(parts[0]);
            Long y = Long.parseLong(parts[1]);
            return Coordinates.builder().x(x).y(y).build();
        } catch (NumberFormatException e) {
            throw new CsvDataTypeMismatchException("Неверный формат чисел для локации " + e.getMessage());
        }
    }

    @Override
    public String convertToWrite(Object value) {
        Coordinates coordinates = (Coordinates) value;
        if (coordinates == null)
            return "";
        return coordinates.getX() + "," + coordinates.getY();
    }
}
