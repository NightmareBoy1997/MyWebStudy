package com.javasm.supermarket.bean;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Member {

  private Integer id;
  private String name;
  private String password;
  private String userImage;
  private String phone;
  private BigDecimal balance;
  private Double point;
  private java.util.Date createTime;
  private java.util.Date updateTime;

}
