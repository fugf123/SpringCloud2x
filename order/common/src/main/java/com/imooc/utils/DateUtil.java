package com.imooc.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期的工具类
 */
public final class DateUtil implements Serializable {

    /**
     * 月的第一天
     */
    private static String MONTH_FIRST_DAY = "month_first_day";
    /**
     * 月的最后一天
     */
    private static String MONTH_LAST_DAY = "month_last_day";

    /**
     * 判断是否至少是下一个月
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean atLeastNextMonth(Date start, Date end) {
        if (start == null || end == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        calendar.setTime(end);
        if (calendar.get(Calendar.YEAR) > year) {
            return true;
        }
        return calendar.get(Calendar.MONTH) > month;
    }

    /**
     * 获取指定时间的下一周
     *
     * @param date
     */
    public static Date nextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        return calendar.getTime();
    }

    /**
     * 获取指定时间在这个月中的那一天
     *
     * @param date
     * @return
     */
    public static int getDayInMonth(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时间在这一天中的最后一秒
     *
     * @param date
     * @return
     */
    public static Date getEndDate(Date date) {
        //判断是否为空
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    private static Date getMonthDay(Date date, int month, String str) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        if (MONTH_FIRST_DAY.equals(str)) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        } else if (MONTH_LAST_DAY.equals(str)) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }
        return calendar.getTime();
    }

    /**
     * 获取下一个月的第一天
     *
     * @param date
     * @return
     */
    public static Date getNextMonthFirstDay(Date date) {
        return getMonthDay(date, 1, MONTH_FIRST_DAY);
    }

    /**
     * 获取下一个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getNextMonthLastDay(Date date) {
        return getMonthDay(date, 1, MONTH_LAST_DAY);
    }

    /**
     * 获取指定月份的第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        return getMonthDay(date, 0, MONTH_FIRST_DAY);
    }

    /**
     * 活动指定月份的最后一天
     *
     * @param date
     */
    public static Date getMonthLastDay(Date date) {
        return getMonthDay(date, 0, MONTH_LAST_DAY);
    }

    /**
     * 计算出初始天数
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static Integer getInitialDays(Date endDate, Date startDate) {
        if (endDate == null || startDate == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);

        int endDay = calendar.get(Calendar.DAY_OF_YEAR);
        int endYear = calendar.get(Calendar.YEAR);
        calendar.setTime(startDate);
        int startDay = calendar.get(Calendar.DAY_OF_YEAR);
        int startYear = calendar.get(Calendar.YEAR);
        return ((endYear - startYear) * 365) + endDay - startDay + 1;
    }

    /**
     * 将日期装换为String类型
     *
     * @param pattern
     * @param date
     * @return
     */
    public static String format(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取活动所属的周次(年计算)
     *
     * @param startDate
     * @return
     */
    public static Integer getSaicWeek(Date startDate) {
        if (startDate == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(startDate);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取下一周之后的第一天
     *
     * @param date
     * @return
     */
    public static Date getWeekFirstDay(Date date, int week) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 如果不是周末就加周, 如果是周末就要先减一周
        if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.WEEK_OF_YEAR, week);
        } else {
            calendar.add(Calendar.WEEK_OF_YEAR, week - 1);
        }
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定周之后的最后一天
     *
     * @param date
     * @param week
     * @return
     */
    public static Date getWeekLastDay(Date date, int week) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //如果不是周末就加一天  如果是周末就不加天
        if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1 + week);
            calendar.set(Calendar.DAY_OF_WEEK, 1);
        } else {
            calendar.add(Calendar.WEEK_OF_YEAR, week);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 添加时间
     *
     * @param date
     * @param field
     * @param number
     * @return
     */
    public static Date add(Date date, int field, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, number);
        return calendar.getTime();
    }

    /**
     * 判断指定时间是否是下一周
     *
     * @param current
     * @return
     */
    public static boolean atLeastNextWeek(Date current, Date nextWeek) {
        if (current == null || nextWeek == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        calendar.setTime(nextWeek);
        if (calendar.get(Calendar.YEAR) > year) {
            return true;
        } else if (calendar.get(Calendar.YEAR) < year) {
            return false;
        }
        return calendar.get(Calendar.WEEK_OF_YEAR) > week;
    }

    /**
     * 活动天数  天数 = 这一天是这一年里的第几天 + 年数 * 365
     *
     * @param date
     */
    public static int getDayWithoutYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR) + calendar.get(Calendar.YEAR) * 365;
    }


    /**
     * 获取两个日期之间天数的方法
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDaysBetween(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        calendar.setTime(start);
        cReturnDate.setTime(end);
        setTimeToMidnight(calendar);
        setTimeToMidnight(cReturnDate);
        long todayMs = calendar.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return millisecondsToDays(intervalMs);
    }


    /**
     * 获取时间
     *
     * @param field
     * @param date
     */
    public static int get(int field, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }


    /**
     * 判断两个日期是否是同一天
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isSameDay(Date start, Date end) {
        if (start == null || end == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(end);
        if (year != calendar.get(Calendar.YEAR)) {
            return false;
        }
        return calendar.get(Calendar.DAY_OF_YEAR) == day;
    }

    /**
     * 判断时间是否是指定时间之后
     *
     * @param startTime
     * @param endTime
     * @param day
     * @return
     */
    public static boolean isDaysLater(Date startTime, Date endTime, int day) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startTime);
        end.setTime(endTime);
        if (start.get(Calendar.YEAR) < end.get(Calendar.YEAR)) {
            return true;
        } else if (start.get(Calendar.YEAR) > end.get(Calendar.YEAR)) {
            return false;
        }
        return (start.get(Calendar.DAY_OF_YEAR) + day) < end.get(Calendar.DAY_OF_YEAR);
    }


    /**
     * 讲当时间格式化为 0时0分0秒
     *
     * @param date
     * @return
     */
    public static Date formatDay(Date date, int start) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (start == 0) {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        } else if (start == 1) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }
        return calendar.getTime();
    }


    /**
     * 判断是否是下周
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isNextWeek(Date start, Date end) {
        int startWeek = getSaicWeek(start);
        int endWeek = getSaicWeek(end);
        Long diff = end.getTime() - start.getTime();
        Long days = diff / (1000 * 60 * 60 * 24);
        return ((startWeek == endWeek - 1) && days < 14);
    }

    private static int millisecondsToDays(long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
    }

    private static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }


    /**
     * 获取指定时间在
     *
     * @param saicStartDate
     * @param days
     * @return
     */
    public static int getDayInWeek(Date saicStartDate, int days) {
        int i = DateUtil.get(Calendar.DAY_OF_WEEK, saicStartDate);
        int dayInWeek;
        switch (i) {
            case 1:
                dayInWeek = 1;
                break;
            default:
                dayInWeek = (i - 1 + days >= 7 ? 7 - i + 1 : (i + days) - 1) + 1;
        }
        return dayInWeek;
    }

    /**
     * 判断是否是同一周的
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isSameWeek(Date start, Date end) {
        int startWeek = getSaicWeek(start);
        int endWeek = getSaicWeek(end);
        Long diff = end.getTime() - start.getTime();
        Long days = diff / (1000 * 60 * 60 * 24);
        return (startWeek == endWeek && days < 7);
    }

    /**
     * 活动年和月
     *
     * @param date
     * @return
     */
    public static String getYearAndMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        StringBuilder stb = new StringBuilder();
        if (month >= 10) {
            stb.append(year).append("-").append(month);
        } else {
            stb.append(year).append("-0").append(month);
        }
        return stb.toString();
    }

    public static Integer getYear(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Integer getMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据给舒的时间集合查询出对应的时间日期数量
     *
     * @param date
     * @return
     */
    public static Set<Integer> getDayOfMonthList(List<Map<String, Date>> date) {
        Set<Integer> dayList = new HashSet<>();
        for (Map<String, Date> map : date) {
            Date start = map.get("start");
            Date end = map.get("end");
            int begin = get(Calendar.DAY_OF_MONTH, start);
            int finish = get(Calendar.DAY_OF_MONTH, end);
            for (int i = begin; i <= finish; i++) {
                dayList.add(i);
            }
        }
        return dayList;
    }
}
