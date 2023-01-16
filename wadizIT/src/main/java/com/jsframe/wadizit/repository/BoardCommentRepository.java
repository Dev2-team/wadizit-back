package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.entity.BoardComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardCommentRepository extends CrudRepository<BoardComment, Long> {
    Iterable<BoardComment> findAllByBoardNum(Board boardNum);

    @Transactional
    void deleteAllByBoardNum(Board bData);

    Iterable<BoardComment> findAllByBoardNumOrderByBoardComNumDesc(Board bNum);
}
