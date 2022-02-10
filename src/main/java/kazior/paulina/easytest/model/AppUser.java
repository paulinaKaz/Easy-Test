package kazior.paulina.easytest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import kazior.paulina.easytest.utility.AppUserRole;
import kazior.paulina.easytest.utility.JsonViewFilter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@SequenceGenerator(name="USER_SEQ")
public abstract class AppUser {


    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "USER_SEQ")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private long id;

    @Column(name = "FIRST_NAME")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String firstName;

    @Column(name = "LAST_NAME")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String lastName;

    @Column(name="USERNAME")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String username;

    @Column(name="EMAIL")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String email;

    @Column(name="PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name="PHONE_NUMBER")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    @JsonView(JsonViewFilter.BasicInfo.class)
    private AppUserRole appUserRole;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignUser", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"comments","testCases","assignUser"})
    private Set<Defect> defects = new HashSet<>();

    public AppUser(String firstName, String lastName, String username, String email, String password, String phoneNumber, AppUserRole appUserRole)
                   {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.appUserRole = appUserRole;
    }

}
