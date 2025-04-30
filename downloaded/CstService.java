package com.t248.lmf.crm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cst_service")
public class CstService implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long svrId;
  private String svrType;
  private String svrTitle;
  private String svrCustNo;
  private String svrCustName;
  private String svrStatus;
  private String svrRequest;
  private Long svrCreateId;
  private String svrCreateBy;
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date svrCreateDate;
  private Long svrDueId;
  private String svrDueTo;
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date svrDueDate;
  private String svrDeal;
  private Long svrDealId;
  private String svrDealBy;
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date svrDealDate;
  private String svrResult;
  private Long svrSatisfy;


  public Long getSvrId() {
    return svrId;
  }

  public void setSvrId(Long svrId) {
    this.svrId = svrId;
  }


  public String getSvrType() {
    return svrType;
  }

  public void setSvrType(String svrType) {
    this.svrType = svrType;
  }


  public String getSvrTitle() {
    return svrTitle;
  }

  public void setSvrTitle(String svrTitle) {
    this.svrTitle = svrTitle;
  }


  public String getSvrCustNo() {
    return svrCustNo;
  }

  public void setSvrCustNo(String svrCustNo) {
    this.svrCustNo = svrCustNo;
  }


  public String getSvrCustName() {
    return svrCustName;
  }

  public void setSvrCustName(String svrCustName) {
    this.svrCustName = svrCustName;
  }


  public String getSvrStatus() {
    return svrStatus;
  }

  public void setSvrStatus(String svrStatus) {
    this.svrStatus = svrStatus;
  }


  public String getSvrRequest() {
    return svrRequest;
  }

  public void setSvrRequest(String svrRequest) {
    this.svrRequest = svrRequest;
  }


  public Long getSvrCreateId() {
    return svrCreateId;
  }

  public void setSvrCreateId(Long svrCreateId) {
    this.svrCreateId = svrCreateId;
  }


  public String getSvrCreateBy() {
    return svrCreateBy;
  }

  public void setSvrCreateBy(String svrCreateBy) {
    this.svrCreateBy = svrCreateBy;
  }


  public Date getSvrCreateDate() {
    return svrCreateDate;
  }

  public void setSvrCreateDate(Date svrCreateDate) {
    this.svrCreateDate = svrCreateDate;
  }


  public Long getSvrDueId() {
    return svrDueId;
  }

  public void setSvrDueId(Long svrDueId) {
    this.svrDueId = svrDueId;
  }


  public String getSvrDueTo() {
    return svrDueTo;
  }

  public void setSvrDueTo(String svrDueTo) {
    this.svrDueTo = svrDueTo;
  }


  public Date getSvrDueDate() {
    return svrDueDate;
  }

  public void setSvrDueDate(Date svrDueDate) {
    this.svrDueDate = svrDueDate;
  }


  public String getSvrDeal() {
    return svrDeal;
  }

  public void setSvrDeal(String svrDeal) {
    this.svrDeal = svrDeal;
  }


  public Long getSvrDealId() {
    return svrDealId;
  }

  public void setSvrDealId(Long svrDealId) {
    this.svrDealId = svrDealId;
  }


  public String getSvrDealBy() {
    return svrDealBy;
  }

  public void setSvrDealBy(String svrDealBy) {
    this.svrDealBy = svrDealBy;
  }


  public Date getSvrDealDate() {
    return svrDealDate;
  }

  public void setSvrDealDate(Date svrDealDate) {
    this.svrDealDate = svrDealDate;
  }


  public String getSvrResult() {
    return svrResult;
  }

  public void setSvrResult(String svrResult) {
    this.svrResult = svrResult;
  }


  public Long getSvrSatisfy() {
    return svrSatisfy;
  }

  public void setSvrSatisfy(Long svrSatisfy) {
    this.svrSatisfy = svrSatisfy;
  }

}
