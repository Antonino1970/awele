package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RestScore
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-06-06T18:38:09.606973459Z[GMT]")


public class RestScore   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("player1")
  private String player1 = null;

  @JsonProperty("player2")
  private String player2 = null;

  @JsonProperty("date")
  private String date = null;

  @JsonProperty("score-player-1")
  private Integer scorePlayer1 = null;

  @JsonProperty("score-player-2")
  private Integer scorePlayer2 = null;

  public RestScore id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
      @NotNull

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public RestScore player1(String player1) {
    this.player1 = player1;
    return this;
  }

  /**
   * Get player1
   * @return player1
   **/
  @Schema(description = "")
      @NotNull

    public String getPlayer1() {
    return player1;
  }

  public void setPlayer1(String player1) {
    this.player1 = player1;
  }

  public RestScore player2(String player2) {
    this.player2 = player2;
    return this;
  }

  /**
   * Get player2
   * @return player2
   **/
  @Schema(description = "")
      @NotNull

    public String getPlayer2() {
    return player2;
  }

  public void setPlayer2(String player2) {
    this.player2 = player2;
  }

  public RestScore date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   **/
  @Schema(description = "")
      @NotNull

    public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public RestScore scorePlayer1(Integer scorePlayer1) {
    this.scorePlayer1 = scorePlayer1;
    return this;
  }

  /**
   * Get scorePlayer1
   * @return scorePlayer1
   **/
  @Schema(description = "")
      @NotNull

    public Integer getScorePlayer1() {
    return scorePlayer1;
  }

  public void setScorePlayer1(Integer scorePlayer1) {
    this.scorePlayer1 = scorePlayer1;
  }

  public RestScore scorePlayer2(Integer scorePlayer2) {
    this.scorePlayer2 = scorePlayer2;
    return this;
  }

  /**
   * Get scorePlayer2
   * @return scorePlayer2
   **/
  @Schema(description = "")
      @NotNull

    public Integer getScorePlayer2() {
    return scorePlayer2;
  }

  public void setScorePlayer2(Integer scorePlayer2) {
    this.scorePlayer2 = scorePlayer2;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestScore restScore = (RestScore) o;
    return Objects.equals(this.id, restScore.id) &&
        Objects.equals(this.player1, restScore.player1) &&
        Objects.equals(this.player2, restScore.player2) &&
        Objects.equals(this.date, restScore.date) &&
        Objects.equals(this.scorePlayer1, restScore.scorePlayer1) &&
        Objects.equals(this.scorePlayer2, restScore.scorePlayer2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, player1, player2, date, scorePlayer1, scorePlayer2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestScore {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    player1: ").append(toIndentedString(player1)).append("\n");
    sb.append("    player2: ").append(toIndentedString(player2)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    scorePlayer1: ").append(toIndentedString(scorePlayer1)).append("\n");
    sb.append("    scorePlayer2: ").append(toIndentedString(scorePlayer2)).append("\n");
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
