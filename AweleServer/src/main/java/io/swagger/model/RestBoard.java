package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RestBoard
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-06-06T18:38:09.606973459Z[GMT]")


public class RestBoard   {
  @JsonProperty("holes-player1")
  @Valid
  private List<Integer> holesPlayer1 = null;

  @JsonProperty("holes-player2")
  @Valid
  private List<Integer> holesPlayer2 = null;

  public RestBoard holesPlayer1(List<Integer> holesPlayer1) {
    this.holesPlayer1 = holesPlayer1;
    return this;
  }

  public RestBoard addHolesPlayer1Item(Integer holesPlayer1Item) {
    if (this.holesPlayer1 == null) {
      this.holesPlayer1 = new ArrayList<Integer>();
    }
    this.holesPlayer1.add(holesPlayer1Item);
    return this;
  }

  /**
   * Get holesPlayer1
   * @return holesPlayer1
   **/
  @Schema(description = "")
      @NotNull

    public List<Integer> getHolesPlayer1() {
    return holesPlayer1;
  }

  public void setHolesPlayer1(List<Integer> holesPlayer1) {
    this.holesPlayer1 = holesPlayer1;
  }

  public RestBoard holesPlayer2(List<Integer> holesPlayer2) {
    this.holesPlayer2 = holesPlayer2;
    return this;
  }

  public RestBoard addHolesPlayer2Item(Integer holesPlayer2Item) {
    if (this.holesPlayer2 == null) {
      this.holesPlayer2 = new ArrayList<Integer>();
    }
    this.holesPlayer2.add(holesPlayer2Item);
    return this;
  }

  /**
   * Get holesPlayer2
   * @return holesPlayer2
   **/
  @Schema(description = "")
      @NotNull

    public List<Integer> getHolesPlayer2() {
    return holesPlayer2;
  }

  public void setHolesPlayer2(List<Integer> holesPlayer2) {
    this.holesPlayer2 = holesPlayer2;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestBoard restBoard = (RestBoard) o;
    return Objects.equals(this.holesPlayer1, restBoard.holesPlayer1) &&
        Objects.equals(this.holesPlayer2, restBoard.holesPlayer2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(holesPlayer1, holesPlayer2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestBoard {\n");
    
    sb.append("    holesPlayer1: ").append(toIndentedString(holesPlayer1)).append("\n");
    sb.append("    holesPlayer2: ").append(toIndentedString(holesPlayer2)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
