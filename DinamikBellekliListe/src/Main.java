import java.util.Random;
import java.util.Scanner;


public class Main {
		public static StudentList list = new StudentList();

		
		public static void main(String args[])
	    {
			crateRandomStudent(5); // 5 adet rastgele öğrenci olusturuyoruz
			startApplication();
	    }
		
		public static void startApplication(){
			 Scanner console = new Scanner(System.in);
			 int studentId;
			 Student student;
			 while(true){
				 System.out.println(
				 "\n\n========== MENU =========\n"+
				 "[T]um ogrenciler listesi\n" +
				 "Ogrenci [e]kle\n"+	 
				 "[O]grenci derslerini göster\n"+
				 "[D]erse kayıtlı öğrenciler\n"+
				 "De[r]s 1e öğrenci ekle\n" +
				 "Der[s] 2ye öğrenci ekle\n" +
				 "[C]ikis\n"+
				 "===========================\n"+
				 "Tercihinizi giriniz:");
				 	
					switch(getChar()){
					 case  't':case 'T':
						 list.printList();
					 break;
					 case 'e':case 'E':
						 student = new Student();
						 student.read();
					 	 list.insertList(student, false, false);
					 break;
					 case  'o':case 'O':
						 System.out.println("Öğrenci numarası giriniz:");
						 studentId =  console.nextInt();
						 
						 list.searchLesson1List(studentId);
						 list.searchLesson2List(studentId);
					 break;
					 case 'd':case 'D':
						 
						 System.out.println("1) Ders 1	2) Ders 2\n Ders Seçiniz:");
						 switch(getChar()){
							 case '1':
								 System.out.println("\n Ders 1: \n");
								 list.printLesson1List();
							 break;
							 case '2':
								 System.out.println("\n Ders 2: \n");
								 list.printLesson2List();
							 break;
						 }
					break;	 
					 case 'r':case 'R':
						 System.out.println("Öğrenci numarası giriniz:");
						 studentId =  console.nextInt();
						 student = list.get(studentId); // Hashtablosu ile kayıt get edildi.
						 if(student != null){
							list.insertLesson1List(student);
						 }else{
							 System.out.println("Kayıt bulunamadı.");
						 }
					break;
					 case 's':case 'S':
						 System.out.println("Öğrenci numarası giriniz:");
						 studentId =  console.nextInt();
						 student = list.get(studentId); // Hashtablosu ile kayıt get edildi.
						 if(student != null){
							list.insertLesson2List(student);
						 }else{
							 System.out.println("Kayıt bulunamadı.");
						 }
					break;	
					 case  'c':case 'C':
						 System.exit(0);
					 break;
					 }
			 
			 }
	 
		 }
		  
	 
		public static void crateRandomStudent(int count){
			Random r = new Random();
			for(int i=0;i<count;i++){
				Student student = new Student();
				student.random();
				student.id=i+1;
				list.insertList(student, r.nextBoolean(), r.nextBoolean());
			}
		}
		
		 public static char getChar(){ 
		      java.util.Scanner reader = new java.util.Scanner(System.in);
	         return reader.next().trim().charAt(0);
		 }
}
