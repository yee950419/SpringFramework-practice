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
            Item album = new Album();
            album.setName("item");
            em.persist(album);

            Item book = new Book();
            book.setName("dkdk");
            em.persist(book);
//            System.out.println(album.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
