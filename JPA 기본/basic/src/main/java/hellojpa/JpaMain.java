package hellojpa;

import hellojpa.domain.Member;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member m1 = new Member();
            em.persist(m1);

            Member m2 = new Member();
            em.persist(m2);

            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, m1.getId());
            System.out.println("reference = " + reference.getClass());
            reference.getName();

            em.getReference(Member.class, m1.getId());
            System.out.println("m2 reference = " + reference.getClass());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
