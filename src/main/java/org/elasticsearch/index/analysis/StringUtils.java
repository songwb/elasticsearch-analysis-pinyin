package org.elasticsearch.index.analysis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StringUtils {

    public static void main(String[] args) throws Exception{
//        String s1 = "qazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnm";
//        String s1 = "qazwsxedcrfvtgbyhnujmikolpqwertyuioplkjhgfdsazxcvbnmqazwsxedcrfvtgbyhnujmfffffffikolpqwertyuioplkqazwsxedcrasdfghgsfdsssssfdgdasdbcnqazwsxedcrfvtgbyhqasdfghjklmnbvcxz";
        String s1 = "zsqygcjdglzhfwdqznxzfzrzsoygcglzhfwdqznzxfzrzsoygcglzhfwdoznxzfzrzsqycbzhfwdqznxzfzrzsoyyfzhfwdqznxzfzrzsoyjyzhfwdoznxzfzrzsqyzgglzhfwdocssybfzrzsxmglzhfwd";
//        String s1 = "zsqygcjdglzhfwdqznxzfzrzsoygcglzhfwdqznzxfzrzsoygcglzhfwdoznxzfzrzsqycbzhfwdqznxzfzrzsoyyfzhfwdqznxzfzrzsoyjyzhfwdqznxzfzrzsqyzhglzhfwdocssybfzrzsxmglzhfwd";
        String s = compress(s1);
        System.out.println(s1.length());
        System.out.println(s);
        System.out.println(s.length()); //qykz0j/4ALkbpew4AQAA
        System.out.println(uncompress(s));
    }

    /**
     * 使用org.apache.commons.codec.binary.Base64压缩字符串
     * @param str 要压缩的字符串
     * @return
     */
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if(str.length()<150){
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Base64.getEncoder().encode(out.toByteArray()))+",compress=";
//        return new sun.misc.BASE64Encoder().encode(out.toByteArray())+",compress=";
    }


    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        if(compressedStr.indexOf(",compress=")<0){
            return compressedStr;
        }
        String compressedStrTemp = new String(compressedStr.substring(0,compressedStr.indexOf(",compress=")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
//            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            compressed = Base64.getDecoder().decode(compressedStrTemp);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

}
