import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Atendee(
        var pesel: String,
        var startDate: LocalDate,
        var endDate: LocalDate,
        @Id @GeneratedValue var id: Long? = null)