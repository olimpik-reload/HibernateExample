package ru.ivmiit.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.Users;

public class Application {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/fix_users");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "123");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.addResource("User.hbm.xml");
        configuration.addAnnotatedClass(Car.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Users user =  session.createQuery("from Users users where users.id=1", Users.class).getSingleResult();

        session.beginTransaction();
        session.save(new Users("mini","max" , 99));
        session.getTransaction().commit();
        System.out.println(user);

        Car car = (Car) session.createQuery("from Car car",Car.class).getResultList();

        int i = 0;
        }
}
