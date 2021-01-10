package Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    String passwordRegex = "^(?=.*[0-9])"+"(?=.*[a-z])(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$).{8,20}$";

    public String validatePassword(String password) {
        Pattern p = Pattern.compile(passwordRegex);
        Matcher m = p.matcher(password);
        if(!m.matches()) {
            return "Password is not a valid password.";
        }
        return null;
    }


}
