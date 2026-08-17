/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        // Har list ka first node heap mein daalo
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {

            // Sabse chhota node nikalo
            ListNode node = pq.poll();

            // Answer mein add karo
            curr.next = node;
            curr = curr.next;

            // Us node ka next bhi heap mein daalo
            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }
}