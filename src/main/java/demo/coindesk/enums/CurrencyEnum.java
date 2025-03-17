package demo.coindesk.enums;

import lombok.Getter;

@Getter
public enum CurrencyEnum {
    USD("美元"),
    GBP("英鎊"),
    EUR("歐元");

    private final String name;

    CurrencyEnum(String name) {
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
            if (code.equals(currencyEnum.toString())) {
                return currencyEnum.getName();
            }
        }
        return null;
    }

}
