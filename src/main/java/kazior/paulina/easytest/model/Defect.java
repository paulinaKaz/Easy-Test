package kazior.paulina.easytest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.utility.JsonViewFilter;
import kazior.paulina.easytest.utility.Priority;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="DEFECT")
@Getter
@Setter
@NoArgsConstructor
public class Defect {


    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE")
    @NotNull
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String title;

    @Column(name="REPORTED_DATE")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private LocalDate reportedDate;

    @Column(name="LAST_UPDATE_DATE")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private LocalDate lastUpdateDate;

    @Column(name = "COMPONENT")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String component;

    @Column(name="ENVIRONMENT")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String environment;

    @Column(name = "DEFECT_DESCRIPTION")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String defectDescription;

    @Column(name = "EXPECTED_RESULT")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String expectedResult;

    @Column(name = "PRIORITY")
    @Enumerated(EnumType.STRING)
    @JsonView(JsonViewFilter.BasicInfo.class)
    private Priority priority;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "defects")
    @JsonIgnoreProperties({"testScenario","testSteps","testers","defects"})
    private Set<TestCase> testCases = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    @JsonIgnoreProperties({"defects", "projects", "testCases"})
    private AppUser assignUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "defect", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"defect"})
    private Set<Comment> comments = new HashSet<>();

    public Defect(@NotNull String title, LocalDate reportedDate, LocalDate lastUpdateDate, String component, String environment, String defectDescription, String expectedResult, Priority priority) {
        this.title = title;
        this.reportedDate = reportedDate;
        this.lastUpdateDate = lastUpdateDate;
        this.component = component;
        this.environment = environment;
        this.defectDescription = defectDescription;
        this.expectedResult = expectedResult;
        this.priority = priority;
    }
}
