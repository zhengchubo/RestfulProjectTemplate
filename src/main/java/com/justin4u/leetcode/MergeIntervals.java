package com.justin4u.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * <p>
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
 * com.justin4u.leetcode
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-07-09</pre>
 */
public class MergeIntervals {

    //题意是给你一组区间，让你把区间合并成没有交集的一组区间。我们可以把区间按 start 进行排序，然后遍历排序后的区间，如果当前的 start 小于前者的 end，那么说明这两个存在交集，我们取两者中较大的 end 即可；否则的话直接插入到结果序列中即可。
    public List<Interval> solution(List<Interval> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        List<Interval> result = new ArrayList<>();
        list.sort((a, b) -> {
            if (a.start < b.start) {
                return -1;
            }
            if (a.start > b.start) {
                return 1;
            }
            return 0;
        });
        int start = list.get(0).start;
        int end = list.get(0).end;
        for (Interval item : list) {
            if (item.start <= end) {
                end = Math.max(end, item.end);
            } else {
                result.add(new Interval(start, end));
                start = item.start;
                end = item.end;
            }
        }
        result.add(new Interval(start, end));
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals instance = new MergeIntervals();
        List<Interval> input = Interval.transform("[[1,3],[2,6],[8,10],[15,18]]");
        List<Interval> result = instance.solution(input);
        String str = result.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(String.format("[%s]", str));
    }

}
