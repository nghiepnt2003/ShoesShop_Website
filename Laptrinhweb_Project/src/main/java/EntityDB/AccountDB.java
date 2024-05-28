package EntityDB;

import DBUtil.DBUtil;
import Entity.Account;
import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AccountDB {
    public static void insert(Account account) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(account);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(Account account) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(account);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void delete(Account account) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(account));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static Account getAccountbyAccountName(String userName){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT account FROM Account account " +
                "WHERE account.userName = :userName";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        q.setParameter("userName",userName);
        try{
            Account acc = q.getSingleResult();
            return acc;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }
    public static Account getAccount(String userName,String pass){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT account FROM Account account " +
                "WHERE account.userName = :userName AND account.password = :password";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        q.setParameter("userName",userName);
        q.setParameter("password",pass);
        try{
            Account acc = q.getSingleResult();
            return acc;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }
    public static Account getAccountByID(Long userID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT account FROM Account account " +
                "WHERE account.id = :id";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        q.setParameter("id",userID);
        try{
            Account acc = q.getSingleResult();
            return acc;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }
    // List toàn bộ account không phải của admin
//    public static List<Account> getAllAccountCustomer(){
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        String qString = "SELECT account FROM Account account " +
//                "WHERE account.isAdmin = false";
//        TypedQuery<Account> q = em.createQuery(qString, Account.class);
//        try{
//            List<Account> accountList = q.getResultList();
//            return accountList;
//        }catch (NoResultException e){
//            return null;
//        } finally {
//            em.close();
//        }
//    }

}
