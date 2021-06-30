class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        
        boolean isAll = true;
        
        for(int v:capital) {
            if(w<v) {
                isAll = false;
            }
        }
        
        
        if(isAll) {
            PriorityQueue<Integer> q = new PriorityQueue<Integer>();
            for(int p:profits) {
                q.add(p);
                if(q.size()>k) {
                    q.poll();
                }
            }
            for(int h:q) {
                w+=h;
            }
            return w;
        }
        
        int n = profits.length;
        // 0th index capital and 1 index profit
        PriorityQueue<int[]> projects = new PriorityQueue<>((p,q)->(p[0] - q[0]));
        for(int i=0;i<n;i++) {
            projects.add(new int[]{capital[i],profits[i]});
        }
        // [{0,1},{0,2},{0,3}]

        PriorityQueue<Integer> available = new PriorityQueue<>((x,y)->(y-x));
        while(k>0) {
            while(!projects.isEmpty() && projects.peek()[0]<=w) {
                available.add(projects.poll()[1]);
            }
            
            if(!available.isEmpty()) {
                w+=available.poll();
            } else {
                break;
            }
            k--;    
        }
        return w;
    }
}
