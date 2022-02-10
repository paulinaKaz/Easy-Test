package kazior.paulina.easytest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.utility.JsonViewFilter;
import kazior.paulina.easytest.utility.Priority;
import kazior.paulina.easytest.utility.TestStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TEST_CASE")
@Getter
@Setter
@NoArgsConstructor
public class TestCase {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String title;

    @Column(name = "DESCRIPTION")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String description;

    @Column(name = "CREATED_BY")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String createdBy;

    @Column(name = "PRIORITY")
    @JsonView(JsonViewFilter.BasicInfo.class)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "SOURCE")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String source;

    @Column(name="STATUS")
    @JsonView(JsonViewFilter.BasicInfo.class)
    @Enumerated(EnumType.STRING)
    private TestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEST_SCENARIO_ID")
    @JsonIgnoreProperties({"project", "testCases"})
    private TestScenario testScenario;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "testCases")
    @JsonIgnoreProperties({"projects","testCases"})
    private Set<Tester> testers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "testCase", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"testCase"})
    private Set<TestStep> testSteps = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"testCases", "appUsers","comments"})
    @JoinTable(name="TEST_CASES_DEFECTS",
            joinColumns = @JoinColumn(name="TEST_CASE_ID"),
            inverseJoinColumns = @JoinColumn(name="DEFECT_ID"))
    private Set<Defect> defects = new HashSet<>();

    public TestCase(String title, String description, String createdBy, Priority priority, String source, TestStatus status) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.priority = priority;
        this.source = source;
        this.status = status;
    }
}
