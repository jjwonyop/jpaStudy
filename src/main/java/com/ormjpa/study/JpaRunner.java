/*
 * Copyright (c) 2019.
 * Made by jjwonyop
 */

package com.ormjpa.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PostRepository postRepository;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
//        Account account = new Account();
//        account.setUsername("juwon");
//        account.setPassword("pass");
//
//        Study study = new Study();
//        study.setName("spring DATA");
////        study.setOwner(account);
//
////        account.getStudies().add(study);
//        account.addStudy(study);
//        // 관계의 주인에다가 세팅을 해줘야됨
//        study.setOwner(account);
//
//        Session session = entityManager.unwrap(Session.class);
//        session.save(account);
//        session.save(study);
//
//        // select가 발생하지 않음, 이미 session이 캐싱하고 있기 때문에 --> 관리중인 객체라고 생각해도 됨
//        Account juwon = session.load(Account.class, account.getId());
//        juwon.setUsername("jjwonyop");
//        juwon.setUsername("jjwonyop1");
//        juwon.setUsername("jjwonyop2");
//        juwon.setUsername("juwon");
//        // 불필요 select안함, 근데 알아서 update --> dirty checking(write behind) --> 최대한 늦게 jpa가 db반영할려고 함
//        System.out.println("===================");
//        System.out.println(juwon.getUsername());
//        // insert는 트랜잭션이 끝나고 발생
//
//        //persistent --> detached 변하는 것은 트랜잭션 종료 후 사용이 될 때
//        //persistent로 되돌릴려면 다른 것으로....(Session.update, merge, saveOrUpdate메소드 사용)
//        // cascade ==> 상태 변화 전이
//        Post post = new Post();
//        post.setTitle("Spring data JPA 언제...");
//
//        Comments comment = new Comments();
//        comment.setComments("hhhh bbbb");
//        post.addComment(comment);
//
//        Comments comment2 = new Comments();
//        comment2.setComments("wwww bbbb");
//        post.addComment(comment2);
//        Session session1 = entityManager.unwrap(Session.class);
//        session1.save(post);
//        Post post = session1.get(Post.class, 1l);
//        session1.delete(post);

//        // fetch --> 연관관계의 엔티티를 어떻게 가져올 것인가....
//        // 지금(eager), 나중에(lazy)
//        // onetomany는 lazy
//        // manytoone은 eager
//
//        Post post1 = session1.get(Post.class, 21l);
//        System.out.println("-----------------------");
//        System.out.println(post1.getTitle());
//
//        post1.getComments().forEach(c -> {
//            System.out.println("=========================");
//            System.out.println(c.getComments());
//        });


        // JPQL(HQL)
        // 테이블 단위가 아닌, 엔티티 객체 모델 기반으로 쿼리 작성
        // type safe하지 않음
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);

        List<Post> posts = query.getResultList();
        posts.forEach(System.out::println);

        // criteria
        // type safe한 쿼리
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.select(root);

        // Native SQL
        Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM POST", Post.class);
        List<Post> resultList1 = nativeQuery.getResultList();
        resultList1.forEach(System.out::println);


        List<Post> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        resultList.forEach(System.out::println);

        List<Post> all = postRepository.findAll();
        all.forEach(System.out::println);
    }
}
