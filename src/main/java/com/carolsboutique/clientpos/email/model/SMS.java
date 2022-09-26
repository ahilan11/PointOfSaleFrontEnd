package com.carolsboutique.clientpos.email.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SMS {
    private LocalDateTime dateTime;
    private String toPhoneNumber;
    private String message;
}
