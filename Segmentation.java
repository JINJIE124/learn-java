import java.io.*;
public class Segmentation {
	final int maxSize=4538;          //定义字典词组的个数
	double    wordCount1=0;
	double    wordCount2=0;
	double    correctNum=0;
	List dictionary=new List(maxSize);      //创建字典对象
	public void setDict(String file) throws IOException, Exception {//将字典文件存进字典对象的方法
	        FileReader fr=new FileReader(file);          	
	        BufferedReader br =new BufferedReader(fr);//按行读取文件使用缓冲流
	        String CurWord=null;
	        int i=0;
	        while((CurWord=br.readLine()) != null) {		
		         dictionary.add(i, CurWord);		//将读取到的字典词组存储到字典对象中
		         i++;		         
	       }	   
	       fr.close();
	       br.close();
	    }   	
	public  void operate(String str1,String str2) throws Exception{ //将已知文件按照字典进行分词的方法
   		FileReader fr=new FileReader(str1);
		BufferedReader br =new BufferedReader(fr);
		FileWriter out=new FileWriter(str2);				
		String CurLine=null;         
		while((CurLine=br.readLine())!=null) {
		   //System.out.println(CurLine);
		   String  Answer="";		   //清空每一行准备输出的结果
           String  CurWord="";         //清空当前处理的字段
           int CurLength=10;
           int aheadCount=0;           //重置当前处理字段的首位置和末位置 
           int behindCount;
           for(;aheadCount<CurLine.length();){      //循环处理当前读到的那一行，直至处理完  	   
        	   for( behindCount=aheadCount;(behindCount-aheadCount<CurLength+1)&&(behindCount<CurLine.length());behindCount++) { 
        	          CurWord+=CurLine.charAt(behindCount);//获取当前条件下能获取到的长度最大的文字段
        	          //System.out.println(CurWord);
        	          //System.out.println(aheadCount);
        	          //System.out.println(behindCount);
        	          //System.out.println(s);
        	   }            	     
               if(dictionary.isIn(CurWord)){           //判断当前字段是否在词典中
            		Answer +=(CurWord+" ");            //将当前字段处理并加入到输出结果中
            		//System.out.println(Answer);
            		//System.out.println("right");
            		CurWord="";                        //清空当前字段
            		aheadCount=behindCount;            //去除已处理字段，当前字段首位置向后移
            		CurLength=10;                      //重置字段最大长度
            	}
               else if((CurLength==0)){//如果当前字段长度为0即只有一个字
            	   Answer +=(CurWord+" ");      //将该字符处理并加入到输出结果中
            	   //System.out.println(Answer);
            	   //System.out.println("false");
                   CurWord="";                  //清空当前字段
                   aheadCount++;                //去除已处理字段，当前字段首位置向后移
                   CurLength=10;                //重置字段最大长度
               }             
               else {                           //如果以上两种情况都不是
            	   CurLength--;                 //字段最大长度-1
            	   CurWord="";                  //清空当前字段
               }
              
           }
           //System.out.println(response);
           out.write(Answer+"\r\n");             //将该行的处理结果写入文件中并换行  	                      
		}
		fr.close();
        br.close();
        out.close();    		                 //关闭文件
       } 
	public void compute(String str1,String str2) throws Exception{		
		 FileReader fr1=new FileReader(str1);
		 BufferedReader br1 =new BufferedReader(fr1);
		 FileReader fr2=new FileReader(str2);
		 BufferedReader br2 =new BufferedReader(fr2);
		 String CurLine1=null;
		 String CurLine2=null;
		 String CurWord1=null;
		 String CurWord2=null;
		 int aheadCount1;
		 int aheadCount2;
		 int spaceCount1;
		 int spaceCount2;
		 while( ((CurLine1=br1.readLine())!=null) &&  (CurLine2=br2.readLine())!=null){
			  //System.out.println(CurLine1);
			  //System.out.println(CurLine2);
			  aheadCount1=0;
			  aheadCount2=0;
			  spaceCount1=0;
			  spaceCount2=0;
			  while(aheadCount1<CurLine1.length() && aheadCount2<CurLine2.length()){
				  
				  while(CurLine1.charAt(aheadCount1) != ' ' ) {
					  CurWord1+=CurLine1.charAt(aheadCount1);
					  aheadCount1++;
				  }
				  //System.out.println(CurWord1);
				  spaceCount1++;					  				  
				  while(CurLine2.charAt(aheadCount2) != ' ' ) {
					  CurWord2+=CurLine2.charAt(aheadCount2);
					  aheadCount2++;
				  }
				  spaceCount2++;					 
				  //System.out.println(CurWord2);
				  if(CurWord1!=null && CurWord2!=null) {
					  if(CurWord1.length()==CurWord2.length() && 
						(aheadCount1-CurWord1.length()-(spaceCount1)) == (aheadCount2-CurWord2.length()-(spaceCount2))) {
						  if(CurWord1.equals(CurWord2)){
					          correctNum++;
					          //System.out.println(CurWord1);
				          }
					      aheadCount1++;					      
					      CurWord1=null;
					  }
					  else if((aheadCount1-spaceCount1)==(aheadCount2-spaceCount2) 
							  || (aheadCount1-CurWord1.length()-(spaceCount1))==(aheadCount2-CurWord2.length()-(spaceCount2))){ 
						      aheadCount1++;					      
					          CurWord1=null;
				      }
					  aheadCount2++;
					  CurWord2=null;	
				  }				  			  				  
			  }			  
		 }
      aheadCount1=0;
      aheadCount2=0;      
      FileReader fr3=new FileReader(str1);
	  BufferedReader br3 =new BufferedReader(fr3);
	  FileReader fr4=new FileReader(str2);
	  BufferedReader br4 =new BufferedReader(fr4);
	  while((CurLine1=br3.readLine())!=null){
		  while(aheadCount1<CurLine1.length()){			  
			  if(CurLine1.charAt(aheadCount1) == ' ' ) {
				  wordCount1++;				  
			  }
			  aheadCount1++;
		  }
		  aheadCount1=0;
		  //wordCount1++;
	  }
	  while((CurLine2=br4.readLine())!=null){
		  while(aheadCount2<CurLine2.length()){
			  if(CurLine2.charAt(aheadCount2) == ' ' )
				  wordCount2++;
			  aheadCount2++;
		  }
		  aheadCount2=0; 
		  //wordCount2++;
	  }	 	  
	  System.out.println("正确分词文件的个体总数："+wordCount2);
	  System.out.println("分词文件的个体总数："+wordCount1);
	  System.out.println("正确分词的个体总数:"+correctNum);	  
	  System.out.println("召回率:"+wordCount1/wordCount2);
	  System.out.println("正确率:"+correctNum/wordCount1);
	  fr1.close();
      br1.close();
      fr2.close();
      br2.close();
      fr3.close();
      br3.close();
      fr4.close();
      br4.close();
	 }
     public static void main(String [] args) throws Exception{
            Segmentation seg=new Segmentation();         //创建一个分词类的对象
            seg.setDict("C:\\Users\\Administrator\\Desktop\\data\\corpus.dict.txt");//载入词典
            seg.operate("C:\\Users\\Administrator\\Desktop\\data\\sentence.txt","C:\\Users\\Administrator\\Desktop\\data\\corpus.out.txt");//处理文档
            seg.compute("C:\\Users\\Administrator\\Desktop\\data\\corpus.out.txt","C:\\Users\\Administrator\\Desktop\\data\\corpus.answer.txt");
        } 	            
}
class List{  //利用List类存储词典
	private Object[] listElem;
	int max;
	public List(int maxSize) {  //类的构造方法
		max=maxSize;
		listElem=new Object[maxSize];
	}
	public void add(int i,Object x)throws Exception{//存储词典的方法
		listElem[i]=x;	
	}
	public boolean isIn(Object x) {          //判断字段是否在词典中的算法
		for(int i=0;i<max;i++) {
			if(listElem[i].equals(x)) {
				return true;			    
			}
		}
		return false;
	}
	/*public void display() {               //输出词典的算法
		for(int i=0;i<max;i++) {
			System.out.println(listElem[i]);
		}
	}*/ 
}
