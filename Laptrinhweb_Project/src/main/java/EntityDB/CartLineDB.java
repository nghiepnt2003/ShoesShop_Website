package EntityDB;

import DBUtil.DBUtil;
import Entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CartLineDB {
    public static void insert(CartLine cartLine) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(cartLine);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(CartLine cartLine) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(cartLine);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void delete(CartLine cartLine) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(cartLine));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static CartLine getCartLineByID(Long cartID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT cartline FROM CartLine cartline " +
                "WHERE cartline.id = :id";
        TypedQuery<CartLine> q = em.createQuery(qString, CartLine.class);
        q.setParameter("id",cartID);
        try{
            CartLine cartLine = q.getSingleResult();
            return cartLine;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }
    public static List<CartLine> getCartLinesByProductID(Long productID)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT cartline FROM CartLine cartline WHERE cartline.product.id = :pid";
        TypedQuery<CartLine> q = em.createQuery(qString, CartLine.class);
        q.setParameter("pid",productID);
        try {
            List<CartLine> cartLines = q.getResultList();
            return cartLines;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }


}
