package br.com.caelum.jms;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Teste {

	public static void main(String[] args) {
		LocalDateTime data01 = LocalDateTime.now();
		ZonedDateTime ld = data01.atZone(ZoneId.systemDefault());
		DateTimeFormatter fmt = DateTimeFormatter
				.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
		System.out.println("DATA PATTERN: " + fmt.format(ld));
	}
}