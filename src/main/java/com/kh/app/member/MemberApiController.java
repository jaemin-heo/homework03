package com.kh.app.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("register")
    public Long register(@RequestBody MemberDto dto){
        return memberService.register(dto);
    }

    @PostMapping("login")
    public MemberDto login(@RequestBody MemberDto dto, HttpSession session){
        MemberDto loginMember = memberService.login(dto);
        if (loginMember == null){
            throw new MemberException("[MEMBER-1] 아이디 또는 비밀번호가 틀립니다.");
        }
        session.setAttribute("loginMember", loginMember);
        return loginMember;
    }

    @PostMapping("logout")
    public Long logout(HttpSession session){
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        if (loginMember == null){
            throw new MemberException("[MEMBER-2] 로그인 상태가 아닙니다.");
        }
        return loginMember.getNo();
    }

    @GetMapping
    public List<MemberDto> getMemberList(){
        return memberService.getMemberList();
    }

}
