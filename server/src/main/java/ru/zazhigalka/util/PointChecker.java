package ru.zazhigalka.util;

import ru.zazhigalka.entity.Point;

public class PointChecker {

    public static boolean inArea(Point point){

        if (0 <= point.x && point.y <= 0 && point.x <= point.r &&  point.y >= -1 * point.r) {
            return true;
        }
        if (point.x <= 0 && point.y <= 0 && -point.x - (point.r / 2) >= point.y) {
            return true;
        }
        if (point.x <= 0 && point.y >= 0 && point.y * point.y <= point.r * point.r - point.x * point.x) {
            return true;
        }
        return false;
    }
}
