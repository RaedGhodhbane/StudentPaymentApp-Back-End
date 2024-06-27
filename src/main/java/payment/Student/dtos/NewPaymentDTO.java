package payment.Student.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import payment.Student.entities.PaymentType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPaymentDTO {
    private double amount;
    private PaymentType type;
    private LocalDate date;
    private String studentCode;
}
