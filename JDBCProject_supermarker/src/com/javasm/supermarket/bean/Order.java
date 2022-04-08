package com.javasm.supermarket.bean;

import lombok.Data;


@Data
public class Order {

  private Integer id;
  private Integer mid;
  private String totalMoney;
  private String payType;
  private java.util.Date payTime;

}
