public class bmi {

	private double height;
	private double weight;
	private double year;
	private double activity;
	private double result_bmi;
	private double result_bmr;
	private double result_healthyweight;
	
	//建構子
	public bmi() { 
		this.height = 0;
		this.weight = 0;
	}
	
	//計算bmi-回傳double型態的資料
	public double count_bmi(double height,double weight) {
	    return result_bmi=(weight)/(Math.pow((height/100),2));
	}
	
	//計算女生的bmr(基礎代謝率)-回傳double型態的資料
	public double count_bmr_girl(double year,double weight,double height)
	{
		return result_bmr=655+(9.6*weight)+(1.8*height)-(4.7*year);
	}
	
	//計算男生的bmr(基礎代謝率)-回傳double型態的資料
	public double count_bmr_man(double year,double weight,double height)
	{
		return result_bmr=66+(13.7*weight)+(5*height)-(6.8*year);
	}
	
	//計算健康體重-回傳double型態的資料
	public double count_healthyweight(double height)
	{
		return result_healthyweight=22*Math.pow((height/100),2);
	}
	
	//計算TDEE(每日總消耗熱量)-回傳double型態的資料
	public double count_cal(double result_bmr,double activity)
	{
		return result_bmr*activity;
	}
}	

