package com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository;

import com.ptjcoding.nbcampspringnewsfeed.domain.comment.model.Comment;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentCreateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.dto.CommentUpdateDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.entity.CommentEntity;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentJpaRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.comment.repository.interfaces.CommentRepository;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.CustomRuntimeException;
import com.ptjcoding.nbcampspringnewsfeed.global.exception.GlobalErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  private final CommentJpaRepository commentJpaRepository;

  @Override
  public Comment createComment(CommentCreateDto createDto) {
    return commentJpaRepository.save(CommentEntity.of(createDto)).toModel();
  }

  @Override
  public Comment findCommentOrElseThrow(Long commentId) {
    CommentEntity commentEntity = commentJpaRepository.findById(commentId)
        .orElseThrow(() -> new CustomRuntimeException(GlobalErrorCode.NOT_FOUND));

    return commentEntity.toModel();
  }

  @Override
  public List<Comment> findCommentsByPostId(Long postId) {
    return commentJpaRepository.findAllByPostIdOrderByCreatedDate(postId)
        .stream()
        .map(CommentEntity::toModel)
        .toList();
  }

  @Override
  public List<Comment> findCommentsByMemberId(Long memberId) {
    return commentJpaRepository.findAllByMemberId(memberId)
        .stream()
        .map(CommentEntity::toModel)
        .toList();
  }

  @Override
  public List<Comment> findCommentsByMemberIdAndPostId(Long memberId, Long postId) {
    return commentJpaRepository.findAllByMemberIdAndPostId(memberId, postId)
        .stream()
        .map(CommentEntity::toModel)
        .toList();
  }

  @Override
  public Comment updateComment(Long commentId, CommentUpdateDto updateDto) {
    CommentEntity commentEntity = commentJpaRepository.findById(commentId)
        .orElseThrow(() -> new CustomRuntimeException(
            GlobalErrorCode.NOT_FOUND));

    commentEntity.update(updateDto);

    return commentJpaRepository.saveAndFlush(commentEntity).toModel();
  }

  @Override
  public void deleteComment(Long commentId) {
    CommentEntity commentEntity = commentJpaRepository.findById(commentId)
        .orElseThrow(() -> new CustomRuntimeException(
            GlobalErrorCode.NOT_FOUND));

    commentJpaRepository.delete(commentEntity);
  }

  @Override
  public void deleteCommentsByPostId(Long postId) {
    commentJpaRepository.deleteAllByPostId(postId);
  }

  @Override
  public void deleteCommentsByMemberId(Long memberId) {
    commentJpaRepository.deleteAllByMemberId(memberId);
  }

  @Override
  public void deleteCommentsByMemberIdAndPostId(Long memberId, Long postId) {
    commentJpaRepository.deleteAll(commentJpaRepository.findAllByMemberIdAndPostId(memberId, postId));
  }

}
