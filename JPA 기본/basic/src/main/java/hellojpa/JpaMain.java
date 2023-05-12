package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        //등록
        try{
            Member member = new Member();
            member.setId(3L);
            member.setName("sanghak");

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
//            em.close();
        }


        //수정
        try{
            Member member = em.find(Member.class, 1L);
            member.setName("joohak");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
//            em.close();
        }

        emf.close();

        //전체 조회
        try{
            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : members) {
                System.out.println(member.getName());
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
