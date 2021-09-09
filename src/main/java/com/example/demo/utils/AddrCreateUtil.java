package com.example.demo.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class AddrCreateUtil {
    // 路
    static String[] road = { "爱国路", "安边路", "安波路", "安德路", "安汾路", "安福路", "安国路", "安化路", "安澜路", "安龙路", "安仁路", "安顺路", "安亭路",
            "安图路", "安业路", "安义路", "安远路", "鞍山路", "鞍山支路", "澳门路", "八一路", "巴林路", "白城路", "白城南路", "白渡路", "白渡桥", "白兰路", "白水路",
            "白玉路", "百安路（方泰镇）", "百官街", "百花街", "百色路", "板泉路", "半淞园路", "包头路", "包头南路", "宝安公路", "宝安路", "宝昌路", "宝联路", "宝林路",
            "宝祁路", "宝山路", "宝通路", "宝杨路", "宝源路", "保德路", "保定路", "保屯路", "保屯路", "北艾路", };
    // 小区名
    static String[] home = { "金色家园", "耀江花园", "阳光翠竹苑", "东新大厦", "溢盈河畔别墅", "真新六街坊", "和亭佳苑", "协通公寓", "博泰新苑", "菊园五街坊",
            "住友嘉馨名园", "复华城市花园", "爱里舍花园", "御水湾", "乐府江南", "浅水湾", "西山枫林", "书苑名家", "风华里", "铂悦府", "墨香楼", "凌月馆", "优山美诗",
            "舞魅楼", "清情楼", "倾梦轩", "长者小镇", "江南摩卡", "年华里", "满庭芳", "繁华里", "四季云顶", "盛世江南", "七里香都", "清江山水", "翠竹园", "忘忧阁",
            "花半里", "西子湾倾月阁", "紫光阁", "逍遥谷", "汤臣一品", "花果园", "德邻居", "三水一生", "幽情阁", "东日晴好", "云烟阁", "小山丛桂轩", "蹈和馆", "丽诗趣院" };
    // 楼栋名称
    static String[] building = { "1栋", "2栋", "3栋", "4栋", "5栋", "6栋", "7栋", "8栋", "9栋", "10栋", "11栋", "12栋", "13栋",
            "14栋", "15栋", "16栋", "17栋", "18栋", "19栋", "20栋", "21栋", "22栋", "23栋", "24栋", "25栋", "26栋", "27栋", "28栋",
            "29栋", "30栋" };
    // 单元号
    static String[] unit = { "1单元", "2单元", "3单元", "4单元" };
    // 门牌号
    static String[] houseNumber = { "101室", "102室", "201室", "202室", "301室", "302室", "401室", "402室", "501室", "502室",
            "601室", "602室", "701室", "702室" };

    // 台变名称
    static String[] transFormerName = { "1#公变", "2#公变", "3#公变", "4#公变", "5#公变", "6#公变", "7#公变" };

    /**
     * 随机创建居民用户地址
     *
     * @return
     */
    public static String makeRandomAddr() {
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            int randomRoadNum = random.nextInt(road.length);
            int randomBuildingNum = random.nextInt(building.length);
            int randomUnitNum = random.nextInt(unit.length);
            int randomHomeNum = random.nextInt(home.length);
            int randomHouseNumber = random.nextInt(houseNumber.length);
            int num = random.nextInt(200);
            return road[randomRoadNum] + num + "号" + home[randomHomeNum] + building[randomBuildingNum]
                    + unit[randomUnitNum] + houseNumber[randomHouseNumber];
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Random random = new Random();
        return null;
    }

    /**
     * 随机创建专变用户地址
     */
    public static String makeTransformerRandomAddr() {
        /*
         * Random random = new Random(); int randomRoadNum =
         * random.nextInt(road.length); int num = random.nextInt(200); return
         * road[randomRoadNum] + num + "号";
         */
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            int randomRoadNum = random.nextInt(road.length);
            int num = random.nextInt(200);
            return road[randomRoadNum];
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String transformerName(){
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            int randomRoadNum = random.nextInt(home.length);
            int num = random.nextInt(200);
            return home[randomRoadNum] + num + "号";
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建多个同小区用户地址
     */
    public static List<String> makeHomeRandomAddr(int count) {

        // int maxNum = 21 * 4 * 14;1680

        List<String> addrs = new ArrayList<String>();
        // Random random = new Random();
        // int randomRoadNum = random.nextInt(road.length);
        // int num = random.nextInt(200);
        // int randomHomeNum = random.nextInt(home.length);

        SecureRandom random;
        int randomRoadNum = 0;
        int num = 0;
        int randomHomeNum = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            randomRoadNum = random.nextInt(road.length);
            num = random.nextInt(200);
            randomHomeNum = random.nextInt(home.length);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int randomBuildingNum = 0; randomBuildingNum < building.length; randomBuildingNum++) {
            for (int randomUnitNum = 0; randomUnitNum < unit.length; randomUnitNum++) {
                for (int randomHouseNumber = 0; randomHouseNumber < houseNumber.length; randomHouseNumber++) {
                    String addr = road[randomRoadNum] + num + "号" + home[randomHomeNum] + building[randomBuildingNum]
                            + unit[randomUnitNum] + houseNumber[randomHouseNumber];
                    addrs.add(addr);
                    if (addrs.size() >= count) {
                        return addrs;
                    }
                }
            }
        }

        return addrs;
    }

    /**
     * 创建同小区同单元多个用户地址
     */
    public static List<String> makeUnitRandomAddr(int count) {

        // int maxNum = 14;

        List<String> addrs = new ArrayList<String>();
        // Random random = new Random();
        // int randomRoadNum = random.nextInt(road.length);
        // int num = random.nextInt(200);
        // int randomHomeNum = random.nextInt(home.length);
        // int randomBuildingNum = random.nextInt(building.length);
        // int randomUnitNum = random.nextInt(unit.length);

        SecureRandom random;
        int num = 0;
        int randomHomeNum = 0;
        int randomBuildingNum = 0;
        int randomUnitNum = 0;
        int randomRoadNum = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            randomRoadNum = random.nextInt(road.length);
            num = random.nextInt(200);
            randomHomeNum = random.nextInt(home.length);
            randomBuildingNum = random.nextInt(building.length);
            randomUnitNum = random.nextInt(unit.length);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int randomHouseNumber = 0; randomHouseNumber < houseNumber.length; randomHouseNumber++) {
            String addr = road[randomRoadNum] + num + "号" + home[randomHomeNum] + building[randomBuildingNum]
                    + unit[randomUnitNum] + houseNumber[randomHouseNumber];
            addrs.add(addr);
            if (addrs.size() >= count) {
                return addrs;
            }
        }

        return addrs;
    }

    public static void main(String[] args) {
        String name = makeRandomAddr();
        String companyName = makeTransformerRandomAddr();
        System.out.println(name);
        System.out.println(companyName);

        List<String> homeAddrs = makeHomeRandomAddr(5000);
        for (String addr : homeAddrs) {
            System.out.println(addr);
        }

        List<String> homeUnitAddrs = makeHomeRandomAddr(14);
        for (String addr : homeUnitAddrs) {
            System.out.println(addr);
        }
    }
}