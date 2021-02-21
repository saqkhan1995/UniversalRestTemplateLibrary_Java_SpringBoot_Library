# UniversalRestTemplateLibrary_Java_SpringBoot_Library

# Created a library for universal / Generic RestTemplate method { handleExchange() } -> capable of calling any external API with HTTP methods POST,GET,UPDATE,DELETE
# Used Custom Exceptions
# Unit Tests using Junit 5 & Mockito -> use following dependencies for that :
    <dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<scope>test</scope>
		</dependency>
#  Use below plugin if working with the existing library
   <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>3.0.0-M4</version>
			</plugin>
#  When used as Service, add SpringBootApplication main class
   replace plugin as follows :
      <plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
    
