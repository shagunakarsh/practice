package com.shagunakarsh.datastructures;

import java.util.Scanner;

public class SegmentTree {
    int maxLength;
    int []segmentTree;
    int []lazy;

    public SegmentTree(int size) {
        this.maxLength =  4*size;
        segmentTree = new int[maxLength];
        lazy = new int[maxLength];
    }

    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int []arr = new int[n];
        for (int i=0;i<n;++i) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("n="+ n);
        SegmentTree segmentTree = new SegmentTree(n);
        segmentTree.build(0, n-1, 0, arr);
        segmentTree.print(0, n-1, 0);

        int q = scanner.nextInt();
        while(q-- > 0) {
            int l=scanner.nextInt();
            int r=scanner.nextInt();
            System.out.println("("+l+","+r+")="+ segmentTree.find(0, n-1, l, r, 0));
        }

        // Update pos 5 to value 100
//        System.out.println("Update pos 5 to 100");
//        int res = segmentTree.update_pos_x_to_value_y(0, n-1, 0, 5, 100);
//        System.out.println("res= "+res);
//
//        // Query sum for range (5,6)
//        System.out.println("Query for range (5,6)");
//        res = segmentTree.find(0, n-1, 5, 6, 0);
//        System.out.println("res="+res);

        segmentTree.print(0, n-1, 0);

        // Update range (1,3) to 10
        System.out.println("Update range (1,3) to 10");
        int res = segmentTree.update_range(0, n-1, 0, 1, 3, 10);
        System.out.println("res="+res);

        segmentTree.print(0, n-1, 0);

        // Query sum for range (1,3)
        System.out.println("Query for range (1,3)");
        res = segmentTree.find(0, n-1, 1, 3, 0);
        System.out.println("res="+res);

    }

    int update_pos_x_to_value_y(int l, int r, int cur, int x, int y) {
        //leaf node
        if (l == r && l==x) return segmentTree[cur] = y;
        // x outside the range
        else if (l>x || r<x) return segmentTree[cur];
        // x within range
        else return segmentTree[cur] = merge(update_pos_x_to_value_y(l, mid(l, r), lChild(cur), x, y),
                    update_pos_x_to_value_y(mid(l, r)+1, r, rChild(cur), x, y));
    }

    /**
     * Update the range from (ll,rr) to value y
     * TODO: fix the bug for range update
     *
     * @param l
     * @param r
     * @param cur
     * @param ll
     * @param rr
     * @return
     */
    int update_range(int l, int r, int cur, int ll, int rr, int y) {
        // (ll, rr) within range
        if (ll>=l && rr<=r) {
            lazy[cur]=y;
            lazy_update(l, r, y);
            return segmentTree[cur];
        }
        // (ll, rr) complete outside current range
        else if (r <ll || l >rr) {
            return segmentTree[cur];
        }
        // overlapping range
        else {
            return  segmentTree[cur] = merge(update_range(l, mid(l, r), lChild(cur), ll, rr, y),
                    update_range(mid(l, r)+1, r, rChild(cur), ll, rr, y));
        }
    }

    int merge(int a, int b) {
        return a+b;
    }

    void lazy_update(int ll, int rr, int cur) {
        if (lazy[cur]==0) return;
        int range_size = rr-ll+1;
        int val_in_range = segmentTree[cur];
        // update
        System.out.println("Updating Lazily for Node: " + cur + " by " + range_size*lazy[cur]);
        segmentTree[cur] = val_in_range + range_size*lazy[cur];

        //push down for non-leaf nodes
        if (ll!=rr) {
            System.out.println("Setting lazy val for "+ lChild(cur)+ " "+ rChild(cur));
            lazy[lChild(cur)] = lazy[cur];
            lazy[rChild(cur)] = lazy[cur];
        }

        //reset
        lazy[cur] = 0;
    }

    int find(int l, int r, int ll, int rr, int cur) {
        System.out.println("Searching for" + "("+ll+","+rr+") in ("+l+","+r+")");
        lazy_update(ll, rr, cur);

        if (l>=ll && r<=rr) return segmentTree[cur];
        else if (rr <= mid(l,r)) {
                return find(l, mid(l, r), ll, rr, lChild(cur));
        } else if (ll > mid(l,r)) {
                return find(mid(l, r) + 1, r, ll, rr, rChild(cur));
        } else {
                // merge
                return merge(find(l, mid(l,r), ll, rr, lChild(cur)), find( mid(l,r)+1, r, ll, rr, rChild(cur)));
        }
    }

    int build(int l, int r, int curr, int []arr) {
        //System.out.println("l="+l+" r="+r+" curr="+curr);
        if (curr == -1) return -1;
        if (l==r) {
            segmentTree[curr]=arr[l];
        } else {
            segmentTree[curr] = merge(build(l, mid(l,r), lChild(curr), arr),
                    build(mid(l,r)+1, r, rChild(curr), arr));
        }
        //System.out.println(curr+" ("+l+","+r+") =" + segmentTree[curr]);
        return segmentTree[curr];
    }

    int print(int l, int r, int i) {
        //System.out.println("Node: "+i);
        if (l==r) {
            System.out.println("Node:" + i + " ("+l+","+r+")=" + segmentTree[i]);
            return segmentTree[i];
        }
        else {
            int sum= print(l, mid(l,r), lChild(i)) + print(mid(l,r)+1, r, rChild(i));
            System.out.println("Node:"+ i + " ("+l+","+r+")="+sum);
            return sum;
        }
    }

    int lChild(int i) {
        int lc= 2*i+1;
        return lc < maxLength-1 ? lc : -1;
    }

    int rChild(int i) {
        int rc = 2*i+2;
        return rc < maxLength-1 ? rc: -1;
    }

    int mid(int l, int r) {
        return l + ((r-l)/2); // (2*l - l +r)/2
    }
}
