package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.RestScore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RestTabScore
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-06-06T18:38:09.606973459Z[GMT]")


public class RestTabScore   {
  @JsonProperty("score")
  @Valid
  private List<RestScore> score = null;

  public RestTabScore score(List<RestScore> score) {
    this.score = score;
    return this;
  }

  public RestTabScore addScoreItem(RestScore scoreItem) {
    if (this.score == null) {
      this.score = new ArrayList<RestScore>();
    }
    this.score.add(scoreItem);
    return this;
  }

  /**
   * Get score
   * @return score
   **/
  @Schema(description = "")
      @NotNull
    @Valid
    public List<RestScore> getScore() {
    return score;
  }

  public void setScore(List<RestScore> score) {
    this.score = score;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestTabScore restTabScore = (RestTabScore) o;
    return Objects.equals(this.score, restTabScore.score);
  }

  @Override
  public int hashCode() {
    return Objects.hash(score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestTabScore {\n");
    
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
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
