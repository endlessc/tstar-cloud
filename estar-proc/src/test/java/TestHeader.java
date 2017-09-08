import com.tiza.process.common.util.CommonUtil;
import com.tiza.process.gb32960.bean.GB32960Header;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * Description: TestHeader
 * Author: Wangw
 * Update: 2017-09-07 10:09
 */
public class TestHeader {


    @Test
    public void test1(){

        GB32960Header header = new GB32960Header();
        header.setCmd(0x01);
        header.setTerminalId("00008613685132382");
        header.setContent(new byte[0]);
        header.setSerial(1);

        ByteBuf buf = Unpooled.buffer(25);

        buf.writeBytes(new byte[]{0x23, 0x23});
        buf.writeByte(0x01);
        buf.writeByte(0xFE);
        buf.writeBytes(header.getTerminalId().getBytes());
        buf.writeByte(0x01);
        buf.writeShort(0);

        byte[] array = new byte[22];
        buf.getBytes(2, array);

        buf.writeByte(CommonUtil.getCheck(array));

        System.out.println(CommonUtil.bytesToStr(buf.array()));
    }

    @Test
    public void test2(){

        String vin = "00008613685132382";

        System.out.println(vin.getBytes().length);
    }
}
