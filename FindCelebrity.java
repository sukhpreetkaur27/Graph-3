// Time Complexity : O(n)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

public class FindCelebrity {
    public int findCelebrity(int n) {
        // pick a probable candidate
        int celeb = 0;
        for (int i = 1; i < n; i++) {
            if (!knows(i, celeb)) {
                celeb = i;
            }
        }
//        check if the probability holds true
        // i.e in case of graph with multiple connected components
        // where some group doesn't know the other or we have celebrities per group
        // return -1
        for (int i = 0; i < n; i++) {
            if (i != celeb && knows(celeb, i) || !knows(i, celeb)) {
                return -1;
            }
        }
        return celeb;
    }

    boolean knows(int a, int b) {
        return true;
    }

}
