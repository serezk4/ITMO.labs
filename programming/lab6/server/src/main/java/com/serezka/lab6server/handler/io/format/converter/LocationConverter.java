package com.serezka.lab6server.handler.io.format.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.serezka.lab6server.handler.object.Location;

public class LocationConverter extends AbstractBeanField<Location, String> {
    @Override
    protected Location convert(String value) throws CsvDataTypeMismatchException {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String[] parts = value.split(",");
        if (parts.length != 3) {
            throw new CsvDataTypeMismatchException("Неверный формат локации");
        }
        try {
            Integer x = Integer.parseInt(parts[0]);
            double y = Double.parseDouble(parts[1]);
            Integer z = Integer.parseInt(parts[2]);
            return Location.builder().x(x).y(y).z(z).build();
        } catch (NumberFormatException e) {
            throw new CsvDataTypeMismatchException("Неверный формат чисел для локации" + e.getMessage());
        }
    }

    @Override
    public String convertToWrite(Object value) {
        Location location = (Location) value;
        if (location == null) {
            return "";
        }
        return location.getX() + "," + location.getY() + "," + location.getZ();
    }
}
