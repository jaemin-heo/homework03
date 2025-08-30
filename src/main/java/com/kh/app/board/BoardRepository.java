package com.kh.app.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public void insert(BoardEntity entity) {
        em.persist(entity);
    }

    public List<BoardEntity> getBoardList() {
        String jpql = "select b from BoardEntity b where b.delYn = 'N' order by b.no desc";
        return em.createQuery(jpql, BoardEntity.class).getResultList();
    }

    public BoardEntity getBoard(Long no) {
        return em.find(BoardEntity.class, no);
    }
}
