package pageobject.impl.results.element;

import pageobject.element.PlaceholdersProvider;

public class ResultRow implements PlaceholdersProvider {

    private final String date;
    private final String tournament;
    private final String match;

    private ResultRow(Builder builder) {
        this.date = builder.date;
        this.tournament = builder.tournament;
        this.match = builder.match;
    }

    public static class Builder {

        private String date;
        private String tournament;
        private String match;

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withTournament(String tournament) {
            this.tournament = tournament;
            return this;
        }

        public Builder withMatch(String match) {
            this.match = match;
            return this;
        }

        public ResultRow create() {
            return new ResultRow(this);
        }

    }

    @Override
    public String[] getPlaceholders() {
        String[] placeholders = new String[3];
        placeholders[0] = date;
        placeholders[1] = tournament;
        placeholders[2] = match;
        return placeholders;
    }

}
