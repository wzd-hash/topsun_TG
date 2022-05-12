package com.topsun.util;

public class CommonUtil {
	
	//车牌省份拼接
	public static String plateNo2Num(String plateNo){
		String plateNO = "";
		String pro = plateNo.substring(0, 1);
		String end = plateNo.substring(1);
		switch (pro) {
		case "京":
			plateNO="00"+end;
			break;
		case "浙":
			plateNO="01"+end;
			break;
		case "津":
			plateNO="02"+end;
			break;
		case "皖":
			plateNO="03"+end;
			break;
		case "沪":
			plateNO="04"+end;
			break;
		case "闽":
			plateNO="05"+end;
			break;
		case "渝":
			plateNO="06"+end;
			break;
		case "赣":
			plateNO="07"+end;
			break;
		case "港":
			plateNO="08"+end;
			break;
		case "鲁":
			plateNO="09"+end;
			break;
		case "澳":
			plateNO="10"+end;
			break;
		case "豫":
			plateNO="11"+end;
			break;
		case "蒙":
			plateNO="12"+end;
			break;
		case "鄂":
			plateNO="13"+end;
			break;
		case "新":
			plateNO="14"+end;
			break;
		case "湘":
			plateNO="15"+end;
			break;
		case "宁":
			plateNO="16"+end;
			break;
		case "粤":
			plateNO="17"+end;
			break;
		case "藏":
			plateNO="18"+end;
			break;
		case "琼":
			plateNO="19"+end;
			break;	
		case "桂":
			plateNO="20"+end;
			break;
		case "川":
			plateNO="21"+end;
			break;
		case "蜀":
			plateNO="22"+end;
			break;
		case "冀":
			plateNO="23"+end;
			break;
		case "贵":
			plateNO="24"+end;
			break;
		case "黔":
			plateNO="25"+end;
			break;
		case "晋":
			plateNO="26"+end;
			break;
		case "云":
			plateNO="27"+end;
			break;
		case "滇":
			plateNO="28"+end;
			break;
		case "辽":
			plateNO="29"+end;
			break;
		case "陕":
			plateNO="30"+end;
			break;
		case "秦":
			plateNO="31"+end;
			break;
		case "吉":
			plateNO="32"+end;
			break;
		case "甘":
			plateNO="33"+end;
			break;
		case "陇":
			plateNO="34"+end;
			break;
		case "黑":
			plateNO="35"+end;
			break;
		case "青":
			plateNO="36"+end;
			break;
		case "苏":
			plateNO="37"+end;
			break;
		case "台":
			plateNO="38"+end;
			break;
		}
		return plateNO;
	}

}
