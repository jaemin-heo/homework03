package com.kh.app.member;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String userId;
    private String userPwd;
    private String userNick;

    private String delYn;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    public MemberEntity() {
        this.delYn = "N";
        this.createAt = LocalDateTime.now();
    }

    public static MemberEntity toEntity(MemberDto dto){
        MemberEntity entity = new MemberEntity();
        entity.userId = dto.getUserId();
        entity.userPwd = dto.getUserPwd();
        entity.userNick = dto.getUserNick();
        return entity;
    }

    public void delete(){
        this.delYn = "Y";
        this.updatedAt = LocalDateTime.now();
    }


}
