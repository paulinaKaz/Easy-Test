package kazior.paulina.easytest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="TEST_SCENARIO")
@Getter
@Setter
@NoArgsConstructor
public class TestScenario {


    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewFilter.BasicInfo.class)
    private long id;

    @Column(name = "TITLE")
            //unique = true)
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PROJECT_ID")
    @JsonIgnoreProperties({"testScenarios", "manager", "testers", "developers"})
    private Project project;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "testScenario", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"testScenario", "testers","testSteps", "defects"})
    private Set<TestCase> testCases = new HashSet<>();

    public TestScenario(String title) {
        this.title = title;
    }
}
