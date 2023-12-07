package kq.practice.ssf19;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import kq.practice.ssf19.model.Bag;
import kq.practice.ssf19.model.Employee;
import kq.practice.ssf19.repository.EmployeeRepo;

@SpringBootApplication
public class Ssf19Application implements CommandLineRunner {

	@Autowired
	EmployeeRepo repo;

	@Autowired
	@Qualifier("totebag")
	Bag bag;

	public static void main(String[] args) {
		SpringApplication.run(Ssf19Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		File file = new File("employee2.json");

		// String result = "";
		// try (FileReader fr = new FileReader(file)) {
		// BufferedReader br = new BufferedReader(fr);
		// result = br.lines().collect(Collectors.joining());
		// }

		InputStream is = new FileInputStream(file);
		JsonReader jReader = Json.createReader(is);
		JsonArray arr = jReader.readArray();
		is.close();

		List<Employee> employees = new ArrayList<>();
		arr.forEach(emp -> {
			Employee e = parseEmpObject((JsonObject) emp);
			employees.add(e);
		});

		for (Employee e : employees) {
			repo.saveRecord(e);
		}

		// System.out.println(repo.getRecord(12345).getName());
		// System.out.println(repo.getRecord(12345).getId());

		// System.out.println(repo.getAll());

		// ==========================================================================

		bag.showBagType();
	}

	private Employee parseEmpObject(JsonObject emp) {

		Employee employee = new Employee(emp.getInt("employeeId"), emp.getString("employeeName"));
		return employee;
	}

}
