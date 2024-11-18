package ru.zazhigalka.net;

import ru.zazhigalka.entity.Result;
import ru.zazhigalka.util.Code;


public class Response {

    public static void send(Result result) {
        if (result.code == Code.SUCCESS){
            System.out.printf(
                    SUCCESS_RESPONSE,
                    result.code,
                    result.point.inArea,
                    result.point.x,
                    result.point.y,
                    result.point.r,
                    result.responseTime,
                    result.workTime
            );
        } else {
            System.out.printf(
                    ERROR_RESPONSE,
                    result.code,
                    result.error,
                    result.responseTime,
                    result.workTime
            );

        }
    }

    private static final String SUCCESS_RESPONSE = """
            Content-Type: application/json
            
            {"code":"%d","result":"%s","x":"%.1f","y":"%d","r":"%.1f","responseTime":"%s","workTime":"%.3f"}
            
            """;
    private static final String ERROR_RESPONSE = """
            Content-Type: application/json
            
            {"code":"%d","result":"%s","time":"%s","responseTime":"%.3f"}
            
            """;
}
