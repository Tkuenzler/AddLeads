package PBM;

import Client.Record;

public class Argus {
	public static String Filter(Record record) {
		switch(record.getPcn()) {
			case "02150000":
			case "05180000":
			case "05190000":
			case "06210000":
			case "06840000":
			case "06810000":
			case "01910000":
				return InsuranceType.PRIVATE_NO_TELMED;
			case "CIHSCARE":
				return InsuranceType.MEDICARE_COMMERCIAL;
			case "01420000":
				return InsuranceType.PRIVATE_UNKNOWN;
			case "06180000":
			case "02530000":
			case "01940000":
			case "07640000":
				return InsuranceType.MEDICAID;
			default:
				return InsuranceType.UNKNOWN_PBM;
		}
	}
}
