package org.johnmusic.service.api.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bankuru on 12/2/17.
 */
public class listEqualsNoOrder {
    public static <T> boolean listEqualsNoOrder(List<T> l1, List<T> l2) {
        final Set<T> s1 = new HashSet<T>(l1);
        final Set<T> s2 = new HashSet<T>(l2);
        return s1.equals(s2);
    }
}
