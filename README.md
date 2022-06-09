# spring-boot-h2-crud-boilerplate

This project explains CRUD (**C**reate, **R**ead, **U**pdate, **D**elete) operations using spring boot and H2 in-memory database.
In this app we are using Spring Data JPA for built-in methods to do CRUD operations.     
`@EnableJpaRepositories` annotation is used on main class to Enable H2 DB related configuration, which will read properties from `application.properties` file.

## Prerequisites 
- Java
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/guides/index.html)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Lombok](https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok)


## Tools
- Eclipse or IntelliJ IDEA (or any preferred IDE) with embedded Maven
- Maven (version >= 3.6.0)
- Postman (or any RESTful API testing tool)


<br/>


###  Build and Run application
_GOTO >_ **~/absolute-path-to-directory/spring-boot-h2-crud-boilerplate**  
and try below command in terminal
> **```mvn spring-boot:run```** it will run application as spring boot application

or
> **```mvn clean install```** it will build application and create **jar** file under target directory 

Run jar file from below path with given command
> **```java -jar ~/path-to-spring-boot-h2-crud-boilerplate/target/spring-boot-h2-crud-boilerplate-0.0.1-SNAPSHOT.jar```**

Or
> run main method from `SpringBootH2CRUDApplication.java` as spring boot application.  


||
|  ---------    |
| **_Note_** : In `SpringBootH2CRUDApplication.java` class we have autowired both SuperHero, Student and Employee repositories. <br/>If there is no record present in DB for any one of that module class (SuperHero, Student and Employee), static data is getting inserted in DB from `HelperUtil.java` class when we are starting the app for the first time.| 



### Code Snippets
1. #### Maven Dependencies
    Need to add below dependencies to enable H2 DB related config in **pom.xml**. Lombok's dependency is to get rid of boiler-plate code.   
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
   
    <!-- update -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
   
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
   
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    ```
   
   
2. #### Properties file
    Reading H2 DB related properties from **application.properties** file and configuring JPA connection factory for H2 database.  

    **src/main/resources/application.properties**
     ```
     server.port=8088
    
     spring.datasource.url=jdbc:h2:mem:sampledb
     spring.datasource.driverClassName=org.h2.Driver
     spring.datasource.username=sa
     spring.datasource.password=password
     spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    
     spring.h2.console.enabled=true
    
     #spring.data.rest.base-path=/phone
     spring.data.rest.base-default-page-size=10
     spring.data.rest.base-max-page-size=20
    
     springdoc.version=1.0.0
     springdoc.swagger-ui.path=/swagger-ui-custom.html 
     ```
   
   
3. #### Model class
    Below are the model classes which we will store in H2 DB and perform CRUD operations.  
    **SuperHero.java**  
    ```
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Entity
    @Table
    public class SuperHero implements Serializable {
    
        @Id
        @GeneratedValue
        private int id;
    
        private String name;
        private String superName;
        private String profession;
        private int age;
        private boolean canFly;
    
        // Constructor, Getter and Setter
    }
    ```
   
    **Employee.java**  
    
    ```
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table
    public class Employee implements Serializable {
    
        @Id
        @GeneratedValue
        private int id;
        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;
        private int age;
    
        @Column(name = "no_of_childrens")
        private int noOfChildrens;
        private boolean spouse;
    
        @JsonManagedReference
        @OneToOne(cascade = { 
                     CascadeType.MERGE,
                     CascadeType.PERSIST,
                     CascadeType.REMOVE
        })
        @JoinColumn(name="address")
        private Address address;
    
    
        @JsonManagedReference
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", 
            cascade = { 
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
        })
        private List<PhoneNumber> phoneNumbers;
    
    
    
        @ElementCollection
        @CollectionTable(name="hobbies", joinColumns=@JoinColumn(name="id"))
        @Column(name="hobby")
        private List<String> hobbies = new ArrayList<>();
    
    }
    ```
   
    **Address.java**
    
    ```
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table
    public class Address implements Serializable {
    
        @Id
        @GeneratedValue
        private int id;
    
        @Column(name = "street_address")
        private String streetAddress;
        private String city;
        private String state;
        private String country;
    
        @Column(name = "postal_address")
        private String postalCode;
    
        @JsonBackReference
        @OneToOne(mappedBy="address", 
                cascade = { 
                        CascadeType.MERGE,
                        CascadeType.PERSIST,
                        CascadeType.REMOVE
                })
        private Employee employee;
    }
    ```
   
    **PhoneNumber.java**
    
    ```
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table
    public class PhoneNumber implements Serializable {
    
        @Id
        @GeneratedValue
        private int id;
        private String type;
        private String number;
    
    
        @JsonBackReference
        @ManyToOne(cascade= { CascadeType.ALL})
        @JoinColumn(name="employee_id")
        private Employee employee;
    
    }
    ```
 
    #    
    #### **Update**: New annotations are added in this application to print log smartly with the help of Aspect (LoggerAspect class from aop package).
    **@LogObjectBefore** - annotation created to print log before method execution <br/>
    **@LogObjectAfter** - annotation created to print the returned value from method
    #   
   
   
4. #### CRUD operation for Super Heroes

    In **SuperHeroController.java** class, 
    we have exposed 5 endpoints for basic CRUD operations
    - GET All Super Heroes
    - GET by ID
    - POST to store Super Hero in DB
    - PUT to update Super Hero
    - DELETE by ID
    
    ```
    @RestController
    @RequestMapping("/super-hero")
    public class SuperHeroController {
        
        @LogObjectAfter
        @GetMapping
        public ResponseEntity<List<?>> findAll();
    
        @LogObjectAfter
        @GetMapping("/{id}")
        public ResponseEntity<?> findById(@PathVariable String id);
    
        @LogObjectBefore
        @LogObjectAfter
        @PostMapping
        public ResponseEntity<?> save(@RequestBody SuperHero superHero);
    
        @LogObjectBefore
        @LogObjectAfter
        @PutMapping("/{id}")
        public ResponseEntity<?> update(@PathVariable int id, @RequestBody SuperHero superHero);
    
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable String id);
    }
    ```
   
   In **SuperHeroServiceImpl.java**, we are autowiring above interface using `@Autowired` annotation and doing CRUD operation.
    
    <br/>
    <br/>
    
    In **ReactiveSuperHeroController.java** class, 
    we have exposed 5 endpoints for basic CRUD operations with spring reactive feature
    - GET All Super Heroes
    - GET by ID
    - POST to store Super Hero in DB
    - PUT to update Super Hero
    - DELETE by ID
       
    ```
    @RestController
    @RequestMapping("/reactive/super-hero")
    public class ReactiveSuperHeroController {
           
        @GetMapping(path = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
        public ResponseEntity<Flux<?>> findAll();
       
        @GetMapping("/{id}")
        public ResponseEntity<?> findById(@PathVariable String id);
       
        @PostMapping
        public ResponseEntity<?> save(@RequestBody SuperHero superHero);
       
        @PutMapping("/{id}")
        public ResponseEntity<?> update(@PathVariable int id, @RequestBody SuperHero superHero);
       
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable String id);
    }
    ```



   
    In **SuperHeroRepository.java**, we are extending `JpaRepository<Class, ID>` interface which enables CRUD related methods.
    
   ```
   public interface SuperHeroRepository extends JpaRepository<SuperHero, String> {
   }
   ```
    
   

   In **EmployeeController.java** class, 
   we have exposed 5 endpoints for basic CRUD operations
   - GET All Employee
   - GET by ID
   - POST to store Employee in DB
   - PUT to update Employee
   - DELETE by ID
    
   ```
   @RestController
   @RequestMapping("/employees")
   public class EmployeeController {
        
       @LogObjectAfter
       @GetMapping
       public ResponseEntity<List<?>> findAll();
    
       @LogObjectAfter
       @GetMapping("/{id}")
       public ResponseEntity<?> findById(@PathVariable int id);
    
       @LogObjectBefore
       @LogObjectAfter
       @PostMapping
       public ResponseEntity<?> save(@RequestBody Employee employee);
    
       @LogObjectBefore
       @LogObjectAfter
       @PutMapping("/{id}")
       public ResponseEntity<?> update(@PathVariable int id, @RequestBody Employee employee);
    
       @DeleteMapping("/{id}")
       public ResponseEntity<?> delete(@PathVariable int id);
   }
   ```
   
   In **EmployeeRepository.java**, we are extending `JpaRepository<Class, ID>` interface which enables CRUD related methods.  
    
   ```
   public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   }
   ```
   
   In **SuperHeroServiceImpl.java**, we are autowiring above interface using `@Autowired` annotation and doing CRUD operation.






5. #### JPA And Query operation for Employee
    In **StudentController.java** class JPA related operations.
    
 
 
    
### API Endpoints

- #### Super Hero CRUD Operations
    > **GET Mapping** http://localhost:8088/super-hero  - Get all Super Heroes
    
    > **GET Mapping** http://localhost:8088/super-hero/1  - Get Super Hero by ID
       
    > **POST Mapping** http://localhost:8088/super-hero  - Add new Super Hero in DB  
    
     Request Body  
     ```
        {
            "name": "Tony",
            "superName": "Iron Man",
            "profession": "Business",
            "age": 50,
            "canFly": true
        }
     ```
    
    > **PUT Mapping** http://localhost:8088/super-hero/3  - Update existing Super Hero for given ID 
                                                       
     Request Body  
     ```
        {
            "id": "3"
            "name": "Tony",
            "superName": "Iron Man",
            "profession": "Business",
            "age": 50,
            "canFly": true
        }
     ```
    
    > **DELETE Mapping** http://localhost:8088/super-hero/4  - Delete Super Hero by ID


