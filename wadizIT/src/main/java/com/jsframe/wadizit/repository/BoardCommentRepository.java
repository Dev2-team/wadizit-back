package com.jsframe.wadizit.repository;

import com.jsframe.wadizit.entity.BoardComment;
import com.jsframe.wadizit.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface BoardCommentRepository extends CrudRepository<BoardComment, Long> {

}
