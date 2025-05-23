package edu.phystech.hw3;

import java.util.HashSet;
import java.util.Set;

public class SetUtils {
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> res = new HashSet<>(s1);
        res.addAll(s2);
        return res;
    }

    public static <E> Set<E> intersection(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> res = new HashSet<>(s1);
        res.retainAll(s2);
        return res;
    }

    public static <E> Set<E> difference(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> res = new HashSet<>(s1);
        res.removeAll(s2);
        return res;
    }

    public static <E> Set<E> symmetricDifference(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> union = union(s1, s2);
        Set<E> intersection = intersection(s1, s2);
        return difference(union, intersection);
    }

}
