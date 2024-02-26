package com.example.carom.Service;



import com.example.carom.Entity.Admin;
import com.example.carom.Entity.Car;
import com.example.carom.Entity.Owner;
import com.example.carom.Entity.Renter;
import com.example.carom.JWT.JwtTokenUtil;
import com.example.carom.Repository.AdminRepository;
import com.example.carom.Repository.CarRepository;
import com.example.carom.Repository.OwnerRepository;
import com.example.carom.Repository.RenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private RenterRepository renterRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ValidCredentials_ReturnsToken() {
        // Arrange
        String username = "admin";
        String password = "password";
        String encodedPassword = "encodedPassword";
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(encodedPassword);
        when(adminRepository.findByUsername(username)).thenReturn(admin);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(jwtTokenUtil.generateToken(username)).thenReturn("token");

        // Act
        String token = adminService.login(username, password);

        // Assert
        assertNotNull(token);
    }


    @Test
    void getAllCars_ReturnsListOfCars() {
        // Arrange
        List<Car> cars = Arrays.asList(new Car(), new Car());
        when(carRepository.findAll()).thenReturn(cars);

        // Act
        List<Car> result = adminService.getAllCars();

        // Assert
        assertEquals(cars.size(), result.size());
    }

    @Test
    void getAllOwners_ReturnsListOfOwners() {
        // Arrange
        List<Owner> owners = Arrays.asList(new Owner(), new Owner());
        when(ownerRepository.findAll()).thenReturn(owners);

        // Act
        List<Owner> result = adminService.getAllOwners();

        // Assert
        assertEquals(owners.size(), result.size());
    }

    @Test
    void getAllRenters_ReturnsListOfRenters() {
        // Arrange
        List<Renter> renters = Arrays.asList(new Renter(), new Renter());
        when(renterRepository.findAll()).thenReturn(renters);

        // Act
        List<Renter> result = adminService.getAllRenters();

        // Assert
        assertEquals(renters.size(), result.size());
    }

    @Test
    void updateCarById_ExistingCar_ReturnsUpdatedCar() {
        // Arrange
        Long id = 1L;
        Car existingCar = new Car();
        existingCar.setId(id);
        Car updatedCar = new Car();
        updatedCar.setId(id);
        when(carRepository.findById(id)).thenReturn(Optional.of(existingCar));
        when(carRepository.save(existingCar)).thenReturn(updatedCar);

        // Act
        Car result = adminService.updateCarById(id, updatedCar);

        // Assert
        assertEquals(updatedCar.getId(), result.getId());
    }

    @Test
    void updateOwnerById_ExistingOwner_ReturnsUpdatedOwner() {
        // Arrange
        Long id = 1L;
        Owner existingOwner = new Owner();
        existingOwner.setId(id);
        Owner updatedOwner = new Owner();
        updatedOwner.setId(id);
        when(ownerRepository.findById(id)).thenReturn(Optional.of(existingOwner));
        when(ownerRepository.save(existingOwner)).thenReturn(updatedOwner);

        // Act
        Owner result = adminService.updateOwnerById(id, updatedOwner);

        // Assert
        assertEquals(updatedOwner.getId(), result.getId());
    }

    @Test
    void updateRenterById_ExistingRenter_ReturnsUpdatedRenter() {
        // Arrange
        Long id = 1L;
        Renter existingRenter = new Renter();
        existingRenter.setId(id);
        Renter updatedRenter = new Renter();
        updatedRenter.setId(id);
        when(renterRepository.findById(id)).thenReturn(Optional.of(existingRenter));
        when(renterRepository.save(existingRenter)).thenReturn(updatedRenter);

        // Act
        Renter result = adminService.updateRenterById(id, updatedRenter);

        // Assert
        assertEquals(updatedRenter.getId(), result.getId());
    }

    @Test
    void addNewAdmin_NonExistingUsername_ReturnsTrue() {
        // Arrange
        Admin admin = new Admin();
        admin.setUsername("newAdmin");
        when(adminRepository.existsByUsername("newAdmin")).thenReturn(false);

        // Act
        boolean result = adminService.addNewAdmin(admin);

        // Assert
        assertTrue(result);
    }

    @Test
    void deleteAdminById_ExistingAdmin_ReturnsTrue() {
        // Arrange
        Long id = 1L;
        when(adminRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = adminService.deleteAdminById(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void deleteAdminById_NonExistingAdmin_ReturnsFalse() {
        // Arrange
        Long id = 1L;
        when(adminRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = adminService.deleteAdminById(id);

        // Assert
        assertFalse(result);
    }
}
