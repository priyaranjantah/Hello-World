package org.Stage.Kickdrum.EnggDashboard;

import com.google.zxing.WriterException;
import org.Utility.Utils;

import java.io.IOException;
import java.util.Scanner;

import static org.Utility.Utils.getTOTPCode;

public class GoogleAuthenticator {

    public static void main(String[] args) throws IOException, WriterException {
        GenerateSecretKey gn = new GenerateSecretKey();
        String secretKey = gn.generateSecretKey();
        String email = "priyaranjantah@gmail.com";
        String companyName = "Awesome Company";
        String barCodeUrl = Utils.getGoogleAuthenticatorBarCode(secretKey, email, companyName);
        System.out.println(barCodeUrl);
        System.out.println(Utils.createQRCode(barCodeUrl, "QRCode.png", 400, 400));


        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        if (code.equals(getTOTPCode(secretKey))) {
            System.out.println("Logged in successfully");
        } else {
            System.out.println("Invalid 2FA Code");
        }

    }
}
