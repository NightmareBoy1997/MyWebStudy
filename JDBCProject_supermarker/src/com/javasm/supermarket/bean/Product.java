package com.javasm.supermarket.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class Product {

  private Integer id;
  private String prodName;
  private Integer typeId;
  private Double prodPrice;
  private Integer prodStore;
  private String prodImage;
  private Integer prodStatus;
  private String prodDesc;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

}
