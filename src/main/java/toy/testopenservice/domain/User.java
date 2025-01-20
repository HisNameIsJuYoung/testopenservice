package toy.testopenservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 32, unique = true)
    private String userId;

    @Column(nullable = false, length = 5)
    private String userName;

    @Column(nullable = false, length = 1200)
    private String password;

    @Column(nullable = false, length = 3)
    private String customs;

    @Column(nullable = false, length = 2)
    private String department;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    private Timestamp createDate;
}
