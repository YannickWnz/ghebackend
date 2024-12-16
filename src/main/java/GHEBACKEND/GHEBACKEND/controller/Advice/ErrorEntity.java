package GHEBACKEND.GHEBACKEND.controller.Advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEntity {
    String code;
    String message;
}
