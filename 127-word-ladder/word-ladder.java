class Solution {
    // Helper class to store the current word and the distance (steps so far)
    class Pair {
        String s;
        int dis;
        Pair(String s, int dis) {
            this.s = s;
            this.dis = dis;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Store all words in a set for O(1) lookup
        Set<String> words = new HashSet<>(wordList);

        // If endWord is not in the dictionary, no valid transformation exists
        if (!words.contains(endWord)) return 0;

        // BFS queue initialization
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            // If we reach the endWord, return the number of steps
            if (p.s.equals(endWord)) return p.dis;

            // Try changing every character in the current word
            for (int i = 0; i < p.s.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    String str = p.s.substring(0, i) + c + p.s.substring(i + 1);

                    // If new word exists in dictionary, it’s a valid move
                    if (words.contains(str)) {
                        q.add(new Pair(str, p.dis + 1));
                        words.remove(str); // Avoid revisiting the same word
                    }
                }
            }
        }

        // If we never reach endWord
        return 0;
    }
}