package kr.co.kgc.temp.biz.global.service;

import kr.co.kgc.temp.biz.global.entity.DocumentMaster;
import kr.co.kgc.temp.biz.global.entity.WorkDocument;
import kr.co.kgc.temp.biz.global.repository.DocumentMasterRepository;
import kr.co.kgc.temp.biz.global.repository.WorkDocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GlobalService {
  
  private final DocumentMasterRepository dmr;
  private final WorkDocumentRepository wdr;
  
  // ===== DocumentMaster =====
  
  public String saveDocumentMaster(DocumentMaster entity) {
    // 이전 최종본을 is_final = false로
    dmr.findTopByIdxAndIsFinalTrueOrderByVersionDesc(entity.getIdx())
        .ifPresent(prev -> {
          prev.setFinal(false);
          prev.setContent(entity.getContent());
          dmr.save(prev);
          entity.setVersion(prev.getVersion() + 1);
          entity.setDocNo(prev.getDocNo());
          entity.setDocName(prev.getDocName());
          entity.setProductName(prev.getProductName());
          entity.setLotNo(prev.getLotNo());
          entity.setLotUnit(prev.getLotUnit());
          entity.setWorkDate(prev.getWorkDate());
          entity.setProcessName(prev.getProcessName());
          if(prev.getParentIdx() != null) entity.setParentIdx(prev.getParentIdx());
          else entity.setParentIdx(prev.getIdx());
          entity.setIdx(null);
        });
  
    if (entity.getVersion() == 0) {
      entity.setVersion(1); // 처음 생성인 경우
    }
    entity.setFinal(true); // 항상 새로 저장된 게 최종본
  
    dmr.save(entity);
    return "작업 문서 저장 완료";
  }
  
  public DocumentMaster loadDocumentMaster(long idx) {
    return dmr.findById(idx).orElse(null);
  }
  
  public DocumentMaster getLatestDocumentMaster(String docNo) {
    return null;
//    return dmr.findTopByIdxAndIsFinalTrueOrderByVersionDesc(docNo).orElse(null);
  }
  
  public List<DocumentMaster> getListDocumentMaster(DocumentMaster req) {
    LocalDate regDate = null;
    if (req.getRegDt() != null) {
      regDate = req.getRegDt().toLocalDate();
    }
  
    return dmr.searchWithConditions(
        regDate,
        req.getProcessName(),
        req.getLotNo(),
        req.getProductName()
    );
  }
  
  // ===== WorkDocument =====
  
  public String saveWorkDocument(WorkDocument entity) {
    // 이전 최종본을 is_final = false로
    wdr.findTopByDocNoAndIsFinalTrueOrderByVersionDesc(entity.getDocNo())
        .ifPresent(prev -> {
          prev.setFinal(false);
          wdr.save(prev);
          entity.setVersion(prev.getVersion() + 1);
        });
    
    if (entity.getVersion() == 0) {
      entity.setVersion(1); // 처음 생성인 경우
    }
    entity.setFinal(true); // 항상 새로 저장된 게 최종본
    
    wdr.save(entity);
    return "작업 문서 저장 완료";
  }
  
  public WorkDocument loadWorkDocument(String docNo) {
    return wdr.findTopByDocNoOrderByVersionDesc(docNo)
        .orElse(null);
  }
  
  public String rollbackWorkDocument(String docNo, int version) {
    wdr.findByDocNo(docNo).forEach(wd -> {
      wd.setFinal(wd.getVersion() == version);
      wdr.save(wd);
    });
    return "롤백 완료";
  }
}
