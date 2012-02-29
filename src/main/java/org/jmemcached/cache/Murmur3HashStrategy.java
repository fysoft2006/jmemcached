package org.jmemcached.cache;

/**
 * MurmurHash3 implementation of x86 32-bit version.
 * <p/>
 * The MurmurHash3 algorithm was created by Austin Appleby.  This java port was authored by
 * Yonik Seeley and is placed into the public domain.  The author hereby disclaims copyright
 * to this source code.
 * <p/>
 * This produces exactly the same hash values as the final C++
 * version of MurmurHash3 and is thus suitable for producing the same hash values across
 * platforms.
 * <p/>
 * Algorithm details are <a href="http://code.google.com/p/smhasher/wiki/MurmurHash3">here</a>.
 * <p/>
 * <pre>Mix function:{@code
 * k *= c1;
 * k = rotl(k,r1);
 * k *= c2;
 * <p/>
 * h ^= k;
 * <p/>
 * h = rotl(h,r1);
 * h = h*m1+n1;
 * }</pre>
 * <p/>
 * <pre>Finalizer:{@code
 * h ^= h >> 16;
 * h *= 0x85ebca6b;
 * h ^= h >> 13;
 * h *= 0xc2b2ae35;
 * h ^= h >> 16;
 * }</pre>
 */
public class Murmur3HashStrategy implements HashStrategy {
	/**
	 * Use randomized seed to prevent
	 * <a href="http://cryptanalysis.eu/blog/2011/12/28/effective-dos-attacks-against-web-application-plattforms-hashdos/">hashDOS</a>.
	 */
	private static final int SEED = (int) System.currentTimeMillis();

	@Override
	public int hash(byte[] data) {
		final int len = data.length;
		final int c1 = 0xcc9e2d51;
		final int c2 = 0x1b873593;

		int h1 = SEED;
		int roundedEnd = len & 0xfffffffc;  // round down to 4 byte block

		for (int i = 0; i < roundedEnd; i += 4) {
			// little endian load order
			int k1 = (data[i] & 0xff) | ((data[i + 1] & 0xff) << 8) | ((data[i + 2] & 0xff) << 16) | (data[i + 3] << 24);
			k1 *= c1;
			k1 = (k1 << 15) | (k1 >>> 17);  // ROTL32(k1,15);
			k1 *= c2;

			h1 ^= k1;
			h1 = (h1 << 13) | (h1 >>> 19);  // ROTL32(h1,13);
			h1 = h1 * 5 + 0xe6546b64;
		}

		// tail
		int k1 = 0;

		switch (len & 0x03) {
			case 3:
				k1 = (data[roundedEnd + 2] & 0xff) << 16;
				// fallthrough
			case 2:
				k1 |= (data[roundedEnd + 1] & 0xff) << 8;
				// fallthrough
			case 1:
				k1 |= (data[roundedEnd] & 0xff);
				k1 *= c1;
				k1 = (k1 << 15) | (k1 >>> 17);  // ROTL32(k1,15);
				k1 *= c2;
				h1 ^= k1;
		}

		// finalization
		h1 ^= len;

		// fmix(h1);
		h1 ^= h1 >>> 16;
		h1 *= 0x85ebca6b;
		h1 ^= h1 >>> 13;
		h1 *= 0xc2b2ae35;
		h1 ^= h1 >>> 16;

		return h1;
	}
}
