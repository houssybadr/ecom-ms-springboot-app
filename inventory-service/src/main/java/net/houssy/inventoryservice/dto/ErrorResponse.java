package net.houssy.inventoryservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorMessage {
    String message;
    int Status;
    LocalDateTime timestamp;
}
