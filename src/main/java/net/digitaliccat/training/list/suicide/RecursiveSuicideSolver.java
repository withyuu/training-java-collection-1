package net.digitaliccat.training.list.suicide;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("recursiveSolver")
public class RecursiveSuicideSolver implements SuicideSolverInterface {

    @Override
    public int solve(int numberOfPeople, int startingPoint, int numberToBeSkipped) {
        return (solveRecursive(numberOfPeople, numberToBeSkipped) + startingPoint) % numberOfPeople;
    }

    private int solveRecursive(int n, int k) {
        if (n == 1) {
            return 0;
        }

        return (solveRecursive(n - 1, k) + k) % n;
    }

}
