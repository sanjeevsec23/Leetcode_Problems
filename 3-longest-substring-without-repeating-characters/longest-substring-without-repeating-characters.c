#include <string.h>
#include <stdbool.h>

int max(int a, int b) {
    return a > b ? a : b;
}

int lengthOfLongestSubstring(char* s) {
    int n = strlen(s);
    int res = 0;
    for (int i = 0; i < n; i++) {
        bool vis[128] = {false};
        for (int j = i; j < n; j++) {
            if (vis[s[j]]) {
                break;
            } else {
                res = max(res, j - i + 1);
                vis[s[j]] = true;
            }
        }
    }
    return res;
}