package com.kh.app.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long register(MemberDto dto) {
        MemberEntity entity = MemberEntity.toEntity(dto);
        memberRepository.register(entity);
        return entity.getNo();
    }

    public MemberDto login(MemberDto dto) {
        MemberEntity entity = memberRepository.login(dto);
        return MemberDto.toDto(entity);
    }

    public List<MemberDto> getMemberList() {
        List<MemberEntity> memberList = memberRepository.getMemberList();
        return memberList.stream().map(MemberDto::toDto).toList();
    }
}
