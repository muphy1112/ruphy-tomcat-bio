package me.muphy.tomcat.servlet;

import me.muphy.tomcat.http.RpRequest;
import me.muphy.tomcat.http.RpResponse;
import me.muphy.tomcat.http.RpServlet;

import java.io.IOException;

/**
 * 2019/6/26
 * 莫非
 */
public class FirstServlet extends RpServlet {


    @Override
    public void doGet(RpRequest request, RpResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(RpRequest request, RpResponse response) throws IOException {
        response.write("This is First Servlet!");
    }

}
