package kr.co.kgc.temp.biz.global.controller.rest;

import kr.co.kgc.temp.biz.global.entity.DocumentMaster;
import kr.co.kgc.temp.biz.global.entity.WorkDocument;
import kr.co.kgc.temp.biz.global.service.GlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GlobalRestController {
  
  private final GlobalService globalService;


  /********************
   * DOCUMENT-MASTER  *
   ********************/

  @PostMapping("/document-master/save")
  public String saveDocumentMaster(@RequestBody DocumentMaster entity) {
    return globalService.saveDocumentMaster(entity);
  }
  
  @PostMapping("/document-master/load")
  public DocumentMaster loadDocumentMaster(@RequestBody DocumentMaster entity) {
    return globalService.loadDocumentMaster(entity.getIdx());
  }
  
  @PostMapping("/document-master/list")
  public List<DocumentMaster> getListDocumentMaster(@RequestBody DocumentMaster entity) {
    return globalService.getListDocumentMaster(entity);
  }

  @PostMapping("/document-master/history")
  public List<DocumentMaster> getLatestDocumentMaster(@RequestBody DocumentMaster entity) {
    return globalService.getDocumentMasterHistory(entity.getIdx());
  }
  


  /******************
   * WORK-DOCUMENT  *
   ******************/
  
  @PostMapping("/work-document/save")
  public String saveWorkDocument(@RequestBody WorkDocument entity) {
    return globalService.saveWorkDocument(entity);
  }

  @PostMapping("/work-document/load")
  public WorkDocument loadWorkDocument(@RequestBody WorkDocument entity) {
    return globalService.loadWorkDocument(entity.getIdx());
  }

  @PostMapping("/work-document/list")
  public List<WorkDocument> getListWorkDocument(@RequestBody WorkDocument entity) {
    return globalService.getListWorkDocument(entity);
  }

  @PostMapping("/work-document/history")
  public List<WorkDocument> rollbackWorkDocument(@RequestBody WorkDocument entity) {
    return globalService.getWorkDocumentHistory(entity.getIdx());
  }
}
