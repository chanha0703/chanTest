package kr.co.kgc.temp.biz.global.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_work_document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDocument {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @Column(name = "parent_idx")
  private Long parentIdx;

  @Column(name = "master_idx")
  private Long masterIdx;

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
  
  @Column(name = "change_reason", columnDefinition = "TEXT")
  private String changeReason;
  
  @Column(length = 20, nullable = false)
  private String status;
  
  @Column(name = "writer_id")
  private String writerId;
  
  @Column(name = "writer_ip")
  private String writerIp;
  
  @Column(name = "write_dt", columnDefinition = "DATETIME")
  private LocalDateTime writeDt;
  
  @PrePersist
  public void prePersist() {
    if (this.writeDt == null) this.writeDt = LocalDateTime.now();
  }
  
}

