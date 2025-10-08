package br.com.fiap.Checkpoint2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pedidos")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "O nome é obrigatório")
    private String clientName;

    private LocalDate orderDate;

    @DecimalMin(value = "0.0", message = "O valor náo pode ser negativo")
    @Positive
    private BigDecimal totalValue;

    @PrePersist
    public void prePersist() {
        if (orderDate == null){
            orderDate = LocalDate.now();
        }
    }
}
