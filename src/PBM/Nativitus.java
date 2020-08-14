package PBM;

import Client.Record;

public class Nativitus {
	public static String Filter(Record record) {
		switch(record.getPcn()) {
			case "NVTD":
				return InsuranceType.PDP;
			default:
				return InsuranceType.UNKNOWN_PBM;
		}
	}
}
