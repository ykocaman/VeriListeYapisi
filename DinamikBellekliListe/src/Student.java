import java.util.Random;
import java.util.Scanner;


public class Student{
	public int id; //   ogrenci no
	public String name; // ogrenci adi
	public String surname; // ogrenci soyadı
	public Student list; // Öğrenci Pointer
	public Student lesson1; // DERS1 pointer
	public Student lesson2; // DERS2 pointer 
	
	 public void print(){
		 if(this==null){
			 System.out.println("\nÖğrenci bulunamadı\n"); return;
		 }
		 System.out.println(String.format(
				"Öğrenci No	: %s\n" +
		 		"Adı		: %s\n" +
		 		"Soyadı		: %s\n",
				 this.id,this.name,this.surname
			));
	 }
	 
	 public void read(){
		 Scanner console = new Scanner(System.in);
		 
		 System.out.println("Öğrenci numarası giriniz:");
		 this.id = console.nextInt();
		 
		 System.out.println("Öğrenci adı giriniz:");
		 this.name = console.next();
		 
		 System.out.println("Öğrenci soyadı giriniz:");
		 this.surname =console.next();
		 console.close();
		 
	 }
	 
	 public void random(){
			this.id 	= (int)(Math.random() * 10 + 1);
			this.name	= this.randomString(5);
			this.surname	= this.randomString(7);
	 }
	 
	
	 private String randomString(int count) {
		 char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		 StringBuilder sb = new StringBuilder();
		 Random random = new Random();
		 for (int i = 0; i < count; i++) {
		     char c = chars[random.nextInt(chars.length)];
		     sb.append(c);
		 }
		return sb.toString();
	 }
}
