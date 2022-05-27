import java.util.ArrayList;

public class Department {
    private int id;
    private String name;
    private int numberOfStaff;
    public static int idCount = 101;

    public Department() {
        this.id = Department.idCount++;
    }

    public Department(String name) {
        this.id = Department.idCount++;
        this.name = name;

    }

    public String toString() {
        String departmentInfo = "";
        departmentInfo += String.format("|%-20s:%-49s|\n", "Ma Bo Phan", this.getId());
        departmentInfo += String.format("|%-20s:%-49s|\n", "Ten Bo Phan", this.getName());
        departmentInfo += String.format("|%-20s:%-49s|\n", "So Luong Nhan Vien", this.getNumberOfStaff());
        departmentInfo += "|----------------------------------------------------------------------|";
        return departmentInfo;
    }
    public void updateWithNewStaff(){
        this.numberOfStaff++;
    }
    public static Department findDepartmentByID(ArrayList<Department> departments,int id) {
        for (Department d : departments) {
            if (d.getId() == id)
                return d;
        }
        return null;
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
     * @return int return the numberOfStaff
     */
    public int getNumberOfStaff() {
        return numberOfStaff;
    }

    /**
     * @param numberOfStaff the numberOfStaff to set
     */
    public void setNumberOfStaff(int numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

}
