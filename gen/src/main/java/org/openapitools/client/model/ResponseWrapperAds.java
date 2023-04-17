/*
 * Ads app API Documentation
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * ResponseWrapperAds
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-04-17T14:52:52.386051200+07:00[Asia/Krasnoyarsk]")
public class ResponseWrapperAds {
  public static final String SERIALIZED_NAME_COUNT = "count";
  @SerializedName(SERIALIZED_NAME_COUNT)
  private Integer count;

  public static final String SERIALIZED_NAME_RESULTS = "results";
  @SerializedName(SERIALIZED_NAME_RESULTS)
  private List<org.openapitools.client.model.Ads> results = null;


  public ResponseWrapperAds count(Integer count) {
    
    this.count = count;
    return this;
  }

   /**
   * общее количество объявлений
   * @return count
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "общее количество объявлений")

  public Integer getCount() {
    return count;
  }


  public void setCount(Integer count) {
    this.count = count;
  }


  public ResponseWrapperAds results(List<org.openapitools.client.model.Ads> results) {
    
    this.results = results;
    return this;
  }

  public ResponseWrapperAds addResultsItem(org.openapitools.client.model.Ads resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<org.openapitools.client.model.Ads>();
    }
    this.results.add(resultsItem);
    return this;
  }

   /**
   * Get results
   * @return results
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<org.openapitools.client.model.Ads> getResults() {
    return results;
  }


  public void setResults(List<org.openapitools.client.model.Ads> results) {
    this.results = results;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseWrapperAds responseWrapperAds = (ResponseWrapperAds) o;
    return Objects.equals(this.count, responseWrapperAds.count) &&
        Objects.equals(this.results, responseWrapperAds.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, results);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseWrapperAds {\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
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

