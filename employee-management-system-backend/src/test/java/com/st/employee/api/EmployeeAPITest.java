package com.st.employee.api;

import com.st.employee.entity.Employee;
import com.st.employee.exception.ResourceNotFoundException;
import com.st.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeAPITest {

    @InjectMocks
    private EmployeeAPI employeeAPI;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        // Initialize any setup or test data if needed.
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange

        List<Employee> mockEmployees = Arrays.asList(
                new Employee("John", "Doe", "john.doe@example.com"),
                new Employee("Jane", "Smith", "jane.smith@example.com")
        );
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // Act
        List<Employee> result = employeeAPI.getAllEmployees();

        // Assert
        assertThat(result).isEqualTo(mockEmployees);
    }

    @Test
    public void testCreateEmployee() {
        // Arrange
        Employee newEmployee = new Employee("John", "Doe", "john.doe@example.com");
        when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);

        // Act
        var response = employeeAPI.createEmployee(newEmployee);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok());
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        Long id = 1L;
        Employee mockEmployee = new Employee("John", "Doe", "john.doe@example.com");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));

        // Act
        ResponseEntity<Employee> response = employeeAPI.getEmployeeById(id);

        // Assert
        assertThat(response.getBody()).isEqualTo(mockEmployee);
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        // Arrange
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeAPI.getEmployeeById(id));
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Long id = 1L;
        Employee mockEmployee = new Employee("John", "Doe", "john.doe@example.com");
        Employee updatedEmployeeDetails = new Employee("Updated", "Doe", "updated.doe@example.com");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));
        when(employeeRepository.save(mockEmployee)).thenReturn(updatedEmployeeDetails);

        // Act
        ResponseEntity<Employee> response = employeeAPI.updateEmployee(id, updatedEmployeeDetails);

        // Assert
        assertThat(response.getBody()).isEqualTo(updatedEmployeeDetails);
    }

    @Test
    public void testUpdateEmployeeNotFound() {
        // Arrange
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> employeeAPI.updateEmployee(id, new Employee()));
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        Long id = 1L;
        Employee mockEmployee = new Employee("John", "Doe", "john.doe@example.com");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));

        // Act
        ResponseEntity<Map<String, Boolean>> response = employeeAPI.deleteEmployee(id);

        // Assert
        assertThat(response.getBody().get("deleted")).isEqualTo(true);
    }

}