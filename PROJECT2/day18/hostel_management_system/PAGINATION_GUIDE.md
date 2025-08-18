# 📄 Pagination & Sorting Implementation Guide

This document outlines the implementation of all 7 pagination and sorting tasks in the Hostel Management System.

## 🎯 Implemented Tasks

### Task 1: Multiple Sort Fields ✅

**Implementation:** Controllers now support multiple sort parameters using `@PageableDefault` and Spring's built-in multi-field sorting.

**Example Usage:**
```bash
# Sort by name ascending, then by email descending
GET /api/students?page=0&size=5&sort=name,asc&sort=email,desc

# Sort by room number and price
GET /api/rooms?page=0&size=10&sort=roomNumber,asc&sort=pricePerMonth,desc

# Sort by staff role and name
GET /api/staff?page=0&size=10&sort=role,asc&sort=name,asc
```

**Code Implementation:**
```java
@GetMapping
public ResponseEntity<Page<StudentResponseDto>> getAllStudents(
        @PageableDefault(size = 5, sort = {"name", "email"}) Pageable pageable) {
    return ResponseEntity.ok(studentService.getAllStudents(pageable));
}
```

---

### Task 2: Default Paging & Sorting ✅

**Implementation:** 
- Global defaults set in `application.properties`
- Method-level defaults using `@PageableDefault`

**Configuration:**
```properties
# Global pagination defaults
spring.data.web.pageable.default-page-size=10
spring.data.web.pageable.page-parameter=page
spring.data.web.pageable.size-parameter=size
```

**Example Usage:**
```bash
# Uses default page size of 10 and sorts by name
GET /api/students

# Uses method-specific defaults
GET /api/demo/students/default-paging
```

**Code Implementation:**
```java
@GetMapping("/students/default-paging")
public ResponseEntity<Page<StudentResponseDto>> getStudentsDefaultPaging(
        @PageableDefault(size = 3, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(studentService.getAllStudents(pageable));
}
```

---

### Task 3: Search with Paging ✅

**Implementation:** All filter endpoints now support pagination, applying filters first then paginating results.

**Example Usage:**
```bash
# Filter by name with pagination
GET /api/students/filter/name/John?page=0&size=3&sort=name,asc

# Advanced filtering with pagination
GET /api/students/filter?name=John&email=gmail&page=0&size=5&sort=name,desc

# Filter rooms by price range with pagination
GET /api/rooms/filter/price-range?minPrice=100&maxPrice=500&page=0&size=10&sort=pricePerMonth,asc
```

**Code Implementation:**
```java
@GetMapping("/filter")
public ResponseEntity<Page<StudentResponseDto>> filterStudents(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String address,
        @RequestParam(required = false) Long staffId,
        @PageableDefault(size = 10, sort = "name") Pageable pageable) {
    return ResponseEntity.ok(studentService.filterStudents(name, email, address, staffId, pageable));
}
```

---

### Task 4: Return Only Paged Metadata ✅

**Implementation:** New endpoint that returns only pagination metadata without actual content.

**Example Usage:**
```bash
# Get only pagination metadata
GET /api/students/metadata?page=0&size=10
GET /api/demo/students/metadata-only?page=1&size=5
```

**Response Example:**
```json
{
  "totalElements": 50,
  "totalPages": 5,
  "currentPage": 0,
  "pageSize": 10,
  "hasNext": true,
  "hasPrevious": false,
  "isFirst": true,
  "isLast": false
}
```

**Code Implementation:**
```java
public Map<String, Object> getStudentsMetadata(Pageable pageable) {
    Page<Student> page = studentRepository.findAll(pageable);
    Map<String, Object> metadata = new HashMap<>();
    metadata.put("totalElements", page.getTotalElements());
    metadata.put("totalPages", page.getTotalPages());
    metadata.put("currentPage", page.getNumber());
    metadata.put("pageSize", page.getSize());
    metadata.put("hasNext", page.hasNext());
    metadata.put("hasPrevious", page.hasPrevious());
    metadata.put("isFirst", page.isFirst());
    metadata.put("isLast", page.isLast());
    return metadata;
}
```

---

### Task 5: Customize Sort Null Handling ✅

**Implementation:** Custom sorting with explicit null handling using `nullsLast()` and `nullsFirst()`.

**Example Usage:**
```bash
# Custom null handling endpoint
GET /api/students/sorted-nulls-last?page=0&size=10
GET /api/demo/students/null-handling
GET /api/demo/students/advanced-sort
```

**Code Implementation:**
```java
public Page<StudentResponseDto> getStudentsSortedNullsLast(Pageable pageable) {
    Sort sort = Sort.by(
        Sort.Order.asc("name").nullsLast(), 
        Sort.Order.desc("email").nullsFirst()
    );
    Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    return studentRepository.findAll(customPageable).map(studentMapper::toResponseDto);
}
```

---

### Task 6: Change Default Page Index ✅

**Implementation:** Configured to use 1-based page indexing instead of 0-based.

**Configuration:**
```properties
# Enable 1-based page indexing
spring.data.web.pageable.one-indexed-parameters=true
```

**Example Usage:**
```bash
# Now page=1 fetches the first page (instead of page=0)
GET /api/students?page=1&size=10
GET /api/rooms?page=1&size=5
GET /api/staff?page=1&size=8
```

**Behavior:**
- `page=1` → First page
- `page=2` → Second page
- More user-friendly for frontend applications

---

### Task 7: Performance Check ✅

**Implementation:** 
- Added `Slice` support for better performance
- Database indexes recommended for frequently sorted columns
- Optimized queries using repository methods instead of stream filtering

**Example Usage:**
```bash
# Using Slice for better performance (no total count calculation)
GET /api/students/slice?page=0&size=10&sort=name,asc
GET /api/demo/students/performance-slice
```

**Performance Optimizations:**
```java
// Using Slice instead of Page (avoids COUNT query)
@Query("SELECT s FROM Student s")
Slice<Student> findAllSlice(Pageable pageable);

// Repository-level filtering instead of stream filtering
Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
Page<Room> findByPricePerMonthBetween(Double minPrice, Double maxPrice, Pageable pageable);
```

**Recommended Database Indexes:**
```sql
-- Add these indexes for better performance
CREATE INDEX idx_student_name ON student(name);
CREATE INDEX idx_student_email ON student(email);
CREATE INDEX idx_room_price ON room(price_per_month);
CREATE INDEX idx_room_number ON room(room_number);
CREATE INDEX idx_staff_role ON staff(role);
```

---

## 🚀 API Endpoints Summary

### Student Endpoints with Pagination
```bash
GET /api/students                           # All students with pagination
GET /api/students/filter/name/{name}        # Filter by name with pagination
GET /api/students/filter/email-domain/{domain} # Filter by email domain with pagination
GET /api/students/filter/city/{city}        # Filter by city with pagination
GET /api/students/filter                    # Advanced filtering with pagination
GET /api/students/filter/without-staff      # Students without staff with pagination
GET /api/students/metadata                  # Pagination metadata only
GET /api/students/sorted-nulls-last         # Custom null handling
GET /api/students/slice                     # Performance optimized slice
```

### Room Endpoints with Pagination
```bash
GET /api/rooms                              # All rooms with pagination
GET /api/rooms/available                    # Available rooms with pagination
GET /api/rooms/hostel/{hostelId}           # Rooms by hostel with pagination
GET /api/rooms/filter/price-range          # Price range filter with pagination
GET /api/rooms/filter/capacity/{capacity}  # Capacity filter with pagination
GET /api/rooms/filter/room-prefix/{prefix} # Room prefix filter with pagination
```

### Staff Endpoints with Pagination
```bash
GET /api/staff                              # All staff with pagination
GET /api/staff/role/{role}                 # Staff by role with pagination
GET /api/staff/filter/name/{name}          # Filter by name with pagination
GET /api/staff/filter/email-domain/{domain} # Filter by email domain with pagination
GET /api/staff/filter/with-students        # Staff with students with pagination
```

### Demo Endpoints
```bash
GET /api/demo/students/multi-sort          # Task 1: Multiple sort fields
GET /api/demo/students/default-paging      # Task 2: Default paging
GET /api/demo/students/search              # Task 3: Search with paging
GET /api/demo/students/metadata-only       # Task 4: Metadata only
GET /api/demo/students/null-handling       # Task 5: Null handling
GET /api/demo/students/one-indexed         # Task 6: One-indexed pages
GET /api/demo/students/performance-slice   # Task 7: Performance slice
GET /api/demo/students/advanced-sort       # Advanced sorting demo
```

---

## 📝 Query Parameters

### Standard Pagination Parameters
- `page` - Page number (1-based due to configuration)
- `size` - Page size (default: 10)
- `sort` - Sort field and direction (e.g., `name,asc` or `email,desc`)

### Multiple Sort Example
```bash
GET /api/students?page=1&size=5&sort=name,asc&sort=email,desc&sort=address,asc
```

### Filter with Pagination Example
```bash
GET /api/students/filter?name=John&email=gmail&page=1&size=10&sort=name,desc
```

---

## 🔧 Configuration Files

### application.properties
```properties
# Pagination Configuration
spring.data.web.pageable.default-page-size=10
spring.data.web.pageable.page-parameter=page
spring.data.web.pageable.size-parameter=size
spring.data.web.pageable.one-indexed-parameters=true
```

---

## 🎯 Key Features Implemented

✅ **Multiple Sort Fields** - Sort by multiple columns with different directions  
✅ **Default Paging & Sorting** - Global and method-level defaults  
✅ **Search with Paging** - Filter results then paginate  
✅ **Metadata Only** - Return pagination info without content  
✅ **Custom Null Handling** - Control null value positioning in sorts  
✅ **One-indexed Pages** - User-friendly page numbering starting from 1  
✅ **Performance Optimization** - Slice support and optimized queries  

---

## 🚀 Testing the Implementation

1. **Start the application:**
   ```bash
   mvn spring-boot:run
   ```

2. **Test multiple sort fields:**
   ```bash
   curl "http://localhost:8080/api/students?page=1&size=5&sort=name,asc&sort=email,desc"
   ```

3. **Test search with paging:**
   ```bash
   curl "http://localhost:8080/api/students/filter?name=John&page=1&size=3&sort=name,asc"
   ```

4. **Test metadata only:**
   ```bash
   curl "http://localhost:8080/api/students/metadata?page=1&size=10"
   ```

5. **Test performance slice:**
   ```bash
   curl "http://localhost:8080/api/students/slice?page=1&size=10&sort=name,asc"
   ```

All 7 pagination and sorting tasks have been successfully implemented! 🎉