package com.example.railwaytelegram.repository;

import com.example.railwaytelegram.entity.UserOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserOperationEntityRepository extends JpaRepository<UserOperationEntity, Long> {
    @Query(value = """
            SELECT uoe.*
            FROM user_operation_entity uoe
            WHERE uoe.user_entity_id = :userId
            ORDER BY uoe.created_at DESC
            LIMIT 5
            """, nativeQuery = true)
    List<UserOperationEntity> findTop10ByUserId(Long userId);

    @Query("""
            SELECT COALESCE(SUM(uoe.sum), 0)
            FROM userOperationEntity uoe
            WHERE uoe.user.id = :userId
            """)
    BigDecimal getTotalSum(Long userId);

    @Query("""
            SELECT COALESCE(SUM(uoe.sum), 0)
            FROM userOperationEntity uoe
            WHERE uoe.user.id = :userId
            AND uoe.sum > 0
            """)
    BigDecimal getInSum(Long userId);

    @Query("""
            SELECT COALESCE(SUM(uoe.sum), 0)
            FROM userOperationEntity uoe
            WHERE uoe.user.id = :userId
            AND uoe.sum < 0
            """)
    BigDecimal getOutSum(Long userId);
}
