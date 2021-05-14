package com.infy.stg.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Beds
 */
@Validated
@javax.annotation.Generated(value = "io.stg.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T01:35:57.739Z[GMT]")


public class Beds   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("bedType")
  private String bedType = null;

  @JsonProperty("capacity")
  private Integer capacity = null;

  @JsonProperty("occupied")
  private Integer occupied = null;

  @JsonProperty("blocked")
  private Integer blocked = null;

  @JsonProperty("vacant")
  private Integer vacant = null;

  public Beds id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Beds bedType(String bedType) {
    this.bedType = bedType;
    return this;
  }

  /**
   * Get bedType
   * @return bedType
   **/
  @Schema(description = "")
  
    public String getBedType() {
    return bedType;
  }

  public void setBedType(String bedType) {
    this.bedType = bedType;
  }

  public Beds capacity(Integer capacity) {
    this.capacity = capacity;
    return this;
  }

  /**
   * Get capacity
   * @return capacity
   **/
  @Schema(description = "")
  
    public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Beds occupied(Integer occupied) {
    this.occupied = occupied;
    return this;
  }

  /**
   * Get occupied
   * @return occupied
   **/
  @Schema(description = "")
  
    public Integer getOccupied() {
    return occupied;
  }

  public void setOccupied(Integer occupied) {
    this.occupied = occupied;
  }

  public Beds blocked(Integer blocked) {
    this.blocked = blocked;
    return this;
  }

  /**
   * Get blocked
   * @return blocked
   **/
  @Schema(description = "")
  
    public Integer getBlocked() {
    return blocked;
  }

  public void setBlocked(Integer blocked) {
    this.blocked = blocked;
  }

  public Beds vacant(Integer vacant) {
    this.vacant = vacant;
    return this;
  }

  /**
   * Get vacant
   * @return vacant
   **/
  @Schema(description = "")
  
    public Integer getVacant() {
    return vacant;
  }

  public void setVacant(Integer vacant) {
    this.vacant = vacant;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Beds beds = (Beds) o;
    return Objects.equals(this.id, beds.id) &&
        Objects.equals(this.bedType, beds.bedType) &&
        Objects.equals(this.capacity, beds.capacity) &&
        Objects.equals(this.occupied, beds.occupied) &&
        Objects.equals(this.blocked, beds.blocked) &&
        Objects.equals(this.vacant, beds.vacant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bedType, capacity, occupied, blocked, vacant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Beds {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    bedType: ").append(toIndentedString(bedType)).append("\n");
    sb.append("    capacity: ").append(toIndentedString(capacity)).append("\n");
    sb.append("    occupied: ").append(toIndentedString(occupied)).append("\n");
    sb.append("    blocked: ").append(toIndentedString(blocked)).append("\n");
    sb.append("    vacant: ").append(toIndentedString(vacant)).append("\n");
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
