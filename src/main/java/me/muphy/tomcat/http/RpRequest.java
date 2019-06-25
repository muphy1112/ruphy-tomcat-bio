package me.muphy.tomcat.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * 2019/6/26
 * 莫非
 */
public class RpRequest {

    private String url;
    private String method;

    public RpRequest(InputStream inputStream) {
        String content = "";
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            if((len = inputStream.read(buf))> 0){
                content += new String(buf, 0, len);
            }
            System.out.println(content);
            String string = content.split("\r?\n")[0];

            String[] arr = string.split(" ");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
