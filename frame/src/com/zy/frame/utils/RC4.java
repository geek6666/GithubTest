package com.zy.frame.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 
 * This is a class that Implements the RC4 encryption algorithm.
 * 
 * 
 * @author			 EJJ
 * @date			 Feb 16, 2012
 */
public class RC4 {
    private static final String APP_ENCODING = "utf-8";
    private static final String KEY = "xxx";

	private static byte[] initKey(String aKey) {
        byte[] b_key = aKey.getBytes();
        byte state[] = new byte[256];

        for (int i = 0; i < 256; i++) {
            state[i] = (byte) i;
        }
        int index1 = 0;
        int index2 = 0;
        if (b_key == null || b_key.length == 0) {
            return null;
        }
        for (int i = 0; i < 256; i++) {
            index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
            byte tmp = state[i];
            state[i] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % b_key.length;
        }
        return state;
    }

	private static byte[] rc4Base (byte [] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte key[] = RC4.initKey(mKkey);
        int xorIndex;
        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 0xff;
            y = ((key[x] & 0xff) + y) & 0xff;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
            result[i] = (byte) (input[i] ^ key[xorIndex]);
        }
        return result;
    }

	public static String base64AndRc4(String password){
		try {
			byte[] rc4Bytes = RC4.rc4Base(password.getBytes(APP_ENCODING), KEY);
			// Create Hex String
			StringBuilder hexString = new StringBuilder();
			for (byte b : rc4Bytes) {
				String h = Integer.toHexString(0xFF & b);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			// System.out.println("rc4 str: " + hexString.toString());

			String base64Str = Base64.encodeToString(rc4Bytes, Base64.DEFAULT);
			return base64Str.substring(0, base64Str.length() - 1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return  null;
	}
}
