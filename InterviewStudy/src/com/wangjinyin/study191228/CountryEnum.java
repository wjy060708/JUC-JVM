package com.wangjinyin.study191228;

public enum CountryEnum {
	
	ONE(1,"齐国"),
	TWO(2,"楚国"),
	THREE(3,"燕国"),
	FOURE(4,"韩国"),
	FIVE(5,"赵国"),
	SIX(6,"魏国"),;

    private Integer retCode;
	
	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public String getRetMessage() {
		return retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	private String retMessage;

	private CountryEnum(Integer retCode, String retMessage) {
		this.retCode = retCode;
		this.retMessage = retMessage;
	}
	
	public static CountryEnum forEachCountryEnum(int index) {
		CountryEnum[] countryEnums = CountryEnum.values();
		
		for(CountryEnum countryEnum : countryEnums) {
			if (index == countryEnum.retCode) {
				return countryEnum;
			}
		}
		return null;
	}
}

/***
 * enum类似于枚举
 * mysql dbName = CountryEnum
 * 
 * table表
 * one
 * Id username ,age,birth
 * 1   齐国     34   公元909
 * 
 * 
 */
