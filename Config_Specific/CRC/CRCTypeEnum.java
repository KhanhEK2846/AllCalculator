package Config_Specific.CRC;

public enum CRCTypeEnum {
    /*----------------------CRC8------------------- */
    CRC8_AUTOSAR(0x2F,0xFF,false,false,0xFF,8),


    /*----------------------CRC16------------------- */
    CRC16_ARC(0x8005,0x0000,true,true,0x0000,16),

    /*----------------------CRC32------------------- */
    CRC_32_AIXM(0x814141AB,0x00000000,false,false,0x00000000,32)



    ;
    public final long polynomial;
    public final long initialValue;
    public final boolean RefIn;
    public final boolean RefOut;
    public final long xorOut;
    public final int CRCwidth;

    CRCTypeEnum(long polynomial, long initialValue, boolean RefIn, boolean RefOut, long xorOut, int CRCwidth){
        this.polynomial = polynomial;
        this.initialValue = initialValue;
        this.RefIn = RefIn;
        this.RefOut = RefOut;
        this.xorOut = xorOut;
        this.CRCwidth = CRCwidth;
    }

}
