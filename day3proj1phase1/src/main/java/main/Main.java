package main;

import java.util.ArrayList;
import models.*;
import interfaces.Taxable;

public class Main {
    public static void main(String[] args){
        ArrayList<Employee> employeeList = new ArrayList<>(); 
        
        //Full Time Employee Creation
        Employee fullTimeEmployee1 = new FullTimeEmployee("SR",25000D);
        employeeList.add(fullTimeEmployee1);
        Employee fullTimeEmployee2 = new FullTimeEmployee("S",134000D);
        employeeList.add(fullTimeEmployee2);
        Employee fullTimeEmployee3 = new FullTimeEmployee("R",309000D);
        employeeList.add(fullTimeEmployee3);
        Employee fullTimeEmployee4 = new FullTimeEmployee("Sid",120090D);
        employeeList.add(fullTimeEmployee4);
        Employee fullTimeEmployee5 = new FullTimeEmployee("Senthil",9990D);
        employeeList.add(fullTimeEmployee5);

        //Part Time Employee Creation
        Employee partTimeEmployee1 = new PartTimeEmployee("Jolly", 143, 9);
        employeeList.add(partTimeEmployee1);
        Employee partTimeEmployee2 = new PartTimeEmployee("John", 142, 3);
        employeeList.add(partTimeEmployee2);
        Employee partTimeEmployee3 = new PartTimeEmployee("Doe", 130, 31);
        employeeList.add(partTimeEmployee3);
        Employee partTimeEmployee4 = new PartTimeEmployee("Kumar", 103, 69);
        employeeList.add(partTimeEmployee4);
        Employee partTimeEmployee5 = new PartTimeEmployee("Jai", 993, 30);
        employeeList.add(partTimeEmployee5);
        
        for(Employee employee: employeeList){
            // converting Employee to TaxableEmployee to calculate tax
            Taxable taxableEmployee = (Taxable) employee;
            System.out.println("Tax Amount for Employee "+employee.getName()+" is "+taxableEmployee.calculateTax());
        }

    }
}
