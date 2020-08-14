package PBM;

import Client.Record;

public class Humana {
	public static String Filter(Record record) {
		switch(record.getPcn()) {
			case "05490000":
			case "05780000":
				return InsuranceType.PRIVATE_VERIFIED;
			case "03190000":
				return InsuranceType.PRIVATE_NO_TELMED;
			default:
				return InsuranceType.UNKNOWN_PBM;
		}
	}
}
