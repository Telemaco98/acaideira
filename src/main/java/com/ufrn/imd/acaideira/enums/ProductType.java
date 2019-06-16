package com.ufrn.imd.acaideira.enums;

import java.util.Arrays;
import java.util.List;

public enum ProductType {
	Beverage, Meat, Condiments, Sauce, Dessest, Snack;
	
	public static ProductType StrToProductType (String str) {
		ProductType pType;
		if (str != null) {
			switch (str) {
				case "Beverage":
					pType = ProductType.Beverage;
					break;
				case "Meat":
					pType = ProductType.Meat;
					break;
				case "Condiments":
					pType = ProductType.Condiments;
					break;	
				case "Sauce":
					pType = ProductType.Sauce;
					break;
				case "Dessest":
					pType = ProductType.Dessest;
					break;
				default:
					pType = ProductType.Snack;
					break;
			}
			return pType;
		} else return ProductType.Snack;
	}
	
	public static List<ProductType> ValuesList () {
		List<ProductType> list = Arrays.asList(ProductType.values());
		return list;
	}
}