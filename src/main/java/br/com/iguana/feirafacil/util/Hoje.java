package br.com.iguana.feirafacil.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Component
public class Hoje {
    private LocalDateTime hojeTime = LocalDateTime.now();
    private LocalDate hoje = LocalDate.now();
}
