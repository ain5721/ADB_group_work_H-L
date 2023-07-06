package com.scalardb.demo.controller;

import com.scalar.db.exception.transaction.TransactionException;
import com.scalardb.demo.model.CarouselModel;
import com.scalardb.demo.model.ProductImageModel;
import com.scalardb.demo.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ListenYoung
 * @date: Created on 17:59 2023/6/30
 * @modified By:
 *
 * Please note, this class is only for demo,
 * ALL THE OPERATIONS in this class should not appear in your normal work.
 */
@Deprecated
@RestController
@RequestMapping("/api/support")
public class SupportController {
  @Autowired
  private CarouselModel carouselModel;
  @Autowired
  private ProductImageModel productImageModel;
  @Autowired
  private ProductModel productModel;

  @Deprecated
  @GetMapping("/init")
  public String init() throws TransactionException {
    // init carousel:
    carouselModel.insert("1", "public/imgs/cms_1.jpg", "None");
    carouselModel.insert("2", "public/imgs/cms_2.jpg", "None");
    carouselModel.insert("3", "public/imgs/cms_3.jpg", "None");
    carouselModel.insert("4", "public/imgs/cms_4.jpg", "None");

    //init product image
    productImageModel.insert("1", "1", "public/imgs/phone/picture/Redmi-k30-1.png", "None");
    productImageModel.insert("2", "1", "public/imgs/phone/picture/Redmi-k30-2.png", "None");
    productImageModel.insert("3", "1", "public/imgs/phone/picture/Redmi-k30-3.png", "None");
    productImageModel.insert("4", "1", "public/imgs/phone/picture/Redmi-k30-4.png", "None");
    productImageModel.insert("5", "1", "public/imgs/phone/picture/Redmi-k30-5.png", "None");
    productImageModel.insert("6", "2", "public/imgs/phone/picture/Redmi K30 5G-1.jpg", "None");
    productImageModel.insert("7", "2", "public/imgs/phone/picture/Redmi K30 5G-2.jpg", "None");
    productImageModel.insert("8", "2", "public/imgs/phone/picture/Redmi K30 5G-3.jpg", "None");
    productImageModel.insert("9", "2", "public/imgs/phone/picture/Redmi K30 5G-4.jpg", "None");
    productImageModel.insert("10", "2", "public/imgs/phone/picture/Redmi K30 5G-5.jpg", "None");
    productImageModel.insert("11", "3", "public/imgs/phone/picture/MI CC9 Pro-1.jpg", "None");
    productImageModel.insert("12", "3", "public/imgs/phone/picture/MI CC9 Pro-2.jpg", "None");
    productImageModel.insert("13", "3", "public/imgs/phone/picture/MI CC9 Pro-3.jpg", "None");
    productImageModel.insert("14", "3", "public/imgs/phone/picture/MI CC9 Pro-4.jpg", "None");
    productImageModel.insert("15", "3", "public/imgs/phone/picture/MI CC9 Pro-5.jpg", "None");
    productImageModel.insert("16", "3", "public/imgs/phone/picture/MI CC9 Pro-6.jpg", "None");
    productImageModel.insert("17", "4", "public/imgs/phone/picture/Redmi 8-1.jpg", "None");
    productImageModel.insert("18", "4", "public/imgs/phone/picture/Redmi 8-2.jpg", "None");
    productImageModel.insert("19", "4", "public/imgs/phone/picture/Redmi 8-3.jpg", "None");
    productImageModel.insert("20", "4", "public/imgs/phone/picture/Redmi 8-4.jpg", "None");
    productImageModel.insert("21", "4", "public/imgs/phone/picture/Redmi 8-5.jpg", "None");
    productImageModel.insert("22", "5", "public/imgs/phone/picture/Redmi 8A-1.jpg", "None");
    productImageModel.insert("23", "6", "public/imgs/phone/picture/Redmi Note8 Pro-1.png", "None");
    productImageModel.insert("24", "6", "public/imgs/phone/picture/Redmi Note8 Pro-2.png", "None");
    productImageModel.insert("25", "6", "public/imgs/phone/picture/Redmi Note8 Pro-3.png", "None");
    productImageModel.insert("26", "6", "public/imgs/phone/picture/Redmi Note8 Pro-4.png", "None");
    productImageModel.insert("27", "6", "public/imgs/phone/picture/Redmi Note8 Pro-5.png", "None");
    productImageModel.insert("28", "7", "public/imgs/phone/picture/Redmi Note8-1.jpg", "None");
    productImageModel.insert("29", "7", "public/imgs/phone/picture/Redmi Note8-2.jpg", "None");
    productImageModel.insert("30", "7", "public/imgs/phone/picture/Redmi Note8-3.jpg", "None");
    productImageModel.insert("31", "7", "public/imgs/phone/picture/Redmi Note8-4.jpg", "None");
    productImageModel.insert("32", "7", "public/imgs/phone/picture/Redmi Note8-5.jpg", "None");
    productImageModel.insert("33", "8", "public/imgs/phone/picture/Redmi 7A-1.jpg", "None");
    productImageModel.insert("34", "8", "public/imgs/phone/picture/Redmi 7A-2.jpg", "None");
    productImageModel.insert("35", "8", "public/imgs/phone/picture/Redmi 7A-3.jpg", "None");
    productImageModel.insert("36", "8", "public/imgs/phone/picture/Redmi 7A-4.jpg", "None");
    productImageModel.insert("37", "8", "public/imgs/phone/picture/Redmi 7A-5.jpg", "None");
    productImageModel.insert("38", "9", "public/imgs/phone/picture/MiTv-4A-32-1.jpg", "None");
    productImageModel.insert("39", "9", "public/imgs/phone/picture/MiTv-4A-32-2.jpg", "None");
    productImageModel.insert("40", "9", "public/imgs/phone/picture/MiTv-4A-32-3.jpg", "None");
    productImageModel.insert("41", "9", "public/imgs/phone/picture/MiTv-4A-32-4.jpg", "None");
    productImageModel.insert("42", "10", "public/imgs/phone/picture/MiTv-E55A-1.jpg", "None");
    productImageModel.insert("43", "10", "public/imgs/phone/picture/MiTv-E55A-2.jpg", "None");
    productImageModel.insert("44", "10", "public/imgs/phone/picture/MiTv-E55A-3.jpg", "None");
    productImageModel.insert("45", "10", "public/imgs/phone/picture/MiTv-E55A-4.jpg", "None");
    productImageModel.insert("46", "11", "public/imgs/phone/picture/MiTv-E65A-1.jpg", "None");
    productImageModel.insert("47", "11", "public/imgs/phone/picture/MiTv-E65A-2.jpg", "None");
    productImageModel.insert("48", "11", "public/imgs/phone/picture/MiTv-E65A-3.jpg", "None");
    productImageModel.insert("49", "11", "public/imgs/phone/picture/MiTv-E65A-4.jpg", "None");
    productImageModel.insert("50", "12", "public/imgs/phone/picture/MiTv-4X 43-1.jpg", "None");
    productImageModel.insert("51", "12", "public/imgs/phone/picture/MiTv-4X 43-2.jpg", "None");
    productImageModel.insert("52", "12", "public/imgs/phone/picture/MiTv-4X 43-3.jpg", "None");
    productImageModel.insert("53", "13", "public/imgs/phone/picture/MiTv-4C 55-1.jpg", "None");
    productImageModel.insert("54", "13", "public/imgs/phone/picture/MiTv-4C 55-2.jpg", "None");
    productImageModel.insert("55", "13", "public/imgs/phone/picture/MiTv-4C 55-3.jpg", "None");
    productImageModel.insert("56", "14", "public/imgs/phone/picture/MiTv-4A 65-1.jpg", "None");
    productImageModel.insert("57", "15", "public/imgs/phone/picture/MiTv-ArtTv-65-1.jpg", "None");
    productImageModel.insert("58", "16", "public/imgs/phone/picture/AirCondition-V1C1-1.jpg", "None");
    productImageModel.insert("59", "17", "public/imgs/phone/picture/AirCondition-F3W1-1.jpg", "None");
    productImageModel.insert("60", "18", "public/imgs/phone/picture/Washer-Pro-10-1.jpg", "None");
    productImageModel.insert("61", "18", "public/imgs/phone/picture/Washer-Pro-10-2.jpg", "None");
    productImageModel.insert("62", "18", "public/imgs/phone/picture/Washer-Pro-10-3.jpg", "None");
    productImageModel.insert("63", "18", "public/imgs/phone/picture/Washer-Pro-10-4.jpg", "None");
    productImageModel.insert("64", "19", "public/imgs/phone/picture/protectingShell-RedMi-K20&pro-1.jpg", "None");
    productImageModel.insert("65", "20", "public/imgs/phone/picture/protectingShell-Mi-9-1.jpg", "None");
    productImageModel.insert("66", "20", "public/imgs/phone/picture/protectingShell-Mi-9-2.jpg", "None");
    productImageModel.insert("67", "21", "public/imgs/phone/picture/protectingMen-Mi-CC9-1.jpg", "None");
    productImageModel.insert("68", "22", "public/imgs/phone/picture/protectingMen-Mi-CC9e-1.jpg", "None");
    productImageModel.insert("69", "23", "public/imgs/phone/picture/charger-30w-1.jpg", "None");
    productImageModel.insert("70", "23", "public/imgs/phone/picture/charger-30w-2.jpg", "None");
    productImageModel.insert("71", "23", "public/imgs/phone/picture/charger-30w-3.jpg", "None");
    productImageModel.insert("72", "23", "public/imgs/phone/picture/charger-30w-4.jpg", "None");
    productImageModel.insert("73", "24", "public/imgs/phone/picture/charger-18w-1.jpg", "None");
    productImageModel.insert("74", "24", "public/imgs/phone/picture/charger-18w-2.jpg", "None");
    productImageModel.insert("75", "24", "public/imgs/phone/picture/charger-18w-3.jpg", "None");
    productImageModel.insert("76", "25", "public/imgs/phone/picture/charger-60w-1.jpg", "None");
    productImageModel.insert("77", "25", "public/imgs/phone/picture/charger-60w-2.jpg", "None");
    productImageModel.insert("78", "25", "public/imgs/phone/picture/charger-60w-3.jpg", "None");
    productImageModel.insert("79", "25", "public/imgs/phone/picture/charger-60w-4.jpg", "None");
    productImageModel.insert("80", "26", "public/imgs/phone/picture/charger-36w-1.jpg", "None");
    productImageModel.insert("81", "26", "public/imgs/phone/picture/charger-36w-2.jpg", "None");
    productImageModel.insert("82", "26", "public/imgs/phone/picture/charger-36w-3.jpg", "None");
    productImageModel.insert("83", "26", "public/imgs/phone/picture/charger-36w-4.jpg", "None");
    productImageModel.insert("84", "26", "public/imgs/phone/picture/charger-36w-5.jpg", "None");
    productImageModel.insert("85", "27", "public/imgs/phone/picture/charger-car-1.jpg", "None");
    productImageModel.insert("86", "27", "public/imgs/phone/picture/charger-car-2.jpg", "None");
    productImageModel.insert("87", "27", "public/imgs/phone/picture/charger-car-3.jpg", "None");
    productImageModel.insert("88", "27", "public/imgs/phone/picture/charger-car-4.jpg", "None");
    productImageModel.insert("89", "27", "public/imgs/phone/picture/charger-car-5.jpg", "None");
    productImageModel.insert("90", "27", "public/imgs/phone/picture/charger-car-6.jpg", "None");
    productImageModel.insert("91", "28", "public/imgs/phone/picture/charger-car-37w-1.jpg", "None");
    productImageModel.insert("92", "28", "public/imgs/phone/picture/charger-car-37w-2.jpg", "None");
    productImageModel.insert("93", "28", "public/imgs/phone/picture/charger-car-37w-3.jpg", "None");
    productImageModel.insert("94", "28", "public/imgs/phone/picture/charger-car-37w-4.jpg", "None");
    productImageModel.insert("95", "28", "public/imgs/phone/picture/charger-car-37w-5.jpg", "None");
    productImageModel.insert("96", "29", "public/imgs/phone/picture/charger-tio-1.jpg", "None");
    productImageModel.insert("97", "29", "public/imgs/phone/picture/charger-tio-2.jpg", "None");
    productImageModel.insert("98", "29", "public/imgs/phone/picture/charger-tio-3.jpg", "None");
    productImageModel.insert("99", "29", "public/imgs/phone/picture/charger-tio-4.jpg", "None");
    productImageModel.insert("100", "29", "public/imgs/phone/picture/charger-tio-5.jpg", "None");
    productImageModel.insert("101", "30", "public/imgs/phone/picture/charger-10000mAh-1.jpg", "None");
    productImageModel.insert("102", "30", "public/imgs/phone/picture/charger-10000mAh-2.jpg", "None");
    productImageModel.insert("103", "30", "public/imgs/phone/picture/charger-10000mAh-3.jpg", "None");
    productImageModel.insert("104", "30", "public/imgs/phone/picture/charger-10000mAh-4.jpg", "None");
    productImageModel.insert("105", "30", "public/imgs/phone/picture/charger-10000mAh-5.jpg", "None");
    productImageModel.insert("106", "31", "public/imgs/phone/picture/protectingShell-Mi-CC9Pro-1.jpg", "None");
    productImageModel.insert("107", "32", "public/imgs/phone/picture/protectingShell-RedMi-K20-1.jpg", "None");
    productImageModel.insert("108", "33", "public/imgs/phone/picture/protectingShell-Mi-9SE-1.jpg", "None");
    productImageModel.insert("109", "34", "public/imgs/phone/picture/protectingShell-Mi-9-red-1.jpg", "None");
    productImageModel.insert("110", "35", "public/imgs/phone/picture/protectingShell-Mix-3-1.jpg", "None");

    //init product
    productModel.insert("1", "Redmi K30", 1, "title of product 1", "intro of product 1", "public/imgs/phone/Redmi-k30.png", 2000.0, 1599.0, 99, 5, 0);
    productModel.insert("2", "Redmi K30 5G", 1, "title of product 2", "intro of product 2", "public/imgs/phone/Redmi-k30-5G.png", 2599.0, 2599.0, 99, 5, 0);
    productModel.insert("3", "Mi CC9 Pro", 1, "title of product 3", "intro of product 3", "public/imgs/phone/Mi-CC9.png", 2799.0, 2599.0, 99, 5, 0);
    productModel.insert("4", "Redmi 8", 1, "title of product 4", "intro of product 4", "public/imgs/phone/Redmi-8.png", 799.0, 699.0, 99, 5, 0);
    productModel.insert("5", "Redmi 8A", 1, "title of product 5", "intro of product 5", "public/imgs/phone/Redmi-8A.png", 599.0, 699.0, 99, 5, 0);
    productModel.insert("6", "Redmi Note8 Pro", 1, "title of product 6", "intro of product 6", "public/imgs/phone/Redmi-Note8-pro.png", 1399.0, 1199.0, 99, 5, 0);
    productModel.insert("7", "Redmi Note8", 1, "title of product 7", "intro of product 7", "public/imgs/phone/Redmi-Note8.png", 999.0, 999.0, 99, 5, 0);
    productModel.insert("8", "Redmi 7A", 1, "title of product 8", "intro of product 8", "public/imgs/phone/Redmi-7A.png", 599.0, 539.0, 99, 5, 0);
    productModel.insert("9", "Mi TV 4A 32 inches", 2, "title of product 9", "intro of product 9", "public/imgs/appliance/MiTv-4A-32.png", 799.0, 799.0, 99, 5, 0);
    productModel.insert("10", "Mi TV E55A", 2, "title of product 10", "intro of product 10", "public/imgs/appliance/MiTv-E55A.png", 2099.0, 1899.0, 99, 5, 0);
    productModel.insert("11", "Mi TV E65A", 2, "title of product 11", "intro of product 11", "public/imgs/appliance/MiTv-E65A.png", 3999.0, 2799.0, 99, 5, 0);
    productModel.insert("12", "Mi TV 4X 43 inches", 2, "title of product 12", "intro of product 12", "public/imgs/appliance/MiTv-4X-43.png", 1499.0, 1299.0, 99, 5, 0);
    productModel.insert("13", "Mi TV 4C 55 inches", 2, "title of product 13", "intro of product 13", "public/imgs/appliance/MiTv-4C-55.png", 1999.0, 1799.0, 99, 5, 0);
    productModel.insert("14", "Mi TV 4A 65 inches", 2, "title of product 14", "intro of product 14", "public/imgs/appliance/MiTv-4A-65.png", 2999.0, 2799.0, 99, 5, 0);
    productModel.insert("15", "Mi Super TV 65 inches", 2, "title of product 15", "intro of product 15", "public/imgs/appliance/MiTv-ArtTv-65.png", 6999.0, 6999.0, 99, 5, 0);
    productModel.insert("16", "Mi Cooler C1", 3, "title of product 16", "intro of product 16", "public/imgs/appliance/AirCondition-V1C1.png", 2699.0, 2599.0, 99, 5, 0);
    productModel.insert("17", "Mi Cooler A3", 3, "title of product 17", "intro of product 17", "public/imgs/appliance/AirCondition-F3W1.png", 1799.0, 1699.0, 99, 5, 0);
    productModel.insert("18", "Mi Washer Pro 10kg", 4, "title of product 18", "intro of product 18", "public/imgs/appliance/Washer-Pro-10.png", 2999.0, 2999.0, 99, 5, 0);
    productModel.insert("19", "Redmi K20/ K20 Pro shell", 5, "title of product 19", "intro of product 19", "public/imgs/accessory/protectingShell-RedMi-K20&pro.png", 39.0, 39.0, 99, 5, 0);
    productModel.insert("20", "Mi 9 ARE U OK Shell", 5, "title of product 20", "intro of product 20", "public/imgs/accessory/protectingShell-Mi-9.png", 49.0, 39.0, 99, 5, 0);
    productModel.insert("21", "Mi CC9 Screen Protector", 6, "title of product 21", "intro of product 21", "public/imgs/accessory/protectingMen-Mi-CC9.png", 19.0, 19.0, 99, 5, 0);
    productModel.insert("22", "Mi CC9e Screen Protector", 6, "title of product 22", "intro of product 22", "public/imgs/accessory/protectingMen-Mi-CC9e.png", 19.0, 19.0, 99, 5, 0);
    productModel.insert("23", "Mi USB Recharger 30W", 7, "title of product 23", "intro of product 23", "public/imgs/accessory/charger-30w.png", 59.0, 59.0, 99, 5, 0);
    productModel.insert("24", "Mi USB Recharger 18W", 7, "title of product 24", "intro of product 24", "public/imgs/accessory/charger-18w.png", 29.0, 29.0, 99, 5, 0);
    productModel.insert("25", "Mi USB Recharger 60W", 7, "title of product 25", "intro of product 25", "public/imgs/accessory/charger-60w.png", 129.0, 129.0, 99, 5, 0);
    productModel.insert("26", "Mi USB Recharger 36W", 7, "title of product 26", "intro of product 26", "public/imgs/accessory/charger-36w.png", 59.0, 59.0, 99, 5, 0);
    productModel.insert("27", "Mi USB Recharger For Car", 7, "title of product 27", "intro of product 27", "public/imgs/accessory/charger-car.png", 69.0, 69.0, 99, 5, 0);
    productModel.insert("28", "Mi USB Recharger 37W For Car", 7, "title of product 28", "intro of product 28", "public/imgs/accessory/charger-car-37w.png", 49.0, 49.0, 99, 5, 0);
    productModel.insert("29", "Mi Power Bank", 7, "title of product 29", "intro of product 29", "public/imgs/accessory/charger-tio.png", 99.0, 99.0, 99, 5, 0);
    productModel.insert("30", "Mi Wireless Power Bank 10000mAh", 8, "title of product 30", "intro of product 30", "public/imgs/accessory/charger-10000mAh.png", 129.0, 129.0, 99, 5, 0);
    productModel.insert("31", "Mi CC9 Pro Shell", 5, "title of product 31", "intro of product 31", "public/imgs/accessory/protectingShell-Mi-CC9Pro.png", 69.0, 69.0, 99, 5, 0);
    productModel.insert("32", "Redmi K20 Shell", 5, "title of product 32", "intro of product 32", "public/imgs/accessory/protectingShell-RedMi-K20.png", 39.0, 39.0, 99, 5, 0);
    productModel.insert("33", "Mi 9 SE Black Shell", 5, "title of product 33", "intro of product 33", "public/imgs/accessory/protectingShell-Mi-9SE.png", 49.0, 49.0, 99, 5, 0);
    productModel.insert("34", "Mi 9 Red Shell", 5, "title of product 34", "intro of product 34", "public/imgs/accessory/protectingShell-Mi-9-red.png", 49.0, 49.0, 99, 5, 0);
    productModel.insert("35", "Mi MIX 3 Blue Shell", 5, "title of product 35", "intro of product 35", "public/imgs/accessory/protectingShell-Mix-3.png", 49.0, 12.0, 99, 5, 0);

    return "Finish!";
  }
}
