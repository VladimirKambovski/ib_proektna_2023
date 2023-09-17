#Проектна задача по информациска безбедност
##E-banking систем 
##Давор Шопар 205012
##Владимир Камбовски 205014

Проектот е изработен во Spring Boot/Java, со Spring Security 5, Spring Data JPA, MySQL датабаза. Корисничкиот интерфејс е изработен со Thymeleaf
Апликацијата е изработена со MVC архитектура.

#Врз база на тоа кој е најавен има различни функционалности

Кога е најавен ADMIN
-Преглед на employees и опција да ги оттргне
Кога е најавен EMPLOYEE
-Преглед на customers и опција да ги оттргне
Кога е најавен CUSTOMER
-Опција Withdraw - подигнување на средства
-Опција Deposit - депозит на средства

#Models 

EmployeeManagement(deleteEmployee method)
TransactionMonitoring(информации за корисникот поврзан со трансакцијата, видот на трансакцијата, износот на трансакцијата и временскиот печат на трансакцијата.)
TransactionType(enum за DEPOSIT, WITHDRAWAL)
User(податоци за корсиникот: creditCardNumber, username, password, balance)
UserManagement(delete customer method)
UserType(enum од трите типови на корисници)

#Репозиториум и Сервиси

UserRepositorium(Репозиториум за user entity, што ни овозможува лесно да комуницираме со базата на податоци)
UserServices & UserServicesImpl (Овие класи ја опфаќаат функционалноста за управување со корисници, вклучувајќи ги операциите CRUD, управувањето со фондови и регистрацијата на корисниците, на структуриран и повторно употреблив начин. UserService го прикажува договорот, додека имплементацијата на услугата UserServiceImpl го обезбедува вистинскиот код за извршување на овие операции.)

#Контролери

AdminController - Контрола на пристап има(ADMIN) преку /admin/**
CustomerController - Контрола на пристап има(CUSTOMER) преку /users/**
EmployeeController - Контрола на пристап има(EMPLOYEE) преку /employee/**
LoginController - Контрола на пристап имаат сите корисници
RegisterController - Контрола на пристап имаат сите корисници

#SecurityConfig(2 way authentication)

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

овој код поставува безбедност за веб-апликација. Дефинира како корисниците се автентицирани, 
до што можат да пристапат врз основа на нивните улоги и како можат да се најавуваат и излегуваат. 
CustomUserDetailsService обезбедува кориснички детали, а апликацијата користи и автентикација базирана на лозинка и X.509 базирана на сертификат.
