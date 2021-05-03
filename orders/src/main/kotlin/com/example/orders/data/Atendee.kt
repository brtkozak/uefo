import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Atendee(
    var pesel: String,
    var name: String,
    var surname: String,
    @Id @GeneratedValue var id: Long? = null
)
