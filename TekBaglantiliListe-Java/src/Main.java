import java.util.Scanner;


public class Main {
	
	public static int studentTotal = 100;
	
	public static Student studentList[] = new Student[studentTotal];
	public static int pointerList[] = new int[studentTotal];
	
	public static int web = -1; // -1 sonlandırıcı
	public static int algoritma = -1; // -1 sonlandırıcı
	
	
	 public static void main(String args[])
	    {
		 initializeVar();
		 	
		 startApplication();
		 
	    }
	 
	 public static void startApplication(){
		 Scanner console = new Scanner(System.in);
		 
		 while(true){
			 System.out.println(
			 "\n\n========== MENU =========\n"+
			 "[L]istele "+
			 "[O]grenci arama "+
			 "[E]kle "+
			 "[C]ikis\n"+
			 "===========================\n"+
			 "Tercihinizi giriniz:");
			 	
				switch(getChar()){
				 case  'l':case 'L':
					 
					 System.out.println("0) Hepsi	1) Web	2) Algoritma\n Ders Seçiniz:");
					 switch(getChar()){
						 case '1':
							 System.out.println("\n Web Dersi: \n");
							 printLesson(web);
						 break;
						 case '2':
							 System.out.println("\n Algoritma Dersi: \n");
							 printLesson(algoritma);
						 break;
						 default:
							 System.out.println("\n Tüm Dersler: \n");
							 printLesson(web);
							 printLesson(algoritma);
						 break;
					 }
					
				 break;
				 case  'o':case 'O':
					 System.out.println("Öğrenci numarası giriniz:");
					 int studentId =  console.nextInt();
					 
					 printLesson(web);
					 printLesson(algoritma);
				 break;
				 case  'e':case 'E':
					 Student student = new Student();
					 student.createFromInput();
					 System.out.println("1) Web	2) Algoritma\n Ders Seçiniz:");
					 
					 switch(getChar()){
					 case '1':
						 web = addStudentTo(web, student);
					 break;
					 case '2':
						 algoritma = addStudentTo(algoritma, student);
					 break;
					 }
					 
				 break;
				 case  'c':case 'C':
					 System.exit(0);
				 break;
				 }
		 
		 }
 
	 }
	  

	 public static void printLesson(int lesson){	
		 while(lesson != -1){
			 studentList[lesson].print(); // pointerın işaret ettiği öğrenciyi yazdırıyoruz.
			 lesson = pointerList[lesson]; // pointerin tuttugu bir sonraki bag sayısını alıyoruz.
		 }
	 }
	 
	 
	 public static int addStudentTo(int lesson, Student student){
		 for(int i=0;i<pointerList.length;i++){
			 if(pointerList[i] == -2){
				pointerList[i] = lesson; // öğrenci bağını, listeye ekliyoruz
				studentList[i] = student; // öğrenci verisini diziye ekliyoruz
				return i; // başlangıcı son eklenen öğrenci donuyoruz
			 }
		 }
		 	return lesson;
	 }
	 
	 public static int addStudentRandomTo(int lesson, Student student){
		 int random;
		 for(int i=0;i<pointerList.length;i++){
			  random = (int)(Math.random() * 100);
			 if(pointerList[random] == -2){
				pointerList[random] = lesson; // öğrenci bağını, listeye ekliyoruz
				studentList[random] = student; // öğrenci verisini diziye ekliyoruz
				return random; // başlangıcı son eklenen öğrenci donuyoruz
			 }
			 
		 }
		 	return lesson;
	 }

	 /* BEGIN BAŞLANGIÇ  Fonksiyonları */
	 
	 private static void initializeVar(){
		 pointerList[0] = -1; // sonlandırıyı ekliyoruz.
		 
		 /* ders listelerini biçimlendiriyoruz. */
		 for(int i=1; i<pointerList.length;i++){
			 pointerList[i] = -2; // Null değer atıyoruz.
		 }
		
		/* Rastgele Veri ekliyoruz. 7 öğrenci notu ekliyoruz, 1 öğrencinin 2 derstede notu var */
		 for(int i=0;i<3;i++){
			 Student student = new Student();
			 student.random();
			 web = addStudentRandomTo(web, student);
		 }
		 
		 for(int i=0;i<3;i++){
			 Student student = new Student();
			 student.random();
			 algoritma = addStudentRandomTo(algoritma, student);
		 }
		 
	 	studentList[web].id = studentList[algoritma].id;
		 
	 }
	 

	 
	 public static char getChar(){ 
	      java.util.Scanner reader = new java.util.Scanner(System.in);
         return reader.next().trim().charAt(0);

	 }
	 
	 /* END BAŞLANGIÇ  Fonksiyonları */
}
