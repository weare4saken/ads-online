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

/**
 * Ads
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-04-17T14:52:52.386051200+07:00[Asia/Krasnoyarsk]")
public class Ads {
  public static final String SERIALIZED_NAME_AUTHOR = "author";
  @SerializedName(SERIALIZED_NAME_AUTHOR)
  private Integer author;

  public static final String SERIALIZED_NAME_IMAGE = "image";
  @SerializedName(SERIALIZED_NAME_IMAGE)
  private String image;

  public static final String SERIALIZED_NAME_PK = "pk";
  @SerializedName(SERIALIZED_NAME_PK)
  private Integer pk;

  public static final String SERIALIZED_NAME_PRICE = "price";
  @SerializedName(SERIALIZED_NAME_PRICE)
  private Integer price;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;


  public Ads author(Integer author) {
    
    this.author = author;
    return this;
  }

   /**
   * id автора объявления
   * @return author
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "id автора объявления")

  public Integer getAuthor() {
    return author;
  }


  public void setAuthor(Integer author) {
    this.author = author;
  }


  public Ads image(String image) {
    
    this.image = image;
    return this;
  }

   /**
   * ссылка на картинку объявления
   * @return image
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "ссылка на картинку объявления")

  public String getImage() {
    return image;
  }


  public void setImage(String image) {
    this.image = image;
  }


  public Ads pk(Integer pk) {
    
    this.pk = pk;
    return this;
  }

   /**
   * id объявления
   * @return pk
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "id объявления")

  public Integer getPk() {
    return pk;
  }


  public void setPk(Integer pk) {
    this.pk = pk;
  }


  public Ads price(Integer price) {
    
    this.price = price;
    return this;
  }

   /**
   * цена объявления
   * @return price
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "цена объявления")

  public Integer getPrice() {
    return price;
  }


  public void setPrice(Integer price) {
    this.price = price;
  }


  public Ads title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * заголовок объявления
   * @return title
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "заголовок объявления")

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ads ads = (Ads) o;
    return Objects.equals(this.author, ads.author) &&
        Objects.equals(this.image, ads.image) &&
        Objects.equals(this.pk, ads.pk) &&
        Objects.equals(this.price, ads.price) &&
        Objects.equals(this.title, ads.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, image, pk, price, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ads {\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

