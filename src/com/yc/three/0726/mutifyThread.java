import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class mutifyThread {
    //当前下载块
    private  int downNums=0;
    public static void main(String[] args) throws Exception {
        new mutifyThread().download();
    }
    //定义下载方法
    public void download() throws Exception {
        URL url = new URL("https://mirror.bit.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
        SslUtils.ignoreSsl();
        String filename = "d:/apache-tomcat-10.0.0-M7-windows-x64.zip";
        long time = System.currentTimeMillis();
        URLConnection connection = url.openConnection();
        //获取文件总大小
        int fileSize = connection.getContentLength();
        //定义每块的大小
        int blockSize = 2 * 1024 * 1024;
        //计算块数
        int blockNums = fileSize / blockSize;
        if (fileSize % blockSize != 0) {
            blockNums++;
        }
        System.out.println("开始下载");

        for (int i = 0; i < blockNums; i++) {
            downNums++;
            int index =i;
            new Thread(){
                public void run() {
                   try{
                       System.out.println("第" + (index + 1) + "块开始下载");
            /*
            每次循环都获取一个连接对象
             */
                       URLConnection connection = url.openConnection();
                       InputStream in = connection.getInputStream();
                       FileOutputStream out = new FileOutputStream(filename + index);
                       //开始的字节数
                       int beginBytes = index * blockSize;
                       //借书的字节数
                       int endBytes = beginBytes + blockSize;
                       //结束字节数不能超过文件大小
                       if (endBytes > fileSize) {
                           endBytes = fileSize;
                       }
                       //跳过开始的字节
                       in.skip(beginBytes);

                       //当前下载位置
                       int position = beginBytes;
                       byte[] buffer = new byte[1024];
                       int count;
                       while ((count = in.read(buffer)) > 0) {
                           if (position + count > endBytes) {
                               //计算超出部分
                               int a = position + count - endBytes;
                               //减去超出部分
                               count = count - a;
                           }
                           out.write(buffer, 0, count);
                           //更新下载位置
                           position += count;
                           //如果下载位置在该块结束位置
                           if (position >= endBytes) {
                               break;
                           }
                       }
                       in.close();
                       out.close();
                       System.out.println("第" + (index + 1) + "块结束下载");
                       synchronized (mutifyThread.this){
                           mutifyThread.this.downNums--;
                           mutifyThread.this.notify();
                       }
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                }
            }.start();
        }
        synchronized (this){
            while (downNums>0){
                wait();
            }
        }
        merge(filename, blockNums);
        System.out.println("共花了" + (System.currentTimeMillis() - time) / 1000 + "秒");
        for (int i=0;i<blockNums;i++){
            File file = new File(filename+i);
            deleteDirectory(file);
        }

        System.out.println("下载完成");
    }
    public static void merge(String path, int fileNums) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        for (int i = 0; i < fileNums; i++) {
            FileInputStream fis = new FileInputStream(path + i);
            byte[] buffer = new byte[1024];
            int count;
            while ((count = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fis.close();
        }
        fos.close();
    }

    public static void deleteDirectory(File file) {
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        } else {
            // 首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
                deleteDirectory(childFile);
            }
            file.delete();
        }
    }

}