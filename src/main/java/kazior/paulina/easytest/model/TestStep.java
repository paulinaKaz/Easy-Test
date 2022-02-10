package kazior.paulina.easytest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.utility.JsonViewFilter;
import kazior.paulina.easytest.utility.TestStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="TEST_STEP")
@Getter
@Setter
@NoArgsConstructor
public class TestStep {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "STEP_NR")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private short stepNumber;

    @Column(name = "STEP_ACTIONS")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String stepActions;

    @Column(name = "EXPECTED_RESULTS")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String expectedResult;

    @Column(name = "TESTER_COMMENT")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String testerComment;

    @Column(name = "STATUS")
    @JsonView(JsonViewFilter.BasicInfo.class)
    @Enumerated(EnumType.STRING)
    private TestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEST_CASE_ID")
    @JsonIgnoreProperties({"testScenario", "testers","testSteps", "defects"})
    private TestCase testCase;

    public TestStep(short stepNumber, String stepActions, String expectedResult, String testerComment, TestStatus status) {
        this.stepNumber = stepNumber;
        this.stepActions = stepActions;
        this.expectedResult = expectedResult;
        this.testerComment = testerComment;
        this.status = status;
    }
}
