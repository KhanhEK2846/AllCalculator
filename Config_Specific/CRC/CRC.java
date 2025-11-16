package Config_Specific.CRC;

import Config_Common.ErrorEvent;
import Utilities.PopUp;

public class CRC {

    public CRC(){

    }

    public String CalculateChecksum(final String hexString, final CRCTypeEnum crcType) throws Exception {

        String returnValue = "";
        String tmpBuffer = preproccess(hexString);


        if(precheck(tmpBuffer)){
            byte[] DataByteHex = PraiseHexStringToByteArray(tmpBuffer);
            returnValue = CRCCalculator(DataByteHex, crcType);
        }

        return returnValue;
    }

    private boolean precheck(String hexData){
        boolean returnValue = false;
        if(hexData.length() == 0){
            new PopUp(ErrorEvent.EmptyInput);
        }else{
            returnValue = true;
        }

        return returnValue;
    }

    private String preproccess(String hexString){
        if(hexString.length() %2 != 0){
            hexString = "0" + hexString;
        }
        return hexString;
    }

    private byte[] PraiseHexStringToByteArray(String hexString){
        byte[] byteArray = new byte[hexString.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(hexString.substring(2 * i, 2 * i + 2), 16);
        }
        return byteArray;
    }

    private String CRCCalculator(byte[] data, CRCTypeEnum crcType){
        String returnValue = "";
        long crcResult = crc(data, crcType.polynomial, crcType.initialValue,crcType.RefIn, crcType.RefOut, crcType.xorOut, crcType.CRCwidth);

        returnValue = crc2HexString(crcResult, crcType.CRCwidth);

        return returnValue;
    }

    private static long reflectBits(long data, int bitCount){
        long reflection = 0;
        for (int i = 0; i < bitCount; i++) {
            if ((data & (1L << i)) != 0) {
                reflection |= (1L << ((bitCount - 1) - i));
            }
        }
        return reflection;
    }

    private String crc2HexString(long crcValue, int crcWidth){
        switch(crcWidth){
            case 8:
                return String.format("%02X", crcValue & 0xFF);
            case 16:
                return String.format("%04X", crcValue & 0xFFFF);
            case 32:
                return String.format("%08X", crcValue & 0xFFFFFFFF);
            default:
                new PopUp(ErrorEvent.ConfigureInvalid);
                return "";
        }
    }

    public static long crc(byte[] data,long poly,long init,boolean refin,boolean refout,long xorout,int width) {
        long crc = init;
        long mask = (width == 32) ? 0xFFFFFFFFL : ((1L << width) - 1);
        long topbit = 1L << (width - 1);

        // Automatically reflect polynomial for LSB-first
        if (refin) poly = reflectBits(poly, width);

        for (byte b : data) {
            int cur = b & 0xFF;
            if (refin) cur = (int) reflectBits(cur, 8);

            if (!refin) { // MSB-first
                crc ^= ((long) cur << (width - 8));
                for (int i = 0; i < 8; i++) {
                    if ((crc & topbit) != 0) {
                        crc = (crc << 1) ^ poly;
                    } else {
                        crc <<= 1;
                    }
                    crc &= mask;
                }
            } else { // LSB-first
                crc ^= cur;
                for (int i = 0; i < 8; i++) {
                    if ((crc & 1) != 0) {
                        crc = (crc >> 1) ^ poly;
                    } else {
                        crc >>= 1;
                    }
                    crc &= mask;
                }
            }
        }

        if (refout) crc = reflectBits(crc, width) & mask;

        crc ^= xorout;
        return crc & mask;


    }
}

