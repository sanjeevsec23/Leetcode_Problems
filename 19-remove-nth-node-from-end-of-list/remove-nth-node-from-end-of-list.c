struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
    // Step 0: create dummy node pointing to head
    struct ListNode* dummy = (struct ListNode*)malloc(sizeof(struct ListNode));
    dummy->val = 0;
    dummy->next = head;
    
    struct ListNode* fast = dummy;
    struct ListNode* slow = dummy;
    
    // Step 1: Move fast n+1 steps ahead
    for (int i = 0; i <= n; i++) {
        fast = fast->next;
    }
    
    // Step 2: Move both pointers until fast reaches the end
    while (fast != NULL) {
        fast = fast->next;
        slow = slow->next;
    }
    
    // Step 3: Delete the target node
    struct ListNode* temp = slow->next;
    slow->next = slow->next->next;
    free(temp); 
    
    // Step 4: Return new head
    struct ListNode* newHead = dummy->next;
    free(dummy); 
    return newHead;
}