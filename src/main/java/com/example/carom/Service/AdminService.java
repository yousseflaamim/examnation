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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private RenterRepository renterRepository;
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public String login(String username, String password) {
        // Retrieve admin by username from the database
        Admin admin = adminRepository.findByUsername(username);

        if (admin != null) {
            // Check if input password matches the hashed password stored in the database
            boolean passwordMatch = passwordEncoder.matches(password, admin.getPassword());

            if (passwordMatch) {
                // If username and password match, generate and return a JWT token
                return jwtTokenUtil.generateToken(username);
            }
        }

        // If authentication fails, throw UsernameNotFoundException
        throw new UsernameNotFoundException("Invalid username or password");
    }
    public boolean registerAdmin(String username, String password) {
        // Check if username is already taken
        if (adminRepository.existsByUsername(username)) {
            return false; // Username is already taken
        }

        // Create a new Admin object
        Admin admin = new Admin();
        admin.setUsername(username);

        // Encode the password and set it for the admin
        String encodedPassword = passwordEncoder.encode(password);
        admin.setPassword(encodedPassword);

        // Save the admin in the database
        adminRepository.save(admin);

        return true; // Admin registered successfully
    }


    public List<Car> getAllCars() {
        return carRepository.findAll(); // Assuming you have a CarRepository
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll(); // Assuming you have an OwnerRepository
    }

    public List<Renter> getAllRenters() {
        return renterRepository.findAll(); // Assuming you have a RenterRepository
    }

    public Car updateCarById(Long id, Car car) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        // Update existingCar fields with values from the 'car' parameter
        existingCar.setCarType(car.getCarType());
        //  existingCar.setCarImage(car.getCarImage());
        existingCar.setLocation(car.getLocation());
        existingCar.setRentPrice(car.getRentPrice());
        // Update other fields as needed...

        // Save and return the updated car
        return carRepository.save(existingCar);
    }

    public Owner updateOwnerById(Long id, Owner owner) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));

        // Update existingOwner fields with values from the 'owner' parameter
        existingOwner.setUsername(owner.getUsername());
        existingOwner.setPassword(owner.getPassword());
        existingOwner.setOwnerBankAccountId(owner.getOwnerBankAccountId());
        // Update other fields as needed...

        // Save and return the updated owner
        return ownerRepository.save(existingOwner);
    }

    public Renter updateRenterById(Long id, Renter renter) {
        Renter existingRenter = renterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Renter not found"));

        // Update existingRenter fields with values from the 'renter' parameter
        existingRenter.setUsername(renter.getUsername());
        existingRenter.setPassword(renter.getPassword());
        //existingRenter.setIdentityImage(renter.getIdentityImage());
        existingRenter.setRenterBankAccountId(renter.getRenterBankAccountId());
        //  existingRenter.setProfileImage(renter.getProfileImage());
        // Update other fields as needed...

        // Save and return the updated renter
        return renterRepository.save(existingRenter);
    }

    public boolean addNewAdmin(Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            return false; // Admin username already exists
        }
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminRepository.save(admin);
        return true; // Admin added successfully
    }

    public boolean deleteAdminById(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true; // Admin deleted successfully
        }
        return false; // Admin not found with the given ID
    }
}
