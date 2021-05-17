package org.bbmp.chbms.model;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Hospitals
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T01:35:57.739Z[GMT]")


public class Hospitals   {
  @JsonProperty("hospitalName")
  private String hospitalName = null;

  @JsonProperty("hospitalId")
  private String hospitalId = null;

  @JsonProperty("hospitalAddress")
  private String hospitalAddress = null;

  @JsonProperty("pincode")
  private Integer pincode = null;

  @JsonProperty("hospitalPhoneNumber")
  private String hospitalPhoneNumber = null;

  @JsonProperty("zone")
  private String zone = null;

  @JsonProperty("hospitalType")
  private String hospitalType = null;

  @JsonProperty("hospitalStatus")
  private String hospitalStatus = null;

  @JsonProperty("beds")
  @Valid
  private List<Beds> beds = null;


  @JsonIgnore
//  @JsonProperty("updatedOn")
  private Instant updatedOn = null;

  @JsonIgnore
//  @JsonProperty("updatedByMessageId")
  private String updatedByMessageId = null;

  public Hospitals hospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
    return this;
  }

  /**
   * Get hospitalName
   * @return hospitalName
   **/
  @Schema(description = "")
  
    public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public Hospitals hospitalId(String hospitalId) {
    this.hospitalId = hospitalId;
    return this;
  }

  /**
   * Get hospitalId
   * @return hospitalId
   **/
  @Schema(description = "")
  
    public String getHospitalId() {
    return hospitalId;
  }

  public void setHospitalId(String hospitalId) {
    this.hospitalId = hospitalId;
  }

  public Hospitals hospitalAddress(String hospitalAddress) {
    this.hospitalAddress = hospitalAddress;
    return this;
  }

  /**
   * Get hospitalAddress
   * @return hospitalAddress
   **/
  @Schema(description = "")
  
    public String getHospitalAddress() {
    return hospitalAddress;
  }

  public void setHospitalAddress(String hospitalAddress) {
    this.hospitalAddress = hospitalAddress;
  }

  public Hospitals pincode(Integer pincode) {
    this.pincode = pincode;
    return this;
  }

  /**
   * Get pincode
   * @return pincode
   **/
  @Schema(description = "")
  
    public Integer getPincode() {
    return pincode;
  }

  public void setPincode(Integer pincode) {
    this.pincode = pincode;
  }

  public Hospitals hospitalPhoneNumber(String hospitalPhoneNumber) {
    this.hospitalPhoneNumber = hospitalPhoneNumber;
    return this;
  }

  /**
   * Get hospitalPhoneNumber
   * @return hospitalPhoneNumber
   **/
  @Schema(description = "")
  
    public String getHospitalPhoneNumber() {
    return hospitalPhoneNumber;
  }

  public void setHospitalPhoneNumber(String hospitalPhoneNumber) {
    this.hospitalPhoneNumber = hospitalPhoneNumber;
  }

  public Hospitals zone(String zone) {
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

  public Hospitals hospitalType(String hospitalType) {
    this.hospitalType = hospitalType;
    return this;
  }

  /**
   * Get hospitalType
   * @return hospitalType
   **/
  @Schema(description = "")
  
    public String getHospitalType() {
    return hospitalType;
  }

  public void setHospitalType(String hospitalType) {
    this.hospitalType = hospitalType;
  }

  public Hospitals hospitalStatus(String hospitalStatus) {
    this.hospitalStatus = hospitalStatus;
    return this;
  }

  /**
   * Get hospitalStatus
   * @return hospitalStatus
   **/
  @Schema(description = "")
  
    public String getHospitalStatus() {
    return hospitalStatus;
  }

  public void setHospitalStatus(String hospitalStatus) {
    this.hospitalStatus = hospitalStatus;
  }

  public Hospitals beds(List<Beds> beds) {
    this.beds = beds;
    return this;
  }

  public Hospitals addBedsItem(Beds bedsItem) {
    if (this.beds == null) {
      this.beds = new ArrayList<Beds>();
    }
    this.beds.add(bedsItem);
    return this;
  }

  /**
   * Get beds
   * @return beds
   **/
  @Schema(description = "")
      @Valid
    public List<Beds> getBeds() {
    return beds;
  }

  public void setBeds(List<Beds> beds) {
    this.beds = beds;
  }

  public Hospitals updatedOn(Instant updatedOn) {
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

  public Hospitals updatedByMessageId(String updatedByMessageId) {
    this.updatedByMessageId = updatedByMessageId;
    return this;
  }

  /**
   * Get updatedByMessageId
   * @return updatedByMessageId
   **/
  @Schema(description = "")
  
    public String getUpdatedByMessageId() {
    return updatedByMessageId;
  }

  public void setUpdatedByMessageId(String updatedByMessageId) {
    this.updatedByMessageId = updatedByMessageId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hospitals hospitals = (Hospitals) o;
    return Objects.equals(this.hospitalName, hospitals.hospitalName) &&
        Objects.equals(this.hospitalId, hospitals.hospitalId) &&
        Objects.equals(this.hospitalAddress, hospitals.hospitalAddress) &&
        Objects.equals(this.pincode, hospitals.pincode) &&
        Objects.equals(this.hospitalPhoneNumber, hospitals.hospitalPhoneNumber) &&
        Objects.equals(this.zone, hospitals.zone) &&
        Objects.equals(this.hospitalType, hospitals.hospitalType) &&
        Objects.equals(this.hospitalStatus, hospitals.hospitalStatus) &&
        Objects.equals(this.beds, hospitals.beds) &&
        Objects.equals(this.updatedOn, hospitals.updatedOn) &&
        Objects.equals(this.updatedByMessageId, hospitals.updatedByMessageId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hospitalName, hospitalId, hospitalAddress, pincode, hospitalPhoneNumber, zone, hospitalType, hospitalStatus, beds, updatedOn, updatedByMessageId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hospitals {\n");
    
    sb.append("    hospitalName: ").append(toIndentedString(hospitalName)).append("\n");
    sb.append("    hospitalId: ").append(toIndentedString(hospitalId)).append("\n");
    sb.append("    hospitalAddress: ").append(toIndentedString(hospitalAddress)).append("\n");
    sb.append("    pincode: ").append(toIndentedString(pincode)).append("\n");
    sb.append("    hospitalPhoneNumber: ").append(toIndentedString(hospitalPhoneNumber)).append("\n");
    sb.append("    zone: ").append(toIndentedString(zone)).append("\n");
    sb.append("    hospitalType: ").append(toIndentedString(hospitalType)).append("\n");
    sb.append("    hospitalStatus: ").append(toIndentedString(hospitalStatus)).append("\n");
    sb.append("    beds: ").append(toIndentedString(beds)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    updatedByMessageId: ").append(toIndentedString(updatedByMessageId)).append("\n");
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
