package EntityDB;

import DBUtil.DBUtil;
import Entity.Account;
import Entity.Cart;
import Entity.Customer;
import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerDB {
    public static void insert(Customer customer) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(customer);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(customer);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void delete(Customer customer) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(customer));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static  Customer getCustomerByAccount(Account account){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT customer FROM Customer customer WHERE customer.account.id = :accountid";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        q.setParameter("accountid",account.getId());
        try{
            Customer customer = q.getSingleResult();
            return customer;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }

    public static  Customer getCustomerByID(Long id){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT customer FROM Customer customer WHERE customer.id = :id";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        q.setParameter("id",id);
        try{
            Customer customer = q.getSingleResult();
            return customer;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }

        // Lấy ra toàn bộ customer (người mua) nên isadmin = false
        public static List<Customer> getAllCustomer(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT customer FROM Customer customer " +
                "WHERE customer.account.isAdmin = false";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        try{
            List<Customer> customerList = q.getResultList();
            return customerList;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }

    public static  Customer getCustomerByMail(String name,String mail){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT customer FROM Customer customer WHERE customer.mail = :mail AND customer.account.userName =:name";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        q.setParameter("name",name);
        q.setParameter("mail",mail);
        try{
            Customer customer = q.getSingleResult();
            return customer;
        }catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }





}
