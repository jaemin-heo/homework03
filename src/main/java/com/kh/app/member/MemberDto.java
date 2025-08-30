package com.kh.app.member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class MemberDto {

    private Long no;
    private String userId;
    private String userPwd;
    private String userNick;
    private String delYn;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    public static MemberDto toDto(MemberEntity entity){
        MemberDto dto = new MemberDto();
        dto.no = entity.getNo();
        dto.userId = entity.getUserId();
        dto.userPwd = entity.getUserPwd();
        dto.userNick = entity.getUserNick();
        dto.delYn = entity.getDelYn();
        dto.createAt = entity.getCreateAt();
        dto.updatedAt = entity.getUpdatedAt();
        return dto;
    }
}
