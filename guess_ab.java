public class guess_ab {
	
	//a.b.c.d���üơA���X�����צr��ans(abcd)
	private int a=(int)(Math.random()*(9))+1;
	private int b=(int)(Math.random()*(10));
	private int c=(int)(Math.random()*(10));
	private int d=(int)(Math.random()*(10));
	private String ans=String.valueOf(a)+String.valueOf(b)+String.valueOf(c)+String.valueOf(d);
	
	//�]�@�Ӿ�ư}�C�s���XA�XB��A.B
	private int[] arr1=new int[2];
	
	//�^�Ǿ�ư}�C(�XA�XB)����k      input����J���r��
	public int[] guess(String input) 
	{
		arr1[0]=0;                         //arr1[0]=A
		arr1[1]=0;                         //arr1[1]=B
		String[]ansTmp = input.split("");  //��J���r��A����4�Ӧr���A�s�JansTmp�r��}�C
		String[]inTmp = ans.split("");     //���צr��A����4�Ӧr���A�s�JinTmp�r��}�C
		for(int i=0;i<ansTmp.length;i++)
		{
			for(int j=0;j<ansTmp.length;j++) 
			{
				if(inTmp[j].equals(ansTmp[i])&&i==j)       //���ץ��T�B��m���T�AA+1(arr1[0]+1)
				{
					arr1[0]++;
				}
				else if(inTmp[j].equals(ansTmp[i])&&i!=j)  //���ץ��T����m���~�AB+1(arr1[1]+1)
				{
					arr1[1]++;
				}
			}
		}
		
		return arr1;    //�^�Ǿ�ư}�Carr1(�XA�XB)
	}
	
	//�^�Ǧr�ꪺ��k
	public String getAnswer() {
		return this.ans;    //�^�ǥ��D����
	}
}
		


