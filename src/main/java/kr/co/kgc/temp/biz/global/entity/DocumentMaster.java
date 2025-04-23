package kr.co.kgc.temp.biz.global.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_document_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentMaster {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;
  
  @Column(name = "parent_idx")
  private Long parentIdx;
  
  @Column(name = "doc_no", nullable = false, length = 50)
  private String docNo;
  
  @Column(name = "doc_name", nullable = false, length = 100)
  private String docName;
  
  @Column(name = "product_name", nullable = false, length = 100)
  private String productName;
  
  @Column(name = "lot_no")
  private String lotNo;
  
  @Column(name = "lot_unit")
  private String lotUnit;
  
  @Column(name = "work_date", nullable = false)
  private LocalDate workDate;
  
  @Column(name = "process_name")
  private String processName;
  
  @Column(columnDefinition = "LONGTEXT")
  private String content;
  
  private int version;
  
  @Column(name = "is_final")
  private boolean isFinal;
  
  @Column(name = "revision_no")
  private String revisionNo;
  
  @Column(name = "revision_date")
  private LocalDate revisionDate;
  
  @Column(name = "revision_history", columnDefinition = "TEXT")
  private String revisionHistory;
  
  @Column(name = "reg_id")
  private String regId;
  
  @Column(name = "reg_ip")
  private String regIp;
  
  @Column(name = "reg_dt", columnDefinition = "DATETIME")
  private LocalDateTime regDt;
  
  @PrePersist
  public void prePersist() {
    if (this.regDt == null) this.regDt = LocalDateTime.now();
  }
  
}
