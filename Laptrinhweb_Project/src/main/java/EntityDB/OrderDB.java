package EntityDB;

import DBUtil.DBUtil;
import Entity.Account;
import Entity.Order;
import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OrderDB {
    public static void insert(Order order) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(order);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(order);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void delete(Order order) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(order));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static List<Order> getAllOrders() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT ord FROM Order ord " +
                "WHERE ord.customer.account.isAdmin = false " +
                "ORDER BY ord.dateOrder DESC";
        TypedQuery<Order> q = em.createQuery(qString, Order.class);
        try {
            List<Order> orders = q.getResultList();
            return orders;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static List<Order> getOrdersbyCusID(Long CusID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT ord FROM Order ord WHERE ord.customer.id = :id ORDER BY ord.dateOrder desc ";
        TypedQuery<Order> q = em.createQuery(qString, Order.class);
        q.setParameter("id",CusID);
        try {
            List<Order> orders = q.getResultList();
            return orders;
        } finally {
            em.close();
        }
    }
    public static Order getOrderByID(Long id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT ord FROM Order ord WHERE ord.id = :id";
        TypedQuery<Order> q = em.createQuery(qString, Order.class);
        q.setParameter("id",id);
        try {
            Order order = q.getSingleResult();
            return order;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }


}
