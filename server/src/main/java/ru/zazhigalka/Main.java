package ru.zazhigalka;

import com.fastcgi.FCGIInterface;
import ru.zazhigalka.entity.Point;
import ru.zazhigalka.entity.Result;
import ru.zazhigalka.net.Request;
import ru.zazhigalka.net.Response;
import ru.zazhigalka.util.Code;
import ru.zazhigalka.util.Timer;


public class Main {
    public static void main(String[] args) {
        int code;
        Timer timer;
        Point point = null;
        Result result;
        FCGIInterface fcgi = new FCGIInterface();

        while (fcgi.FCGIaccept() >= 0) {
            try {
                if (FCGIInterface.request.params.getProperty("REQUEST_METHOD").equals("GET")) {
                    timer = new Timer();
                    timer.start();

                    String input = System.getProperties().getProperty("QUERY_STRING");
                    if (input.isBlank()) {
                        code = Code.ERROR_EMPTY_REQUEST;
                    } else {
                        try {
                            point = Request.parse(input);
                            code = Code.SUCCESS;
                        } catch (IllegalArgumentException exception) {
                            code = Code.ERROR_BAD_REQUEST;
                        }
                    }
                    result = new Result(code, point, timer);
                    Response.send(result);
                }
            }catch (Exception e){
                timer = new Timer();
                timer.start();
                result = new Result(Code.ERROR_SERVER_TROUBLE, null, timer);
                Response.send(result);

            }
        }
    }
}