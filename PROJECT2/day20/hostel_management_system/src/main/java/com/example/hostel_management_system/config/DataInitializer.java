package com.example.hostel_management_system.config;

import com.example.hostel_management_system.domain.Hostel;
import com.example.hostel_management_system.domain.Room;
import com.example.hostel_management_system.domain.Staff;
import com.example.hostel_management_system.domain.Student;
import com.example.hostel_management_system.repository.HostelRepository;
import com.example.hostel_management_system.repository.RoomRepository;
import com.example.hostel_management_system.repository.StaffRepository;
import com.example.hostel_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private HostelRepository hostelRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Create sample hostels
        Hostel boysHostel = new Hostel("Boys Hostel", "15/2, MG Road, Koramangala, Bangalore, Karnataka - 560034", Hostel.HostelType.BOYS);
        boysHostel.setEmail("contact@boyshostel.in");
        hostelRepository.save(boysHostel);
        
        Hostel girlsHostel = new Hostel("Girls Hostel", "23/A, Brigade Road, Shivaji Nagar, Bangalore, Karnataka - 560001", Hostel.HostelType.GIRLS);
        girlsHostel.setEmail("contact@girlshostel.in");
        hostelRepository.save(girlsHostel);
        
        // Create sample rooms for Boys Hostel
        Room boysRoom101 = new Room("B101", Room.RoomType.SINGLE, 1, 8000.0);
        boysRoom101.setHostel(boysHostel);
        roomRepository.save(boysRoom101);
        
        Room boysRoom102 = new Room("B102", Room.RoomType.DOUBLE, 2, 6000.0);
        boysRoom102.setHostel(boysHostel);
        roomRepository.save(boysRoom102);
        
        Room boysRoom103 = new Room("B103", Room.RoomType.SINGLE, 1, 8000.0);
        boysRoom103.setHostel(boysHostel);
        roomRepository.save(boysRoom103);
        
        // Create sample rooms for Girls Hostel
        Room girlsRoom201 = new Room("G201", Room.RoomType.SINGLE, 1, 9000.0);
        girlsRoom201.setHostel(girlsHostel);
        roomRepository.save(girlsRoom201);
        
        Room girlsRoom202 = new Room("G202", Room.RoomType.DOUBLE, 2, 7000.0);
        girlsRoom202.setHostel(girlsHostel);
        roomRepository.save(girlsRoom202);
        
        // Create sample staff for Boys Hostel
        Staff boysWarden = new Staff("Soundar Raja", "soundar.warden@boyshostel.in", Staff.StaffRole.WARDEN);
        boysWarden.setAddress("Staff Quarter A-1, Boys Hostel Campus, Koramangala, Bangalore - 560034");
        staffRepository.save(boysWarden);
        
        Staff boysAssistant = new Staff("Lokesh Raj", "lokesh.assistant@boyshostel.in", Staff.StaffRole.ASSISTANT_WARDEN);
        boysAssistant.setAddress("Staff Quarter A-2, Boys Hostel Campus, Koramangala, Bangalore - 560034");
        staffRepository.save(boysAssistant);
        
        // Create sample staff for Girls Hostel
        Staff girlsWarden = new Staff("Senthil Kumari", "senthil.warden@girlshostel.in", Staff.StaffRole.WARDEN);
        girlsWarden.setAddress("Staff Quarter B-1, Girls Hostel Campus, Shivaji Nagar, Bangalore - 560001");
        staffRepository.save(girlsWarden);
        
        Staff girlsAssistant = new Staff("Nithish Priya", "nithish.assistant@girlshostel.in", Staff.StaffRole.ASSISTANT_WARDEN);
        girlsAssistant.setAddress("Staff Quarter B-2, Girls Hostel Campus, Shivaji Nagar, Bangalore - 560001");
        staffRepository.save(girlsAssistant);
        
        // Create sample boys students under Boys Hostel staff
        Student boyStudent1 = new Student("Sidd Arun", "sidd.arun@student.vtu.ac.in");
        boyStudent1.setAddress("H.No. 45/2, 2nd Cross, Jayanagar 4th Block, Bangalore, Karnataka - 560011");
        boyStudent1.setStaff(boysWarden);
        studentRepository.save(boyStudent1);
        
        Student boyStudent2 = new Student("Nithish Raj", "nithish.raj@student.vtu.ac.in");
        boyStudent2.setAddress("#234, 8th Main, Indiranagar, Bangalore, Karnataka - 560038");
        boyStudent2.setStaff(boysWarden);
        studentRepository.save(boyStudent2);
        
        Student boyStudent3 = new Student("Lokesh Vikram", "lokesh.vikram@student.vtu.ac.in");
        boyStudent3.setAddress("Plot No. 78, 5th Cross, BTM Layout, Bangalore, Karnataka - 560076");
        boyStudent3.setStaff(boysAssistant);
        studentRepository.save(boyStudent3);
        
        // Create sample girls students under Girls Hostel staff
        Student girlStudent1 = new Student("Priya", "priya@student.vtu.ac.in");
        girlStudent1.setAddress("Plot No. 12, Sector 15, HSR Layout, Bangalore, Karnataka - 560102");
        girlStudent1.setStaff(girlsWarden);
        studentRepository.save(girlStudent1);
        
        Student girlStudent2 = new Student("Sneha", "sneha@student.vtu.ac.in");
        girlStudent2.setAddress("#456, 3rd Main, Malleswaram, Bangalore, Karnataka - 560003");
        girlStudent2.setStaff(girlsWarden);
        studentRepository.save(girlStudent2);
        
        Student girlStudent3 = new Student("Kavya", "kavya@student.vtu.ac.in");
        girlStudent3.setAddress("House No. 89, 7th Cross, Rajajinagar, Bangalore, Karnataka - 560010");
        girlStudent3.setStaff(girlsAssistant);
        studentRepository.save(girlStudent3);
    }
}