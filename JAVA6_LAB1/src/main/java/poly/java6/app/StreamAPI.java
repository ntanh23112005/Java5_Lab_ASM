package poly.java6.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import poly.java6.entity.Student;

public class StreamAPI {

	static List<Student> list = Arrays.asList(new Student("Nguyễn Văn Tèo", true, 7.5),
			new Student("Trần Thị Thu Hương", false, 5.5), new Student("Phạm Đức Cường", true, 9.5),
			new Student("Lê Thị Mỹ Hồng", false, 6.5), new Student("Đoàn Thị Kim Ty", false, 8.0));


	
	public static void main(String[] args) {
//		demo1();
//		demo2();
		demo3();
	}

	private static void demo3() {
		// TODO Auto-generated method stub
		
	}

	private static void demo2() {
		List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
		List<Double> result = list.stream() // Stream<Integer>
				.filter(n -> n % 2 == 0) // Stream<Integer>
				.map(n -> Math.sqrt(n)) // Stream<Double>
				.peek(d -> System.out.println(d)) // Stream<Double>
				.collect(Collectors.toList()); // List<Double>

	}

	private static void demo1() {
		Stream<Integer> stream1 = Stream.of(1, 9, 4, 7, 5, 2);
		stream1.forEach(n -> System.out.println(n));

		List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
//		Stream<Integer> stream2 = list.stream();
		list.stream().forEach(n -> System.out.println(n));
	}
}
