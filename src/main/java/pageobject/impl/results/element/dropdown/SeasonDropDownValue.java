package pageobject.impl.results.element.dropdown;

import pageobject.element.DropDownValue;

public enum SeasonDropDownValue implements DropDownValue {

    SEASON_2015_2016("Sezon 2015/16"),
    SEASON_2015_2014("Sezon 2014/15");

    private final String VALUE;

    SeasonDropDownValue(String value) {
        VALUE = value;
    }

    @Override
    public String getValue() {
        return VALUE;
    }


}
