package net.digitaliccat.training.list.suicide;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LinkedLIstSuicideSolverTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 4, 0, 2, 0 },
                { 34, 0, 7, 18 },
                { 39, 5, 7, 21 },
                { 7, 6, 24, 6 },
                { 41, 0, 3, 30 }
        });
    }

    private int numberOfPeople;
    private int startingPoint;
    private int numberToBeSkipped;
    private int answer;
    private LinkedListSuicideSolver sut;

    public LinkedLIstSuicideSolverTest(int numberOfPeople, int startingPoint, int numberToBeSkipped, int answer) {
        this.numberOfPeople = numberOfPeople;
        this.startingPoint = startingPoint;
        this.numberToBeSkipped = numberToBeSkipped;
        this.answer = answer;

        sut = new LinkedListSuicideSolver();
    }

    @Test
    public void test() {
        assertEquals(answer, sut.solve(numberOfPeople, startingPoint, numberToBeSkipped));
    }
}
