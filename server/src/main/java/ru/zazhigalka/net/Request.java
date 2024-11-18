package ru.zazhigalka.net;

import ru.zazhigalka.entity.Point;

public class Request {

    public static Point parse(String input) throws IllegalArgumentException {
        try {
            String[] nums = input.split("&");
            double x = Float.parseFloat(nums[0].substring(2));
            int y = (int) Float.parseFloat(nums[1].substring(2));
            double r = Float.parseFloat(nums[2].substring(2));

            return new Point(x, y, r);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format. Please provide valid integers.");
        }
    }
}
