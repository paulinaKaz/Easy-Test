package kazior.paulina.easytest.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kazior.paulina.easytest.utility.AppUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "MANAGER")
@Getter
@Setter
@NoArgsConstructor
public class Manager extends AppUser {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
    @JsonIgnoreProperties({"testScenarios", "manager", "testers", "developers"})
    private Set<Project> projects = new HashSet<>();


    public Manager(String firstName, String lastName, String username, String email, String password, String phoneNumber) {
        super(firstName, lastName, username, email, password, phoneNumber, AppUserRole.MANAGER);
    }

}
