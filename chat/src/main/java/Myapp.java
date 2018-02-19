import ir.oddrun.befrest.utils.HibernateUtil;
import ir.oddrun.befrest.views.View;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class Myapp {
    private static Session session;
    private static SessionFactory sessionFactory;
    private static final Logger LOGGER = LogManager.getLogger(Myapp.class);

    public static final String USER_NAME = "userName";

    public Session getSession() {
        return session;
    }

    public static void main(String[] args) throws Exception {
//        try {
//            org.apache.log4j.BasicConfigurator.configure();
//            LOGGER.debug("1");
//            BasicConfigurator.configure();
//            LOGGER.debug("2");
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//            LOGGER.debug("3");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
        port(9090);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        View.create(session);
        session.getTransaction().commit();
    }
}


