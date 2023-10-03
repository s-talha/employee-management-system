# Employee Management API Documentation

The Employee Management API provides endpoints for managing employee data.

## Get All Employees

- **Endpoint:** `/api/vi/employees/employees`
- **HTTP Method:** GET
- **Description:** Get a list of all employees in the system.

## Create Employee

- **Endpoint:** `/api/vi/employees/employees`
- **HTTP Method:** POST
- **Description:** Create a new employee in the system.

### Request Body

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "emailId": "john.doe@example.com"
}
```

## Get Employee by ID

- **Endpoint:** `/api/vi/employees/employees/{id}`
- **HTTP Method:** GET
- **Description:** Get information about a specific employee by their ID.

### Path Parameters

- `id` (Long): The ID of the employee to retrieve.

## Update Employee by ID

- **Endpoint:** `/api/vi/employees/employees/{id}`
- **HTTP Method:** PUT
- **Description:** Update the information of an employee by their ID.

### Path Parameters

- `id` (Long): The ID of the employee to update.

### Request Body

```json
{
  "firstName": "Updated First Name",
  "lastName": "Updated Last Name",
  "emailId": "updated.email@example.com"
}
```

## Delete Employee by ID

- **Endpoint:** `/api/vi/employees/employees/{id}`
- **HTTP Method:** DELETE
- **Description:** Delete an employee from the system by their ID.

### Path Parameters

- `id` (Long): The ID of the employee to delete.
