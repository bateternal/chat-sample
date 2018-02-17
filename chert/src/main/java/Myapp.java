import ir.oddrun.befrest.utils.HibernateUtil;
import ir.oddrun.befrest.views.view;
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
        ///
        view.create(session);
//        view.check(session);
        session.getTransaction().commit();
//        Scanner s = new Scanner(System.in);
//        int i= s.nextInt();

//        Users user = new Users();
//        user.setUsername("bateternal");
//        String hql = "SELECT E.id FROM Users E WHERE E.username = :a ";
//        Query query = session.createQuery(hql);
//        query.setParameter("a", "bateternal");
//        List results = query.list();
//        System.out.println(results);
//        Users userToDelete = session.load(Users.class, (int)results.get(0));
//        session.beginTransaction();
//        session.delete(userToDelete);
//        session.getTransaction().commit();
        ///
//        Users kevin = new Users();
//
//        final String json = toJson(kevin);
//
//        puts("JSON", json);
//
//        post("/create", (req, res) -> req.raw().getParameter("data"));
    }
}


