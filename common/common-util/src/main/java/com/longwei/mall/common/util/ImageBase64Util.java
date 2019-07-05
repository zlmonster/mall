package com.longwei.mall.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageBase64Util {
	public static void main(String[] args) throws IOException {
		//生成jpeg图片 
		String imgFilePath = "d:\\222.jpg";//新生成的图片 
		OutputStream out = new FileOutputStream(imgFilePath);
		ImageBase64Util.GenerateImage("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",out);		
		out.flush();
		out.close();
	}

	public static String GetImageStr(byte[] data) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理 		
		//对字节数组Base64编码 
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);//返回Base64编码过的字节数组字符串 
	}

	public static boolean GenerateImage(String imgStr, OutputStream out) {//对字节数组字符串进行Base64解码并生成图片 
		if (imgStr == null) //图像数据为空 
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64解码 
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据 
					b[i] += 256;
				}
			}
			out.write(b);	
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}