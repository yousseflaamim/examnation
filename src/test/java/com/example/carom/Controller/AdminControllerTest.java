package com.example.carom.Controller;



import com.example.carom.Entity.Admin;
import com.example.carom.Entity.Car;
import com.example.carom.Entity.Owner;
import com.example.carom.Entity.Renter;
import com.example.carom.Service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin_Success() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password");

        when(adminService.login(anyString(), anyString())).thenReturn("token");

        ResponseEntity<String> response = adminController.login(credentials);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("token", response.getBody());
    }

    @Test
    void testLogin_Failure() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password");

        when(adminService.login(anyString(), anyString())).thenReturn(null);

        ResponseEntity<String> response = adminController.login(credentials);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody());
    }
    @Test
    void testAddNewAdmin_Success() {
        Admin admin = new Admin();
        when(adminService.addNewAdmin(any(Admin.class))).thenReturn(true);

        ResponseEntity<?> response = adminController.addNewAdmin(admin);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Admin registered successfully", response.getBody());
    }

    @Test
    void testAddNewAdmin_Failure() {
        Admin admin = new Admin();
        when(adminService.addNewAdmin(any(Admin.class))).thenReturn(false);

        ResponseEntity<?> response = adminController.addNewAdmin(admin);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Admin username already exists", response.getBody());
    }

    @Test
    void testGetAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        cars.add(new Car());
        when(adminService.getAllCars()).thenReturn(cars);

        ResponseEntity<List<Car>> response = adminController.getAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void testGetAllOwners() {
        List<Owner> owners = new ArrayList<>();
        owners.add(new Owner());
        owners.add(new Owner());
        when(adminService.getAllOwners()).thenReturn(owners);

        ResponseEntity<List<Owner>> response = adminController.getAllOwners();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(owners, response.getBody());
    }

    @Test
    void testGetAllRenters() {
        List<Renter> renters = new ArrayList<>();
        renters.add(new Renter());
        renters.add(new Renter());
        when(adminService.getAllRenters()).thenReturn(renters);

        ResponseEntity<List<Renter>> response = adminController.getAllRenters();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(renters, response.getBody());
    }
    // Add more test cases for other controller methods as needed
}
