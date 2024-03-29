package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

  List<CommentEntity> findAllByPostIdOrderByCreatedDate(Long postId);

  List<CommentEntity> findAllByMemberId(Long memberId);

  List<CommentEntity> findAllByMemberIdAndPostId(Long memberId, Long postId);

  void deleteAllByPostId(Long postId);

  void deleteAllByMemberId(Long memberId);

}
