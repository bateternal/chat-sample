package ir.oddrun.befrest.tools;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BefrestAuth {

    private static final long UID = 1;
    private static final String API_KEY = "/* YOUR API KEY */";
    private static final String SHARED_KEY = "/* YOUR SHARED KEY */";

    public String generatePublishAuth(String chid) {
        return generateAuthToken(String.format("/xapi/1/publish/%d/%s", UID, chid));
    }

    public String generateSubscriptionAuth(String chid, int sdkVersion) {
        return generateAuthToken(String.format("/xapi/1/subscribe/%d/%s/%d", UID, chid, sdkVersion));
    }

    public String generateChannelStatusAuth(String chid) {
        return generateAuthToken(String.format("/xapi/1/channel-status/%d/%s", UID, chid));
    }

    /**
     * @param addr Please refer to https://bef.rest/documentation#generating-auth-string for instructions on
     *             how to build the addr parameter
     */
    private String generateAuthToken(String addr) {
        try {
            String initialPayload = String.format("%s,%s", API_KEY, addr);
            byte[] md5 = md5(initialPayload);
            String base64 = base64Encode(md5);

            String payload = String.format("%s,%s", SHARED_KEY, base64);
            md5 = md5(payload);
            return base64Encode(md5);
        } catch (Exception e) {
            // Log the occurred exception
            return null;
        }
    }

    private byte[] md5(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(input.getBytes());
        return messageDigest.digest();
    }

    private String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input))
                .replaceAll("\\+", "-")
                .replaceAll("=", "")
                .replaceAll("/", "_");
    }
}