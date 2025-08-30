package com.kh.app.board;

import com.kh.app.member.MemberDto;
import com.kh.app.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerNo", nullable = false)
    private MemberEntity writer;

    private String delYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardEntity() {
        this.delYn = "N";
        this.createdAt = LocalDateTime.now();
    }

    public static BoardEntity toEntity(BoardDto dto, MemberEntity memberEntity){
        BoardEntity entity = new BoardEntity();
        entity.title = dto.getTitle();
        entity.content = dto.getContent();
        entity.writer = memberEntity;
        return entity;
    }

    public void delete() {
        this.delYn = "Y";
        this.updatedAt = LocalDateTime.now();
    }

    public void update(BoardDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.updatedAt = LocalDateTime.now();
    }
}
