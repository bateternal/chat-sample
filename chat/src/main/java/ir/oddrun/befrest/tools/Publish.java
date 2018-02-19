package ir.oddrun.befrest.tools;

import ir.oddrun.befrest.controller.HttpUtil;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.HashMap;

public class Publish {
    public static boolean publish(String chid,String msg){
        try {
            HashMap<String,String> headers = new HashMap<>();
            String auth = BefrestAuth.generatePublishAuth(chid);
            headers.put("X-BF-AUTH",auth);
            String url = String.format("https://api.bef.rest/xapi/1/publish/11257/%s", chid);
            HttpRequestBase req = HttpUtil.buildHttpRequest("POST", url, null, headers, msg);
            HttpUtil.fetchRawResponse(req, 1);
            return true;
        }
        catch (Exception e){
            System.out.println("0000000000000000000000000000000000");
            System.out.println(e.toString());
            System.out.println("0000000000000000000000000000000000");
            return false;
        }
    }
}
