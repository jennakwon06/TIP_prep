package Q6_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AdvanceThruAnArray {


    public static boolean isEndReachable(List<Integer> maxSteps) {
        if (maxSteps.size() == 1) {
            return maxSteps.get(0) >= 0;
        }

        int curMaxReachable = maxSteps.get(0);

        for (int i = 1; i < maxSteps.size(); i++) {
            // keep track of the max reachable capacity; it is the max reachable capacity of previous elem minus one,
            // or the max reachable capacity of the current element 
            curMaxReachable = Math.max(curMaxReachable - 1, maxSteps.get(i));
            if (curMaxReachable <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * EPI Solution
     */
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestReachSoFar = 0, lastIndex = maxAdvanceSteps.size() - 1;
        for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex; ++i) {
            furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
        }
        return furthestReachSoFar >= lastIndex;
    }


    public static void main(String[] args) {
        assert(isEndReachable(Arrays.asList(2, 3, 1, 1, 4)));
        assert(!isEndReachable(Arrays.asList(3, 2, 1, 0, 4)));
        assert(!isEndReachable(Arrays.asList(3, 2, 1, -10, 4)));
        assert(isEndReachable(Arrays.asList(2, 3, -1, -1, 4)));
        assert(!isEndReachable(Arrays.asList(2, 2, -1, -1, 100)));

        Random r = new Random();
        int n;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        } else {
            n = r.nextInt(1000) + 1;
        }
        List<Integer> maxAdvanceSteps = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            maxAdvanceSteps.add(r.nextInt(10));
        }
        System.out.println(maxAdvanceSteps.toString());
        System.out.println(isEndReachable(maxAdvanceSteps));
        System.out.println(canReachEnd(maxAdvanceSteps));

    }

}
