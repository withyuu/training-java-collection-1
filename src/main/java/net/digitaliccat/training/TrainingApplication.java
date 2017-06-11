package net.digitaliccat.training;

import net.digitaliccat.training.list.suicide.RecursiveSuicideSolver;
import net.digitaliccat.training.list.suicide.SuicideSolverInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import sun.tools.jar.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class TrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApplication.class, args);
	}

	@Autowired
	@Qualifier("linkedListSolver")
    SuicideSolverInterface linkedListSolver;

    @Autowired
    @Qualifier("recursiveSolver")
    SuicideSolverInterface recursiveSolver;

	@Bean
	CommandLineRunner executeSuicideProblemWithLinkedList() {
		return args -> {
            StopWatch timer = new StopWatch();
            timer.start();
            int result = linkedListSolver.solve(999, 3, 17);
            timer.stop();
            System.out.printf("[LinkedListSolver] Result:%d, Time Used:%d%n", result, timer.getTotalTimeMillis());

		};
	}

    @Bean
    CommandLineRunner executeSuicideProblemWithRecursive() {
        return args -> {
            StopWatch timer = new StopWatch();
            timer.start();
            int result = recursiveSolver.solve(999, 3, 17);
            timer.stop();
            System.out.printf("[RecursiveSolver] Result:%d, Time Used:%d%n", result, timer.getTotalTimeMillis());
        };
    }

}
