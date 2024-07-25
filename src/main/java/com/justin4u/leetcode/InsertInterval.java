package com.justin4u.leetcode;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 * <p>
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-07-09</pre>
 */
public class InsertInterval {

    //题意是给你一组有序区间，和一个待插入区间，让你待插入区间插入到前面的区间中，我们分三步走：
    //
    //    首先把有序区间中小于待插入区间的部分加入到结果中；
    //
    //    其次是插入待插入区间，如果有交集的话取两者交集的端点值；
    //
    //    最后把有序区间中大于待插入区间的部分加入到结果中；
    public List<Interval> solution(List<Interval> list, Interval newItem) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.singletonList(newItem);
        }

        List<Interval> result = new ArrayList<>();
        int i = 0;
        int length = list.size();
        while (i < length) {
            Interval item = list.get(i);
            if (item.end < newItem.start) {
                result.add(item);
            } else {
                break;
            }
            i++;
        }
        while (i < length) {
            Interval item = list.get(i);
            if (item.start <= newItem.end) {
                newItem.start = Math.min(item.start, newItem.start);
                newItem.end = Math.max(item.end, newItem.end);
            } else {
                break;
            }
            i++;
        }
        result.add(newItem);
        while (i < length) {
            result.add(list.get(i));
            i++;
        }

        return result;
    }



    public static void main(String[] args) {
        InsertInterval instance = new InsertInterval();
        List<Interval> input = Interval.transform("[[1,3],[6,9]]");
        Interval newItem = new Interval(2, 5);
        List<Interval> result = instance.solution(input, newItem);
        String str = result.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(String.format("[%s]", str).equals("[[1,5],[6,9]]"));

        List<Interval> input2 = Interval.transform("[[1,2],[3,5],[6,7],[8,10],[12,16]]");
        Interval newItem2 = new Interval(4, 8);
        List<Interval> result2 = instance.solution(input2, newItem2);
        String str2 = result2.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println(String.format("[%s]", str2).equals("[[1,2],[3,10],[12,16]]"));
    }


}
