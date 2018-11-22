package problems;

/**
 * https://www.geeksforgeeks.org/minimum-cost-to-buy-n-kilograms-of-sweet-for-m-persons/
 */
public class NSweetMPerson {

    int findMinCost(int totalWeight, int personCount, int[] sweets) {

        if (totalWeight < 0 || personCount < 0 || totalWeight < personCount) return -1;

        if (totalWeight == 0) return personCount == 0 ? 0 : -1;

        if (personCount == 1)
            return (totalWeight < sweets.length && sweets[totalWeight - 1] > 0) ?
                    sweets[totalWeight - 1] : -1;

        //=>personCount > 1 && totalWeight >= personCount
        int min = -1;

        for (int lastPacketSize = 1;
             lastPacketSize <= (totalWeight - (personCount - 1)) && lastPacketSize < sweets.length;
             ++lastPacketSize) {

            if (sweets[lastPacketSize - 1] <= 0) continue;
            int currentMin = findMinCost(totalWeight - lastPacketSize, personCount - 1, sweets);
            if (currentMin == -1) continue;
            currentMin += sweets[lastPacketSize - 1];
            if (currentMin < min || min == -1)
                min = currentMin;
        }
        return min;
    }
}