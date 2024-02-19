public class guess_ab {
	
	//a.b.c.d取亂數，結合成答案字串ans(abcd)
	private int a=(int)(Math.random()*(9))+1;
	private int b=(int)(Math.random()*(10));
	private int c=(int)(Math.random()*(10));
	private int d=(int)(Math.random()*(10));
	private String ans=String.valueOf(a)+String.valueOf(b)+String.valueOf(c)+String.valueOf(d);
	
	//設一個整數陣列存取幾A幾B的A.B
	private int[] arr1=new int[2];
	
	//回傳整數陣列(幾A幾B)的方法      input為輸入的字串
	public int[] guess(String input) 
	{
		arr1[0]=0;                         //arr1[0]=A
		arr1[1]=0;                         //arr1[1]=B
		String[]ansTmp = input.split("");  //輸入的字串，分成4個字元，存入ansTmp字串陣列
		String[]inTmp = ans.split("");     //答案字串，分成4個字元，存入inTmp字串陣列
		for(int i=0;i<ansTmp.length;i++)
		{
			for(int j=0;j<ansTmp.length;j++) 
			{
				if(inTmp[j].equals(ansTmp[i])&&i==j)       //答案正確且位置正確，A+1(arr1[0]+1)
				{
					arr1[0]++;
				}
				else if(inTmp[j].equals(ansTmp[i])&&i!=j)  //答案正確但位置錯誤，B+1(arr1[1]+1)
				{
					arr1[1]++;
				}
			}
		}
		
		return arr1;    //回傳整數陣列arr1(幾A幾B)
	}
	
	//回傳字串的方法
	public String getAnswer() {
		return this.ans;    //回傳本題答案
	}
}
		


