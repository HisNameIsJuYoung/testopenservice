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
@Table(name = "DNSChecklist")
public class DNSChecklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 32)
    private String userId;

    @Column(length = 32)
    private String userName;

    @Column(nullable = false, length = 3)
    private String customs;

    @Column(nullable = false, length = 2)
    private String department;

    @Column(nullable = true)
    private Boolean DNSVariResu;

    @CreationTimestamp
    private Timestamp creaDate;
}
