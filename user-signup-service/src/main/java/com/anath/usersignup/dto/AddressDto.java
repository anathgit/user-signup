package com.anath.usersignup.dto;

public class AddressDto {
    private final String state;
    private final String city;

    private AddressDto(AddressDtoBuilder addressDtoBuilder){
        this.state = addressDtoBuilder.getState();
        this.city = addressDtoBuilder.getCity();
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public static class AddressDtoBuilder {
        private String state;
        private String city;

        public AddressDtoBuilder(){

        }

        public AddressDto build() {
            return new AddressDto(this);
        }

        public AddressDtoBuilder state(String state) {
            this.state = state;
            return this;
        }

        public AddressDtoBuilder city(String city) {
            this.city = city;
            return this;
        }

        public String getState() {
            return state;
        }

        public String getCity() {
            return city;
        }
    }
}
