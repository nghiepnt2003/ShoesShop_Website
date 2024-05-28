package EntityDB;

import DBUtil.DBUtil;
import Entity.Account;
import Entity.Category;
import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CategoryDB {
    public static void insert(Category category) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(Category category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void delete(Category category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(category));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static List<Category> getAllCategory() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c";
        TypedQuery<Category> q = em.createQuery(qString, Category.class);
        try {
            List<Category> categories = q.getResultList();
            return categories;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static Category getCategoryByID(int id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Category c WHERE c.id = :id";
        TypedQuery<Category> q = em.createQuery(qString, Category.class);
        q.setParameter("id",id);
        try {
            Category category = q.getSingleResult();
            return category;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
