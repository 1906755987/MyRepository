package jar;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparator;


public class MyGroupingComparator implements RawComparator<KeyBean>{

	public int compare(KeyBean o1, KeyBean o2) {
		
		return o2.getLeft().compareTo(o1.getLeft());
	}

	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		
		return WritableComparator.compareBytes(b1, s1, l1-4, b2, s2, l2-4);
	}

	

}
