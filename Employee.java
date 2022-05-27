
import java.util.Date;
import java.util.Scanner;

public class Employee extends Staff { 
     private int overTimeHours;
     public static final int EMPLOYEE_BASE_SALARY = 3000000;
     public Employee(){
         super();
     }
     public Employee(String name,int age,float salaryGrade,Date startDate,int offDays,int overTimeHours,Department department){
       super(name,age,salaryGrade,startDate,offDays,department);
       this.overTimeHours=overTimeHours;
       this.salary=calculateSalary();
     }
    @Override
    void displayInformation() {
        CustomDateFormat myDateFormat=new CustomDateFormat();
        // TODO Auto-generated method stub
        System.out.printf("|%-20s:%-49s|\n","Chuc vu","Employee");
        System.out.printf("|%-20s:%-49d|\n","Ma nhan vien",this.getId());
        System.out.printf("|%-20s:%-49s|\n","Ten nhan vien",this.getName());
        System.out.printf("|%-20s:%-49d|\n","Tuoi :",this.getAge());
        System.out.printf("|%-20s:%-49s|\n","Ngay bat dau lam",myDateFormat.cvDateToString(this.getStartDate()));
        System.out.printf("|%-20s:%-49s|\n","Bo phan lam viec",this.getWorkingDepartment().getName());
        System.out.printf("|%-20s:%-49d|\n","So ngay nghi phep",this.getOffDays());
        System.out.printf("|%-20s:%-49d|\n","So gio lam them",this.getOverTimeHours());
        System.out.println("|----------------------------------------------------------------------|");

    }
    @Override
    public int calculateSalary() {
        // TODO Auto-generated method stub
    return (int)(this.getSalaryGrade()*EMPLOYEE_BASE_SALARY)+this.getOverTimeHours()*200000;
    }
    @Override
    void displaySalary() {
        // TODO Auto-generated method stub
        System.out.printf("|%-20s:%-49s|\n","Chuc vu","Employee");
        System.out.printf("|%-20s:%-49d|\n","Ma nhan vien",this.getId());
        System.out.printf("|%-20s:%-49s|\n","Ten nhan vien",this.getName());
        System.out.printf("|%-20s:%-49.2f|\n","He so luong",this.getSalaryGrade());
        System.out.printf("|%-20s:%-49d|\n","So gio lam them",this.getOverTimeHours());
        System.out.printf("|%-20s:%-49d|\n","Tien luong",this.getSalary());
        System.out.println("|----------------------------------------------------------------------|");
    }

    /**
     * @return int return the overTimeHours
     */
    public int getOverTimeHours() {
        return overTimeHours;
    }

    /**
     * @param overTimeHours the overTimeHours to set
     */
    public void setOverTimeHours(int overTimeHours) {
        this.overTimeHours = overTimeHours;
    }
   
    
}
