package com.kh.app.board;

import com.kh.app.member.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("insert")
    public Long insert(@RequestBody BoardDto dto, HttpSession session){
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        if (loginMember == null){
            throw new BoardException("[BOARD-1] 로그인 상태가 아닙니다.");
        }
        return boardService.insert(dto, loginMember);
    }

    @GetMapping
    public List<BoardDto> getBoardList(){
        return boardService.getBoardList();
    }

    @GetMapping("{no}")
    public BoardDto getBoard(@PathVariable Long no){
        return boardService.getBoard(no);
    }

    @PutMapping
    public Long update(@RequestBody BoardDto dto){
        return boardService.update(dto);
    }

    @DeleteMapping("{no}")
    public Long delete(@PathVariable Long no){
        return boardService.delete(no);
    }
}
