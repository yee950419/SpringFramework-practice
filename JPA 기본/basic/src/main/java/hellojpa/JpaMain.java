package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Order;
import hellojpa.domain.OrderStatus;
import hellojpa.domain.item.Album;
import hellojpa.domain.item.Book;
import hellojpa.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member member = new Member();
            member.setName("sanghak");
            member.setCreatedDate(LocalDateTime.now());
            em.persist(member);

            System.out.println(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
