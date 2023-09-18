# Проектна задача по информациска безбедност

## E-banking систем

**Давор Шопар 205012**

**Владимир Камбовски 205014**

Проектот е изработен во Spring Boot/Java, со Spring Security 5, Spring Data JPA, MySQL датабаза. Корисничкиот интерфејс е изработен со Thymeleaf. Апликацијата е изработена со MVC архитектура.

## Врз база на тоа кој е најавен има различни функционалности

### Кога е најавен ADMIN
- Преглед на employees и опција да ги оттргне

### Кога е најавен EMPLOYEE
- Преглед на customers и опција да ги оттргне

### Кога е најавен CUSTOMER
- Опција Withdraw - подигнување на средства
- Опција Deposit - депозит на средства

## Models 

- **EmployeeManagement:** Содржи метода за бришење на Employee.
- **TransactionMonitoring:** Содржи информации за корисникот поврзан со трансакцијата, видот на трансакцијата, износот на трансакцијата и временскиот печат на трансакцијата.
- **TransactionType:** Enum за видовите на трансакции (DEPOSIT, WITHDRAWAL).
- **User:** Содржи податоци за корисникот, вклучувајќи creditCardNumber, username, password и balance.
- **UserManagement:** Содржи метода за бришење на корисник.
- **UserType:** Enum кој ги дефинира трите типови на корисници.

## Репозиториум и Сервиси

- **UserRepository:** Репозиториум за суштноста User, што овозможува лесна комуникација со базата на податоци.
- **UserServices & UserServicesImpl:** Овие класи ги обработуваат функционалностите за управување со корисници, вклучувајќи операции како CRUD, управување со фондови и регистрација на корисници на структуриран и повторно употреблив начин. UserService го прикажува договорот, додека имплементацијата на UserServiceImpl го обезбедува вистинскиот код за извршување на овие операции.

## Контролери

- **AdminController:** Контролира пристапот на ADMIN корисниците преку /admin/**
- **CustomerController:** Контролира пристапот на CUSTOMER корисниците преку /users/**
- **EmployeeController:** Контролира пристапот на EMPLOYEE корисниците преку /employee/**
- **LoginController:** Контролира пристапот за сите корисници
- **RegisterController:** Контролира пристапот за сите корисници

## SecurityConfig (2-way authentication)
**Овој код обезбедува безбедност за веб-апликацијата. Дефинира како корисниците се автентицираат, до кои ресурси можат да пристапат во зависност од нивните улоги и како можат да се најавуваат и одјавуваат. CustomUserDetailsService обезбедува кориснички детали, а апликацијата користи и автентикација базирана на лозинка и X.509 базирана на сертификат.
```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Your security configuration here
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/employee/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .x509()
                .subjectPrincipalRegex("CN=(.*?)(?:,|$)") // Extracts the common name (CN) from the certificate
                .userDetailsService(userDetailsService)
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password("password") // Replace with the user's password hash
                .roles("USER") // Add roles as needed
                .build();
    }
}


