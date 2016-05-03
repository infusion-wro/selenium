package pageobject.impl.results.element.dropdown;

import pageobject.element.DropDownValue;

public enum TournamentDropDownValue implements DropDownValue {

    OFFICIAL_MATCHES("Mecze oficjalne"),
    UNOFFICIAL_MATCHES("Mecze nieoficjalne");

    private final String VALUE;

    TournamentDropDownValue(String value) {
        VALUE = value;
    }

    @Override
    public String getValue() {
        return VALUE;
    }

}
