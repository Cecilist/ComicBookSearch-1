package edu.bsu.cs222.model;

public enum SearchType {
    CHARACTERS {
        @Override
        public String asLowerCase() {
            return CHARACTERS.toString().toLowerCase();
        }
    },
    CREATORS {
        @Override
        public String asLowerCase() {
            return CREATORS.toString().toLowerCase();
        }
    };

    public abstract String asLowerCase();
}
