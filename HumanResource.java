/**
 * @author Pham Tuan Vu
 * @email vu.pt172@gmail.com  
 */
import java.lang.StackWalker.StackFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.RunElement;

public class HumanResource {
    Scanner sc = new Scanner(System.in);
    ArrayList<Staff> staffList;
    ArrayList<Department> departments;
    CustomDateFormat myDateFormater;

    public HumanResource() {
        myDateFormater = new CustomDateFormat();
        staffList = new ArrayList<>();
        departments = new ArrayList<>();
        departments.add(new Department("Kinh Doanh"));
        departments.add(new Department("Ky Thuat"));
        departments.add(new Department("Ke Toan"));
        departments.add(new Department("Hanh Chinh"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HumanResource hm = new HumanResource();
        int option;
        do {
            option = hm.getMenuOption();
            switch (option) {
                case 0:
                    return;
                case 1:
                    hm.displayAllStaffInfo();
                    break;
                case 2:
                    hm.displayAllDepartmentInfo();
                    break;
                case 3:
                    hm.displayAllStaffInfoEachDepartment();
                    break;

                case 4:
                    hm.createNewStaff();
                    break;
                case 5:
                    hm.searchStaff();
                    break;
                case 6:
                    hm.displayAllStaffSalaryById();
                    break;
                case 7:
                    hm.displayAllStaffSalaryByAscendingOrder();
                    break;
                    case 8:
                    hm.displayAllStaffSalaryByDecendingOrder();
                    break;
                    default:
                    System.out.println("Phim chuc nang khong hop le.");
            }

            /* System.out.print("->Go phim bat ky de tiep tuc_");
            sc.nextLine(); */
            System.out.println("\n");
        } while (true);

    }

    public int getMenuOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*----------------------------------------------------------------------*");
        /* System.out.println("=========== QUAN LY NHAN VIEN ==========="); */

        System.out.printf("|#%-69s|\n", "QUAN LY NHAN VIEN");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|%-70s|\n", "0.Thoat");
        System.out.printf("|%-70s|\n", "1.Hien thi danh sach nhan vien hien co trong cong ty");
        System.out.printf("|%-70s|\n", "2.Hien thi cac bo phan trong cong ty");
        System.out.printf("|%-70s|\n", "3.Hien thi cac nhan vien theo tung bo phan");
        System.out.printf("|%-70s|\n", "4.Them nhan vien moi vao cong ty");
        System.out.printf("|%-70s|\n", "5.Tim kiem thong tin nhan vien theo ten hoac ma nhan vien");
        System.out.printf("|%-70s|\n", "6.Hien thi bang luong cua nhan vien toan cong ty");
        System.out.printf("|%-70s|\n", "7.Hien thi bang luong cua nhan vien theo thu tu tang dan");
        System.out.printf("|%-70s|\n", "8.Hien thi bang luong cua nhan vien theo thu tu giam dan");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.print("->Chon chuc nang : ");
        int option = Integer.parseInt(sc.nextLine());
        return option;
    }

    // 1
    public void displayAllStaffInfo() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "DANH SACH NHAN VIEN TRONG CONG TY");
        System.out.println("*----------------------------------------------------------------------*");
        if (this.staffList.isEmpty()) {
            System.out.printf("|%-70s|\n", "Danh sach nhan vien dang bi trong!");
            System.out.println("*----------------------------------------------------------------------*");
            return;
        }
        for (Staff s : this.staffList) {
            s.displayInformation();
        }
    }

    // 2
    public void displayAllDepartmentInfo() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "DANH SACH BO PHAN TRONG CONG TY");
        System.out.println("*----------------------------------------------------------------------*");
        for (Department d : this.departments) {
            System.out.println(d.toString());
        }
    }

    // 3
    public void displayAllStaffInfoEachDepartment() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "DANH SACH NHAN VIEN THEO TUNG BO PHAN");
        System.out.println("*----------------------------------------------------------------------*");
        for (Department d : this.departments) {
            System.out.printf("|%-70s|\n", "DEPARMENT:" + d.getName());
            System.out.println("*----------------------------------------------------------------------*");
            ArrayList<Staff> thisDepartmentStaff = this.getStaffListByDepartment(d);
            if (thisDepartmentStaff.isEmpty()) {
                System.out.printf("|%-70s|\n", "Bo phan nay hien khong co nhan vien");
                System.out.println("*----------------------------------------------------------------------*");
            }
            for (Staff s : thisDepartmentStaff) {
                s.displayInformation();
            }
        }

    }

    // 4
    public void createNewStaff() {
        Staff newStaff = null;
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "THEM NHAN VIEN");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|%-70s|\n","Chon loai nhan vien :");
        System.out.printf("|%-70s|\n","1.Nhan vien thong thuong");
        System.out.printf("|%-70s|\n","2.Nhan vien cap quan ly");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.print("->");
        int option = Integer.parseInt(sc.nextLine());
        if (option <= 0 || option > 2) {
            System.out.println("Khong co chuc nang thu " + option);
            return;
        }
        if (option == 1) {
            newStaff = createNewEmployeeByInput();

        } else if (option == 2) {
            newStaff = createNewManagerByInput();
        }

        if (this.addStaff(newStaff)) {
            System.out.println("Them nhan vien thanh cong!");
        } else
            System.out.println("Them nhan vien that bai!");

    }

    // 5
    public void searchStaff() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "TIM KIEM NHAN VIEN");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|%-70s|\n", "1.Tim kiem theo ma nhan vien");
        System.out.printf("|%-70s|\n", "2.Tim kiem theo ten nhan vien");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.print("->Chon chuc nang :");
        int option = Integer.parseInt(sc.nextLine());
        if (option <= 0 || option > 2) {
            System.out.println("Khong co chuc nang thu " + option);
            return;
        }
        if (option == 1) {
            System.out.print("->Nhap ma nhan vien : ");
            int staffId = Integer.parseInt(sc.nextLine());
            Staff foundedStaff = Staff.findStaffById(this.staffList, staffId);
            if (foundedStaff != null) {
                foundedStaff.displayInformation();
            } else {
                System.out.println("Khong tim thay nhan vien voi id = " + staffId);

            }
        } else if (option == 2) {
            System.out.print("->Nhap ten nhan vien :");
            String staffName = sc.nextLine();
            ArrayList<Staff> foundedList = Staff.findStaffByName(this.staffList, staffName);
            if (!foundedList.isEmpty()) {
                System.out.println("*----------------------------------------------------------------------*");
                for (Staff s : foundedList) {
                    s.displayInformation();
                }

            } else {
                System.out.printf("|%-70s|\n", "Khong tim thay nhan vien voi name = " + staffName);
                System.out.println("*----------------------------------------------------------------------*");
            }
        }
    }

    // 6
    public void displayAllStaffSalaryById() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "HIEN THI BANG LUONG CUA NHAN VIEN TOAN CONG TY");
        System.out.println("*----------------------------------------------------------------------*");
        this.getAllStaffSalaryInfo(this.staffList);
    }

    // 7
    public void displayAllStaffSalaryByAscendingOrder() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "HIEN THI BANG LUONG CUA NHAN VIEN TOAN CONG TY [TANG DAN]");
        System.out.println("*----------------------------------------------------------------------*");
        ArrayList<Staff> sortedList = (ArrayList<Staff>)this.staffList
                .stream().sorted(Comparator.comparing(Staff::getSalary))
                .collect(Collectors.toList());
       getAllStaffSalaryInfo(sortedList);
    }

    // 8
    public void displayAllStaffSalaryByDecendingOrder() {
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "HIEN THI BANG LUONG CUA NHAN VIEN TOAN CONG TY [GIAM DAN]");
        System.out.println("*----------------------------------------------------------------------*");
        Comparator<Staff> salaryComparator=Comparator.comparing(Staff::getSalary);
        ArrayList<Staff> sortedList=(ArrayList<Staff>)this.staffList
        .stream().sorted(salaryComparator.reversed())
        .collect(Collectors.toList());
        getAllStaffSalaryInfo(sortedList);

    }
    public void getAllStaffSalaryInfo(ArrayList<Staff> staffList){
        if (staffList.isEmpty()) {
            System.out.printf("|%-70s|\n", "Danh sach nhan vien dang bi trong!");
            System.out.println("*----------------------------------------------------------------------*");
            return;
        }
        for (Staff s : staffList) {
            s.displaySalary();
        }
    }

    public boolean addStaff(Staff staff) {
        Scanner sc = new Scanner(System.in);
        if (staff != null) {
            Department workingDepartment = staff.getWorkingDepartment();
            System.out.println("Ban co muon them <" + staff.getName() + "> vao bo phan ["
                    + workingDepartment.getName() + "] khong?(y,yes)");
            String str = sc.next();
            if (str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes")) {
                staff.setId((Staff.idCount++));
                staffList.add(staff);
                workingDepartment.updateWithNewStaff();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Staff> getStaffListByDepartment(Department d) {
        ArrayList<Staff> departmentStaffs = new ArrayList<>();
        for (Staff s : this.staffList) {
            if (s.getWorkingDepartment() == d) {
                departmentStaffs.add(s);
            }
        }
        return departmentStaffs;
    }

    public Employee createNewEmployeeByInput() {
        String name;
        Date startDate;
        int age, overTimeHours, offDays, departmentId;
        float salaryGrade;

        System.out.print("->Nhap ten nhan vien:");
        name = sc.nextLine();
        System.out.print("->Nhap tuoi :");
        age = Integer.parseInt(sc.nextLine());
        System.out.print("->Nhap he so luong :");
        salaryGrade = Float.parseFloat(sc.nextLine());
        do{
        System.out.print("->Nhap ngay vao lam :");
        startDate = this.myDateFormater.cvStringToDate(sc.nextLine());
        }
        while(startDate==null);
        System.out.print("->Nhap so gio lam them :");
        overTimeHours = Integer.parseInt(sc.nextLine());
        System.out.print("->Nhap so ngay nghi phep :");
        offDays = Integer.parseInt(sc.nextLine());
        displayAllDepartmentInfoShortcut();
        System.out.print("->Nhap id bo phan tham gia:");
        departmentId = Integer.parseInt(sc.nextLine());

        Department department = Department.findDepartmentByID(this.departments, departmentId);
        if (department == null) {
            System.out.println("Department is not existed with id = " + departmentId);
            return null;
        }
        Employee employee = new Employee(name, age, salaryGrade, startDate, offDays, overTimeHours, department);

        return employee;
    }

    public Manager createNewManagerByInput() {

        String name, position;
        Date startDate;
        int age, overTimeHours, offDays, departmentId;
        float salaryGrade;
        Scanner sc = new Scanner(System.in);
        System.out.print("->Nhap ten nhan vien:");
        name = sc.nextLine();
        System.out.print("->Nhap tuoi :");
        age = Integer.parseInt(sc.nextLine());
        System.out.print("->Nhap he so luong :");
        salaryGrade = Float.parseFloat(sc.nextLine());
        do{
            System.out.print("->Nhap ngay vao lam :");
            startDate = this.myDateFormater.cvStringToDate(sc.nextLine());
            }
            while(startDate==null);
        System.out.print("->Nhap so ngay nghi phep :");
        offDays = Integer.parseInt(sc.nextLine());
        int i;
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n","DANH SACH CHUC DANH [POSITON]");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|%-70s|\n","1.Business Leader");
        System.out.printf("|%-70s|\n","2.Project Leader");
        System.out.printf("|%-70s|\n","3.Technical Leader");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.print("->Chon chuc danh :");
        i = Integer.parseInt(sc.nextLine());
        switch (i) {
            case 1:
                position = Manager.BUSINESS_LEADER;
                break;
            case 2:
                position = Manager.PROJECT_LEADER;
                break;
            case 3:
                position = Manager.TECHNICAL_LEADER;
                break;
            default:
                return null;
        }
        displayAllDepartmentInfoShortcut();
        System.out.print("->Nhap id bo phan tham gia:");
        departmentId = Integer.parseInt(sc.nextLine());
        Department department = Department.findDepartmentByID(this.departments, departmentId);
        if (department == null) {
            System.out.println("Department khong tim thay voi id = " + departmentId);
            return null;
        }
        Manager manager = new Manager(name, age, salaryGrade, startDate, offDays, department, position);
        return manager;
    }

    public void calculateAllStaffSalary() {
        for (Staff s : this.staffList) {
            s.calculateSalary();
        }
    }
    public void displayAllDepartmentInfoShortcut(){
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|#%-69s|\n", "DANH SACH BO PHAN [DEPARTMENT]");
        System.out.println("*----------------------------------------------------------------------*");
        System.out.printf("|%-20s|%-49s|\n","ID","NAME");
        System.out.println("*----------------------------------------------------------------------*");
        for(Department d:this.departments){
            System.out.printf("|%-20d|%-49s|\n",d.getId(),d.getName());
        }
        System.out.println("*----------------------------------------------------------------------*");
    }

}
