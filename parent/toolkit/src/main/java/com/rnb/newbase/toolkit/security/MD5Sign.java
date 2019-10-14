package com.rnb.newbase.toolkit.security;


import java.security.MessageDigest;

public class MD5Sign {
    private static final String ALGORITHM = "MD5";

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * encode string
     *
     * @param algorithm
     * @param str
     * @return String
     */
    public static String encode(String algorithm, String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * encode By MD5
     *
     * @param str
     * @return String
     */
    public static String encodeByMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes
     *            the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        System.out.println("111111 MD5  :"
                + MD5Sign.encodeByMD5("action=json&body=Pay order&mch_id=Laowang&notify_url=http://18.138.181.63/core-server/api/paymentUilioner/callback&out_trade_no=24&redirect_url=http://18.138.181.63/payment/wallet.html?id=nOgeCzzqUqIpP1ChmZdbxw==&spbill_create_ip=127.0.0.1&total_fee=1104&trade_type=WX"));
        System.out.println("111111 MD5  :"
                + MD5Sign.encode("MD5", "action=json&body=Pay order&mch_id=Laowang&notify_url=http://18.138.181.63/core-server/api/paymentUilioner/callback&out_trade_no=24&redirect_url=http://18.138.181.63/payment/wallet.html?id=nOgeCzzqUqIpP1ChmZdbxw==&spbill_create_ip=127.0.0.1&total_fee=1104&trade_type=WX"));
        System.out.println("111111 SHA1 :"
                + MD5Sign.encode("SHA1", "action=json&body=Pay order&mch_id=Laowang&notify_url=http://18.138.181.63/core-server/api/paymentUilioner/callback&out_trade_no=24&redirect_url=http://18.138.181.63/payment/wallet.html?id=nOgeCzzqUqIpP1ChmZdbxw==&spbill_create_ip=127.0.0.1&total_fee=1104&trade_type=WX"));
    }
}
