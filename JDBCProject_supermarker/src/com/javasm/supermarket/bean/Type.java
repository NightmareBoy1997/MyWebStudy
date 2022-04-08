package com.javasm.supermarket.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Type {

  private Integer id;
  private String typeName;
  private Integer parentId;
  private boolean parent;
  private boolean typeStatus;
  private java.util.Date createTime;
  private java.util.Date updateTime;
}
