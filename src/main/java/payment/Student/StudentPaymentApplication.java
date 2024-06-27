package payment.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import payment.Student.entities.Payment;
import payment.Student.entities.PaymentStatus;
import payment.Student.entities.PaymentType;
import payment.Student.entities.Student;
import payment.Student.repositories.PaymentRepository;
import payment.Student.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class StudentPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentPaymentApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository) {
		return args -> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Mounir").code("112233").programId("SDIA")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Imane").code("112244").programId("SDIA")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Yasmine").code("112255").programId("GLSID")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Najet").code("112266").programId("BDCC")
					.build());

			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();

			studentRepository.findAll().forEach(st -> {
				int index = random.nextInt(paymentTypes.length);
				for (int i = 0; i< 10; i++) {
					Payment payment = Payment.builder()
							.amount(1000+(int)(Math.random()*20000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();
					paymentRepository.save(payment);
				}
			});
		};
	}
}
