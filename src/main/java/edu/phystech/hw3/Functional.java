package edu.phystech.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Functional {

    public static <T, R> List<R> map(List<? extends  T> collection, Function<T, R> function) {
        List<R> res = new ArrayList<>();
        for (T elem : collection) {
            res.add(function.apply(elem));
        }
        return res;
    }

    public static <T, R> T reduce(List<T> collection, BinaryOperator<T> operator, T identity) {
        T res = identity;
        for (T elem : collection) {
            res = operator.apply(res, elem);
        }
        return res;
    }
}




