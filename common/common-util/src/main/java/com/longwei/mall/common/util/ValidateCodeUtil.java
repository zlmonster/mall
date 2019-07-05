package com.longwei.mall.common.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by ASUS on 2018/6/23.
 */
public class ValidateCodeUtil {
    private static char[] digital_value = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 获取数字验证码
     * @param n
     * @throws IOException
     */

    public static String getDigitdalValidateCode(int n)  {
        Random random = new Random();
        char[] c = new char[n];
        // 生成的验证码
        for (int i = 0; i < n; i++) {
            c[i] = digital_value[random.nextInt(digital_value.length)];
        }
        String ValidateCode = String.valueOf(c);
        return ValidateCode;
    }


   private static char[] value = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * 获取验证码图片，参数：n:验证码字符数量
     * @param n
     * @throws IOException
     */

    public static String getValidateCode(int n)  {

        Random random = new Random();
        char[] c = new char[n];
        // 生成的验证码
        for (int i = 0; i < n; i++) {
            c[i] = value[random.nextInt(36)];

        }


        String ValidateCode = String.valueOf(c);

        return ValidateCode;
    }

    private static char[] value1 = {  'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * 获取验证码图片，参数：n:验证码字符数量
     * @param n
     * @throws IOException
     */

    public static String getValidateCodeNew(int n)  {

        Random random = new Random();
        char[] c = new char[n];
        // 生成的验证码
        for (int i = 0; i < n; i++) {
            c[i] = value1[random.nextInt(value1.length)];

        }


        String ValidateCode = String.valueOf(c);

        return ValidateCode;
    }

    public static BufferedImage   getValidateCodeStream(String ValidateCode,int n){
        //验证码图片尺寸
        int width = 25 * n;
        int height = 30;

        Font font = new Font("黑体", Font.BOLD, 25);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        //背景色
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        //字体
        g2.setFont(font);
        char[] c = ValidateCode.toCharArray();

        //绘制字符
        for (int i = 0; i < n; i++) {
            g2.setColor(getRandColor(0, 255));
            g2.drawString(String.valueOf(c[i]), width / n * i, height - 5);
        }

        Random random = new Random();
        //绘制线条
        for (int i = 0; i < 10; i++) {
            g2.setPaint(getRandColor(0, 255));
            g2.drawLine(random.nextInt(width), random.nextInt(30), random.nextInt(width), random.nextInt(30));
            g2.drawPolygon(new Polygon());
        }
        return bufferedImage;
    }
    /**
     * 给定范围获得随机颜色
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
