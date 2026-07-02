package vn.edu.epu.quanlyhoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class QuanLyHoSoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyHoSoBackendApplication.class, args);
		System.out.println("Da chay THANH CONG");
	}

}
