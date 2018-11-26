package com.bodhi.framecore.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 16:35
 * desc :
 */
public class CommonUtil {

    private static CommonUtil commonUtil;

    private CommonUtil(){}

    public static CommonUtil getInstance(){
        if (commonUtil==null) {
            commonUtil=new CommonUtil();
        }

        return commonUtil;
    }

    /**
     * 数字相关
     */
    public boolean isNumeric(String str){
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public boolean isMobileNo(String mobil){
        return Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(mobil).matches();
    }

    private static int[] idsArray = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    /**
     * 验证身份证的号码是否是格式正确的(精确)
     * @param idCardNumber
     * @return
     */
    public static boolean isIDCardVerify(String idCardNumber){
        if(null == idCardNumber){
            return false;
        }

        if("".equals(idCardNumber.trim())){
            return false;
        }

        String idCardNumberTrim = idCardNumber.trim();
        String idPattern = "^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X|x)?$";
        Pattern patternId = Pattern.compile(idPattern);//身份证号�?
        Matcher matcherId = patternId.matcher(idCardNumberTrim);
        if (!matcherId.matches()) {
            return false;
        }

        //下面是验�?8位身份证的最后一位字�?数字是否正确
        int temp  = 0;
        if(idCardNumberTrim.length() == 18){
            char[] idArray = idCardNumberTrim.toCharArray();

            for (int i = 0; i < idArray.length - 1; i++) {
                String valueOf = String.valueOf(idArray[i]);
                int parseInt = Integer.parseInt(valueOf);
                temp += parseInt * idsArray[i];
            }
            int temp2 = temp % 11;
            String lastChar  = "";
            switch (temp2) {
                case 0:
                    lastChar = "1";
                    break;
                case 1:
                    lastChar = "0";
                    break;
                case 2:
                    lastChar = "X";
                    break;
                case 3:
                    lastChar = "9";
                    break;
                case 4:
                    lastChar = "8";
                    break;
                case 5:
                    lastChar = "7";
                    break;
                case 6:
                    lastChar = "6";
                    break;
                case 7:
                    lastChar = "5";
                    break;
                case 8:
                    lastChar = "4";
                    break;
                case 9:
                    lastChar = "3";
                    break;
                case 10:
                    lastChar = "2";
                    break;
            }
            char charAtLast = idCardNumberTrim.charAt(17);
            if(!(""+charAtLast).equalsIgnoreCase(lastChar)){
                return false;
            }
        }
        //以上条件都不符合才会返回true
        return true;
    }
}
