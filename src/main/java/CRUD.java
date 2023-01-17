import Entidades.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUD {

    private static SessionFactory sessionFactory;
    private static Session session;

    protected static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void open() {
        setUp();
        session = sessionFactory.openSession();
    }

    public static void close() {
        sessionFactory.close();
    }

    /**
     * Procedimiento que recibe un objeto por parámetros y lo inserta en la base de datos
     *
     * @param objetoInsertar objeto que se desea insertar en la base de datos
     **/
    public static void create(Object objetoInsertar) {
        Transaction transaction1 = session.beginTransaction();
        session.save(objetoInsertar);
        transaction1.commit();
    }

    /**
     * Función que recibe un id y una opción, busca un objeto en la base de datos y lo devuelve
     *
     * @param id entero que corresponde al id del objeto que se desea sacar de la base de datos
     * @param opc entero que corresponde al tipo de objeto que se desea sacar.
     * 1: Entidades.MedicamentosEntity
     * 2: Entidades.PacientesEntity
     * 3: Entidades.RecetasEntity
     * @return Object, devuelve el objeto del tipo correspondiente a la opción encontrado en la base de datos, si no encuentra nada devuelve un Object vacío
     **/
    public static Object read(int id, int opc) {
        Transaction transaction = session.beginTransaction();
        Object objetoLeer = new Object();
        switch (opc){
            case 1 -> objetoLeer = session.get(MedicamentosEntity.class, id);
            case 2 -> objetoLeer = session.get(PacientesEntity.class, id);
            case 3 -> objetoLeer = session.get(RecetasEntity.class, id);
        }
        transaction.commit();
        return objetoLeer;
    }

    /**
     * Función que recibe un objeto y lo actualiza en la base de datos
     *
     * @param objetoEditado objeto que se desea actualizar en la base de datos
     **/
    public static void update(Object objetoEditado) {
        Transaction transaction = session.beginTransaction();
        session.merge(objetoEditado);
        transaction.commit();
    }

    /**
     * Función que recibe un objeto y lo elimina de la base de datos
     *
     * @param objetoEliminar objeto que se desea eliminar de la base de datos
     **/
    public static void delete(Object objetoEliminar) {
        Transaction transaction = session.beginTransaction();
        session.remove(objetoEliminar);
        transaction.commit();
    }

}