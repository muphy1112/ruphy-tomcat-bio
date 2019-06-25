package me.muphy.tomcat.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 2019/6/26
 * 莫非
 */
public class RpResponse {

    private OutputStream outputStream;
    public RpResponse(OutputStream outputStream){
this.outputStream = outputStream;
    }

    public void write(String s) throws IOException {
        StringBuilder sb = new StringBuilder();
outputStream.write(sb.append("HTTP/1.1 200 OK\n").append("Content-Type: text/html\n")
        .append("\r\n")
        .append(s).toString().getBytes());
    }
}
