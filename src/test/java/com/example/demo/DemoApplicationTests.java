package com.example.demo;

import com.example.demo.ac.entity.*;
import com.example.demo.ac.mapper.*;
import com.example.demo.ac.service.*;
import com.example.demo.utils.AddrCreateUtil;
import com.example.demo.utils.NameCreateUtil;
import com.example.demo.utils2.ExcelLogs;
import com.example.demo.utils2.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



@SpringBootTest
class DemoApplicationTests {

    @Autowired
    public CaseListService caseListService;
    @Autowired
    public CaseDataReadService caseDataReadService;
    @Autowired
    public CaseDataSslService caseDataSslService;
    @Autowired
    public CaseDeviceMeterService caseDeviceMeterService;
    @Autowired
    public CaseDeviceRtuService caseDeviceRtuService;
    @Autowired
    public  CaseCustomerService caseCustomerService;
    @Autowired
    public  CaseTransformerService caseTransformerService;
    @Autowired
    public  CaseTransfornerLinelossService caseTransfornerLinelossService;
    @Autowired
    public CaseDataEnergyService caseDataEnergyService;


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
    static Collection<Map> JLD_CLD = new ArrayList<>();


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
                //System.out.println("????????????????????????" + map.get(p) + "     ????????????" + parse);
                break;
            }
        }
        return reDate;
    }

    public static Date transformDate(String sx) throws ParseException {
        Date parse = null;
        for (Pattern p : map.keySet()){
            Matcher m = p.matcher(sx);
            if (m.matches()){
                SimpleDateFormat sdf = new SimpleDateFormat(map.get(p));
                parse = sdf.parse(sx);
                //System.out.println("????????????????????????" + map.get(p) + "     ????????????" + parse);
                break;
            }
        }
        return parse;
    }

    @Test
    void aa() throws Exception {

        //?????????????????????
        List<CaseCustomer> caseCustomers = new ArrayList<>();
        List<CaseDataRead> caseDataReads = new ArrayList<>();
        List<CaseDataSsl> caseDataSsls = new ArrayList<>();
        List<CaseDeviceMeter> caseDeviceMeters = new ArrayList<>();
        List<CaseDeviceRtu> caseDeviceRtus = new ArrayList<>();
        List<CaseList> caseList = new ArrayList<>();
        List<CaseTransformer> caseTransformers = new ArrayList<>();
        List<CaseTransfornerLineloss> caseTransfornerLinelosses = new ArrayList<>();
        List<CaseDataEnergy> caseDataEnergies = new ArrayList<>();

        Map<String, Collection<Map>> importNames = new HashMap<String, Collection<Map>>(){{
            put("1-??????????????????", TG);
            put("2-?????????????????????", JLD);
            put("3-??????????????????", USER);
            put("4-?????????????????????", METER);
            put("5-?????????????????????", CL);
            put("6-??????????????????", ZD);
            put("7-??????????????????????????????", TG_METER);
            put("8-??????????????????", V);
            put("9-??????????????????", A);
            put("10-?????????????????????", Q);
            put("11-????????????", PF);
            put("12-??????????????????", LOSS_DATA);
            put("????????????????????????????????????", JLD_CLD);
        }};


        String dicPath = "D:\\Users\\HP\\Desktop\\????????????";
        //?????????????????????????????????
        String[]  filelist= new File(dicPath).list();
        assert filelist != null;
        for (String s : filelist){
            String[] ns = s.split("\\.");
            ExcelLogs logs =new ExcelLogs();
            if(!importNames.containsKey(ns[0]))
                continue;
            if(ns[1].equals("csv")){
                FileReader fileReader = new FileReader(dicPath + "\\\\" + s);
                try(BufferedReader in = new BufferedReader(fileReader)){
                    Collection<Map> maps = ExcelUtil.csvReader(in);
                    importNames.get(ns[0]).addAll(maps);
                    System.out.println(s + " " + maps.size() + " " + importNames.get(ns[0]).size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(ns[1].equals("xlsx") || ns[1].equals("xls")) {
                File f = new File(dicPath + "\\" + s);
                try(InputStream inputStream= new FileInputStream(f);){
                    Collection<Map> maps = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);
                    importNames.get(ns[0]).addAll(maps);
                    System.out.println(s + " " + maps.size() + " " + importNames.get(ns[0]).size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        List<String> units = AddrCreateUtil.makeHomeRandomAddr(TG.size());


        Map cus;
        Map cus1;
        Set<String> zd_set = new HashSet<>();
        //???????????? 1.???????????? ??????????????????????????????  CaseTransformer & CaseList
        for(Map m : TG){
            String caseid = String.valueOf(UUID.randomUUID()).replace("-", "");
            //????????????
            caseList.add(new CaseList(){
                {
                    setCaseid(caseid);
                    setCaseName("??????" + (caseList.size() + 1));
                }
            });

            //??????????????????
            caseTransformers.add(new CaseTransformer(){{
                setTransformerid(m.get("TG_ID").toString());
                setTransformeCode(m.get("TG_NO").toString());
                setTransformeCapacity(m.get("TG_CAP").toString());
                setTransformeName("??????" + (caseTransformers.size() + 1));
                setManager(m.get("SYS_USER_NO").toString());
                setCommunityid(units.get(caseTransformers.size()));
                setOrgid(m.get("ORG_NO").toString());
                setCaseid(caseid);
            }});




            //?????????????????? ???????????????
            List<Map> JLD_TQ = JLD.stream().sequential()
                    .filter(e ->  e.get("TG_ID") != null && e.get("TG_ID").equals(m.get("TG_ID")))
                    .collect(Collectors.toList());

            //????????????
            JLD_TQ = JLD_TQ.stream().sequential().filter(e -> e.get("USAGE_TYPE_CODE").equals("01") || e.get("USAGE_TYPE_CODE").equals("02")).collect(Collectors.toList());

            for(Map c : JLD_TQ){
                List<Map> user = USER.stream().filter(e -> e.get("CONS_ID").equals(c.get("CONS_ID")))
                        .collect(Collectors.toList());


                if (user.size() != 0){
                    cus = user.get(0);
                    CaseCustomer caseCustomer = new CaseCustomer();
                    caseCustomer.setCustmerid(cus.get("CONS_ID").toString());
                    caseCustomer.setConsNo(cus.get("CONS_NO").toString());
                    caseCustomer.setCustmerName(NameCreateUtil.makeName());
                    caseCustomer.setCustmerAddr(AddrCreateUtil.makeRandomAddr());
                    caseCustomer.setTransformerid(c.get("TG_ID").toString());
                    caseCustomer.setOrgid(cus.get("ORG_NO").toString());
                    caseCustomer.setCaseid(caseid);
                    caseCustomers.add(caseCustomer);
                }



                //????????????????????????????????????????????? JLD_CLD
                List<Map> jl = JLD_CLD.stream().sequential()
                        .filter(e -> e.get("MP_ID").equals(c.get("MP_ID")))
                        .collect(Collectors.toList());



                if (jl.size() == 0){
                    continue;
                }

                //?????????????????????
                List<Map> CLD_JLD = CL.stream().sequential()
                        .filter(string -> !string.isEmpty())
                        .filter(e -> e.get("SP_OBJ_ID").equals(jl.get(0).get("METER_ID")))
                        .collect(Collectors.toList());
                if (CLD_JLD.size() == 0){
                    continue;
                }

                //??????????????????
                for (Map cl : CLD_JLD){
                    List<Map> zd = ZD.stream().sequential()
                            .filter(string -> !string.isEmpty())
                            .filter(e -> e.get("TERMINAL_ID").equals(cl.get("TERMINAL_ID")))
                            .collect(Collectors.toList());
                    if (zd.size() == 0){
                        continue;
                    }




                    //??????????????????
                    cus = zd.get(0);
                    boolean terminal_id = zd_set.add(cus.get("TERMINAL_ID").toString());
                    if(terminal_id){
                        CaseDeviceRtu deviceRtu = new CaseDeviceRtu();
                        deviceRtu.setRtuid(cus.get("TERMINAL_ID").toString());
                        deviceRtu.setRtuName("??????" + (caseDeviceRtus.size() + 1));
                        deviceRtu.setRtuAddress(cus.get("TERMINAL_ADDR").toString());
                        deviceRtu.setReadScheme(cus.get("COLL_MODE").toString());
                        deviceRtu.setRtuType(Integer.parseInt(cus.get("TERMINAL_TYPE_CODE").toString()));
                        deviceRtu.setAssetCode(cus.get("ASSET_NO").toString());
                        deviceRtu.setProtocol(Integer.parseInt(cus.get("PROTOCOL_CODE").toString()));
                        deviceRtu.setManufacturer(Integer.parseInt(cus.get("FACTORY_CODE").toString()));
                        deviceRtu.setModel(Integer.parseInt(cus.get("ID").toString()));
                        deviceRtu.setUpSide(Integer.parseInt(cus.get("CHANNEL_UP").toString()));
                        deviceRtu.setDownSide(Integer.parseInt(cus.get("CHANNEL_DOWN").toString()));
                        deviceRtu.setCreateTime(new Date());
                        deviceRtu.setOrgid(cus.get("ORG_NO").toString());
                        deviceRtu.setCaseid(caseid);
                        caseDeviceRtus.add(deviceRtu);
                    }

                    //???????????????    ?????? ????????????????????????????????????
                    List<Map> dn = METER.stream().sequential()
                            .filter(string -> !string.isEmpty())
                            .filter(e -> e.get("METER_ID").equals(cl.get("SP_OBJ_ID")))
                            .collect(Collectors.toList());
                    if (dn.size() == 0 && zd.size() == 0 ){
                        continue;
                    }



                    //??????????????????
                    cus1 = dn.get(0);
                    CaseDeviceMeter deviceMeter = new CaseDeviceMeter();
                    deviceMeter.setMeterid(cus1.get("METER_ID").toString());
                    deviceMeter.setAssetCode(cus1.get("ASSET_NO").toString());
                    deviceMeter.setCtid(Integer.parseInt(cus1.get("CT_RATIO").toString()));
                    deviceMeter.setPtid(Integer.parseInt(cus1.get("PT_RATIO").toString()));
                    deviceMeter.setRate(Integer.parseInt(cus1.get("T_FACTOR").toString()));

//                    deviceMeter.setVoltageClassid(Integer.parseInt(cus.get("VOLT_CODE").toString()));
                    deviceMeter.setMeterAddress(cus1.get("COMM_ADDR1").toString());
                    deviceMeter.setProtocol(Integer.parseInt(cus1.get("COMM_NO").toString()));
                    deviceMeter.setOrgid(cus1.get("ORG_NO").toString());
//                    deviceMeter.setWireType(Integer.parseInt(cus.get("WIRING_MODE").toString()));
//                    deviceMeter.setTransformerid(cus.get("TG_ID").toString());
//                    deviceMeter.setMapAttrCode(Integer.parseInt(cus.get("MP_ATTR_CODE").toString()));
//                    deviceMeter.setUsageTypeCode(Integer.parseInt(cus.get("USAGE_TYPE_CODE").toString()));
                    deviceMeter.setCaseid(caseid);
                    caseDeviceMeters.add(deviceMeter);


//                    List<Map> collect = new ArrayList<>(TG_METER);

                    //??????????????????
                    List<Map> METER_DN = TG_METER.stream()
                            .filter(e -> e.get("ID").equals(cl.get("SP_ID"))).sorted(Comparator.comparing(DemoApplicationTests::comparingByKpScore))
                            .collect(Collectors.toList());
                    if(METER_DN.size() == 0){
                        continue;
                    }



                    int lastflag = 0;

                    List<CaseDataRead> lastmap = new ArrayList<>();

                    for (Map dnsj : METER_DN){
                        //???????????????????????????
                        int Direct = 1;
                        if(!dnsj.get("RAP_R").equals(""))
                            Direct = 2;

                        Date data_date = transformDate(dnsj.get("COL_TIME").toString());


                        //????????????
                        BigDecimal z_zbm = new BigDecimal(dnsj.get("PAP_R").toString());
                        BigDecimal z_jbm = new BigDecimal(dnsj.get("PAP_R1").toString());
                        BigDecimal z_fbm = new BigDecimal(dnsj.get("PAP_R2").toString());
                        BigDecimal z_pbm = new BigDecimal(dnsj.get("PAP_R3").toString());
                        BigDecimal z_gbm = new BigDecimal(dnsj.get("PAP_R4").toString());

                        int finalDirect = Direct;

                        BigDecimal f_zbm = new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R").toString() : "0");
                        BigDecimal f_jbm = new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R1").toString() : "0");
                        BigDecimal f_fbm = new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R2").toString() : "0");
                        BigDecimal f_pbm = new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R3").toString() : "0");
                        BigDecimal f_gbm = new BigDecimal(finalDirect == 2 ? dnsj.get("RAP_R4").toString() : "0");



                        CaseDataRead zread = new CaseDataRead(){{
                            setMeterid(cl.get("SP_OBJ_ID").toString());
                            setDataTime(data_date);
                            setDirection(1);
                            setZBm(z_zbm);
                            setJBm(z_jbm);
                            setFBm(z_fbm);
                            setPBm(z_pbm);
                            setGBm(z_gbm);
                            setCaseid(caseid);
                        }};

                        CaseDataRead fread = new CaseDataRead(){{
                            setMeterid(cl.get("SP_OBJ_ID").toString());
                            setDataTime(data_date);
                            setDirection(2);
                            setZBm(f_zbm);
                            setJBm(f_jbm);
                            setFBm(f_fbm);
                            setPBm(f_pbm);
                            setGBm(f_gbm);
                            setCaseid(caseid);
                        }};


                        caseDataReads.add(zread);
                        caseDataReads.add(fread);



                        if(lastmap.size() != 0){

                            CaseDataEnergy zenergy = new CaseDataEnergy(){{
                                setMeterid(cl.get("SP_OBJ_ID").toString());
                                setBegintime(lastmap.get(0).getDataTime());
                                setEndtime(data_date);
                                setDirection(1);
                                setCycle(3);
                                setZBm(z_zbm.subtract(lastmap.get(0).getZBm()));
                                setJBm(z_jbm.subtract(lastmap.get(0).getJBm()));
                                setFBm(z_fbm.subtract(lastmap.get(0).getFBm()));
                                setPBm(z_pbm.subtract(lastmap.get(0).getPBm()));
                                setGBm(z_gbm.subtract(lastmap.get(0).getGBm()));
                                setCaseid(caseid);
                            }};

                            CaseDataEnergy fenergy = new CaseDataEnergy(){
                                {
                                    setMeterid(cl.get("SP_OBJ_ID").toString());
                                    setBegintime(lastmap.get(0).getDataTime());
                                    setEndtime(data_date);
                                    setDirection(2);
                                    setCycle(3);
                                    setZBm(f_zbm.subtract(lastmap.get(1).getZBm()));
                                    setJBm(f_jbm.subtract(lastmap.get(1).getJBm()));
                                    setFBm(f_fbm.subtract(lastmap.get(1).getFBm()));
                                    setPBm(f_pbm.subtract(lastmap.get(1).getPBm()));
                                    setGBm(f_gbm.subtract(lastmap.get(1).getGBm()));
                                    setCaseid(caseid);
                                }
                            };

                            caseDataEnergies.add(zenergy);
                            caseDataEnergies.add(fenergy);
                            lastmap.clear();
                        }
                        lastflag++;

                        if(lastflag != METER_DN.size()){
                            //??????????????????????????????
                            lastmap.add(zread);
                            lastmap.add(fread);
                        }




                    }

                    //????????????????????????
                    caseDataReads = caseDataReads.stream().sorted(Comparator.comparing(CaseDataRead::getDataTime))
                            .collect(Collectors.groupingBy(CaseDataRead::getMeterid))
                            .values().stream().sequential().flatMap(List::stream).collect(Collectors.toList());





                    //???USAGE_TYPE_CODE = 02 ??????????????????  ??????????????????
                    if(c.get("USAGE_TYPE_CODE").equals("02")){
                        //?????????????????????????????????????????????????????????id
                        //????????????
                        List<Map> V_data = V.stream().sequential().filter(e -> e.get("ID").equals(cl.get("SP_ID"))).collect(Collectors.toList());
                        List<Map> A_data = A.stream().sequential().filter(e -> e.get("ID").equals(cl.get("SP_ID"))).collect(Collectors.toList());
                        List<Map> Q_data = Q.stream().sequential().filter(e -> e.get("ID").equals(cl.get("SP_ID"))).collect(Collectors.toList());
                        List<Map> PF_data = PF.stream().sequential().filter(e -> e.get("ID").equals(cl.get("SP_ID"))).collect(Collectors.toList());
                        if (V_data.size() == 0 || A_data.size() == 0 || Q_data.size() == 0 || PF_data.size() == 0)
                            continue;
                        //??????data point flag ?????????????????????
                        //?????????????????????????????? flag??????

                        //???????????????
                        Calendar startTime = Calendar.getInstance();
                        Calendar endTime = Calendar.getInstance();
                        startTime.setTime(transformDate(V_data.get(0).get("DATA_DATE").toString()));
                        endTime.setTime(transformDate(V_data.get(V_data.size()-1).get("DATA_DATE").toString()));
                        endTime.add(Calendar.DAY_OF_MONTH, 1);


                        int minute;
                        int rang = 1;
                        //??????flag
                        if(V_data.get(0).get("DATA_POINT_FLAG").equals("1")){
                            minute = 15;
                        }
                        else if (V_data.get(0).get("DATA_POINT_FLAG").equals("2")){
                            minute = 30;
                            rang = 2;
                        }else {
                            minute = 60;
                            rang = 4;
                        }

                        int pf_sum = 0;
                        int vi = 1;
                        while (startTime.compareTo(endTime) < 0){
                            CaseDataSsl caseDataSsl = new CaseDataSsl();
                            String Date = startTime.get(Calendar.YEAR)+"/"+(startTime.get(Calendar.MONTH) + 1)+"/"+startTime.get(Calendar.DAY_OF_MONTH);


                            List<Map> v_date = V_data.stream().filter(e -> e.get("DATA_DATE").equals(Date)).collect(Collectors.toList());
                            List<Map> a_data = A_data.stream().filter(e -> e.get("DATA_DATE").equals(Date)).collect(Collectors.toList());
                            List<Map> q_data = Q_data.stream().filter(e -> e.get("DATA_DATE").equals(Date)).collect(Collectors.toList());
                            List<Map> pf_data = PF_data.stream().filter(e -> e.get("DATA_DATE").equals(Date)).collect(Collectors.toList());


                            caseDataSsl.setMeterid(cl.get("SP_OBJ_ID").toString());

                            String VV = "U" + vi;
                            //??????
                            for (Map ssl_v : v_date){
                                if(ssl_v.get("PHASE_FLAG").equals("1")){
                                    caseDataSsl.setVA(new BigDecimal(ssl_v.get(VV).toString() == null || ssl_v.get(VV).toString().equals("") ?  "0" : ssl_v.get(VV).toString()));
                                }else if(ssl_v.get("PHASE_FLAG").equals("2")){
                                    caseDataSsl.setVB(new BigDecimal(ssl_v.get(VV).toString() == null || ssl_v.get(VV).toString().equals("") ? "0" : ssl_v.get(VV).toString()));
                                }else{
                                    caseDataSsl.setVC(new BigDecimal(ssl_v.get(VV).toString() == null || ssl_v.get(VV).toString().equals("") ? "0" : ssl_v.get(VV).toString()));
                                }
                            }

                            String II = "I" + vi;
                            //??????
                            for (Map ssl_a : a_data){
                                if(ssl_a.get("PHASE_FLAG").equals("1")){
                                    caseDataSsl.setIA(new BigDecimal(ssl_a.get(II).toString() == null || ssl_a.get(II).toString().equals("")? "0":ssl_a.get(II).toString()));
                                }else if(ssl_a.get("PHASE_FLAG").equals("2")){
                                    caseDataSsl.setIB(new BigDecimal(ssl_a.get(II).toString() == null || ssl_a.get(II).toString().equals("")? "0":ssl_a.get(II).toString()));
                                }else{
                                    caseDataSsl.setIC(new BigDecimal(ssl_a.get(II).toString() == null || ssl_a.get(II).toString().equals("")?"0":ssl_a.get(II).toString()));
                                }
                            }


                            String Q = "P" + vi;
                            //?????????????????????
                            for (Map ssl_q : q_data){
                                if(ssl_q.get("DATA_TYPE").equals("1")){
                                    caseDataSsl.setP(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("")?  "0":ssl_q.get(Q).toString()));
                                }else if(ssl_q.get("DATA_TYPE").equals("2")){
                                    caseDataSsl.setPA(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("")? "0":ssl_q.get(Q).toString()));
                                }else if (ssl_q.get("DATA_TYPE").equals("3")){
                                    caseDataSsl.setPB(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("")? "0":ssl_q.get(Q).toString()));
                                }else if(ssl_q.get("DATA_TYPE").equals("4")){
                                    caseDataSsl.setPC(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("")? "0":ssl_q.get(Q).toString()));
                                }else if(ssl_q.get("DATA_TYPE").equals("5")){
                                    caseDataSsl.setQ(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("")? "0":ssl_q.get(Q).toString()));
                                } else if(ssl_q.get("DATA_TYPE").equals("6")){
                                    caseDataSsl.setQA(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("")? "0":ssl_q.get(Q).toString()));
                                }else if(ssl_q.get("DATA_TYPE").equals("7")){
                                    caseDataSsl.setQB(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("")? "0":ssl_q.get(Q).toString()));
                                }else if(ssl_q.get("DATA_TYPE").equals("8")){
                                    caseDataSsl.setQC(new BigDecimal(ssl_q.get(Q).toString() == null || ssl_q.get(Q).toString().equals("") ? "0":ssl_q.get(Q).toString()));
                                }
                            }

                            if(pf_sum % 60 == 0){
                                String PF = "C" + vi;
                                //????????????
                                for (Map ssl_pf : pf_data) {
                                    if (ssl_pf.get("PHASE_FLAG").equals("0")){
                                        caseDataSsl.setPf(new BigDecimal(ssl_pf.get(PF).toString() == null || ssl_pf.get(PF).toString().equals("")? "0" : ssl_pf.get(PF).toString()));
                                    }else if(ssl_pf.get("PHASE_FLAG").equals("1")){
                                        caseDataSsl.setPfA(new BigDecimal(ssl_pf.get(PF).toString() == null || ssl_pf.get(PF).toString().equals("")? "0" : ssl_pf.get(PF).toString()));
                                    }else if(ssl_pf.get("PHASE_FLAG").equals("2")){
                                        caseDataSsl.setPfB(new BigDecimal(ssl_pf.get(PF).toString() == null || ssl_pf.get(PF).toString().equals("")? "0" : ssl_pf.get(PF).toString()));
                                    }else if(ssl_pf.get("PHASE_FLAG").equals("3")){
                                        caseDataSsl.setPfC(new BigDecimal(ssl_pf.get(PF).toString() == null || ssl_pf.get(PF).toString().equals("")? "0" : ssl_pf.get(PF).toString()));
                                    }
                                }
                            }

//                            LocalDateTime of = LocalDateTime.of(startTime.get(Calendar.YEAR), startTime.get(Calendar.MONTH) + 1, startTime.get(Calendar.DAY_OF_MONTH),
//                                    startTime.get(Calendar.HOUR), startTime.get(Calendar.MINUTE), startTime.get(Calendar.SECOND));

                            Date of = startTime.getTime();
                            caseDataSsl.setDataTime(of);
                            caseDataSsl.setCaseid(caseid);
                            startTime.add(Calendar.MINUTE, minute);
                            caseDataSsls.add(caseDataSsl);
                            pf_sum += minute;
                            vi+=rang;
                            vi = vi == 97? 1 : vi;
                        }
                    }
                }
            }



        //?????????????????????????????????
        //???????????????????????????????????????id????????????
        List<Map> jld_tq = JLD.stream().sequential().filter(e -> e.get("TG_ID").equals(m.get("TG_ID"))).collect(Collectors.toList());
        List<List<Map>> tq_user = new ArrayList<>();
        List<Map> Z_METER = new ArrayList<>();
        for (Map jt : jld_tq) {
            //????????????????????????
            List<Map> jld_cld = JLD_CLD.stream().sequential().filter(e -> e.get("MP_ID").equals(jt.get("MP_ID"))).collect(Collectors.toList());
            List<Map> cld = new ArrayList<>();
            List<Map> tg_meter = new ArrayList<>();
            if(jld_cld.size() != 0){
                cld = CL.stream().sequential().filter(e -> e.get("SP_OBJ_ID").equals(jld_cld.get(0).get("METER_ID"))).collect(Collectors.toList());
            }
            if(cld.size() != 0){
                List<Map> finalCld = cld;
                tg_meter = LOSS_DATA.stream().sequential()
                        .filter(s -> s.get("ID_GROUP") != null)
                        .filter(e -> e.get("ID_GROUP").equals(finalCld.get(0).get("SP_ID")))
                        .filter(e -> e.get("READ_TYPE_CODE").equals("1"))
                        .collect(Collectors.toList());
            }
            if(tg_meter.size() != 0){
                if(jt.get("USAGE_TYPE_CODE").equals("02")){
                    Z_METER = tg_meter;
                }else {
                    tq_user.add(tg_meter);
                }
            }
        }

        //??????????????????
        double gr = 0.0;
        double gc = 0.0;
        double lineloss;
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();

        startTime.setTime(transformDate(Z_METER.get(0).get("STAT_DATE").toString()));
        endTime.setTime(transformDate(Z_METER.get(Z_METER.size() - 1).get("STAT_DATE").toString()));
        while (startTime.compareTo(endTime) <= 0){
            CaseTransfornerLineloss cls = new CaseTransfornerLineloss();
            List<Map> stat_date = Z_METER.stream().filter(e -> {
                try {
                    return transformDate(e.get("STAT_DATE").toString()).equals(startTime.getTime());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());
            if (stat_date.size() > 0)
                gr = Double.parseDouble(stat_date.get(0).get("COLL_PQ").toString());
            for (List<Map> meter : tq_user) {
                List<Map> fb_meter = meter.stream().filter(e -> {
                    try {
                        return transformDate(e.get("STAT_DATE").toString()).equals(startTime.getTime());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());
                if (fb_meter.size() > 0)
                    gc += Double.parseDouble(fb_meter.get(0).get("COLL_PQ").toString());
            }
            lineloss = (gr - gc) / gr;
            UUID uuid = UUID.randomUUID();
            cls.setTransformerLossId(String.valueOf(uuid).replace("-", ""));
            cls.setTransformerid(m.get("TG_ID").toString());
            cls.setCycle(3);
            cls.setSuppleyEnergy(new BigDecimal(gr));
            cls.setSaleEnergy(new BigDecimal(gc));
            cls.setLossEnergy(new BigDecimal(gr - gc));
            cls.setLossRate(new BigDecimal(lineloss));
            cls.setDatatime(startTime.getTime());
            cls.setCaseid(caseid);
            caseTransfornerLinelosses.add(cls);
            gr = 0;
            gc = 0;

            startTime.add(Calendar.DAY_OF_MONTH, 1);
        }

        }

        //??????????????????
        caseCustomers = caseCustomers.stream().sequential().filter(distinctByKey(CaseCustomer::getCustmerid)).collect(Collectors.toList());






        //??????????????????
        caseListService.saveBatch(caseList);
        caseCustomerService.saveBatch(caseCustomers);
        caseDataReadService.saveBatch(caseDataReads);
        caseDataSslService.saveBatch(caseDataSsls);
        caseDeviceMeterService.saveBatch(caseDeviceMeters);
        caseDeviceRtuService.saveBatch(caseDeviceRtus);
        //System.out.println(caseTransformers.size());
        caseTransformerService.saveBatch(caseTransformers);
        caseTransfornerLinelossService.saveBatch(caseTransfornerLinelosses);
        caseDataEnergyService.saveBatch(caseDataEnergies);



//        Map<String, List<CaseCustomer>> collect = caseCustomers.stream().collect(Collectors.groupingBy(CaseCustomer::getCustmerid));
//        for (String caseCustomer : collect.keySet()) {
//            System.out.println(caseCustomer + " " + collect.get(caseCustomer));
//        }


//        Map<String, List<CaseDataRead>> collect = caseDataReads.stream().collect(Collectors.groupingBy(CaseDataRead::getMeterid));
//        for (String caseCustomer : collect.keySet()) {
//            if(caseCustomer.equals("1000000108898165")){
//                collect.get(caseCustomer).forEach(System.out::println);
//            }
//        }


//        Map<String, List<CaseDeviceRtu>> collect = caseDeviceRtus.stream().sequential().collect(Collectors.groupingBy(CaseDeviceRtu::getRtuid));
//        for (String caseCustomer : collect.keySet()) {
//            System.out.println(caseCustomer + " " + collect.get(caseCustomer).size());
//        }

        //caseDataEnergies.forEach(System.out::println);


    }



    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static Date comparingByKpScore(Map map){
        try {
            return  transformDate(String.valueOf(map.get("DATA_DATE")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Test
    public void sc() {
        String s =  "01";
        System.out.println(Integer.parseInt(s));
    }


    @Test
    public void testc() throws FileNotFoundException {
//        BufferedReader in = new BufferedReader(new FileReader("D:\\Users\\HP\\Desktop\\????????????\\11-????????????.csv"));
//        try {
//            Collection<Map> importExcel = ExcelUtil.csvReader(in);
//            for(Map m : importExcel){
//                System.out.println(m.get("ID"));
//                assert 1==0;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        File f=new File("D:\\Users\\HP\\Desktop\\????????????\\2-?????????????????????.xls");
//        InputStream inputStream= new FileInputStream(f);
//        ExcelLogs logs =new ExcelLogs();
//        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
//        for(Map m : importExcel){
//            System.out.println(m.get(""));
//        }
    }

}
