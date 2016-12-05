package jar;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
public class HDFSDemoTest {
  FileSystem fileSystem=null;
  @Before
  public void getConf() throws IOException, InterruptedException, URISyntaxException{
	//获取hdfs配置文件(core-site.xml,core-deflt.xml,hdfs-deflt.xml,hdfs-site.xml)
	//通过获得配置文件获得distributedFileSystem对象
	  Configuration conf = new  Configuration();
	//获取hdfs文件系统  FileSystem是一个抽象类  子类DistributedFileSystem继承FileSystem
	fileSystem=FileSystem.get(new URI("hdfs://ocean:8020"), conf, "hadoop");
  }
  //文件下载
  @Test
  public void downLoadFile() throws IllegalArgumentException, IOException{
	  //获取hdfs文件输入流(读取文件)
	  FSDataInputStream in = fileSystem.open(new Path("/input/wc.input"));
	  //获取文件输出流(写出)
	   FileOutputStream out = new FileOutputStream("D://ocean.input");
	  IOUtils.copyBytes(in, out, 4096,true);
	  /**
	   * 运行该方法相当于客户端，准备下载linux服务端HDFS上的数据
	   */
	  
  }
  //上传文件
  @Test
  public void UploadFile() throws IllegalArgumentException, IOException{
	  FSDataOutputStream out = fileSystem.create(new Path("/output/wangou.jpg"),true);
	  FileInputStream in = new FileInputStream("D://wangou.jpg");
	  IOUtils.copyBytes(in, out, 4096, true);
  }
  //创建文件夹
  @Test
  public void createFile() throws IllegalArgumentException, IOException{
	  boolean mkdirs = fileSystem.mkdirs(new Path("/input/hadoop.txt"));
	  System.err.println(mkdirs);
  }
  
  //删除文件
  @Test
  public void deleteFile() throws IllegalArgumentException, IOException{
	  
	  boolean delete = fileSystem.delete(new Path("/input/hadoop.txt"));
	  System.err.println(delete);
	  new HDFSDemoTest();
  }
 /* @Test
  public static void read(String fileName){
	 //通过fileName获得真实文件路径
	 Path readPath = new Path(fileName);
	 //打开文件或者是获得输入流
	 FSDataInputStream inputStream=
	fileSystem.open(readPath);
	 IOUtils.copyBytes(in, out, 4096, true);
	 
	  
	  
  }
  public static void main(String[] args) {
	String fileName="";
	read(fileName);
}*/
  
}
