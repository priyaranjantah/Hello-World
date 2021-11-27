package entrypoint;

import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

public class TOTPCode {

    public static String getTOTPCode(String securityKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(SecurityKey.getSecurityKey());
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }
    }


