package net.houssy.inventoryservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    String message;
    int Status;
    LocalDateTime timestamp;
}
