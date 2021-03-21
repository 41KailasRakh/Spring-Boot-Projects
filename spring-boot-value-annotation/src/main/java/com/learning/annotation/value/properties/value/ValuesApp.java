package com.learning.annotation.value.properties.value;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "myProperties", value = "values.properties")
public class ValuesApp {

	@Value(value = "String Value")
	private String stringValue ;
	
	@Value(value = "${value.from.file}")
	private String stringValueFromFile ;
	
	@Value(value = "${systemValue: Default system value}")
	private String systemValue ;
	
	@Value("${unknown_param:Some defalt}")
	private String someDefault ;
	
	@Value(value = "${priority}")
	private String prioritySystemProperty ;
	
	@Value(value = "${listofValues}")
	private String[] valuesArray ;
	
	@Value(value = "${listofValues}")
	private List<String> valuesList ;

	@Value("#{systemProperties['priority']}: No value found so assigning default systemProperties")
	private String spelValue;
	
	@Value("#{someBean.someValue}")
	private Integer someValue ;
	
	@Value("#{ '${listofValues}'.split(',')}")
	private List<String> valueList ;
	
	@Value("#{ ${valuesMap} }")
	private Map<String, Integer> valueMap ;
	
	@Value("#{ ${valuesMap}.key1 }")
	private String valuesMapKey1 ;
	
	@Value("#{ ${valuesMap}['unknownKey'] }")
	private Integer unknownMapKey1; 
	
	@Value("#{ ${valuesMap}['unknownKey'] ?: 15}")
	private Integer unknownMapKey2; 
	
	@Value("#{ ${valuesMap}['key2'] }")
	private Integer valuesMapKey2; 
	
	@Value("#{ ${unknownMap: {key4: '4', key5: '5'} } }")
	private Map<String, Integer> unknownMap ;
	

	@Value("#{ ${valuesMap}.?[value>'1'] }")
	private Map<String, Integer> valuesMapFiltered ;
	
	
	@Bean
	public SomeBean someBean() {
		return new SomeBean(10);
	}
	
	@PostConstruct
	public void afterInitialize() {
		System.out.println(stringValue);
		System.out.println(stringValueFromFile);
		System.out.println(systemValue);
		System.out.println(someDefault);
		System.out.println(prioritySystemProperty);
		System.out.println(valuesArray);
		System.out.println(valuesList);
		System.out.println(spelValue);
		System.out.println(someValue);
		System.out.println(valueList);
		System.out.println(valueMap);
		System.out.println(valuesMapKey1);
		System.out.println(unknownMapKey1);
		System.out.println(unknownMapKey2);
		System.out.println(unknownMap);
		System.out.println(valuesMapFiltered);
		
	}
	
	public static void main(String[] args) {
        System.setProperty("systemValue", "Some system parameter value");
        System.setProperty("priority", "System property");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ValuesApp.class);
    }
}

