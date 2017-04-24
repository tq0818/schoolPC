package com.yuxin.wx.common;

import java.util.ArrayList;
import java.util.List;

public class TikuConstant {
    /* 题库做题试卷类型 -- 章节练习 */
    public static final String EXERCISE_TYPE_CHAPTER = "EXERCISE_TYPE_CHAPTER";
    /* 题库做题试卷类型 -- 快速做题 */
    public static final String EXERCISE_TYPE_15 = "EXERCISE_TYPE_15";
    /* 题库做题试卷类型 -- 模拟题/真题 */
    public static final String EXERCISE_TYPE_PAPER = "EXERCISE_TYPE_PAPER";
    /* 考试 */
    public static final String EXERCISE_TYPE_EXAM = "EXERCISE_TYPE_EXAM";
    /* 课后练习 */
    public static final String PRACTICE_AFTER_CLASS = "PRACTICE_AFTER_CLASS";
    /* 课后作业 */
    public static final String EXERCISE_TYPE_HOMEWORK = "EXERCISE_TYPE_HOMEWORK";
    /* 测验 */
    public static final String EXERCISE_TYPE_COURSE = "EXERCISE_TYPE_COURSE";
    /* 课后测验章节 */
    public static final String EXERCISE_TYPE_COURSECHAPTER = "EXERCISE_TYPE_COURSECHAPTER";
    /* 课后测验试卷 */
    public static final String EXERCISE_TYPE_COURSEPAPER = "EXERCISE_TYPE_COURSEPAPER";

    /** 题库做题试卷状态 -- 答题中 **/
    public static final String EXERCISE_STATUS_ING = "EXERCISE_STATUS_ING";
    /** 题库做题试卷状态 -- 暂停中 **/
    public static final String EXERCISE_STATUS_PAUSE = "EXERCISE_STATUS_PAUSE";
    /** 题库做题试卷状态 -- 完成 **/
    public static final String EXERCISE_STATUS_FINISH = "EXERCISE_STATUS_FINISH";

    /* 做题类型 */
    public static List<String> cstmExam = new ArrayList<String>();
    public static List<String> paperExam = new ArrayList<String>();

    static {
        TikuConstant.cstmExam.add(TikuConstant.EXERCISE_TYPE_CHAPTER);
        TikuConstant.cstmExam.add(TikuConstant.EXERCISE_TYPE_15);
        TikuConstant.cstmExam.add(TikuConstant.PRACTICE_AFTER_CLASS);
        TikuConstant.cstmExam.add(TikuConstant.EXERCISE_TYPE_COURSECHAPTER);

        TikuConstant.paperExam.add(TikuConstant.EXERCISE_TYPE_HOMEWORK);
        TikuConstant.paperExam.add(TikuConstant.EXERCISE_TYPE_PAPER);
        TikuConstant.paperExam.add(TikuConstant.EXERCISE_TYPE_EXAM);
        TikuConstant.paperExam.add(TikuConstant.EXERCISE_TYPE_COURSEPAPER);
    }

    /** 题的类型 */
    public static String TOPIC_TYPE_RADIO = "TOPIC_TYPE_RADIO";
    public static String TOPIC_TYPE_MULTIPLE = "TOPIC_TYPE_MULTIPLE";
    public static String TOPIC_TYPE_UNDEFINED = "TOPIC_TYPE_UNDEFINED";
    public static String TOPIC_TYPE_TRUE_FALSE = "TOPIC_TYPE_TRUE_FALSE";
    public static String TOPIC_TYPE_FILLING = "TOPIC_TYPE_FILLING";
    public static String TOPIC_TYPE_ANSWER = "TOPIC_TYPE_ANSWER";
    public static String TOPIC_TYPE_CASE = "TOPIC_TYPE_CASE";

    // 枚举类实例
    public static enum TopicType {
        TOPIC_TYPE_RADIO("TOPIC_TYPE_RADIO", "1"), TOPIC_TYPE_MULTIPLE("TOPIC_TYPE_MULTIPLE", "2"), TOPIC_TYPE_UNDEFINED("TOPIC_TYPE_UNDEFINED",
                "3"), TOPIC_TYPE_TRUE_FALSE("TOPIC_TYPE_TRUE_FALSE", "4"), TOPIC_TYPE_FILLING("TOPIC_TYPE_FILLING",
                        "5"), TOPIC_TYPE_ANSWER("TOPIC_TYPE_ANSWER", "6"), TOPIC_TYPE_CASE("TOPIC_TYPE_CASE", "7");

        private String name;

        private String value;

        TopicType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public String value() {
            return this.value;
        }

        public static String name(String value) {
            for (TopicType siteNo : TopicType.values()) {
                if (siteNo.value().equals(value)) {
                    return siteNo.name;
                }
            }
            return null;
        }
    }
}
