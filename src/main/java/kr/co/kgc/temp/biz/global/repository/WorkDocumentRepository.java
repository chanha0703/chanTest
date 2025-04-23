package kr.co.kgc.temp.biz.global.repository;

import kr.co.kgc.temp.biz.global.entity.WorkDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkDocumentRepository extends JpaRepository<WorkDocument, Long> {
  Optional<WorkDocument> findTopByDocNoOrderByVersionDesc(String docNo);
  Optional<WorkDocument> findTopByDocNoAndIsFinalTrueOrderByVersionDesc(String docNo);
  List<WorkDocument> findByDocNo(String docNo);
}
