package com.example.demo.utils2;

import com.example.demo.ac.entity.*;
import com.example.demo.ac.mapper.*;
import com.example.demo.utils.AddrCreateUtil;
import com.example.demo.utils.NameCreateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class test {
//
//    @Autowired
//    public static CaseListMapper caseListMapper;
//    @Autowired
//    public static CaseDataReadMapper caseDataReadMapper;
//    @Autowired
//    public static CaseDataSslMapper caseDataSslMapper;
//    @Autowired
//    public static CaseDeviceMeterMapper caseDeviceMeterMapper;
//    @Autowired
//    public static CaseDeviceRtuMapper caseDeviceRtuMapper;
//    @Autowired
//    public static CaseCustomerMapper caseCustomerMapper;
//    @Autowired
//    public static CaseTransformerMapper caseTransformerMapper;
//    @Autowired
//    public static CaseTransfornerLinelossMapper caseTransfornerLinelossMapper;

    static Collection<Map> TG = new ArrayList<>();
    static Collection<Map> JLD = new ArrayList<>();
    static Collection<Map> USER = new ArrayList<>();
    static Collection<Map> METER = new ArrayList<>();
    static Collection<Map> CL= new ArrayList<>();
    static Collection<Map> ZD= new ArrayList<>();
    static Collection<Map> TG_METER= new ArrayList<>();
    static Collection<Map>V = new ArrayList<>();
    static Collection<Map> A= new ArrayList<>();
    static Collection<Map> Q = new ArrayList<>();
    static Collection<Map> PF= new ArrayList<>();
    static Collection<Map>LOSS_DATA= new ArrayList<>();


    static Pattern compile = Pattern.compile("[1-9]?\\d{3}/\\d{1,2}/\\d{1,2}");
    static Pattern compile2 = Pattern.compile("[1-9]?\\d{3}/\\d{1,2}/\\d{1,2} [0-5]?[0-9]?:[0-5]?[0-9]?:[0-5]?[0-9]?");
    static Map<Pattern, String> map = new HashMap<Pattern, String>(){{
        put(compile,"yyyy/MM/dd");
        put(compile2, "yyyy/MM/dd HH:mm:ss");
    }};

    public static LocalDateTime transformLocalData(String sx) throws ParseException {
        LocalDateTime reDate = null;
        ZoneId zoneId = ZoneId.systemDefault();
        for (Pattern p : map.keySet()){
            Matcher m = p.matcher(sx);
            if (m.matches()){
                SimpleDateFormat sdf = new SimpleDateFormat(map.get(p));
                Date parse = sdf.parse(sx);
                reDate = LocalDateTime.ofInstant(parse.toInstant(), zoneId);
                //System.out.println("时间转换格式为：" + map.get(p) + "     结果为：" + parse);
                break;
            }
        }
        return reDate;
    }
//
//    public static void main(String[] args) throws Exception {
//
//        //声明数据库实体
//        List<CaseCustomer> caseCustomers = new ArrayList<>();
//        List<CaseDataRead> caseDataReads = new ArrayList<>();
//        List<CaseDataSsl> caseDataSsls = new ArrayList<>();
//        List<CaseDeviceMeter> caseDeviceMeters = new ArrayList<>();
//        List<CaseDeviceRtu> caseDeviceRtus = new ArrayList<>();
//        List<CaseList> caseList = new ArrayList<>();
//        List<CaseTransformer> caseTransformers = new ArrayList<>();
//        List<CaseTransfornerLineloss> caseTransfornerLinelosses = new ArrayList<>();
//
//        Map<String, Collection<Map>> importNames = new HashMap<String, Collection<Map>>(){{
//            put("1-台区档案数据", TG);
//            put("2-计量点档案信息", JLD);
//            put("3-用户档案信息", USER);
//            put("4-电能表档案信息", METER);
//            put("5-测量点档案信息", CL);
//            put("6-终端档案信息", ZD);
//            put("7-台区总表用户表码数据", TG_METER);
//            put("8-电压表码数据", V);
//            put("9-电流表码数据", A);
//            put("10-有功、无功功率", Q);
//            put("11-功率因数", PF);
//            put("12-台区线损数据", LOSS_DATA);
//        }};
//
//
//        String dicPath = "D:\\Users\\HP\\Desktop\\台区数据";
//        //获取文件夹下的所有文件
//        String[]  filelist= new File(dicPath).list();
//        assert filelist != null;
//        for (String s : filelist){
//            String[] ns = s.split("\\.");
//            ExcelLogs logs =new ExcelLogs();
//            if(!importNames.containsKey(ns[0]))
//                continue;
//            if(ns[1].equals("csv")){
//                FileReader fileReader = new FileReader(dicPath + "\\\\" + s);
//                try(BufferedReader in = new BufferedReader(fileReader)){
//                    Collection<Map> maps = ExcelUtil.csvReader(in);
//                    importNames.get(ns[0]).addAll(maps);
//                    System.out.println(s + " " + maps.size() + " " + importNames.get(ns[0]).size());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }else if(ns[1].equals("xlsx") || ns[1].equals("xls")) {
//                File f = new File(dicPath + "\\" + s);
//                try(InputStream inputStream= new FileInputStream(f);){
//                    Collection<Map> maps = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);
//                    importNames.get(ns[0]).addAll(maps);
//                    System.out.println(s + " " + maps.size() + " " + importNames.get(ns[0]).size());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//
//        List<String> units = AddrCreateUtil.makeHomeRandomAddr(TG.size());
//
//
//        //cs
//        List<String> csss = new ArrayList<>();
//
//
//        Map cus;
//        Map cus1;
//        //导入顺序 1.生成案例 一个台区生成一个案例  CaseTransformer & CaseList
//        for(Map m : TG){
//            String caseid = String.valueOf(UUID.randomUUID()).replace("-", "");
//            //生成案例
//            caseList.add(new CaseList(){
//                {
//                    setCaseid(caseid);
//                    setCaseName("案例" + caseList.size() + 1);
//                }
//            });
//
//            //存储台区信息
//            caseTransformers.add(new CaseTransformer(){{
//                setTransformerid(m.get("TG_ID").toString());
//                setTransformeCode(m.get("TG_NO").toString());
//                setTransformeName("台区" + caseTransformers.size() + 1);
//                setManager(m.get("SYS_USER_NO").toString());
//                setCommunityid(units.get(caseTransformers.size()));
//                setOrgid(m.get("ORG_NO").toString());
//                setCaseid(caseid);
//            }});
//
//
//            //存储用户信息 按台区的来
//            List<Map> JLD_TQ = JLD.stream().sequential()
//                    .filter(string -> !string.isEmpty())
//                    .filter(e -> e.get("CONS_ID") != null || e.get("CONS_ID").toString().equals(""))
//                    .filter(e ->  e.get("TG_ID") != null && e.get("TG_ID").equals(m.get("TG_ID")))
//                    .collect(Collectors.toList());
//
//            for(Map c : JLD_TQ){
//                List<Map> user = USER.stream().filter(e -> e.get("CONS_ID").equals(c.get("CONS_ID")))
//                        .collect(Collectors.toList());
//                if (user.size() == 0){
//                    continue;
//                }
//                cus = user.get(0);
//                CaseCustomer caseCustomer = new CaseCustomer();
//                caseCustomer.setCustmerid(cus.get("CONS_ID").toString());
//                caseCustomer.setConsNo(cus.get("CONS_NO").toString());
//                caseCustomer.setCustmerName(NameCreateUtil.makeName());
//                caseCustomer.setCustmerAddr(AddrCreateUtil.makeRandomAddr());
//                caseCustomer.setTransformerid(c.get("TG_ID").toString());
//                caseCustomer.setOrgid(cus.get("ORG_NO").toString());
//                caseCustomer.setCaseid(caseid);
//                caseCustomers.add(caseCustomer);
//
//                //将对应的CLD找出并存入
////                List<Map> CLD_JLD = CL.stream().sequential()
////                        .filter(e -> e.get())
////                        .collect(Collectors.toList());
//
//
//            }
//
//
//            //以下为模拟代码，不是真实关系
//            if(!csss.contains(m.get("ORG_NO")))
//            {
//                //测量点
//                List<Map> CLD_JLD = CL.stream().sequential()
//                        .filter(string -> !string.isEmpty())
//                        .filter(e -> e.get("ORG_NO").equals(m.get("ORG_NO"))).
//                        collect(Collectors.toList());
//
//
//                //查询终端数据
//                for (Map cl : CLD_JLD){
//                    List<Map> zd = ZD.stream().sequential()
//                            .filter(string -> !string.isEmpty())
//                            .filter(e -> e.get("TERMINAL_ID").equals(cl.get("TERMINAL_ID")))
//                            .collect(Collectors.toList());
//                    if (zd.size() == 0){
//                        continue;
//                    }
//
//                    //填入终端数据
//                    cus = zd.get(0);
//                    CaseDeviceRtu deviceRtu = new CaseDeviceRtu();
//                    deviceRtu.setRtuid(cus.get("TERMINAL_ID").toString());
//                    deviceRtu.setRtuName("终端" + caseDeviceRtus.size() + 1);
//                    deviceRtu.setRtuAddress(cus.get("TERMINAL_ADDR").toString());
//                    deviceRtu.setReadScheme(cus.get("COLL_MODE").toString());
//                    deviceRtu.setRtuType(Integer.parseInt(cus.get("TERMINAL_TYPE_CODE").toString()));
//                    deviceRtu.setAssetCode(cus.get("ASSET_NO").toString());
//                    deviceRtu.setProtocol(Integer.parseInt(cus.get("PROTOCOL_CODE").toString()));
//                    deviceRtu.setManufacturer(Integer.parseInt(cus.get("FACTORY_CODE").toString()));
//                    deviceRtu.setModel(Integer.parseInt(cus.get("ID").toString()));
//                    deviceRtu.setUpSide(Integer.parseInt(cus.get("CHANNEL_UP").toString()));
//                    deviceRtu.setDownSide(Integer.parseInt(cus.get("CHANNEL_DOWN").toString()));
//                    deviceRtu.setCreateTime(LocalDateTime.now());
//                    deviceRtu.setOrgid(cus.get("ORG_NO").toString());
//                    deviceRtu.setCaseid(caseid);
//                    caseDeviceRtus.add(deviceRtu);
//
//
//
//
//
//
//                    //添入电能表    计算 正向有功无功的关系，反向有功无公的关系不确定？？？
//                    List<Map> dn = METER.stream().sequential()
//                            .filter(string -> !string.isEmpty())
//                            .filter(e -> e.get("METER_ID").equals(cl.get("SP_OBJ_ID")))
//                            .collect(Collectors.toList());
//                    if (dn.size() == 0 && zd.size() == 0 ){
//                        continue;
//                    }
//                    //填入电能表据
//                    cus1 = dn.get(0);
//                    CaseDeviceMeter deviceMeter = new CaseDeviceMeter();
//                    deviceMeter.setMeterid(cus1.get("METER_ID").toString());
//                    deviceMeter.setAssetCode(cus1.get("ASSET_NO").toString());
//                    deviceMeter.setCtid(Integer.parseInt(cus1.get("CT_RATIO").toString()));
//                    deviceMeter.setPtid(Integer.parseInt(cus1.get("PT_RATIO").toString()));
//                    deviceMeter.setRate(Integer.parseInt(cus1.get("T_FACTOR").toString()));
//
////                    deviceMeter.setVoltageClassid(Integer.parseInt(cus.get("VOLT_CODE").toString()));
//                    deviceMeter.setMeterAddress(cus1.get("COMM_ADDR1").toString());
//                    deviceMeter.setProtocol(Integer.parseInt(cus1.get("COMM_NO").toString()));
//                    deviceMeter.setOrgid(cus1.get("ORG_NO").toString());
////                    deviceMeter.setWireType(Integer.parseInt(cus.get("WIRING_MODE").toString()));
////                    deviceMeter.setTransformerid(cus.get("TG_ID").toString());
////                    deviceMeter.setMapAttrCode(Integer.parseInt(cus.get("MP_ATTR_CODE").toString()));
////                    deviceMeter.setUsageTypeCode(Integer.parseInt(cus.get("USAGE_TYPE_CODE").toString()));
//                    deviceMeter.setCaseid(caseid);
//                    caseDeviceMeters.add(deviceMeter);
//
//
//                    List<Map> collect = new ArrayList<>(TG_METER);
//                    Object[] oc = collect.get(0).keySet().toArray();
//
//                    //添入电能数据
//                    List<Map> METER_DN = TG_METER.stream()
//                            .filter(e -> e.get("ID").equals(cl.get("SP_ID")))
//                            .collect(Collectors.toList());
//                    if(METER_DN.size() == 0){
//                        continue;
//                    }
//                    for (Map dnsj : METER_DN){
//                        //根据数据判断正反向
//                        int Direct = 1;
//                        if(!dnsj.get("RAP_R").equals(""))
//                            Direct = 2;
//
//                       LocalDateTime data_date = transformLocalData(dnsj.get("COL_TIME").toString());
//
//
//                        caseDataReads.add(new CaseDataRead(){{
//                            setMeterid(cl.get("SP_OBJ_ID").toString());
//                            setDataTime(data_date);
//                            setDirection(1);
//                            setZBm(new BigDecimal(dnsj.get("PAP_R").toString()));
//                            setJBm(new BigDecimal(dnsj.get("PAP_R1").toString()));
//                            setFBm(new BigDecimal(dnsj.get("PAP_R2").toString()));
//                            setPBm(new BigDecimal(dnsj.get("PAP_R3").toString()));
//                            setGBm(new BigDecimal(dnsj.get("PAP_R4").toString()));
//                            setCaseid(caseid);
//                        }});
//
//                        int finalDirect = Direct;
//                        caseDataReads.add(new CaseDataRead(){{
//                            setMeterid(cl.get("SP_OBJ_ID").toString());
//                            setDataTime(data_date);
//                            setDirection(2);
//                            setZBm(new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R").toString() : "0"));
//                            setJBm(new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R1").toString() : "0"));
//                            setFBm(new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R2").toString() : "0"));
//                            setPBm(new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R3").toString() : "0"));
//                            setGBm(new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R4").toString() : "0"));
//                            setCaseid(caseid);
//                        }});
//                    }
//
//                    //对电能表进行排序
//                    caseDataReads = caseDataReads.stream().sorted(Comparator.comparing(CaseDataRead::getDataTime))
//                            .collect(Collectors.groupingBy(CaseDataRead::getMeterid))
//                            .values().stream().sequential().flatMap(List::stream).collect(Collectors.toList());
//
//                }
//
//
//
//                //组合负荷数据
//
//
//
//
//            }
//
//            csss.add(m.get("ORG_NO").toString());
//        }
//
//
//        for(CaseList c : caseList){
//            System.out.println(c);
//        }
//
////        for (CaseTransformer c : caseTransformers){
////            System.out.println(c);
////        }
////
////        for (CaseDeviceMeter c : caseDeviceMeters) {
////            System.out.println(c);
////        }
////
////        for (CaseCustomer c : caseCustomers){
////            System.out.println(c);
////        }
////
////        for (CaseDataRead c : caseDataReads){
////            System.out.println(c);
////        }
//
//        CaseList caseList1 = caseList.get(0);
//        caseListMapper.insert(caseList1);
//
//
//
//
//
//    }






    @Test
    public void sc() {
        String s =  "01";
        System.out.println(Integer.parseInt(s));

//        //生成每个台区的线损数据
//        //确定电能标编号，并更具台区id进行分组
//        List<Map> jld_tq = JLD.stream().sequential().filter(e -> e.get("TG_ID").equals(m.get("TG_ID"))).collect(Collectors.toList());
//        List<List<Map>> tq_user = new ArrayList<>();
//        List<Map> Z_METER = new ArrayList<>();
//        for (Map jt : jld_tq) {
//            //查找对应的测量点
//            List<Map> jld_cld = JLD_CLD.stream().sequential().filter(e -> e.get("MP_ID").equals(jt.get("MP_ID"))).collect(Collectors.toList());
//            List<Map> cld = new ArrayList<>();
//            List<Map> tg_meter = new ArrayList<>();
//            if(jld_cld.size() != 0){
//                cld = CL.stream().sequential().filter(e -> e.get("SP_OBJ_ID").equals(jld_cld.get(0).get("METER_ID"))).collect(Collectors.toList());
//            }
//            if(cld.size() != 0){
//                List<Map> finalCld = cld;
//                tg_meter = LOSS_DATA.stream().sequential()
//                        .filter(s -> s.get("ID_GROUP") != null)
//                        .filter(e -> e.get("ID_GROUP").equals(finalCld.get(0).get("SP_ID")))
//                        .filter(e -> e.get("READ_TYPE_CODE").equals("1"))
//                        .collect(Collectors.toList());
//            }
//            if(tg_meter.size() != 0){
//                if(jt.get("USAGE_TYPE_CODE").equals("02")){
//                    Z_METER = tg_meter;
//                }else {
//                    tq_user.add(tg_meter);
//                }
//            }
//        }
//
//        //计算线损率等
//        double gr = 0.0;
//        double gc = 0.0;
//        double lineloss;
//        Calendar startTime = Calendar.getInstance();
//        Calendar endTime = Calendar.getInstance();
//
//        startTime.setTime(transformDate(Z_METER.get(0).get("STAT_DATE").toString()));
//        endTime.setTime(transformDate(Z_METER.get(Z_METER.size() - 1).get("STAT_DATE").toString()));
//        while (startTime.compareTo(endTime) <= 0){
//            CaseTransfornerLineloss cls = new CaseTransfornerLineloss();
//            List<Map> stat_date = Z_METER.stream().filter(e -> {
//                try {
//                    return transformDate(e.get("STAT_DATE").toString()).equals(startTime.getTime());
//                } catch (ParseException parseException) {
//                    parseException.printStackTrace();
//                }
//                return false;
//            }).collect(Collectors.toList());
//            if (stat_date.size() > 0)
//                gr = Double.parseDouble(stat_date.get(0).get("COLL_PQ").toString());
//            for (List<Map> meter : tq_user) {
//                List<Map> fb_meter = meter.stream().filter(e -> {
//                    try {
//                        return transformDate(e.get("STAT_DATE").toString()).equals(startTime.getTime());
//                    } catch (ParseException parseException) {
//                        parseException.printStackTrace();
//                    }
//                    return false;
//                }).collect(Collectors.toList());
//                if (fb_meter.size() > 0)
//                    gc += Double.parseDouble(fb_meter.get(0).get("COLL_PQ").toString());
//            }
//            lineloss = (gr - gc) / gr;
//            UUID uuid = UUID.randomUUID();
//            cls.setTransformerLossId(String.valueOf(uuid).replace("-", ""));
//            cls.setTransformerid(m.get("TG_ID").toString());
//            cls.setCycle(3);
//            cls.setSuppleyEnergy(new BigDecimal(gr));
//            cls.setSaleEnergy(new BigDecimal(gc));
//            cls.setLossEnergy(new BigDecimal(gr - gc));
//            cls.setLossRate(new BigDecimal(lineloss));
//            cls.setDatatime(startTime.getTime());
//            cls.setCaseid(caseid);
//            caseTransfornerLinelosses.add(cls);
//            gr = 0;
//            gc = 0;
//
//            startTime.add(Calendar.DAY_OF_MONTH, 1);
//        }
    }


    @Test
    public void testc() throws FileNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader("D:\\Users\\HP\\Desktop\\台区数据\\11-功率因数.csv"));
        try {
            Collection<Map> importExcel = ExcelUtil.csvReader(in);
            for(Map m : importExcel){
                System.out.println(m.get("ID"));
                assert 1==0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        File f=new File("D:\\Users\\HP\\Desktop\\台区数据\\2-计量点档案信息.xls");
//        InputStream inputStream= new FileInputStream(f);
//        ExcelLogs logs =new ExcelLogs();
//        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
//        for(Map m : importExcel){
//            System.out.println(m.get(""));
//        }
    }
}
