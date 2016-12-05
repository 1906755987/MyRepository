package jar;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SortTwiceMapReduce {
   public static class SortTwiceMap extends Mapper<LongWritable, Text, KeyBean, Text>{
  Text v=new Text();
  KeyBean kBean = new KeyBean();
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] split = value.toString().split(",");
		kBean.set(split[0], Integer.valueOf(split[1]));
		v.set(split[1]);
		context.write(kBean, v);
	}
   }
   
   
   public static class SortTwiceReduce extends Reducer<KeyBean, Text, Text, Text>{
	   Text key= new Text();
	@Override
	protected void reduce(KeyBean keyBean, Iterable<Text> iterable,
			Context context)
			throws IOException, InterruptedException {
		for (Text text : iterable) {
			key.set(keyBean.getLeft());
			context.write(key, text);
		}
	}
   }
   
   public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
	Configuration configuration = new Configuration();
	Job job = Job.getInstance(configuration);
	
	job.setJarByClass(SortTwiceMapReduce.class);
	
	
	job.setMapperClass(SortTwiceMap.class);
	job.setMapOutputKeyClass(KeyBean.class);
	job.setMapOutputValueClass(Text.class);
	
	job.setReducerClass(SortTwiceReduce.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);
	
	job.setGroupingComparatorClass(MyGroupingComparator.class);
	FileInputFormat.setInputPaths(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	
	job.waitForCompletion(true);
}
}
