package EntityDB;

import DBUtil.DBUtil;
import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OrderDetailDB {

    public static void insert(OrderDetail orderDetail) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(orderDetail);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(OrderDetail orderDetail) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(orderDetail);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void delete(OrderDetail orderDetail) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(orderDetail));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

//    public static List<OrderDetail> getAllOrderDetailsByOrder(Order order) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        String qString = "SELECT orderdetail  FROM OrderDetail orderdetail " +
//                "WHERE  ";
//        TypedQuery<Order> q = em.createQuery(qString, Order.class);
//        try {
//            List<Order> orders = q.getResultList();
//            return orders;
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }
}
