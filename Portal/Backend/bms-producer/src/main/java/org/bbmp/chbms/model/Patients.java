package org.bbmp.chbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Objects;

/**
 * Patients
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T01:35:57.739Z[GMT]")


public class Patients   {
  @JsonProperty("patientId")
  private String patientId = null;

  @JsonProperty("phoneLastFour")
  private String phoneLastFour = null;

  @JsonProperty("srfNumber")
  private String srfNumber = null;

  @JsonProperty("bucode")
  private String bucode = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("zone")
  private String zone = null;

  @JsonProperty("queueType")
  private String queueType = null;

  @JsonProperty("patientStatus")
  private String patientStatus = null;

  @JsonProperty("queueName")
  private String queueName = null;

  @JsonProperty("timeAddedToQueue")
  private Instant timeAddedToQueue = null;

//  @JsonProperty("updatedOn")
  @JsonIgnore
  private Instant updatedOn = null;

//  @JsonProperty("updatedByMsgId")
  @JsonIgnore
  private String updatedByMsgId = null;

  public Patients patientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  /**
   * Get patientId
   * @return patientId
   **/
  @Schema(description = "")
  
    public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public Patients phoneLastFour(String phoneLastFour) {
    this.phoneLastFour = phoneLastFour;
    return this;
  }

  /**
   * Get phoneLastFour
   * @return phoneLastFour
   **/
  @Schema(description = "")
  
    public String getPhoneLastFour() {
    return phoneLastFour;
  }

  public void setPhoneLastFour(String phoneLastFour) {
    this.phoneLastFour = phoneLastFour;
  }

  public Patients srfNumber(String srfNumber) {
    this.srfNumber = srfNumber;
    return this;
  }

  /**
   * Get srfNumber
   * @return srfNumber
   **/
  @Schema(description = "")
  
    public String getSrfNumber() {
    return srfNumber;
  }

  public void setSrfNumber(String srfNumber) {
    this.srfNumber = srfNumber;
  }

  public Patients bucode(String bucode) {
    this.bucode = bucode;
    return this;
  }

  /**
   * Get bucode
   * @return bucode
   **/
  @Schema(description = "")
  
    public String getBucode() {
    return bucode;
  }

  public void setBucode(String bucode) {
    this.bucode = bucode;
  }

  public Patients category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   **/
  @Schema(description = "")
  
    public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Patients zone(String zone) {
    this.zone = zone;
    return this;
  }

  /**
   * Get zone
   * @return zone
   **/
  @Schema(description = "")
  
    public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }

  public Patients queueType(String queueType) {
    this.queueType = queueType;
    return this;
  }

  /**
   * Get queueType
   * @return queueType
   **/
  @Schema(description = "")
  
    public String getQueueType() {
    return queueType;
  }

  public void setQueueType(String queueType) {
    this.queueType = queueType;
  }

  public Patients patientStatus(String patientStatus) {
    this.patientStatus = patientStatus;
    return this;
  }

  /**
   * Get patientStatus
   * @return patientStatus
   **/
  @Schema(description = "")
  
    public String getPatientStatus() {
    return patientStatus;
  }

  public void setPatientStatus(String patientStatus) {
    this.patientStatus = patientStatus;
  }

  public Patients queueName(String queueName) {
    this.queueName = queueName;
    return this;
  }

  /**
   * Get queueName
   * @return queueName
   **/
  @Schema(description = "")
  
    public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
  }

  public Patients timeAddedToQueue(Instant timeAddedToQueue) {
    this.timeAddedToQueue = timeAddedToQueue;
    return this;
  }

  /**
   * Get timeAddedToQueue
   * @return timeAddedToQueue
   **/
  @Schema(description = "")

  public Instant getTimeAddedToQueue() {
    return timeAddedToQueue;
  }

  public void setTimeAddedToQueue(Instant timeAddedToQueue) {
    this.timeAddedToQueue = timeAddedToQueue;
  }

  public Patients updatedOn(Instant updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * Get updatedOn
   * @return updatedOn
   **/
  @Schema(description = "")
  
    @Valid
    public Instant getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Instant updatedOn) {
    this.updatedOn = updatedOn;
  }

  public Patients updatedByMsgId(String updatedByMsgId) {
    this.updatedByMsgId = updatedByMsgId;
    return this;
  }

  /**
   * Get updatedByMsgId
   * @return updatedByMsgId
   **/
  @Schema(description = "")
  
    public String getUpdatedByMsgId() {
    return updatedByMsgId;
  }

  public void setUpdatedByMsgId(String updatedByMsgId) {
    this.updatedByMsgId = updatedByMsgId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Patients patients = (Patients) o;
    return Objects.equals(this.patientId, patients.patientId) &&
        Objects.equals(this.phoneLastFour, patients.phoneLastFour) &&
        Objects.equals(this.srfNumber, patients.srfNumber) &&
        Objects.equals(this.bucode, patients.bucode) &&
        Objects.equals(this.category, patients.category) &&
        Objects.equals(this.zone, patients.zone) &&
        Objects.equals(this.queueType, patients.queueType) &&
        Objects.equals(this.patientStatus, patients.patientStatus) &&
        Objects.equals(this.queueName, patients.queueName) &&
        Objects.equals(this.timeAddedToQueue, patients.timeAddedToQueue) &&
        Objects.equals(this.updatedOn, patients.updatedOn) &&
        Objects.equals(this.updatedByMsgId, patients.updatedByMsgId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(patientId, phoneLastFour, srfNumber, bucode, category, zone, queueType, patientStatus, queueName, timeAddedToQueue, updatedOn, updatedByMsgId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Patients {\n");
    
    sb.append("    patientId: ").append(toIndentedString(patientId)).append("\n");
    sb.append("    phoneLastFour: ").append(toIndentedString(phoneLastFour)).append("\n");
    sb.append("    srfNumber: ").append(toIndentedString(srfNumber)).append("\n");
    sb.append("    bucode: ").append(toIndentedString(bucode)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    zone: ").append(toIndentedString(zone)).append("\n");
    sb.append("    queueType: ").append(toIndentedString(queueType)).append("\n");
    sb.append("    patientStatus: ").append(toIndentedString(patientStatus)).append("\n");
    sb.append("    queueName: ").append(toIndentedString(queueName)).append("\n");
    sb.append("    timeAddedToQueue: ").append(toIndentedString(timeAddedToQueue)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    updatedByMsgId: ").append(toIndentedString(updatedByMsgId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
