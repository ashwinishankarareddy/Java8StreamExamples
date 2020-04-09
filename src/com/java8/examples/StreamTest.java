package com.java8.examples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamTest {

	public static void main(String[] args) {
		Student student1 = new Student("Jayesh", 20, new Address("1234"),
				Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

		Student student2 = new Student("Khyati", 20, new Address("1235"),
				Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

		Student student3 = new Student("Jason", 20, new Address("1236"),
				Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

		List<Student> students = Arrays.asList(student1, student2, student3);

		Student st = students.stream().filter(p -> p.getName().equals("Jayesh")).findFirst().get();
		System.out.println(st);

		Student st1 = students.stream().filter(p -> p.getAddress().getZipcode().equals("1235")).findFirst().get();
		System.out.println(st1);

		List<Student> st2 = students.stream()
				.filter(p -> p.getMobileNumbers().stream().anyMatch(x -> Objects.equals(x.getNumber(), "3333")))
				.collect(Collectors.toList());
		String stu2 = st2.stream().map(Student::getName).collect(Collectors.joining(",", "[", "]"));
		System.out.println(stu2);

		List<Student> st3 = students.stream()
				.filter(p -> p.getMobileNumbers().stream()
						.allMatch(x -> Objects.equals(x.getNumber(), "1233") || Objects.equals(x.getNumber(), "1234")))
				.collect(Collectors.toList());
		System.out.println(st3);

		TempStudent tmpStud1 = new TempStudent("Jayesh1", 201, new Address("12341"),
				Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));

		TempStudent tmpStud2 = new TempStudent("Khyati1", 202, new Address("12351"),
				Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));

		List<TempStudent> tmpStudents = Arrays.asList(tmpStud1, tmpStud2);
		
		List<Student> stud = tmpStudents.stream().map(t -> new Student(t.name, t.age, t.address, t.mobileNumbers))
				.collect(Collectors.toList());
		System.out.println(stud);
		
		List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());
		System.out.println(names);
		
		names.stream().map(String::toUpperCase).forEach(System.out::println);
		
		names.stream().sorted().forEach(System.out::println);
		System.out.println("=================================");
		students.stream().filter(s -> s.getName().startsWith("J"))
		.sorted(Comparator.comparing(Student::getName)).forEach(System.out::println);
	}

}
