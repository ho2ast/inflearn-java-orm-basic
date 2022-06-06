package hellojpa;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            저장
//            비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("helloA");

//            영속
//            em.persist(member);

//            1차 캐시에서 조회
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println(findMember2 == findMember1);

//            수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJpa");

//             JPQL로 조회 페이징도 각 디비에 맞게 방언으로 변경됨... 미쳤다
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();


//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(151L, "B");
//             엔티티 매니져에 등록 commit()이 되어야 실제로 쿼리 실행한다.
//            em.persist(member1);
//            em.persist(member2);

//            Member member = new Member(201L, "member201");
//            em.persist(member);
//            em.flush();

//            준영속
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJpa");
//            em.detach(findMember); // 특정 엔티디만 준영속 상태
//            em.clear(); // 영속성 컨텍스트 완전히 초기화, 1차 캐시 통으로 초기화

//            Member member = new Member();
//            member.setUsername("A");
//            member.setRoleType(RoleType.GUEST);
//            em.persist(member);

            // 연관관계매핑 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member2");
            member.changeTeam(team);

            // 양뱡향 관계 매핑시 실수
            team.getMembers().add(member);
            em.persist(member);

            em.flush();
            em.clear();

//            Member member = em.find(Member.class, 2L);
//            System.out.println("member = " + member.getTeamId());
//            Team team = em.find(Team.class, member.getTeamId());
//            System.out.println("team.getName() = " + team.getName());

//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();

            // 수정
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

//            List<Member> members = findMember.getTeam().getMembers();

//            members.forEach(m -> {
//                System.out.println("m.getUsername() = " + m.getUsername());
//            });


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
