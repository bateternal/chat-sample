package ir.oddrun.befrest.views;

import org.hibernate.Session;

import static ir.oddrun.befrest.controller.ModelHandler.Check;
import static ir.oddrun.befrest.controller.ModelHandler.SaveToDatabase;
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
    }
    public static void check(Session session){
        post("/check",(req,res) -> {
            String username = req.raw().getParameter("uname");
            String password = req.raw().getParameter("pass");
            if (Check(session,username,password)) return "1";
            return "2";
        });
    }
}
