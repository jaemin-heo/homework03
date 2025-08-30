package com.kh.app.board;

import com.kh.app.member.MemberDto;
import com.kh.app.member.MemberEntity;
import com.kh.app.member.MemberException;
import com.kh.app.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public Long insert(BoardDto dto, MemberDto memberDto) {
        MemberEntity loginMember = memberRepository.login(memberDto);
        if (loginMember == null){
            throw new MemberException("[MEMBER-1] 아이디 또는 비밀번호가 틀립니다.");
        }
        BoardEntity entity = BoardEntity.toEntity(dto, loginMember);
        boardRepository.insert(entity);
        return entity.getNo();
    }

    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntityList = boardRepository.getBoardList();
        return boardEntityList.stream().map(BoardDto::toDto).toList();
    }

    public BoardDto getBoard(Long no) {
        BoardEntity boardEntity = boardRepository.getBoard(no);
        return BoardDto.toDto(boardEntity);
    }

    public Long update(BoardDto dto) {
        BoardEntity boardEntity = boardRepository.getBoard(dto.getNo());
        if(boardEntity == null){
            throw new BoardException("[BOARD-2] 게시물이 없습니다.");
        }
        boardEntity.update(dto);
        return boardEntity.getNo();
    }

    public Long delete(Long no) {
        BoardEntity boardEntity = boardRepository.getBoard(no);
        boardEntity.delete();
        return boardEntity.getNo();
    }
}
