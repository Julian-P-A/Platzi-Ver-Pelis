package com.platzi.ver_pelis_plus.services.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieDto(
	Long id,
	String title,
	String genre,
	LocalDate date,
	Integer duration,
	BigDecimal classification,
	String status
) {
	
}
