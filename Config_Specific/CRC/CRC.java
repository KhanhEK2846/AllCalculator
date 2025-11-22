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
            byteArray[i] = (byte) Integer.parseInt(hexString.substring(2 * i, 2 * i + +2), 16);
        }
        return byteArray;
    }

    private String CRCCalculator(byte[] data, CRCTypeEnum crcType){
        String returnValue = "";
        long crcResult = 0;

        switch(crcType.CRCwidth){
            case 8:
            case 16:
            case 32:
            case 64:
                crcResult = crc(data, crcType);
                break;
            default:
                new PopUp(ErrorEvent.ConfigureInvalid);
                return "";
        }

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

    private static long crc(byte[] data,CRCTypeEnum crcType){
            long crc = crcType.initialValue;
            long topBit = 1L << (crcType.CRCwidth - 1);
            long mask = (crcType.CRCwidth == 64) ? ~0L : ((1L << crcType.CRCwidth) - 1);

            // If reflected mode, reflect the polynomial
            long polyRef = crcType.RefIn ? reflectBits(crcType.polynomial, crcType.CRCwidth) : crcType.polynomial;

            for (byte b : data) {
                int cur = b & 0xFF;

                if (crcType.RefIn) {
                    cur = (int) reflectBits(cur, 8);
                    crc ^= (cur & 0xFF);
                    for (int i = 0; i < 8; i++) {
                        if ((crc & 1) != 0)
                            crc = (crc >>> 1) ^ polyRef;
                        else
                            crc >>>= 1;
                    }
                } else {
                    crc ^= (cur << (crcType.CRCwidth - 8));
                    for (int i = 0; i < 8; i++) {
                        if ((crc & topBit) != 0)
                            crc = ((crc << 1) ^ polyRef) & mask;
                        else
                            crc = (crc << 1) & mask;
                    }
                }
            }

            if (crcType.RefOut)
                crc = reflectBits(crc, crcType.CRCwidth);

            crc ^= crcType.xorOut;

            return crc & mask;
        }
}

