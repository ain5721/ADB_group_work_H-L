package com.scalardb.demo.enums;

/**
 * @author: ListenYoung
 * @date: Created on 12:18 2023/6/30
 * @modified By:
 */
public enum ProductCategory {
  PHONE(1, "smart phone"),
  TELEVISION(2, "TV"),
  AIR_CONDITIONER(3, "cooler"),
  WASHER(4, "washer"),
  PHONE_SHELL(5, "phone shell"),
  PHONE_SCREEN_PROTECTOR(6, "phone screen protector"),
  RECHARGER(7, "recharger"),
  POWER_BANK(8, "power bank"),
  ;
  public Integer code;
  public String desc;

  ProductCategory(Integer code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static ProductCategory fromCode(Integer code) {
    for (ProductCategory category : ProductCategory.values()) {
      if (category.code.equals(code)) {
        return category;
      }
    }
    throw new RuntimeException("Error when get product category from code, code = " + code);
  }

  public static ProductCategory fromDesc(String desc) {
    for (ProductCategory category : ProductCategory.values()) {
      if (category.desc.equals(desc)) {
        return category;
      }
    }
    throw new RuntimeException("Error when get product category from desc, desc = " + desc);
  }
}
