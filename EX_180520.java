package EX1;
/*春天是鲜花的季节，水仙花就是其中最迷人的代表，数学上有个水仙花数，他是这样定义的： “水仙花数”是指一个三位数，
 * 它的各位数字的立方和等于其本身，比如：153=1^3+5^3+3^3。 现在要求输出所有在m和n范围内的水仙花数。
 * 输入数据有多组，每组占一行，包括两个整数m和n（100 ≤ m ≤ n ≤ 999）。*/
import java.io.*;
public class EX_180520 {		
	 void operate(int num1,int num2){
	        boolean bool=false;
	        for(int i=num1;i<=num2;i++){      
	            int n1=i/100;
	            int n2=(i-n1*100)/10;
	            int n3=i-n1*100-n2*10;
	            int j=n1*n1*n1+n2*n2*n2+n3*n3*n3;
	            if(i==j){               
	                System.out.print(i+" "); 
	                bool=true;
	            }          
	        }
	        if(!bool)
	            System.out.print("no");
	        System.out.println("");
	    }
	    public static void main(String[] args) throws Exception{
	        //Scanner reader=new Scanner(System.in);
	        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	        String line;
	        while((line=bf.readLine()) != null){
	            String str[]=line.split(" ");
	            int num1=Integer.parseInt(str[0]);
	            int num2=Integer.parseInt(str[1]);
	            EX_180520 Num=new EX_180520();
	            Num.operate(num1,num2);	            
	        }
	    }
}
