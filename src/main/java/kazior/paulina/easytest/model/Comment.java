package kazior.paulina.easytest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="COMMENT")
@Setter
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRIPTION")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String description;

    @Column(name = "DATE_ADDED")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private LocalDate dateAdded;

    @Column(name = "ADDED_BY")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String addedBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DEFECT_ID")
    @JsonIgnoreProperties({"testCases","appUsers","comments"})
    private Defect defect;

    public Comment(String description, LocalDate dateAdded, String addedBy) {
        this.description = description;
        this.dateAdded = dateAdded;
        this.addedBy = addedBy;
    }
}
