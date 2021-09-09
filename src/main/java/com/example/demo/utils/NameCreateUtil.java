package com.example.demo.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class NameCreateUtil {

    static String[] firstName = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱",
            "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水",
            "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁",
            "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
            "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹" };

    static String[] lastName = { "子", "璇", "淼", "国", "栋", "夫", "子", "瑞", "堂", "甜", "敏", "尚", "国", "贤", "贺", "祥", "晨",
            "涛", "昊", "轩", "易", "轩", "益", "辰", "益", "帆", "益", "冉", "瑾", "春", "瑾", "昆", "春", "齐", "杨", "文", "昊", "东",
            "东", "雄", "霖", "浩", "晨", "熙", "涵", "溶", "溶", "冰", "枫", "欣", "欣", "宜", "豪", "欣", "慧", "建", "政", "美", "欣",
            "淑", "慧", "文", "轩", "文", "杰", "欣", "源", "忠", "林", "榕", "润", "欣", "汝", "慧", "嘉", "新", "建", "建", "林", "亦",
            "菲", "林", "冰", "洁", "佳", "欣", "涵", "涵", "禹", "辰", "淳", "美", "泽", "惠", "伟", "洋", "涵", "越", "润", "丽", "翔",
            "淑", "华", "晶", "莹", "凌", "晶", "苒", "溪", "雨", "涵", "嘉", "怡", "佳", "毅", "子", "辰", "佳", "琪", "紫", "轩", "瑞",
            "辰", "昕", "蕊", "萌", "明", "远", "欣", "宜", "泽", "远", "欣", "怡", "佳", "怡", "佳", "惠", "晨", "茜", "晨", "璐", "运",
            "昊", "汝", "鑫", "淑", "君", "晶", "滢", "润", "莎", "榕", "汕", "佳", "钰", "佳", "玉", "晓", "庆", "一", "鸣", "语", "晨",
            "添", "池", "添", "昊", "雨", "泽", "雅", "晗", "雅", "涵", "清", "妍", "诗", "悦", "嘉", "乐", "晨", "涵", "天", "赫", "玥",
            "傲", "佳", "昊", "天", "昊" };

    static String[] companyName = { "鹏信", "诺比", "弘宇", "赫度", "颂臣", "振鹏", "中科", "山东大成", "金山", "索兔", "和川教学", "市伊登", "巨会",
            "卓为", "亚太", "奇智", "思服", "凯路", "皓永", "星畅", "宏联", "春桥", "近利", "朝腾", "立友", "巨赫", "着陆", "贵凯", "凡象", "竞成", "杜优",
            "锐高", "米孚", "纵腾", "锐成", "梵脉", "星睿", "微握", "卓为", "谷尼国际", "友耐", "酷象", "睿函", "墨韬", "国极", "华伟", "函智", "赛意",
            "赫牛", "歌国", "金泓", "思服", "胜元", "问源", "威森", "穿越", "卓伙", "创势纪", "博和利", "巨焦", "华宇", "奇智", "谷尼", "金诚", "金算盘",
            "帝贸", "睿章", "同益", "泰豪", "劲士", "敖凯", "酷象", "云创", "微领", "卓象", "零零壹", "硕翔", "精睿", "创赢智能", "丽泰", "掌沃", "匹卓",
            "万政数码", "凌智", "Panda", "喆鹰", "崛创", "易控", "成方圆", "鼓点", "易掌", "倡德", "贯乐", "青穗", "格瑞莱", "酷亿", "络际飞", "巨罡",
            "远光", "大宇", "欧美", "三硕", "隆杰", "和谐", "数码人", "淮安七夕", "东智", "纬创", "杰奇", "索澜", "倡韵", "海梦泽", "峰尖", "泓佳", "启明",
            "卓雀", "酷象", "虎硕", "帅梦", "哈派", "凯恒", "云赞", "弘煜", "贯乐", "维沃", "启橙", "中程", "深蓝宇", "壹源", "胜海", "芽慧", "赛象", "富将",
            "青谷", "慧至", "微握", "卓普", "冠群金辰", "虎硕", "世纪高蓝", "纬创", "亿峰", "安捷伦", "科诗特", "创惠", "亿联", "智维", "墨韬", "有生", "赫米",
            "钛萍洋", "搜企", "中维", "族普", "智冠", "联道", "赛象", "蝶普", "万兴", "斯维尔", "匹卓", "领睿", "墨克", "巨瓦", "卓雀", "三丰电子", "五凡",
            "奥凯", "臻游", "卓象", "龙辉", "函智", "亿源", "易峰", "中睿", "飘普", "卓园", "东智", "安淼", "增星", "劲火", "锐成", "兼程", "朋聪", "米孚",
            "磊伦", "橙红", "臻智", "汇达", "鹰", "润物", "智敏", "巨焦", "亿源", "朝腾", "劲火", "御瑜", "成海", "纵腾" };

    static String[] companyOtherName = { "科技有限公司", "智能科技有限公司", "科技股份有限公司", "化工股份有限公司", "钢铁股份有限公司", "智能制造有限公司",
            "科技股份有限公司" };

    /**
     * 随机生成姓名
     *
     * @return
     */
    public static String makeName() {
        /*
         * Random random = new Random(); int firstNameNum =
         * random.nextInt(firstName.length); int lastNameNum1 =
         * random.nextInt(lastName.length); int lastNameNum2 =
         * random.nextInt(lastName.length);
         */

        SecureRandom random;
        int firstNameNum = 0;
        int lastNameNum1 = 0;
        int lastNameNum2 = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            firstNameNum = random.nextInt(firstName.length);
            lastNameNum1 = random.nextInt(lastName.length);
            lastNameNum2 = random.nextInt(lastName.length);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return firstName[firstNameNum] + lastName[lastNameNum1] + lastName[lastNameNum2];
    }

    /**
     * 随机生成公司名称
     *
     * @return
     */
    public static String makeCompanyName() {
        /*
         * Random random = new Random(); int companyNameNum =
         * random.nextInt(companyName.length); int companyOtherNameNum =
         * random.nextInt(companyOtherName.length);
         */

        SecureRandom random;
        int companyNameNum = 0;
        int companyOtherNameNum = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            companyNameNum = random.nextInt(companyName.length);
            companyOtherNameNum = random.nextInt(companyOtherName.length);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return companyName[companyNameNum] + companyOtherName[companyOtherNameNum];
    }

    /**
     * 随机生成客户编号
     *
     * @return
     */
    public static String makeCustomerNumber() {
        /*
         * Random random = new Random(); Integer number = random.nextInt(90) +
         * 10;
         */

        SecureRandom random;
        Integer number = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            number = random.nextInt(90) + 10;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return System.currentTimeMillis() + String.valueOf(number);
    }

    /**
     * 随机生成工单号
     *
     * @return
     */
    public static String makeWordOrderId() {
        // Random random = new Random();
        // Integer number = random.nextInt(90) + 10;
        SecureRandom random;
        Integer number = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            number = random.nextInt(90) + 10;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "1903" + System.currentTimeMillis() + String.valueOf(number);
    }

    /**
     * 随机生成台区编码
     *
     * @return
     */
    public static String makeTransformerCode() {
        // Random random = new Random();
        // Integer number = random.nextInt(90) + 10;
        SecureRandom random;
        Integer number = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            number = random.nextInt(90) + 10;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "19" + System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        String name = makeName();
        String companyName = makeCompanyName();
        System.out.println(name);
        System.out.println(companyName);
        System.out.println(makeCustomerNumber());
    }
}