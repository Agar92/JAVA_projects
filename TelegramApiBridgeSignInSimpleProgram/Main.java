import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.UserContact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TelegramApiBridge bridge = new TelegramApiBridge("149.154.167.50:443", 68588, "11b1339bc7acda1bed464ab2313617ae");
        System.out.println("Please type phone number");
        AuthSentCode sentCode = bridge.authSendCode(reader.readLine().trim());
        System.out.println("User registered: " + sentCode.isRegistered());
        System.out.println("Please enter your authorization code");
        AuthAuthorization authorisation = bridge.authSignIn(reader.readLine().trim());
        System.out.println("The registered user name is: " + authorisation.getUser());
        ArrayList<UserContact> userContactArrayList = bridge.contactsGetContacts();
        for (UserContact userContactElement : userContactArrayList) {
            System.out.println(userContactElement.toString()
                    + " " + userContactElement.getPhone());
        }
            // закрыть сеанс
            bridge.authLogOut();
    }
}

