import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class demoURL {
    public static void main(String[] args) throws IOException {
        URL url =new URL("http://hieiko.com");
        System.out.println(url.getProtocol());//协议
        System.out.println(url.getPort());//端口
        System.out.println(url.getPath());//路径
        System.out.println(url.getHost());//域名
        System.out.println(url.getFile());//文件
        System.out.println(url.getQuery());//参数

        URLConnection connection=url.openConnection();
        System.out.println(connection.getLastModified());//更新时间
        System.out.println(connection.getContentLengthLong());
        System.out.println(connection.getContentType());

        System.out.println("==============================");
        InputStream in =connection.getInputStream();
        byte[] buffer =new byte[1024];
        int count;
        while ((count=in.read(buffer))>0){
            System.out.println(new String(buffer,0,count));
        }
        in.close();
    }
}
