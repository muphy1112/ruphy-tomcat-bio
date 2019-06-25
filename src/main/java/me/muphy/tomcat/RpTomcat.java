package me.muphy.tomcat;

import me.muphy.tomcat.http.RpRequest;
import me.muphy.tomcat.http.RpResponse;
import me.muphy.tomcat.http.RpServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 2019/6/26
 * 莫非
 */
public class RpTomcat {

    private int port = 8080;
    private ServerSocket serverSocket;

    private Map<String, RpServlet> servletMapping = new HashMap<>();

    private Properties webxml = new Properties();

    private void init() {
        try {
            String web_inf = this.getClass().getResource("/").getPath();

            FileInputStream fis = new FileInputStream(web_inf + "web.properties");
            webxml.load(fis);

            for (Object o : webxml.keySet()) {
                String key = o.toString();
                if(key.endsWith(".url")){
                    String servletName = key.replaceAll("\\.url","");
                    String url = webxml.getProperty(key);

                    String className = webxml.getProperty(servletName + ".className");
                    RpServlet servlet = (RpServlet) Class.forName(className).newInstance();
                    servletMapping.put(url.toLowerCase(), servlet);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void start(){
        init();

        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Ruphy tomcat 已启动，监听端口号：" + this.port);

            while(true){
                Socket socket = serverSocket.accept();
                process(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void process(Socket socket) throws IOException {

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        RpRequest request = new RpRequest(inputStream);
        RpResponse response = new RpResponse(outputStream);

        String url = request.getUrl().toLowerCase();

        if(servletMapping.containsKey(url)){
            RpServlet servlet = servletMapping.get(url);
            servlet.service(request, response);
        } else{
            response.write("404 - Not Found!");
        }

        outputStream.flush();
        inputStream.close();
        outputStream.close();

        socket.close();

    }

}
