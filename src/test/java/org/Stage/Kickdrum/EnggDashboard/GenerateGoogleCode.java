package org.Stage.Kickdrum.EnggDashboard;

import static org.Stage.Kickdrum.EnggDashboard.GenerateSecretKey.generateSecretKey;
import static org.Stage.Kickdrum.EnggDashboard.GenerateSecretKey.getTOTPCode;

public class GenerateGoogleCode {
    public String generateGoogleCode() {
        String secretKey = generateSecretKey();
        String lastCode = null;
        while (true) {
            String code = getTOTPCode(secretKey);
            if (!code.equals(lastCode)) {
                return code;
            }
            lastCode = code;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
