package com.ankit.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/* 819. Most Common Word
  Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.
  The words in paragraph are case-insensitive and the answer should be returned in lowercase.
  Note that words can not contain punctuation symbols.
  <pre>{@code
  Example 1:

  Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
  Output: "ball"
  Explanation:
  "hit" occurs 3 times, but it is a banned word.
  "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
  Note that words in the paragraph are not case-sensitive,
  that punctuation is ignored (even if adjacent to words, such as "ball,"),
  and that "hit" isn't the answer even though it occurs more because it is banned.

  Example 2:
  Input: paragraph = "a.", banned = []
  Output: "a"

  Constraints:
  1 <= paragraph.length <= 1000
  paragraph consists of English letters, space ' ', or one of the symbols: "!?',;.".
  0 <= banned.length <= 100
  1 <= banned[i].length <= 10
  banned[i] consists of only lowercase English letters.
  }</pre>
*/
class MostCommonWord {

  public static void main(String[] args) {
    MostCommonWord mcw = new MostCommonWord();
    String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
    String[] banned = {"hit"};
    System.out.println(mcw.mostCommonWord(paragraph, banned));
  }


  public String mostCommonWord(String paragraph, String[] banned) {
    List<String> bannedList = Arrays.asList(banned);
    Map<String, Long> map = Arrays.stream(paragraph.split("\\W+"))
        .map(str -> str.toLowerCase().replaceAll("[!?',;.]", ""))
        .filter(str -> !bannedList.contains(str))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    return map.entrySet()
        .stream()
        .max((entryA, entryB) -> entryA.getValue().compareTo(entryB.getValue()))
        .map(entry -> entry.getKey())
        .orElse("");
  }
}