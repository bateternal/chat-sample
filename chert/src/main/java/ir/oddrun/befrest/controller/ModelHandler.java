package ir.oddrun.befrest.controller;

import ir.oddrun.befrest.hibernate.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ModelHandler {
    public static boolean SaveToDatabase(String name, String lastName, String userName, String Password, Session session){
        try {
            session.beginTransaction();
            Users u = new Users();
            u.setName(name);
            u.setLastname(lastName);
            u.setUsername(userName);
            u.setPassword(Password);
            session.save(u);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean Check(Session session,String username,String password){
        try {
            String hql = "SELECT E.password FROM Users E WHERE E.username = :a ";
            Query query = session.createQuery(hql);
            query.setParameter("a", username);
            List results = query.list();
            System.out.println("0000000000000000000000000000000000000000000000000000000000000000000000");
            System.out.println(results);
            System.out.println(password);
            System.out.println(results.get(0));
            if(results.get(0).toString().equals(password)) {
                System.out.println("salam");
                return true;
            }
            return false;
        }
        catch(Exception e){
            return false;
        }

    }

    public static String GetName(Session session,String username){
        try {
            String hql = "SELECT E.name FROM Users E WHERE E.username = :a ";
            Query query = session.createQuery(hql);
            query.setParameter("a", username);
            List results = query.list();
            return results.get(0).toString();
        }
        catch(Exception e){
            return "{234}";
        }
    }
}
