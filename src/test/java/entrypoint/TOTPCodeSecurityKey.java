package entrypoint;

public class TOTPCodeSecurityKey {

    public static String getTOTPCodeSecurityKey(String securityKey) {

        String lastCode = null;
        while (true) {
            String code = TOTPCode.getTOTPCode(SecurityKey.getSecurityKey());
            if (!code.equals(lastCode)) {
                System.out.println(code);
            }
            lastCode = code;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
