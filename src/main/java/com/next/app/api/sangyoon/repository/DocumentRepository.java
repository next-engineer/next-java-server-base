package com.next.app.api.sangyoon.repository;

import com.next.app.api.sangyoon.entity.Document;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByOwnerId(Long ownerId);

    boolean existsByFilePath(String filePath);

    // 더티 체킹 방지용
    @Modifying
    @Query("UPDATE Document d SET d.title = :#{#document.title}, d.ownerId = :#{#document.ownerId}, d.filePath = :#{#document.filePath} WHERE d.id = :#{#document.docId}")
    int updateById(@Param("document") Document document);

    int deleteByDocId(Long docId);
}
