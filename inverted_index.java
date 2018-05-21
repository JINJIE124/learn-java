import java.io.*;
import java.util.*;
public class inverted_index {
	static HashMap<String,String>  map=new HashMap<String,String>();
	void getDocId() throws Exception {	//建立倒排索引表	
		   int  filenum=1;
		   while(filenum<1001) {
			   String num=String.valueOf(filenum);
			   String filepath="C:\\Users\\Administrator\\Desktop\\file\\file\\"+num;
		       File file=new File(filepath);
		       BufferedReader reader =new BufferedReader(new FileReader(file));
		       String s=null;
		       String words1[]=null;
		       String words2[]=null;
		       while((s=reader.readLine()) != null) {
		    	   words1=s.split(" ");            	    	   	   
		    	   for(int i=0;i<words1.length;i++) {
		    		   words1[i]=words1[i].toLowerCase();  //小写化
		    		   if(map.containsKey(words1[i])) {		  //  词项已经出现过			   
		    			   words2=map.get(words1[i]).split(" ");
		    			   boolean bool=true;
		    			   for(int j=0;j<words2.length;j++) {
		    				   if(words2[j].equals(num))
		    					   bool=false;	    				   
		    			   }
		    			   if(bool)                  //词项的索引里没有当前文档ID
		    				   map.put(words1[i], map.get(words1[i])+num+" ");		    			       
		    		   }
		    		   else {//词项第一次出现
		    			   map.put(words1[i], num+" ");		    			   
		    		   }		  
		    	   }	   
		       }
		       reader.close();	       
		       filenum++;
		   	}
	}  
   public static void main(String args[]) throws Exception {
	    inverted_index index=new inverted_index();
	    index.getDocId();//建立倒排索引表并存放在map里	    
	    BufferedWriter out =new BufferedWriter (new FileWriter("C:\\Users\\Administrator\\Desktop\\file\\file\\dict.index"));	   	
	    Object key[]=map.keySet().toArray();
	    Arrays.sort(key);  //对key进行排序
	    for(int i=0;i<key.length;i++) {
	    	String words[] =map.get(key[i]).split(" ");	//将排序后的倒排索引输出到文档   			   		
	   		if(!key[i].equals("") && words!=null)	   				   		    
	   			out.write(key[i]+"/"+words.length+"/"+map.get(key[i])+"\n");
	    }
	   	out.flush();
	   	out.close();
	   	boolQuery bq=new boolQuery();
	   	boolean bool=true;	
	   	Scanner reader=new Scanner(System.in);
	   	while(bool) {
	   		System.out.println("请输入您想查询的词项，多词查询请用and或者or连接，单词之间请以空格分开，退出查询请输入-1");    		
	   		String Str1=reader.nextLine(); //输入查询信息
	   		String str[]=Str1.split(" ");  //分解查询信息
	   		if(str[0].equals("-1"))        //-1表示退出查询
	   			bool=false;
	   		if(bool) {	   			
	   			String answer=null;		   			
	   			if(str.length==1)	{   			
	   				answer=bq.query(str[0]);	   					   				
	   			}
	   			else {		   				
	   				if(str[1].equals("and")) {   //and查询
	   					answer=bq.boolOperate(bq.query(str[0]), bq.query(str[2]), true);
	   				}
	   				else if(str[1].equals("or")) { //or查询
	   					answer=bq.boolOperate(bq.query(str[0]), bq.query(str[2]), false);
	   				}
	   			}
	   			if(answer.equals(""))
	   				System.out.println("没有符合要求的文档"); 
	   			else {
	   				System.out.println(answer);	   				
	   			}
	   			}	   				   			   		
	   	}
	   	reader.close();
   }
}
class boolQuery{
	HashMap<String,String>  map2=new HashMap<String,String>();
	File file=new File("C:\\Users\\Administrator\\Desktop\\file\\file\\dict.index");
	String query(String str) throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(file));//读取到排索引表文件
		String s=null;
		String words[]=null;
		while((s=br.readLine()) != null) {
			words=s.split("/");
			map2.put(words[0], words[2]);//将索引信息放入map中
		}
		br.close();
		if(map2.containsKey(str))
			return map2.get(str);      //返回查询到的文档ID
		else
			return "";
	}
	String boolOperate(String str1,String str2,boolean flag) {
		if(str1.equals("") ) {    
			if(flag) {
				return "";
			}
			else {
				return str2;
			}
		}
		else if(str2.equals("")) {   //其中一词的文档ID不存在
			if(flag) {
				return "";
			}
			else {
				return str1;
			}
		}
		else {
			String words1[]=str1.split(" ");
			String words2[]=str2.split(" ");
			String words3="";
			if(flag) {                       //and查询
				int i=0;
				int j=0;			
				while(i<words1.length && j<words2.length ) {
					int docId1=Integer.parseInt(words1[i]);  //转化为int类型
					int docId2=Integer.parseInt(words2[j]);	 //相等时将文档ID放入答案中				
					if(docId1==docId2) {                     //不相等时小的文档ID索引加1
						words3=words3+words1[i]+" ";						
						i++;
						j++;
			    	}
			    	else if(docId1<docId2)
			    		i++;
			    	else if(docId1>docId2)
			    		j++;
			    }			
			}
			else {							
				int i=0;
				int j=0;			
				while(i<words1.length && j<words2.length ) { //将较小的文档ID放入答案中
					int docId1=Integer.parseInt(words1[i]);
					int docId2=Integer.parseInt(words2[j]);					
					if(docId1<docId2) {
						words3=words3+words1[i]+" ";						
						i++;
			    	}			    	
			    	else if(docId1>docId2) {
			    		words3=words3+words2[j]+" ";
			    		j++;
			    	}
			    	else if(docId1==docId2) {
			    		words3=words3+words2[j]+" ";
			    		i++;
			    		j++;
			    	}
				}
			    while(i<words1.length) {               //将剩余的所有文档ID放入答案中
			    	words3=words3+words1[i]+" ";
			    	i++;
			    }
			    while(j<words2.length) {
			    	words3=words3+words2[j]+" ";
			    	j++;
			    }
			}
			return words3;
		}	
	}
}