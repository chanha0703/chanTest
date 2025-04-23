package kr.co.kgc.temp.biz.global.repository;

import kr.co.kgc.temp.biz.global.entity.DocumentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DocumentMasterRepository extends JpaRepository<DocumentMaster, Long> {
  Optional<DocumentMaster> findTopByDocNoOrderByVersionDesc(String docNo);
  Optional<DocumentMaster> findTopByIdxAndIsFinalTrueOrderByVersionDesc(Long idx);
  
  @Query("SELECT d FROM DocumentMaster d " +
      "WHERE d.isFinal = true " +
      "AND (:regDt IS NULL OR FUNCTION('DATE', d.regDt) = :regDt) " +
      "AND (:processName IS NULL OR :processName = '' OR d.processName LIKE %:processName%) " +
      "AND (:lotNo IS NULL OR :lotNo = '' OR d.lotNo LIKE %:lotNo%) " +
      "AND (:productName IS NULL OR :productName = '' OR d.productName LIKE %:productName%)" +
      "ORDER BY d.regDt DESC, d.docNo"
  )
  List<DocumentMaster> searchWithConditions(
      @Param("regDt") LocalDate regDt,
      @Param("processName") String processName,
      @Param("lotNo") String lotNo,
      @Param("productName") String productName
  );

  @Query("""
    SELECT d
    FROM DocumentMaster d
    WHERE
      (:parentIdx IS NOT NULL AND (d.parentIdx = :parentIdx OR d.idx = :parentIdx))
      OR (:parentIdx IS NULL AND d.idx = :idx)
    ORDER BY d.version DESC
  """)
  List<DocumentMaster> findRelatedDocuments(
          @Param("idx") Long idx,
          @Param("parentIdx") Long parentIdx
  );
  
}
