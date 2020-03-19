package com.rbkmoney.adapter.common.utils.converter;

import com.rbkmoney.adapter.common.enums.BankCardExpDateFormat;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.cds.ExpDate;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardExpDate;
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankCardUtils {

    private static final String DEFAULT_NUMBER_FORMAT = "%1$02d";

    public static String getFullCardExpDate(BankCardExpDate expDate) {
        int correctYear = expDate.getYear() / 100 == 0 ? expDate.getYear() + 2000 : expDate.getYear();
        return String.format("%1$04d%2$02d%3$02d", correctYear, expDate.getMonth(), getDayOfMonth(expDate));
    }

    public static Integer getDayOfMonth(BankCardExpDate expDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(expDate.getYear(), expDate.getMonth(), -1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String getYearFromBankCardExpDate(BankCardExpDate expDate) {
        return getBankCardFormattedYear(expDate.getYear());
    }

    @Deprecated
    public static String getMonthFromExpDate(Byte month) {
        return getBankCardFormattedMonth(month);
    }

    @Deprecated
    public static String getFullDateFromBankCardExpDate(BankCardExpDate bankCardExpDate) {
        return getFullCardExpDate(bankCardExpDate);
    }

    public static String getMonthFromBankCardExpDate(BankCardExpDate expDate) {
        return getBankCardFormattedMonth(expDate.getMonth());
    }

    public static String getBankCardFormattedYear(Short year) {
        return String.format(DEFAULT_NUMBER_FORMAT, year % 100);
    }

    public static String getBankCardFormattedMonth(Byte month) {
        return String.format(DEFAULT_NUMBER_FORMAT, month);
    }

    public static String expDateToString(CardData cardData, BankCard bankCard) {
        if (cardData.isSetExpDate()) {
            return expDateToString(cardData.getExpDate());
        }
        return expDateToString(bankCard.getExpDate());
    }

    public static String expDateToString(CardDataProxyModel cardDataProxyModel) {
        return expDateToString(cardDataProxyModel.getExpYear(), cardDataProxyModel.getExpMonth(), BankCardExpDateFormat.YYMM);
    }

    public static String expDateToString(ExpDate expDate) {
        return expDateToString(expDate.getYear(), expDate.getMonth(), BankCardExpDateFormat.YYMM);
    }

    public static String expDateToString(BankCardExpDate expDate) {
        return expDateToString(expDate.getYear(), expDate.getMonth(), BankCardExpDateFormat.YYMM);
    }

    public static String expDateToString(short year, byte month, BankCardExpDateFormat format) {
        String formattedYear = getBankCardFormattedYear(year);
        String formattedMonth = getBankCardFormattedMonth(month);
        if (format == BankCardExpDateFormat.MMYY) {
            return formattedMonth + formattedYear;
        } else {
            return formattedYear + formattedMonth;
        }
    }
}
