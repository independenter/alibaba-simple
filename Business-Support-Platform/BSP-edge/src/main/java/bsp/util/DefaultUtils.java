package bsp.util;

import org.apache.tomcat.util.buf.HexUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class DefaultUtils {
    public static String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz",32);
    }

    public static String randomCode(String s, int i) {
        StringBuilder result = new StringBuilder(i);
        Random random = new Random();
        for(int j=0;j<i;j++){
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    public static String md5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(password.getBytes("utf-8"));
            return HexUtils.toHexString(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
