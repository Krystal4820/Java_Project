public class bmi {

	private double height;
	private double weight;
	private double year;
	private double activity;
	private double result_bmi;
	private double result_bmr;
	private double result_healthyweight;
	
	//�غc�l
	public bmi() { 
		this.height = 0;
		this.weight = 0;
	}
	
	//�p��bmi-�^��double���A�����
	public double count_bmi(double height,double weight) {
	    return result_bmi=(weight)/(Math.pow((height/100),2));
	}
	
	//�p��k�ͪ�bmr(��¦�N�²v)-�^��double���A�����
	public double count_bmr_girl(double year,double weight,double height)
	{
		return result_bmr=655+(9.6*weight)+(1.8*height)-(4.7*year);
	}
	
	//�p��k�ͪ�bmr(��¦�N�²v)-�^��double���A�����
	public double count_bmr_man(double year,double weight,double height)
	{
		return result_bmr=66+(13.7*weight)+(5*height)-(6.8*year);
	}
	
	//�p�ⰷ�d�魫-�^��double���A�����
	public double count_healthyweight(double height)
	{
		return result_healthyweight=22*Math.pow((height/100),2);
	}
	
	//�p��TDEE(�C���`���Ӽ��q)-�^��double���A�����
	public double count_cal(double result_bmr,double activity)
	{
		return result_bmr*activity;
	}
}	

