package com.thetuxwhocodes.javaee7.jaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

public class EmployeeMarshaller 
{

    public static void main(String[] args) throws JAXBException, FileNotFoundException 
    {
        
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Sachin");
        employee.setNickNames(Arrays.asList(new String[]{"Tendlya", "Little Master"}));
        
        JAXBContext jAXBContext = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = jAXBContext.createMarshaller();
        marshaller.marshal(employee, System.out);
        
        System.out.println();
        
        Path path = FileSystems.getDefault().getPath("src/com/thetuxwhocodes/javaee7/jaxb", "Output.xml");
        File empFile = path.toFile();
        marshaller.marshal(employee, empFile);
        
        Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
        Employee employee2 = (Employee) unmarshaller.unmarshal(new FileReader(FileSystems.getDefault().getPath("src/com/thetuxwhocodes/javaee7/jaxb", "Input.xml").toFile()));
        
        System.out.println();
        System.out.print(employee2);
    }
}

@XmlRootElement
class Employee
{
    String name;
    Long id;
    List<String> nickNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElementWrapper(name="nickNames")
    public List<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(List<String> nickNames) {
        this.nickNames = nickNames;
    }

    @Override
    public String toString() {
        return "Employee ==> {" + "name=" + name + ", id=" + id + ", nickNames=" + nickNames + '}';
    }
}
