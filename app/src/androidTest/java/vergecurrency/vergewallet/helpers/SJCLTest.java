package vergecurrency.vergewallet.helpers;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SJCLTest {
	private SJCL sjcl;

	public SJCLTest() {
		sjcl = new SJCL(InstrumentationRegistry.getInstrumentation().getTargetContext());
		sjcl.init();

	}

	@Test
	public void base64ToBitsTest() {
		int[] result = sjcl.base64ToBits("TXkgY3JpbWUgaXMgdGhhdCBvZiBjdXJpb3NpdHk=");
		assertArrayEquals(new int[]{1299783779,1919511909,543781664,1952997748,544171552,1668641385,1869834612,2030043136}, result);
	}

	@Test
	public void encryptAndDecryptTest() {
		int[] key = sjcl.base64ToBits("TXkgY3JpbWUgaXMgdGhhdCBvZiBjdXJpb3NpdHk=");
		String encoded = sjcl.encrypt( key,"Friday", new int[] {128,1});
		String decoded = sjcl.decrypt(key, encoded);

		assertEquals("Friday",decoded);
	}


	@Test
	public void sha256HastTest(){
		int[] hash = sjcl.sha256Hash("Hello Friday");

		assertArrayEquals(new int[]{118124657,-117793667,97360985,-421688632,-1022390789,506062141,-1117669270,-704988805},hash);
	}

	@Test
	public void hexFromBitsTest() {
		int[] hash = sjcl.sha256Hash("Hello Friday");
		String hex = sjcl.hexFromBits(hash);

		assertEquals("070a7071f8fa9c7d05cd9c59e6dd8ac8c30f8dfb1e29e53dbd61b86ad5fab97b", hex);
	}


}