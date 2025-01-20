package toy.testopenservice.domain;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "checklist")
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
    private String userId;
    
    @Column(length = 3)
    private String customs;
    
    @Column(length = 2)
    private String department;

    @CreationTimestamp
    private Timestamp creaDate;
}