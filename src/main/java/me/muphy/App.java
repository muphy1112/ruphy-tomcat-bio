package me.muphy;

import me.muphy.tomcat.RpTomcat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new RpTomcat().start();
    }
}
