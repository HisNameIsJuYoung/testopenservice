package toy.testopenservice.domain;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 32)
    private String checListId;

    @Column(nullable = false, length = 32)
    private String swVariId;

    @Column(nullable = false, length = 32)
    private String systName;

    @Column(nullable = false, length = 32)
    private String fronVariId;

    @Column(nullable = false, length = 1024)
    private String fronVariStep;

    @Column(length = 32)
    private String manaUser;

    @Column(length = 2)
    private String variResu;

    @Column(length = 16)
    private String logiUser;

    @CreationTimestamp
    private Timestamp creaDate;
}