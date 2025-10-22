/*If curr == 0 → count = high × position
If curr == 1 → count = (high × position) + (low + 1)
If curr > 1 → count = (high + 1) × position */

class Solution {
    public int countDigitOne(int n) {
        int b = n;
        int count = 0;
        int total = 0;

        while (b != 0) {
            int position = (int)Math.pow(10, count);  // ✅ position (1,10,100..)
            int curr = (n / position) % 10;           // ✅ current digit at pos

            count++;

            int a = count;
            int mul = 1;
            while (a != 0) {   // ✅ keep your loop
                mul = mul * 10;
                a--;
            }

            int left = n / mul;        // ✅ left digits (use n, not b)
            int right = n % position;  // ✅ right digits (use n, not b)

            if (curr == 0) {
                total += left * position;
            } else if (curr == 1) {
                total += (left * position) + (right + 1);
            } else if (curr > 1) {
                total += (left + 1) * position;
            }

            b = b / 10;   // ✅ keep this to move loop forward
        }

        return total;
    }
}