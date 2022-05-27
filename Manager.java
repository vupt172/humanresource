import java.util.Date;

public class Manager extends Staff {
     public static final String BUSINESS_LEADER="Business Leader";
     public static final String PROJECT_LEADER="Project Leader";
     public static final String TECHNICAL_LEADER="Technical Leader";
     
    private String position;
     public static final int MANAGER_BASE_SALARY = 5000000;
     public Manager(){
        super();
     }
     public Manager(String name, int age, float salaryGrade, Date startDate, int offDays, Department department,String position) {
        super(name, age, salaryGrade, startDate, offDays, department);
        this.position=position;
        this.salary=this.calculateSalary();
        //TODO Auto-generated constructor stub
    }
    @Override
    void displayInformation() {
        CustomDateFormat myDateFormat=new CustomDateFormat();
        // TODO Auto-generated method stub
        System.out.printf("|%-20s:%-49s|\n","Chuc vu","Manager");
        System.out.printf("|%-20s:%-49d|\n","Ma nhan vien",this.getId());
        System.out.printf("|%-20s:%-49s|\n","Ten nhan vien",this.getName());
        System.out.printf("|%-20s:%-49d|\n","Tuoi :",this.getAge());
        System.out.printf("|%-20s:%-49s|\n","Ngay bat dau lam",myDateFormat.cvDateToString(this.getStartDate()));
        System.out.printf("|%-20s:%-49s|\n","Bo phan lam viec",this.getWorkingDepartment().getName());
        System.out.printf("|%-20s:%-49d|\n","So ngay nghi phep",this.getOffDays());
        System.out.printf("|%-20s:%-49s|\n","Chuc danh",this.getPosition());
        System.out.println("|----------------------------------------------------------------------|");
    }

    @Override
    public int calculateSalary() {
        // TODO Auto-generated method stub
        int roleSalary=0;
        switch(this.getPosition()){
            case "Business Leader":
            roleSalary=8000000;
            break;
            case "Project Leader":
            roleSalary=5000000;
            break;
            case "Technical Leader":
            roleSalary=6000000;
            break;
            default:
            System.out.println("Luong cua manager nay khong co san");
        }
        return (int)(this.getSalaryGrade()*MANAGER_BASE_SALARY+roleSalary);
    }
    
    

    /**
     * @return String return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }
    @Override
    void displaySalary() {
        // TODO Auto-generated method stub
        System.out.printf("|%-20s:%-49s|\n","Chuc vu","Manager");
        System.out.printf("|%-20s:%-49d|\n","Ma nhan vien",this.getId());
        System.out.printf("|%-20s:%-49s|\n","Ten nhan vien",this.getName());
        System.out.printf("|%-20s:%-49s|\n","Chuc danh",this.getPosition());
        System.out.printf("|%-20s:%-49.2f|\n","He so luong",this.getSalaryGrade());
        System.out.printf("|%-20s:%-49d|\n","Tien luong",this.calculateSalary());
        System.out.println("|----------------------------------------------------------------------|");
    }
}
