package jar;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public final class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	
 @Override
protected void map(LongWritable key, Text value,
		org.apache.hadoop.mapreduce.Mapper.Context context)
		throws IOException, InterruptedException {
	 
	String lineStr = value.toString();
	String[] split=lineStr.split("");
	for (String string : split) {
		
	}
	
}
}
