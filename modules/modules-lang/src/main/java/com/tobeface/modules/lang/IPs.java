package com.tobeface.modules.lang;

import java.util.Random;

/**
 * 
 * @author loudyn
 * 
 */
public final class IPs {
	/**
	 * 
	 * @return
	 */
	public static String nextIp() {
		Random random = new Random();
		IPBoudary boudary = ipBoudaries[random.nextInt(ipBoudaries.length)];
		long ipAsLong = boudary.getStart() + random.nextInt((int) (boudary.getEnd() - boudary.getStart()) + 1);
		return long2Ip(ipAsLong);
	}

	private static String long2Ip(long ipAsLong) {
		StringBuilder buf = new StringBuilder();
		buf.append(ipAsLong >> 24 & 0xFF).append(".");
		buf.append(ipAsLong >> 16 & 0xFF).append(".");
		buf.append(ipAsLong >> 8 & 0xFF).append(".");
		buf.append(ipAsLong & 0xFF);
		return buf.toString();
	}

	private static IPBoudary[] ipBoudaries = new IPBoudary[] {
																new IPBoudary(607649792L, 608174079L),
																new IPBoudary(1038614528L, 1039007743L),
																new IPBoudary(1783627776L, 1784676351L),
																new IPBoudary(2035023872L, 2035154943L),
																new IPBoudary(2078801920L, 2079064063L),
																new IPBoudary(-1950089216L, -1948778497L),
																new IPBoudary(-1425539072L, -1425014785L),
																new IPBoudary(-1236271104L, -1235419137L),
																new IPBoudary(-770113536L, -768606209L),
																new IPBoudary(-569376768L, -564133889L)
														};

	static class IPBoudary {
		private final long start;
		private final long end;

		public IPBoudary(long start, long end) {
			this.start = start;
			this.end = end;
		}

		public long getStart() {
			return start;
		}

		public long getEnd() {
			return end;
		}
	}

	private IPs() {}
}
