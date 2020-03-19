package com.rbkmoney.adapter.common.utils.converter;

import com.rbkmoney.adapter.common.enums.BankCardExpDateFormat;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.ExpDate;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardExpDate;
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel;
import org.junit.Test;

import static com.rbkmoney.adapter.common.utils.converter.BankCardUtils.*;
import static org.junit.Assert.assertEquals;

public class BankCardUtilsTest {

    private static final short TEST_YEAR = 2020;
    private static final byte TEST_MONTH = 3;
    private static final byte TEST_NEW_MONTH = 6;
    private static final ExpDate TEST_EXP_DATE = new ExpDate(TEST_MONTH, TEST_YEAR);
    private static final CardData TEST_CARD_DATA = new CardData("pan", new ExpDate(TEST_NEW_MONTH, TEST_YEAR));
    private static final BankCardExpDate TEST_BANK_CARD_EXP_DATE = new BankCardExpDate(TEST_MONTH, TEST_YEAR);
    private static final BankCard TEST_BANK_CARD = new BankCard().setExpDate(TEST_BANK_CARD_EXP_DATE);
    private static final CardDataProxyModel TEST_CARD_DATA_PROXY_MODEL = CardDataProxyModel.builder()
            .expMonth(TEST_MONTH)
            .expYear(TEST_YEAR)
            .build();

    @Test
    public void bankCardDateTest() {
        // exp date: year 2020, month 3

        assertEquals("20200331", getFullCardExpDate(TEST_BANK_CARD_EXP_DATE));
        assertEquals(new Integer(31), getDayOfMonth(TEST_BANK_CARD_EXP_DATE));
        assertEquals("20", getYearFromBankCardExpDate(TEST_BANK_CARD_EXP_DATE));
        assertEquals("20", getBankCardFormattedYear(TEST_YEAR));
        assertEquals("03", getBankCardFormattedMonth(TEST_MONTH));
        assertEquals("03", getMonthFromBankCardExpDate(TEST_BANK_CARD_EXP_DATE));
        assertEquals("2003", expDateToString(new CardData(), TEST_BANK_CARD));
        assertEquals("2006", expDateToString(TEST_CARD_DATA, TEST_BANK_CARD));

        assertEquals("2003", expDateToString(TEST_CARD_DATA_PROXY_MODEL));
        assertEquals("2003", expDateToString(TEST_EXP_DATE));
        assertEquals("2003", expDateToString(TEST_BANK_CARD_EXP_DATE));

        assertEquals("0620", expDateToString(TEST_YEAR, TEST_NEW_MONTH, BankCardExpDateFormat.MMYY));
    }

}
