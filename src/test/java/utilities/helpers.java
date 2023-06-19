package utilities;

import java.util.Base64;
import java.util.logging.Logger;

public class helpers {

    private static final Logger logger = Logger.getLogger("Helpers");

    public static String base64toString(String base64) {
        return new String(Base64.getDecoder().decode(base64));
    }

    public static boolean isLong(String val) {
        boolean status = false;
        try {
            Long.parseLong(val);
            status = true;
        } catch (Exception e) {
            status = false;
            logger.info("Exception is : " + e);
        }
        return status;
    }
}
