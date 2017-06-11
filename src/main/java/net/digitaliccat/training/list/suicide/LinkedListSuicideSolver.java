package net.digitaliccat.training.list.suicide;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.ListIterator;

@Component
@Qualifier("linkedListSolver")
public class LinkedListSuicideSolver implements SuicideSolverInterface {

    @Override
    public int solve(int numberOfPeople, int startingPoint, int numberToBeSkipped) {

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            list.add(i);
        }

        int count = 0;
        ListIterator<Integer> itr = list.listIterator(startingPoint);
        while (list.size() > 1) {

            if (itr.hasNext()) {
                itr.next();
            } else {
                itr = list.listIterator();
                itr.next();
            }
            ++count;

            if (count == numberToBeSkipped) {
                itr.remove();
                count = 0;
            }
        }

        return list.get(0);
    }

}
