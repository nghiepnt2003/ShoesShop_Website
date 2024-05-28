package EntityDB;

import DBUtil.DBUtil;
import Entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class CartDB {
    public static void insert(Cart cart) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(cart);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(cart);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(cart));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static Cart getCartByCustomer(Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT cart FROM Cart cart WHERE cart.customer.id = :customerid";
        TypedQuery<Cart> q = em.createQuery(qString, Cart.class);
        q.setParameter("customerid", customer.getId());
        try {
            Cart cart = q.getSingleResult();
            return cart;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static void updateCart(Cart cart) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            em.merge(cart);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Log or handle the exception appropriately
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Cart getCartByID(Long cartID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT cart FROM Cart cart " +
                "WHERE cart.id = :id";
        TypedQuery<Cart> q = em.createQuery(qString, Cart.class);
        q.setParameter("id", cartID);
        try {
            Cart acc = q.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }



    public static Cart getCartByCartLine(CartLine cartLine) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT cart FROM Cart cart " +
                "WHERE cart.cartLines = :cartline";
        TypedQuery<Cart> q = em.createQuery(qString, Cart.class);
        q.setParameter("cartline", cartLine);
        try {
            Cart acc = q.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
