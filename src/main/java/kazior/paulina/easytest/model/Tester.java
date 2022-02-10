package kazior.paulina.easytest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kazior.paulina.easytest.utility.AppUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "TESTER")
@Getter
@Setter
@NoArgsConstructor
public class Tester extends AppUser{

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "testers")
    @JsonIgnoreProperties({"testScenarios", "manager", "testers", "developers"})
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"testScenario", "testers","testSteps", "defects"})
    @JoinTable(name="TESTERS_TEST_CASES",
            joinColumns = @JoinColumn(name="TESTER_ID"),
            inverseJoinColumns = @JoinColumn(name="TESTCASE_ID"))
    private Set<TestCase> testCases = new HashSet<>();

    public Tester(String firstName, String lastName, String username, String email, String password, String phoneNumber) {
        super(firstName, lastName, username, email, password, phoneNumber, AppUserRole.TESTER);

    }
}
