package demo.coindesk.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coindesk")
public class CoindeskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "char_name", nullable = false, length = 50)
    private String charName;
    @Column(name = "updated_utc", nullable = false, length = 50)
    private String updatedUtc;
    @Column(name = "code", nullable = false, length = 10)
    private String code;
    @Column(name = "currency_en", nullable = false, length = 10)
    private String currencyEn;
    @Column(name = "currency_ch", nullable = false, length = 10)
    private String currencyCh;
    @Column(name = "symbol", nullable = false, length = 10)
    private String symbol;
    @Column(name = "rate", nullable = false, length = 50)
    private String rate;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "rate_float", nullable = false)
    private BigDecimal rateFloat;
    @Column(name = "created_by", nullable = false, length = 50)
    @CreatedBy
    private String createdBy;
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "updated_by", nullable = false, length = 50)
    private String updatedBy;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
