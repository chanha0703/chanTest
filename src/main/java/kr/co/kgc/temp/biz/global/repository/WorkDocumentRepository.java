package kr.co.kgc.temp.biz.global.repository;

import kr.co.kgc.temp.biz.global.entity.WorkDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkDocumentRepository extends JpaRepository<WorkDocument, Long> {
  Optional<WorkDocument> findTopByDocNoOrderByVersionDesc(String docNo);
  Optional<WorkDocument> findTopByIdxAndIsFinalTrueOrderByVersionDesc(Long idx);
  List<WorkDocument> findByDocNo(String docNo);


  @Query("SELECT d FROM WorkDocument d " +
          "WHERE d.isFinal = true " +
          "AND (:writeDt IS NULL OR FUNCTION('DATE', d.writeDt) = :writeDt) " +
          "AND (:processName IS NULL OR :processName = '' OR d.processName LIKE %:processName%) " +
          "AND (:lotNo IS NULL OR :lotNo = '' OR d.lotNo LIKE %:lotNo%) " +
          "AND (:productName IS NULL OR :productName = '' OR d.productName LIKE %:productName%)" +
          "ORDER BY d.writeDt DESC, d.docNo"
  )
  List<WorkDocument> searchWithConditions(
          @Param("writeDt") LocalDate writeDt,
          @Param("processName") String processName,
          @Param("lotNo") String lotNo,
          @Param("productName") String productName
  );

  @Query("""
    SELECT d
    FROM WorkDocument d
    WHERE
      (:parentIdx IS NOT NULL AND (d.parentIdx = :parentIdx OR d.idx = :parentIdx))
      OR (:parentIdx IS NULL AND d.idx = :idx)
    ORDER BY d.version DESC
  """)
  List<WorkDocument> findRelatedDocuments(
          @Param("idx") Long idx,
          @Param("parentIdx") Long parentIdx
  );
}
