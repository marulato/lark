package org.avalon.lark.common.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayUtils {

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static byte[] extract(byte[] array, int start, int end) {
        if (!isEmpty(array) && start <= end && end < array.length) {
            byte[] copy = new byte[end - start + 1];
            int index = 0;
            for (int i = start; i <= end; i++) {
                copy[index ++] = array[i];
            }
            return copy;
        } else {
            throw new IndexOutOfBoundsException("提取范围不在数组的长度范围之内");
        }
    }

    public static String[] extract(String[] array, int start, int end) {
        if (!isEmpty(array) && start <= end && end < array.length) {
            String[] copy = new String[end - start + 1];
            int index = 0;
            for (int i = start; i <= end; i++) {
                copy[index ++] = array[i];
            }
            return copy;
        } else {
            throw new IndexOutOfBoundsException("提取范围不在数组的长度范围之内");
        }
    }

    public static String[] removeEmpty(String[] array) {
        if (!isEmpty(array)) {
            List<String> list = new ArrayList<>(Arrays.asList(array));
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (StringUtils.isEmpty(item))
                    iterator.remove();
            }
            String[] result = new String[list.size()];
            list.toArray(result);
            return result;
        }
        return array;
    }

    public static char[] joint(char[] head, char[] tail) {
        if (head == null)
            return tail;
        if (tail == null)
            return head;
        char[] link = new char[head.length + tail.length];
        for (int i = 0; i < head.length; i++) {
            link[i] = head[i];
        }
        for (int j = 0; j < tail.length; j++) {
            link[head.length + j] = tail[j];
        }
        return link;
    }

    public static char[] joint(char[]... elements) {
        if (elements == null)
            return new char[0];
        char[] temp = new char[0];
        for (int i = 0; i < elements.length; i++) {
            temp = joint(temp, elements[i]);
        }
        return temp;
    }
}
