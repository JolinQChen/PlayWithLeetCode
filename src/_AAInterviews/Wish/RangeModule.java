package _AAInterviews.Wish;


public class RangeModule {
    class RangeNode {
        int start;
        int end;
        RangeNode next;
        RangeNode prev;
        public RangeNode (int left, int right) {
            start = left;
            end = right;
        }
    }

    private RangeNode head;

    public RangeModule() {
        head = new RangeNode(-1,-1);
    }

    public void addRange(int left, int right) {
        RangeNode p = head.next;
        RangeNode pre = head;
        while (p!=null) {
            if(p.end>=left) {
                if(p.start>right) {
                    // no overlap, add to prev
                    RangeNode curr = new RangeNode(left, right);
                    pre.next = curr;
                    curr.prev = pre;
                    p.prev = curr;
                    curr.next = p;
                    return;
                }
                else if(p.start == right) {
                    p.start = left;
                    return;
                }
                else {
                    if(p.start>left) p.start = left;
                    if(p.end>=right) return;
                    else {
                        RangeNode init = p;
                        while(p.next!=null && p.next.start<=right) p=p.next;
                        if(p.end<=right) {
                            init.end = right;
                            init.next = p.next;
                            if(p.next!=null) p.next.prev = init;
                            return;
                        }
                        else {
                            init.end = p.end;
                            init.next = p.next;
                            if(p.next!=null) p.next.prev = init;
                            return;
                        }
                    }
                }
            }
            p = p.next;
            pre = pre.next;
        }
        RangeNode curr = new RangeNode(left, right);
        pre.next = curr;
        curr.prev = pre;
    }

    public boolean queryRange(int left, int right) {
        RangeNode p = head.next;
        while (p!=null){
            if(p.start<=left){
                if(p.end>=right) return true;
                else if(p.end<left) p = p.next;
                else return false;
            }
            else return false;
        }
        return false;
    }

    public void removeRange(int left, int right) {
        RangeNode p = head.next;
        RangeNode pre = head;
        while (p!=null) {
            if(p.end>left) {
                if(p.start<left) {
                    if(p.end>right) {
                        RangeNode curr = new RangeNode(p.start,left);
                        p.start = right;
                        pre.next = curr;
                        curr.prev = pre;
                        curr.next = p;
                        p.prev = curr;
                        return;
                    }

                    RangeNode init = p;
                    while (p.next!=null && p.next.start<right) p = p.next;
                    if(p.end<=right) {
                        init.end = left;
                        init.next = p.next;
                        if(p.next!=null) p.next.prev = init;
                        return;
                    }
                    else {
                        init.end = left;
                        p.start = right;
                        init.next = p;
                        p.prev = init;
                        return;
                    }

                }
                else {
                    if(p.start>=right)return;
                    while (p.next!=null && p.next.start<right) p=p.next;
                    if(p.end<=right) {
                        pre.next = p.next;
                        if(p.next!=null)p.next.prev = pre;
                        return;
                    }
                    else {
                        p.start = right;
                        pre.next = p;
                        return;
                    }
                }
            }
            p=p.next;
            pre=pre.next;
        }
    }
}
