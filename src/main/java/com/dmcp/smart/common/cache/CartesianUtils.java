package com.dmcp.smart.common.cache;

import java.util.LinkedList;
import java.util.List;

/**
 * 计算几个集合的笛卡尔积
 * <p>
 * $Id: CartesianUtils.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/3/20 13:56
 */
public class CartesianUtils {

    /**
     * 计算集合的笛卡尔积
     *
     * @param result   计算结果
     * @param dimvalue 集合列表
     */
    public static void cartesian(List<Object[]> result, List<Object[]> dimvalue) {
        List<Object> linkedList = new LinkedList<>();

        doCartesian(result, 0, dimvalue, linkedList);
    }

    /**
     * 计算集合的笛卡尔积
     *
     * @param result    计算结果
     * @param dimension 集合的数量
     * @param dimvalue  集合列表
     */
    private static void doCartesian(List<Object[]> result, int dimension, List<Object[]> dimvalue, List<Object> list) {
        if (dimension < dimvalue.size() - 1) {
            if (dimvalue.get(dimension).length == 0)
                doCartesian(result, dimension + 1, dimvalue, list);
            else {
                for (int i = 0; i < dimvalue.get(dimension).length; i++) {
                    List<Object> tmp = new LinkedList<>();
                    tmp.addAll(list);
                    tmp.add(dimvalue.get(dimension)[i]);

                    doCartesian(result, dimension + 1, dimvalue, tmp);
                }
            }
        } else if (dimension == dimvalue.size() - 1) {
            if (dimvalue.get(dimension).length == 0) {
                result.add(list.toArray());
            } else {
                for (int i = 0; i < dimvalue.get(dimension).length; i++) {
                    List<Object> tmp = new LinkedList<>();
                    tmp.addAll(list);
                    tmp.add(dimvalue.get(dimension)[i]);
                    result.add(tmp.toArray());
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Object[]> result = new LinkedList<>();
        List<Object[]> dimvalue = new LinkedList<>();
        dimvalue.add(new Object[]{1, 2, 3});
        //dimvalue.add(new Object[]{4, 5});
        //dimvalue.add(new Object[]{99, 77});

        cartesian(result, dimvalue);

        System.out.println(result.size());

        for (Object[] objects : result) {
            System.out.print("obj=");
            for (Object obj : objects) {
                System.out.print(obj.toString());
                System.out.print("~");
            }
            System.out.println();
        }
    }

}
