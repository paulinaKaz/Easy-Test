package kazior.paulina.easytest.utility;

import kazior.paulina.easytest.model.*;
import kazior.paulina.easytest.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitialDataBaseSetUp implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final TesterRepository testerRepository;
    private final DeveloperRepository developerRepository;
    private final TestScenarioRepository testScenarioRepository;
    private final ManagerRepository managerRepository;
    private final DefectRepository defectRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        addTester();
        addDeveloper();
        addManager();
    }

    private void addTester(){
        Tester tester = new Tester ("Ula", "Tester", "testerula","ulatester@fake.fake", passwordEncoder.encode("qwerty"), "987678654");
        Tester tester2 = new Tester("Janusz", "Polak", "polakJnsz", "jpolak@fake.fake", passwordEncoder.encode("polskaWalcząca"), "767888755");
       testerRepository.save(tester);
       testerRepository.save(tester2);
    }

    private void addDeveloper(){
        Developer developer = new Developer ("Ola", "Nowakowa", "devola", "olanowakowa@fake.fake", passwordEncoder.encode("motylki09"), "878978678");
        Developer developer2 = new Developer ("Michał", "Kowalski", "michalDeveloper", "kowalskim@fake.fake", passwordEncoder.encode("hasełko123"), "654777345");
        developerRepository.save(developer);
        developerRepository.save(developer2);
    }

    private void addManager(){
        Manager manager = new Manager ("Kasia", "Kowal", "KasiaMenago", "kasiakowal@fake.fake", passwordEncoder.encode("makaronZsosem"), "939212345");
        managerRepository.save(manager);
    }


}
