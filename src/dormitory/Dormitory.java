/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dormitory;
import static java.lang.System.*;
import java.util.InputMismatchException;
import java.util.Scanner;

enum Sex{Male,Female};
enum Department{Chemical,Electrical,Electro_Mechanical,Software};
class Student{ // a class for a student entity
    public final String fName,lName;
    public final int id;
    public final Sex sex;
    public final Department department;
    public final int year;
    private Dorm placedAt;
    Student(int id, String fName, String lName, Sex sex, Department department, int year){
        this.year=year;
        this.id=id;
        this.fName=fName;
        this.lName=lName;
        this.sex=sex;
        this.department=department;
    }
    public void placeStudent(Dorm dorm){
        placedAt=dorm;
    }
    public Dorm getPlacement(){
        return placedAt;
    }
    @Override
    public String toString(){
        String y=(year==1)?"First":
                (year==2)?"Second":
                (year==3)?"Third":
                (year==4)?"Fourth":"Fifth";
        return "ID: "+id+"\nName: "+fName+" "+lName+"\nSex: "+sex+"\nDeparment: "+department+"\nYear: "+y;
    }
}
class Dorm{ // a class for a dorm entity
    private final Student students[]; // the students a dorm can hold
    private int studentCount=0;
    public final int ID;
    public final int MAX_STUD_DORM;
    public final Department department;
    public final Sex sex;
    public final int year;
    private Floor isIn;
    Dorm(int maxStudDorm, dormitory.Sex sex, Department department, int year, int floorLevel, int dormNumber){ // a constructor for student
        this.year=year;
        this.sex=sex;
        this.department=department;
        String temp=floorLevel+""+dormNumber;
        this.ID=Integer.parseInt(temp);
        MAX_STUD_DORM=maxStudDorm;
        students=new Student[maxStudDorm];
    }
    public void placeDormIn(Floor floor){
        isIn=floor;
    }
    public Floor getPlacement(){
        return isIn;
    }
    public boolean addStudent(Student stud){
        if(studentCount<MAX_STUD_DORM){
            if(stud.department==department){
                if((stud.sex==sex)){
                    if(stud.year==year){
                        students[studentCount]=stud;
                        studentCount++;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Student getStudentAt(int index){
        if(index<MAX_STUD_DORM && index>-1)
            return students[index];
        return null;
    }
    public int getStudentCount(){
        return studentCount;
    }
    @Override
    public String toString(){
        return "Dorm "+ID;
    }
}
class Floor{
    public final int MAX_STUD_DORM;
    public final int NO_OF_DORMS;
    private int dormCount=0;
    private final Dorm dorms[];
    public final Department department;
    public final Sex sex;
    public final int year;
    public final int level;
    private static int floorCount=0;
    private Block isIn;
    Floor(int no_of_dorms, Sex sex, Department department, int year, int maxStudDorm, int level){
        this.year=year;
        this.level=level;
        floorCount++;
        this.sex=sex;
        this.department=department;
        this.NO_OF_DORMS=no_of_dorms;
        this.dorms=new Dorm[no_of_dorms];
        this.MAX_STUD_DORM=maxStudDorm;
    }
    public void placeFloorIn(Block block){
        isIn=block;
    }
    public Block getPlacement(){
        return isIn;
    }
    public boolean addDorm(Dorm dorm){
        if(dormCount<NO_OF_DORMS){
            if(dorm.department==department){
                if(dorm.sex==sex){
                    if(dorm.year==year){
                        dorms[dormCount]=dorm;
                        dormCount++;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Dorm getDormAt(int index){
        if(index<NO_OF_DORMS && index>-1)
            return dorms[index];
        return null;
    }
    public int getDormCount(){
        return dormCount;
    }
    @Override
    public String toString(){
        return "Floor "+level;
    }
}
class Block{ // a class for a Block entity
    public final int ID;
    public final int NO_OF_FLOORS;
    public final Sex sex;
    private int floorCount=0;
    private static int blockCount=0;
    private final Floor floors[];
    public final Department department;
    public Block(Sex catagory, int noOfFloors, Department department){
        this.ID=++blockCount;
        this.sex=catagory;
        this.NO_OF_FLOORS=noOfFloors;
        this.department=department;
        floors=new Floor[noOfFloors];
    }
    public int getFloorCount(){
        return floorCount;
    }
    public boolean addFloor(Floor floor){
        if(floorCount<NO_OF_FLOORS){
            if(floor.department==department){
                if(floor.sex==sex){
                    floors[floorCount]=floor;
                    floorCount++;
                    return true;
                }
            }
        }
        return false;
    }
    public Floor getFloorAt(int index){
        if(index<NO_OF_FLOORS && index>-1)
            return floors[index];
        return null;
    }
    @Override
    public String toString(){
        return "Block "+ID+" For "+sex+" students with "+NO_OF_FLOORS+" floors, For "+department;
    }
}
// main class
public class Dormitory {
    static int placedStudentsCount=0;
    static String cls="\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    static Student students[]=new Student[100];
    static Block blocks[]=new Block[50];
    static Scanner input=new Scanner(System.in);
    static int studentCount=0,blockCount=0;
    static int getInt(int min,int max){
        while(true){
        Scanner x=new Scanner(System.in);
        int temp;
        try{
        temp=x.nextInt();
        } catch(InputMismatchException e){
            out.println("Invalid Input");
            continue;
        }
        if(temp>=min && temp<=max) return temp;
        else {
            out.println("Invalid Input");
        }
        }
    }
    static void addStudent(){
        int temp;
        int id;
        out.print("Enter ID of the new Student: ");
        while(true){
        boolean found=false;
        id=getInt(1,100);
        for(int i=0;i<studentCount;i++){
            if(students[i].id==id){
                out.println("This ID belongs to "+students[i].fName
                +"\nRetype ID");
                found=true;
                break;
            }
        }
        if(!found)break;
        }
        out.print("Enter student's First name: ");
        String fName=input.next();
        out.print("Enter "+fName+"'s Last name: ");
        String lName=input.next();
        Sex sex;
        out.println("is "+fName+" Male or Female?\n1.Male\n2.Female");
        temp=getInt(1,2);
        sex=(temp==1)?Sex.Male:
                (temp==2)?Sex.Female:null;
        out.println("Enter year of entrance for "+fName
                +"\n1.first Year\n2.Second Year\n3.Third Year\n4.Fourth Year\nFifth Year");
        int year=getInt(1,5);
        Department department;
        out.println("choose Department of "+fName+
                "\n1.Chemical\n2.Electrical\n3.Electro-Mechanical\n4.Software");
        temp=getInt(1,4);
        department=(temp==1)?Department.Chemical:
                (temp==2)?Department.Electrical:
                (temp==3)?Department.Electro_Mechanical:
                (temp==4)?Department.Software:null;
        students[studentCount]=new Student(id,fName,lName,sex,department,year);
        studentCount++;
        out.println("Student Added Successfully!!!");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){}
    }
    static void addStudent(int id,String fName,String lName,Sex sex,Department department,int year){
        for(int i=0;i<studentCount;i++)
            if(students[i].id==id)
               return;
        students[studentCount]=new Student(id,fName, lName, sex,department,year);
        studentCount++;
    }
    static void doPlacement(){
        for(int i=placedStudentsCount;i<studentCount;i++){ // for every student
            Block block=getBlockFor(students[i],1);
            int blockCnt=1;
            boolean hasFoundPlace=false;
            while(block!=null){
                // search appropriate floor
                Floor floor=null;
                for(int f=0;f<block.NO_OF_FLOORS;f++){
                    if(block.getFloorAt(f).year==students[i].year)
                        floor=block.getFloorAt(f);
                }
                if(floor!=null){
                    // search appropriate dorm here
                    Dorm d=findDormFor(students[i],floor);
                    if(d!=null){ // if dorm is found
                        d.addStudent(students[i]);
                        students[i].placeStudent(d);
                        hasFoundPlace=true;
                        break;
                    }
                }
                blockCnt++;
                block=getBlockFor(students[i],blockCnt);
            }
            if(!hasFoundPlace)System.out.println("we were unable to find a place for "+students[i].fName);
        }
        placedStudentsCount=studentCount;
    }
    static void addDormitory(){
        int temp;
        out.println("How many floors does the new block has?");
        temp=input.nextInt();
        int floors=temp;
        out.println("how many dorms does each floor contain?");
        temp=input.nextInt();
        int max_dorm=temp;
        out.println("what year of entrance is accepted at floor 0(ground floor)?");
        temp=input.nextInt();
        int year0=temp;
        out.println("in what way year is changed to the upper floors?"+
                "\n1.Ascending\n2.Descinding");
        temp=getInt(1,2);
        int asc=temp;
        out.println("how many students per dorm is possible in this block?");
        temp=input.nextInt();
        int max_stud=temp;
        out.println("Choose student sex for which this block belongs to:"
        +"\n1.Male\n2.Female");
        temp=getInt(1,2);
        Sex sexb=(temp==1)?Sex.Male:(temp==2)?Sex.Female:null;
        out.println("Choose department for which this block belongs to:"
        +"\n1.Chemical\n2.Electrical\n3.Electro-Mechanical\n4.Software");
        temp=getInt(1,4);
        Department departmentd=(temp==1)?Department.Chemical:
                (temp==2)?Department.Electrical:
                (temp==3)?Department.Electro_Mechanical:
                (temp==4)?Department.Software:null;
        blocks[blockCount]=new Block(sexb,floors,departmentd);
        for(int i=0;i<floors;i++){
            int yearf;
            if(asc==1)yearf=year0+i;
            else yearf=year0-i;
            Floor f=new Floor(max_dorm,sexb,departmentd,yearf,max_stud, i);
            blocks[blockCount].addFloor(f);
            f.placeFloorIn(blocks[blockCount]);
        }
        blockCount++;
        out.println("Block registered successfully!");
    }
    static void addDormitory(int floors,int dormsPerFloor,int studsPerDorm,Sex sex,Department department,int year0,int asc){
        blocks[blockCount]=new Block(sex,floors,department);
        for(int i=0;i<floors;i++){
            int yearf;
            if(asc==1)yearf=year0+i;
            else yearf=year0-i;
            Floor f=new Floor(dormsPerFloor,sex,department,yearf,studsPerDorm, i);
            blocks[blockCount].addFloor(f);
            f.placeFloorIn(blocks[blockCount]);
        }
        blockCount++;
    }
    static Block getBlockFor(Student s,int blockCnt){
        int counter=0;
        for(int startIndex=0;startIndex<blockCount;startIndex++) // find appropriate block
            if(s.sex==blocks[startIndex].sex && s.department==blocks[startIndex].department){
                counter++;
                if(counter==blockCnt)return blocks[startIndex];
            }
        return null;
    }
    static boolean setStudentAt(Student s,Dorm d){
        if(d==null)return false;
        return (d.addStudent(s));
    }
    static Dorm findDormFor(Student s,Floor f){
        if(f==null)return null;
        for(int i=0;i<f.NO_OF_DORMS;i++){ // for all the dorms there
            if(f.getDormAt(i)==null){
                f.addDorm(new Dorm(f.MAX_STUD_DORM,f.sex,f.department,f.year, f.level, i+1));
                f.getDormAt(i).placeDormIn(f);
                return f.getDormAt(i);
            }
            else if(f.getDormAt(i).getStudentCount()==f.getDormAt(i).MAX_STUD_DORM); // goto the next dorm
            else return f.getDormAt(i);
        }
        return null;
    }
    public static void admin(){
        int temp;
        boolean exit=false;
        while(!exit){
        out.println(cls+"1.Add a new Student"
                + "\n2.Show Placement Result"
                +"\n3.Add Dormitories"
                +"\n4.Go back");
        temp=getInt(1,4);
        switch(temp){
            case 1: // add a new student
                addStudent();
                break;
            case 2: // show placement result
                doPlacement();
                for(int i=0;i<studentCount;i++){
                    if(students[i].getPlacement()!=null)
                    out.println(students[i].fName+" "+students[i].lName+" is placed at block "+students[i].getPlacement().getPlacement().getPlacement().ID
                    +", "+students[i].getPlacement().getPlacement()+", "+students[i].getPlacement());
                    else out.println("Appropriate dorm for "+students[i].fName+" was not found!");
                }
                out.println("Enter any Character to continue");
                input.next();
                break;
            case 3: // modify dormitories
                out.println("our campus currently has the following registered blocks:");
                for(int i=0;i<blockCount;i++){
                    out.println(blocks[i]);
                }
                out.println("Press a to add a new block else Press ESC");
                String str=input.next();
                if(str.charAt(0)=='a' || str.charAt(0)=='A'){ // if Enter is pressed!
                    addDormitory();
                }
                break;
            case 4:
                exit=true;
                break;
            default:
                out.println("Invalid input");
        }
        }
    }
    public static void student(){
        out.println(cls+"Enter your id to see your placement");
        int id=getInt(1,100);
        // search the id among the students
        int index=-1;
        for(int i=0;i<studentCount;i++)
            if(students[i].id==id){
                index=i;
                break;
            }
        if(index==-1)out.println("You are not in the students list!!!");
        else{ // if student is found
            out.println("Your Information:\n"+students[index]);
            if(students[index].getPlacement()!=null){
            out.println("You are placed at Block "+students[index].getPlacement().getPlacement().getPlacement().ID
            +", "+students[index].getPlacement().getPlacement()+", "+students[index].getPlacement());
            out.println("We wish you a happy life in our campus!!!");
            } else {
                out.println("You are not placed yet!!!");
            }
        }
        out.println("Enter any character to continue...");
        input.next();
    }
    public static void main(String[] args) {
        // initial data
        addStudent(1,"Abebe","Alemu",Sex.Male,Department.Chemical,3);
        addStudent(2,"Aster","Tsegaye",Sex.Male,Department.Software,3);
        addStudent(3,"Kebede","Belew",Sex.Male,Department.Software,3);
        addStudent(4,"Mulugeta","Birhanu",Sex.Male,Department.Software,2);
        addStudent(11,"Abebe","Kindie",Sex.Male,Department.Software,2);
        addStudent(12,"Meles","Zenawi",Sex.Male,Department.Software,2);
        addStudent(13,"Birhanu","Nega",Sex.Male,Department.Software,2);
        addStudent(14,"Kirubel","Adamu",Sex.Male,Department.Software,2);
        addStudent(15,"Kebek","Chala",Sex.Male,Department.Software,2);
        addStudent(16,"Mengistu","Birhie",Sex.Male,Department.Software,2);
        addStudent(17,"Messi","Caffee",Sex.Male,Department.Software,2);
        addStudent(18,"Mickael","Mamaye",Sex.Male,Department.Electrical,2);
        addStudent(19,"Nati","Asela",Sex.Male,Department.Electrical,2);
        addStudent(20,"Tihitna","Tsegaye",Sex.Female,Department.Chemical,2);
        addStudent(21,"Kidan","Getu",Sex.Female,Department.Electro_Mechanical,2);
        addStudent(22,"Shega","Shegenaw",Sex.Female,Department.Software,3);
        addStudent(10,"Genet","Dinku",Sex.Female,Department.Electrical,4);
        addStudent(5,"Mengie","Hailie",Sex.Male,Department.Electro_Mechanical,4);
        addStudent(6,"Difabachew","Acid",Sex.Male,Department.Electrical,5);
        addDormitory(5,4,4,Sex.Female,Department.Chemical,1,1);
        addDormitory(5,4,4,Sex.Male,Department.Chemical,1,1);
        addDormitory(5,4,4,Sex.Male,Department.Software,1,1);
        addDormitory(5,4,4,Sex.Female,Department.Software,1,1);
        addDormitory(5,4,4,Sex.Female,Department.Electrical,1,1);
        addDormitory(5,4,4,Sex.Male,Department.Electrical,1,1);
        addDormitory(5,4,4,Sex.Female,Department.Electro_Mechanical,1,1);
        addDormitory(5,4,4,Sex.Male,Department.Electro_Mechanical,1,1);
        // end
        while(true){
        out.println(cls+"Dormitory Placement");
        out.println("1.Adminstrator Account"
                +"\n2.Student Account"
                +"\n3.Exit");
        int temp=getInt(1,3);
        switch(temp){
            case 1: // go to adminstrator's account management
                admin();
                break;
            case 2: // go to student's account management
                student();
                break;
            case 3: // just pass silently...
                break;
            default: // invalid input catch
                out.println("Invalid Input!!!");
        }
        if(temp==3)break;
        }
        System.out.println("See you soon!!!");
    }
}