package com.kh.app.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void register(MemberEntity entity) {
        em.persist(entity);
    }

    public MemberEntity login(MemberDto dto) {
        String jpql = "select m from MemberEntity m where m.userId = :userId and m.userPwd = :userPwd and delYn = 'N'";
        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("userId", dto.getUserId())
                .setParameter("userPwd", dto.getUserPwd())
                .getSingleResult();
    }

    public List<MemberEntity> getMemberList() {
        String jpql = "select m from MemberEntity m where delYn = 'N' order by m.no desc";
        return em.createQuery(jpql, MemberEntity.class).getResultList();
    }
}
