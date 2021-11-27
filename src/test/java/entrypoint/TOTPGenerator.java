package entrypoint;

import org.jboss.aerogear.security.otp.Totp;

public class TOTPGenerator {


    /**
     * Method is used to get the TOTP based on the security token
     * @return
     */
    public static String getTwoFactorCode(){
//Replace with your security key copied from step 12
        Totp totp = new Totp(System.getenv("GOOGLE_SECURITY_KEY_PE_LIBRARY")); // 2FA secret key
        String twoFactorCode = totp.now(); //Generated 2FA code here
        return twoFactorCode;
    }


}
