package jar;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class KeyPartitioner extends Partitioner<KeyBean, Text>{

	@Override
	public int getPartition(KeyBean key, Text value, int numPartitions) {
		//将哈希取模，将哈希值相同的放在一个区，余数是几，就有几个分区
		return key.getLeft().hashCode()*127%numPartitions;
	}

}
