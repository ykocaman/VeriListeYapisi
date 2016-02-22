public class StudentList {
	   private Student listPointer;
	   private Student lesson1Pointer;
	   private Student lesson2Pointer;
	   
	   public static int  hashCount = 10;
	   public static Student[] hashList = new Student[hashCount];
	   
	   public StudentList()      
	      {
		   listPointer = lesson1Pointer = lesson2Pointer = null;             
	      }
	
	   public void insertList(Student student,boolean addLesson1,boolean addLesson2)
	      { 
		   student.list = listPointer;    // liste pointerine ekleniyor
		   listPointer = student; 
		   
		   hashList[student.id % hashCount] = student; // Pointer hash listesine eklendi
		   
		   
		   if(addLesson1){
			   insertLesson1List(student);
		   }
		   
		   if(addLesson2){
			   insertLesson2List(student);
		   }
	      }
	   
	   public void insertLesson1List(Student student){
		   student.lesson1= lesson1Pointer;     
		   lesson1Pointer = student; 
	   }
	   
	   public void insertLesson2List(Student student){
		   student.lesson2= lesson2Pointer;     
		   lesson2Pointer = student; 
	   }
	   

	   public boolean searchStudent(int studentId){
		  return hashList[studentId % hashCount] != null ;
	   }
	   
	   public Student get(int studentId){
		  return hashList[studentId % hashCount];
	   }

	   public void printList()
	      {
		   Student current = listPointer;
	      while(current != null)  
	         {
	         current.print(); 
	         current = current.list;
	         }
	      }
	   
	   public void printLesson1List()
	      {
		   Student current = lesson1Pointer;
	      while(current != null)  
	         {
	         current.print(); 
	         current = current.lesson1;
	         }
	      }
	   
	   public void searchLesson1List(int studentId)
	      {
		   Student current = lesson1Pointer;
	      while(current != null)  
	         {
	    	  if(current.id == studentId)
	    		  current.print(); 
	         current = current.lesson1;
	         }
	      }
	   
	   public void printLesson2List()
	      {
		   Student current = lesson2Pointer;
	      while(current != null)  
	         {
	         current.print(); 
	         current = current.lesson2;
	         }
	      }
	   
	   public void searchLesson2List(int studentId)
	      {
		   Student current = lesson2Pointer;
	      while(current != null)  
	         {
	    	  if(current.id == studentId)
	    		  current.print(); 
	         current = current.lesson2;
	         }
	      }

}


