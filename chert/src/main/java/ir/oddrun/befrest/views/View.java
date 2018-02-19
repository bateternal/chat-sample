package ir.oddrun.befrest.views;

import ir.oddrun.befrest.tools.BefrestAuth;
import org.hibernate.Session;

import static ir.oddrun.befrest.controller.ModelHandler.Check;
import static ir.oddrun.befrest.controller.ModelHandler.GetName;
import static ir.oddrun.befrest.controller.ModelHandler.SaveToDatabase;
import static ir.oddrun.befrest.tools.Publish.publish;
import static org.boon.Boon.toJson;
import static spark.Spark.get;
import static spark.Spark.post;

public class view {
    public static void create(Session session){
        post("/create", (req, res) -> {
            String name = req.raw().getParameter("name");
            String lastName = req.raw().getParameter("last");
            String username = req.raw().getParameter("uname");
            String password = req.raw().getParameter("pass");
            boolean b = SaveToDatabase(name,lastName,username,password,session);
            if(b)return "done";
            return "sorry,this username is already taken";
        });
        post("/check",(req,res) -> {
            String username = req.raw().getParameter("uname");
            String password = req.raw().getParameter("pass");
            boolean b = Check(session,username,password);
            if (b) {
                return "1";
            }
            return "2";
        });
        post("/getname",(req,res) -> {
            String username = req.raw().getParameter("uname");
            return GetName(session,username);
        });
        post("/getauth",(req,res)->{
            String username = req.raw().getParameter("uname");
            return BefrestAuth.generateSubscriptionAuth(username);
        });
        post("/send",(req,res) -> {
            String username = req.raw().getParameter("uname");
            String content = req.raw().getParameter("content");
            boolean b = publish(username,content);
            if(b)return "ok";
            return "fail";
        });
    }
//    public static void check(Session session){
//
//    }
}
