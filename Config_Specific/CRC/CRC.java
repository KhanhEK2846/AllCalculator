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
                crcResult = crc8(data, crcType);
                break;
            case 16:
                //TODO CRC16
                break;
            case 32:
                //TODO CRC32
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

    private static long crc8(byte[] data,CRCTypeEnum crcType){
        int crc = (int)crcType.initialValue & 0xFF;
        int poly = (int)crcType.polynomial & 0xFF;

        //Reflect polynomial for LSB-first
        if (!crcType.RefIn) poly = (int) reflectBits(poly, 8);

        for (byte b : data){
            int cur = b & 0xFF;
            //if (crcType.RefIn) cur = (int) reflectBits(cur, 8);
            crc ^= cur;

            for (int i = 0; i < 8; i++){
                if ((crc & (!crcType.RefIn ? 0x01 : 0x80)) != 0) {
                    if (!crcType.RefIn)
                        crc = (crc >> 1) ^ poly;
                    else
                        crc = ((crc << 1) ^ poly) & 0xFF;
                } else {
                    if (!crcType.RefIn)
                        crc >>= 1;
                    else
                        crc = (crc << 1) & 0xFF;
                }
            }
        }

        //if (crcType.RefOut) crc = (int) reflectBits(crc, 8);

        crc ^= (int) crcType.xorOut & 0xFF;

        return crc & 0xFF;
    }

}

