
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Staff implements ICaculator{
    private int id;
    private String name;
    private int age;
    private float salaryGrade;
    private Date startDate;
    private Department workingDepartment;
    private int offDays;
    public int salary;
    public static int idCount = 1;
    
   
    abstract void displayInformation();
 
    abstract void displaySalary();
    public Staff() {
    }

    public Staff(String name, int age, float salaryGrade, Date startDate, int offDays, Department department) {
        this.name = name;
        this.age = age;
        this.salaryGrade = salaryGrade;
        this.startDate=startDate;
        this.offDays = offDays;
        this.workingDepartment = department;
    }
   /**
    * 
    * @param staffs lists of staff to retrieve
    * @param id id which want to find
    * @return object Staff with given id |null if not found
    */
    public static Staff findStaffById(ArrayList<Staff> staffs, int id) {
        for (Staff s : staffs) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }
   /**
    * 
    * @param staffs list of staff to retrieve
    * @param name name which want to find
    * @return arraylist of Staff that have same name | empty list if not found
    */
    public static ArrayList<Staff> findStaffByName(ArrayList<Staff> staffs, String name) {
        ArrayList<Staff> foundedStaffs = new ArrayList<>();
        for (Staff s : staffs) {
            if (s.getName().equalsIgnoreCase(name))
                foundedStaffs.add(s);
        }
        return foundedStaffs;
    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return Date return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return Department return the workingDepartment
     */
    public Department getWorkingDepartment() {
        return workingDepartment;
    }

    /**
     * @param workingDepartment the workingDepartment to set
     */
    public void setWorkingDepartment(Department workingDepartment) {
        this.workingDepartment = workingDepartment;
    }

    /**
     * @return float return the salaryGrade
     */
    public float getSalaryGrade() {
        return salaryGrade;
    }

    /**
     * @param salaryGrade the salaryGrade to set
     */
    public void setSalaryGrade(float salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    /**
     * @return int return the offDays
     */
    public int getOffDays() {
        return offDays;
    }

    /**
     * @param offDays the offDays to set
     */
    public void setOffDays(int offDays) {
        this.offDays = offDays;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}