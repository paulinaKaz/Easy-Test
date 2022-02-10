package kazior.paulina.easytest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewFilter.BasicInfo.class)
    private long id;

    @Column(name = "PROJECT_SIGNATURE")
    @JsonView(JsonViewFilter.BasicInfo.class)
    @Length(min=1, max=10)
    @NotNull
    private String projectSignature;

    @Column(name = "PROJECT_NAME")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String projectName;

    @Column(name = "DESCRIPTION")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"project", "testCases"})
    private Set<TestScenario> testScenarios = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MANAGER_ID")
    @JsonIgnoreProperties({"projects", "defects"})
    private Manager manager;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"projects", "testCases",  "defects"})
    @JoinTable(name="TESTERS_PROJECTS",
    joinColumns = @JoinColumn(name="PROJECT_ID"),
    inverseJoinColumns = @JoinColumn(name="TESTER_ID"))
    private Set<Tester> testers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"projects",  "defects"})
    @JoinTable(name="DEVELOPERS_PROJECTS",
    joinColumns = @JoinColumn(name="PROJECT_ID"),
    inverseJoinColumns = @JoinColumn(name="DEVELOPER_ID"))
    private Set<Developer> developers = new HashSet<>();

    public Project(String projectName, String description, String projectSignature) {
        this.projectSignature = projectSignature;
        this.projectName = projectName;
        this.description = description;
    }
}
