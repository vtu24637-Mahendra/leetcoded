import java.util.*;

class Solution {

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;

        int[] count = new int[n];
        int[] index = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) index[i] = i;

        mergeSort(nums, index, count, temp, 0, n - 1);

        List<Integer> res = new ArrayList<>();
        for (int x : count) res.add(x);

        return res;
    }

    void mergeSort(int[] nums, int[] index, int[] count, int[] temp, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(nums, index, count, temp, left, mid);
        mergeSort(nums, index, count, temp, mid + 1, right);
        merge(nums, index, count, temp, left, mid, right);
    }

    void merge(int[] nums, int[] index, int[] count, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (nums[index[j]] < nums[index[i]]) {
                temp[k++] = index[j++];
                rightCount++;
            } else {
                count[index[i]] += rightCount;
                temp[k++] = index[i++];
            }
        }

        while (i <= mid) {
            count[index[i]] += rightCount;
            temp[k++] = index[i++];
        }

        while (j <= right) {
            temp[k++] = index[j++];
        }

        for (int x = left; x <= right; x++) {
            index[x] = temp[x];
        }
    }
}