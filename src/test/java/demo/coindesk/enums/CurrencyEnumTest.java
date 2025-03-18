package demo.coindesk.enums;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyEnumTest {
    @Test
    public void testGetNameByCode_ValidCode() {
        assertEquals("美元", CurrencyEnum.getNameByCode("USD"));
        assertEquals("英鎊", CurrencyEnum.getNameByCode("GBP"));
        assertEquals("歐元", CurrencyEnum.getNameByCode("EUR"));
    }
}
