package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.Board;
import com.jsframe.wadizit.entity.BoardFile;
import org.springframework.data.repository.CrudRepository;

public interface BoardFileRepository extends CrudRepository<BoardFile, Long> {
    Iterable<BoardFile> findAllByBoardNum(Board boardNum);
}
