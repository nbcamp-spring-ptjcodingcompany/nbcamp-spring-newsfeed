package com.ptjcoding.nbcampspringnewsfeed.global.jwt.infrastructure.jpa;

import com.ptjcoding.nbcampspringnewsfeed.global.jwt.infrastructure.jpa.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String refreshToken);
    void deleteByToken(String token);
    void deleteByMemberId(Long memberId);
}
