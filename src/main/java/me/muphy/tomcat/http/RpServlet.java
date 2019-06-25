package me.muphy.tomcat.http;

import java.io.IOException;

/**
 * 2019/6/26
 * 莫非
 */
public abstract class RpServlet {

    public void service(RpRequest request, RpResponse response) throws IOException {
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        } else if("POST".equalsIgnoreCase(request.getMethod())){
            doPost(request, response);
        } else {
            doPost(request, response);
        }


    }

    public abstract void doGet(RpRequest request, RpResponse response) throws IOException;

    public abstract void doPost(RpRequest request, RpResponse response) throws IOException;

}
