package edu.bsu.cs222.model;

public enum SearchType {
    CHARACTER{
        @Override
        public String asLowerCase(){
            return CHARACTER.toString().toLowerCase();
        }
    },
    CREATOR{
        @Override
        public String asLowerCase(){
            return CHARACTER.toString().toLowerCase();
        }
    };
    public abstract String asLowerCase();
}
