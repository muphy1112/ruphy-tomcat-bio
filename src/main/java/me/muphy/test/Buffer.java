package me.muphy.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 2019/6/26
 * 莫非
 */
public class Buffer {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("E:\\gpstudy\\ruphy-tomcat\\temp\\test.txt");
        FileChannel fc = fin.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化：", buffer);

//        fc.read(buffer);
        output("read：", buffer);

        buffer.flip();
        output("flip：", buffer);

        while (0 < buffer.remaining()) {
            byte b = buffer.get();
        }
        output("get：", buffer);

        buffer.clear();
        output("clear：", buffer);

        FileOutputStream outputStream = new FileOutputStream("E:\\gpstudy\\ruphy-tomcat\\temp\\copy-test.txt");
        FileChannel fileChannel = outputStream.getChannel();
        FileChannel fc1 = fin.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocateDirect(1024);

        while (true){
            buffer1.clear();
            int i = fc1.read(buffer1);
            if(i == -1){
                break;
            }
            buffer1.flip();
            fileChannel.write(buffer1);
        }

        fileChannel.close();
        fc1.close();
        fc.close();
        fin.close();
        outputStream.close();

    }

    private static void output(String s, ByteBuffer buffer) {
        System.out.println(s);
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
    }
}
