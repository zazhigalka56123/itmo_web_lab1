package ru.zazhigalka.entity;

import ru.zazhigalka.util.Code;
import ru.zazhigalka.util.Timer;

public class Result {

    public final int code;
    public final String responseTime;
    public final double workTime;
    public final Point point;
    public final String error;


    public Result(int code, Point point, Timer timer) {
        this.code = code;
        this.point = point;
        this.workTime = timer.getTime();
        this.responseTime = timer.getServerTime();
        this.error = getErrorText();
    }

    private String getErrorText() {
        return switch (this.code) {
            case (Code.SUCCESS) -> "";
            case (Code.ERROR_EMPTY_REQUEST) -> TEXT_ERROR_EMPTY_REQUEST;
            case (Code.ERROR_BAD_REQUEST) -> TEXT_ERROR_BAD_REQUEST;
            case (Code.ERROR_SERVER_TROUBLE) -> TEXT_ERROR_SERVER_TROUBLE;
            default -> TEXT_ERROR_DEFAULT;
        };


    }

    private static final String SUCCESS_RESPONSE = """
            Content-Type: application/json
            
            {"code":"%d","result":"%s","x":"%d","y":"%.3f","r":"%.1f","time":"%s","scriptTime":"%.3f"}
            
            """;
    private static final String HTTP_ERROR = """
            Content-Type: application/json
            
            {"code":"%d","result":"%s","time":"%s","scriptTime":"%.3f"}
            
            """;

    private static final String TEXT_ERROR_DEFAULT = "Error: something went wrong";
    private static final String TEXT_ERROR_EMPTY_REQUEST = "Error: get empty request";
    private static final String TEXT_ERROR_BAD_REQUEST = "Error: bad request";
    private static final String TEXT_ERROR_SERVER_TROUBLE = "Error: exception on server";
}
