package Q6_10;

/**
 * Problem
 *
 * Given a permutation P (indices of permuted array), apply it to A
 */
public class PermuteElemsInArray {

    // note - if you want to restore the elements, then set charArr[curInd] = intArr[curInd] -  intArr.len
    // and   restore it at the end
    public static void permuteElements(char[] charArr, int[] intArr) {
        if (charArr.length != intArr.length) return;

        for (int i = 0; i < charArr.length; i++) {
            int curInd = i;
            char toWrite = charArr[curInd];

            while (intArr[curInd] != -1) {
                int nextInd = intArr[curInd];
                char temp = charArr[nextInd]; //save before deleting
                charArr[nextInd] = toWrite;
                intArr[curInd] = -1; // mark as visited
                curInd = nextInd;
                toWrite = temp;
            }
        }


    }


    public static void main(String[] args) {

        char[] charArr = {'a', 'b', 'c', 'd'};
        int[] intArr = {3, 0, 1, 2};
        permuteElements(charArr, intArr);
        System.out.println(charArr);

        char[] charArr2 = {'a', 'b', 'c', 'd'};
        int[] intArr2 = {2, 0, 1, 3};
        permuteElements(charArr2, intArr2);
        System.out.println(charArr2);

    }
}
