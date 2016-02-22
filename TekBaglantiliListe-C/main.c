#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

/* ana değişken yapı olan student objesi */
typedef struct _STUDENT{
	int id; // ogrenci no
	int quiz1; // vize 1
	int quiz2; // vize 2
	int exam; // final
	int lessonid; // ders adı
	struct _STUDENT* next; // BAG 
} STUDENT;

STUDENT *first; // ilk verimizin tutuldugu yer
STUDENT *pointer;

char* lessons[2]= {"ALGORITMA","WEB"};
const char* KAYITYOK = "\nKayit bulunamadi\nR'ye basip rastgele veri ekleyebilirsiniz\n";
const char* HATALIGIRIS = "\nHatali giris yaptiniz!\nAna menuye geri donuluyor...\n";

void addStudent(STUDENT* student);
bool insertStudent(STUDENT* student,int order);
bool printToScreen(STUDENT* student);
STUDENT* newStudent();
int getIntFromInput(const char* message);
bool addDummyData();
float getScore(STUDENT* student);
int countStudent();
int listToScreen();
int searchToScreen(int studentid,int lessonid);

int main(int argc, char **argv)
{
	char chosen;
	
	while(1){
		
puts( "\n\n========== MENU =========\n\
[L]istele	\
[O]grenci arama	\
[D]ers arama	\
[E]kle	\
[R]astgele veri ekle	\
[C]ikis\n\
===========================\n\
Tercihinizi giriniz:");
		
		chosen = getch();
		int order=0; // girilecek tercih numarası
		switch(chosen){
			case 'L':case 'l':
				puts("\n\n========== Liste ========\n\n");
				if(!listToScreen())
					puts(KAYITYOK);
			break;
			case 'E':case 'e':
				puts("\n\nKacinci siraya eklensin? (Liste sonu icin 100 giriniz):\n");
				order=0;
				scanf("%d",&order);
				if(insertStudent(newStudent(),order))
					puts("\n\nYeni ogrenci eklendi\n");
				else
					puts("\n\nHata olustu. Eklenemedi\n");
			break;
			case 'R':case 'r':
				if(addDummyData())
					puts("\n\nRastgele ogrenci verileri eklendi\n");
				else
					puts("\n\nHata olustu.Bellekte ram yok");
			break;
			case 'O':case 'o':
				puts("Ogrenci numarasi giriniz:");
				order=0;
				if(scanf("%d",&order)){
					if(!searchToScreen(order,-1)) // 1. paramtre ogrenci no, 2 parametre ders no, -1 parametresi ise tümü demek
						puts(KAYITYOK);
				}else{
					puts(HATALIGIRIS);
					char *enter;
					scanf("%c",&enter);
				}
			break;
			case 'D':case 'd':
				puts("1) ALGORITMA 2) WEB\nDers numarasi giriniz:");
				order=0;
				if(scanf("%d",&order)){
					if(!searchToScreen(-1,order -1)) // dizi indisi 0 dan başladıgı için 1 cıkarttık, -1 parametresi ise tümü demek
						puts(KAYITYOK);
				}else{
					puts(HATALIGIRIS);
					char *enter;
					scanf("%c",&enter);
				}
			break;
			case 'C':case 'c':
			puts("Program kapatiliyor");
			exit(0);
			break;
			case (char)13: // entera basmıs ise işlem yapmıyoruz.
			break;
			default:
			puts("Hatali secim yaptiniz. Tekrar deneyin.\n");
		}
	}
	return 0;
}

int listToScreen(){
	return searchToScreen(-1,-1);
}

int searchToScreen(int studentid,int lessonid){ // -1den farklı olan parametre degeri varsa ona gore arama yapıyoruz
	int i=0; 
	pointer = first;
	while(pointer!=NULL){
		if((studentid== -1 && lessonid == -1) || // -1 degeri geliyosa hepsini yazdırıyoruz yani parametreyı gecersiz yapıyoruz
			pointer->id==studentid || pointer->lessonid==lessonid){
			i++; printf("\n%d)",i);
			printToScreen(pointer);
		}
		pointer=pointer->next;
	}
	return i;
}


bool printToScreen(STUDENT* student){
	if(student==NULL)
		return false;
	printf("\nOgrenci No:%d\nDers Adi:%s\nVize1 Notu:%d\nVize2 Notu:%d\nFinal Notu:%d\nBasari Notu:%3.2f\n",
			// değişkenler
			student->id,
			lessons[student->lessonid],
			student->quiz1,
			student->quiz2,
			student->exam,
			getScore(student));
	return true;
}

STUDENT* newStudent(){
	STUDENT *student;
	student = malloc(sizeof(STUDENT));
	if(student==NULL)
		return NULL; // bellekten yer alınamadı.
		
	student->id = getIntFromInput("Ogrenci No:");
	puts("1) ALGORITMA 2) WEB");
	student->lessonid = getIntFromInput("Ders No:") - 1;
	student->quiz1 = getIntFromInput("Vize1 Notu:");
	student->quiz2 = getIntFromInput("Vize2 Notu:");
	student->exam = getIntFromInput("Final Notu:");

	return student;
}

int getIntFromInput(const char* message){
	int var;
	puts(message);
	if(scanf("%d",&var))
		return var;
	return 0;
}

bool insertStudent(STUDENT* student,int order){
	order--;
	if(order>countStudent()) order = countStudent();
	if(order<1){ // liste başı ise sabit adresi değiştiriyoruz
		student->next = first;
		first = student;
		return true;
	}
		
	int i=1;
	pointer = first;
	while(pointer!=NULL){
		if(i==order){
			student->next = pointer->next;
			pointer->next = student;
			return true;
		}
		pointer = pointer->next;
		i++;
	}
	return false;
}


void addStudent(STUDENT* student){
	insertStudent(student,countStudent()+1);
}

int countStudent(){
	int i=0;
	pointer = first;
	while(pointer!=NULL){
		pointer=pointer->next;
		i++;
	} 
	return i;
}

float getScore(STUDENT* student){
	return (float)((float)student->quiz1 / 100 * 25  + (float)student->quiz2 / 100 * 25 + (float)student->exam / 100 * 50);
}

bool addDummyData(){
	srand((unsigned)time(0)); // rastgele sayı üretmek için  zamanı alıyoruz.
	
	STUDENT *student;
	int i;
	for(i=0;i<6;i++){ // 6 adet öğrenci oluşturuyoruz 1 tanesi 2 tane ders alacak, öğrenci no eşitliycez
		student = malloc(sizeof(STUDENT));
		if(student==NULL)
			return false; // bellekten yer alınamadı.
			
		student->id =  rand() % 100; // gelen sayıyı 100 ile modlayıp max sayıyı belirliyoruz
		student->lessonid = rand() % 2; // iki dersten birisini rastgele seciyoruz.
		student->quiz1 =  rand() % 101;
		student->quiz2 = rand() % 101;
		student->exam = rand() % 101;
		student->next = NULL;	
	
		addStudent(student);
	}
	
	// ilk ogrenci için 2 ders tanımlıyoruz
	pointer = first;
	pointer->id = ((STUDENT*)pointer->next)->id;
	pointer->lessonid =0;
	((STUDENT*)pointer->next)->lessonid =1;
		
	return true;
}
