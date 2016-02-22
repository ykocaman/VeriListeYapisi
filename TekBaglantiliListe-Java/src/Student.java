import java.util.Scanner;


public class Student {
	public int id; //   ogrenci no
	public int quiz1; // vize 1
	public int quiz2; // vize 2
	public int exam; // final
	
	
	public float getScore(){
		float result = 0;
		
		result = (float)this.quiz1 * 25 / 100 + 
				 (float)this.quiz2 * 25 / 100 + 
				 (float)this.exam * 50 / 100;
		
		return result;
	}
	
	 
	 public void print(){
		 System.out.println(String.format(
				"Öğrenci No	: %d\n" +
		 		"Vize 1		: %d\n" +
		 		"Vize 2		: %d\n" +
		 		"Final		: %d\n" +
		 		"Ortalama	: %.2f\n",
				 this.id,this.quiz1,this.quiz2,this.exam,this.getScore()
			));
	 }
	 
	 public void createFromInput(){
		 Scanner console = new Scanner(System.in);
		 
		 System.out.println("Öğrenci numarası giriniz:");
		 this.id = console.nextInt();
		 
		 System.out.println("Vize 1 giriniz:");
		 this.quiz1 = console.nextInt();
		 
		 System.out.println("Vize 2 giriniz:");
		 this.quiz2 =console.nextInt();
		 
		 System.out.println("Final giriniz:");
		 this.exam = console.nextInt();
	 }
	 
	public void random(){
			this.id 	= (int)(Math.random() * 100 + 1);
			this.quiz1	= (int)(Math.random() * 100 + 1);
			this.quiz2	= (int)(Math.random() * 100 + 1);
			this.exam	= (int)(Math.random() * 100 + 1);
	 }
}
