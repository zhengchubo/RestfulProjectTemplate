package com.justin4u.util;

import com.justin4u.exception.ClientReportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-23</pre>
 */
public class BlockingQueueUtil {

    private static Logger logger = LoggerFactory.getLogger(BlockingQueueUtil.class);

    private static BlockingQueue<ClientReportException> QUEUE;

    private static final int DEFAULT_CAPACITY = 50;

    private static int capacity = 300;

    public static boolean produce(ClientReportException ex) {
        return QUEUE.offer(ex);
    }

    public static ClientReportException consume() {
        return QUEUE.poll();
    }

    public static List<ClientReportException> consume(int num) {
        List<ClientReportException> result = new ArrayList<>();
        if (num <= 0) {
            return result;
        }
        QUEUE.drainTo(result, num);
        return result;
    }

    public static int getSize() {
        return QUEUE.size();
    }

    public static int getCapacity() {
        return capacity;
    }

    public static void main(String[] args) {
        List<ClientReportException> sources = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClientReportException item = new ClientReportException();
            item.setMessage("" +i );
            sources.add(item);
        }
        QUEUE.addAll(sources);
        List<ClientReportException> list = QUEUE.stream().limit(-1).collect(Collectors.toList());
        System.out.println(list.size());
        System.out.println(list.get(0).getMessage());
    }
}
