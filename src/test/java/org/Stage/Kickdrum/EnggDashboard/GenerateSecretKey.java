package org.Stage.Kickdrum.EnggDashboard;

import clover.org.apache.commons.codec.binary.Base64;
import clover.org.apache.commons.codec.binary.Hex;
import com.google.api.client.googleapis.util.Utils;
import de.taimos.totp.TOTP;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

public class GenerateSecretKey {
    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base64 base32 = new Base64();
        return base32.encodeToString(bytes);
    }

    public static String getTOTPCode(String secretKey) {
        Base64 base32 = new Base64();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {

        String secretKey = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
        String email = "priyaranjantah@gmail.com";
        String companyName = "Github";
        String barCodeUrl = GenerateSecretKey.getGoogleAuthenticatorBarCode(secretKey, email, companyName);
        System.out.println(barCodeUrl);
    }
}
