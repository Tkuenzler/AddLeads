package PBM;

import Client.Record;

public class OptumRx {
	public static String Filter(Record record) {
		if(record.getGrp().startsWith("ACU"))
			return InsuranceType.MEDICAID;
		switch(record.getGrp()) {
			case "CURX":
			case "WEISRX":
			case "LABCORP":
			case "DENSO":
			case "PSI6238":
			case "MSG":
			case "ERSTX":
			case "AHCRX":
			case "ATLUFCW":
			case "OE3":
			case "MTRX":
			case "LOVESRX":
			case "SHIRE":
			case "PCCA":
			case "TRNSTRX":
			case "MILWPS":
			case "MILWCTY":
			case "ILWU":
			case "UTF":
			case "CTYMILW":
			case "YRGRX":
			case "GECRX":
				return InsuranceType.PRIVATE_NO_TELMED;
			case "SIE":
			case "AMNJ":
			case "NAESRX":
				return InsuranceType.MEDICAID;
			case "ACUOHMMP":
				return InsuranceType.MEDICARE_COMMERCIAL;
			case "01960610":
			case "01960665":
			case "01960872":
			case "01962111":
			case "01961656":
			case "01961686":
				return InsuranceType.PRIVATE_VERIFIED;
			default:
				return InsuranceType.UNKNOWN_PBM;
		}
	}
}
