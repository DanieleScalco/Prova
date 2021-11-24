package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.example.demo.prova.GenericContainer;
import com.example.demo.prova.NumberWithName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class TestApplication {
	
	// Necessario per avere l'elenco dei Bean
	private static ApplicationContext applicationContext;
		
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(TestApplication.class, args);
		
//		System.out.println("\n\n\n>>>Template test");
//		
//		fizzBuzz();
//
//		invioMailTest();
//		
//		displayAllBeans();
//		
//		prestazioniCreatoriStringheTest();
//		
//		stringFormatTest();
//		
//		jsonTest();
//		
//		stringBuilderTest();
//		
//		mapTest();
//		
//		lambdaTest();
//		
//		patternMatcherTest();
//		
//		genericsTest();
//		
//		streamTest();
		
	}

	private static <T> void stampaLista(Collection<T> list, boolean singleLine) {
		if (singleLine)
			for (T t: list)
				System.out.print(t + " ");
		else 
			for (T t: list)
				System.out.println(t);
	}

	private static void fizzBuzz() {
		//System.out.println("\n\n\n>>>FizzBuzz");
		for (int i = 1; i <= 100; i++) {
			if (i % 3 == 0 && i % 5 == 0)
				System.out.println("FizzBuzz");
			else if (i % 3 == 0)
				System.out.println("Fizz");
			else if (i % 5 == 0)
				System.out.println("Buzz");
			else
				System.out.println(i);
			
		}
	}

	private static void invioMailTest() {
		System.out.println("\n\n\n>>>Test invio mail");
		
		String mail = "danielescalco@hotmail.it";
		String password = null;
		String subject = "Oggetto di prova";
		String text = "Prima Spring Boot mail";
		String host = "smtp-mail.outlook.com";
		int port = 587;
		
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setPort(port);
		javaMailSenderImpl.setUsername(mail);
		javaMailSenderImpl.setPassword(password);
		
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		javaMailSenderImpl.setJavaMailProperties(props);
		
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		try {
			mimeMessageHelper.setFrom(mail);
			mimeMessageHelper.setText(text);
			mimeMessageHelper.setTo(mail);
			mimeMessageHelper.setSubject(subject);
			javaMailSenderImpl.send(mimeMessage);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	    
	}
	
	// Mostra tutti i bean
	public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

	private static void patternMatcherTest() {
		System.out.println("\n\n\n>>>Trovare il pattern in una stringa");
		String cassa = "cassa";
		String cassapanca = "cassapanca";
		Pattern p = Pattern.compile(cassa);
		Matcher m = p.matcher(cassapanca);
		System.out.println(cassapanca + " contiene " + cassa + "? " + m.find());
	}

	private static void prestazioniCreatoriStringheTest() {
		System.out.println("\n\n\n>>>Prestazioni creatori stringhe");
		
		System.out.println("\nTentativo 1");
		singleRunStringSpeedTest();
		System.out.println("\nTentativo 2");
		singleRunStringSpeedTest();
		System.out.println("\nTentativo 3");
		singleRunStringSpeedTest();
	}

	private static void singleRunStringSpeedTest() {
		
		// String.format()
		String formatString = "Hi %s; Hi to you %s";

	    long start = System.currentTimeMillis();
	    
	    for (int i = 0; i < 100000; i++) {
	        String s = String.format(formatString, i, +i * 2);
	    }

	    long end = System.currentTimeMillis();
	    System.out.println("Format = " + ((end - start)) + " millisecond");

	    // concatenazione
	    start = System.currentTimeMillis();

	    for (int i = 0; i < 100000; i++) {
	        String s ="";
	        s += "Hi " + i + "; Hi to you " + i * 2;
	    }

	    end = System.currentTimeMillis();

	    System.out.println("Concatenation = " + ((end - start)) + " millisecond");

	    // StringBuilder
	    start = System.currentTimeMillis();

	    for (int i = 0; i < 100000; i++) {
	        StringBuilder bldString = new StringBuilder("Hi ");
	        bldString.append(i).append("Hi to you ").append(i * 2).toString();
	    }

	    end = System.currentTimeMillis();

	    System.out.println("String Builder = " + ((end - start)) + " millisecond");
	}

	private static void streamTest() {
		System.out.println("\n\n\n>>>Utilizzo streams");
		List<String> list = new ArrayList<>();
		list.add("pippo");
		list.add("pluto");
		list.add("paperino");
		list.add("topolino");
		System.out.print("Lista: ");
		stampaLista(list, true);

		
		List<String> listaManipolata = list.stream()
										   .filter(s -> s.charAt(0) == 'p')
										   .map(s -> s.concat("super"))
										   .collect(Collectors.toList());
		
		System.out.print("\nLista manipolata: ");
		stampaLista(listaManipolata, true);
		
		
		///////////
		System.out.println();
		System.out.println();
		
		List<NumberWithName> numbers = new ArrayList<>();
		numbers.add(new NumberWithName(1, "Uno"));
		numbers.add(new NumberWithName(3, "Tre"));
		numbers.add(new NumberWithName(2, "Due"));
		
		System.out.print("Lista numbers: ");
		stampaLista(numbers, true);

		
		List<NumberWithName> numbersManipulated = numbers.stream()
														 .sorted((a, b) -> a.getN().compareTo(b.getN()))
														 .peek(a -> a.setName(a.getName() + "2"))
														 .collect(Collectors.toList());

		NumberWithName numbersMin = numbers.stream()
										   .min((a, b) -> a.getN() - b.getN())
										   .orElseThrow();
		System.out.println("Number min: " + numbersMin.getN());
		
		System.out.print("\nListanumbers manipolata: ");
		stampaLista(numbersManipulated, true);
		
		
		
		Stream.of("one", "two", "three", "four")
	          .filter(e -> e.length() > 3)
	          .peek(e -> System.out.println("Filtered value: " + e))
	          .map(String::toUpperCase)
	          .peek(e -> System.out.println("Mapped value: " + e))
	          .collect(Collectors.toList());
	}

	private static void genericsTest() {
		System.out.println("\n\n\n>>>Prova generics");
		GenericContainer<NumberWithName> numberContainer = new GenericContainer<>();
		numberContainer.setElement(new NumberWithName(1, "Uno"));
		System.out.println(numberContainer.getElement().getN());
	}

	private static void lambdaTest() {
		System.out.println("\n\n\n>>>Prova funzioni lambda");
		
		// Consumer tiene una funzione con un parametro e ritorna void
		Consumer<Integer> funzione = (n) -> {System.out.println("Consumer: " + n);
											 System.out.println("Funzione1 finita\n");
											};
		funzione.accept(1);	// Chiamo la funzione salvata nella variabile
		
		Function<Integer, Integer> funzione2 = (n) -> {System.out.println("Function: " + n);
														  return n;
												      };
		System.out.println("Funzione2 ritorna: " + funzione2.apply(2));
	}

	private static void mapTest() {
		System.out.println("\n\n\n>>>Prova Map");
		// Se la chiave è nulla crea un entry apposta
		Map<String, Integer> map = new HashMap<>();
		map.putIfAbsent(null, 0);
		map.putIfAbsent("uno", 1);
		map.putIfAbsent("due", 2);
		
		for (String s: map.keySet()) {
			System.out.println(s + ": " + map.get(s));
		}
	}

	private static void stringBuilderTest() {
		System.out.println("\n\n\n>>>Prova StringBuilder");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Stringa1");
		stringBuilder.append("Stringa2");
		System.out.println("String builder: " + stringBuilder.toString());
	}

	private static void jsonTest() {
		System.out.println("\n\n\n>>>Prova JSON");
		ObjectMapper objectMapper = new ObjectMapper();
		NumberWithName numberWithNameJSON1 = new NumberWithName(1, "uno");
		NumberWithName numberWithNameJSON2 = new NumberWithName(2, "due");
		NumberWithName numberWithNameJSON3 = new NumberWithName(3, "tre");
		List<NumberWithName> listNumberWithNames = new ArrayList<>();
		listNumberWithNames.add(numberWithNameJSON1);
		listNumberWithNames.add(numberWithNameJSON2);
		listNumberWithNames.add(numberWithNameJSON3);
		
		try {
			String JSONNumberWithName1 = objectMapper.writeValueAsString(numberWithNameJSON1);
			String JSONListNumberWithName = objectMapper.writeValueAsString(listNumberWithNames);
			// Mostro l'oggetto convertito in JSON
			System.out.println("JSON: " + JSONNumberWithName1);
			System.out.println("JSON: " + JSONListNumberWithName);
			
			// Ricostruisco l'oggetto dal JSON (l'oggetto deve avere il costruttore di default
			NumberWithName numberWithNameJSONRecreate = objectMapper.readValue(JSONNumberWithName1, NumberWithName.class);
			List<NumberWithName> numberWithNameJSONRecreateList = objectMapper.readValue(JSONListNumberWithName, new TypeReference<List<NumberWithName>>() {});
			System.out.println("NumberWithName from JSON: " + numberWithNameJSONRecreate);
			System.out.println("NumberWithNameList from JSON: " + numberWithNameJSONRecreateList);
			
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
	}

	private static void stringFormatTest() {
		System.out.println("\n\n\n>>>Prova String.format()");
		String lavoro = "informatico", nome = "Daniele";
		// E' possibile riusare più volte la stessa variabile
		System.out.println(String.format("Ciao %1$s %1s %1$s!!! Si vede che sei un grande %2$s", nome, lavoro));
	}

}
