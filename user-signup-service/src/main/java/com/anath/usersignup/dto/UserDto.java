package com.anath.usersignup.dto;

import com.anath.model.Address;

import java.time.LocalDateTime;


public class UserDto {
    private final String email;
    private final LocalDateTime registeredAt;
    private final String firstName;
    private final String lastName;
    private final String instagramUsername;
    private final String twitterUsername;
    private final String developmentEnvironment;
    private final AddressDto address;

    private UserDto(UserDtoBuilder builder) {
        this.email = builder.getEmail();
        this.registeredAt = builder.getRegisteredAt();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.instagramUsername = builder.getInstagramUsername();
        this.twitterUsername = builder.getTwitterUsername();
        this.developmentEnvironment = builder.getDevelopmentEnvironment();
        this.address = builder.getAddress();
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public String getDevelopmentEnvironment() {
        return developmentEnvironment;
    }

    public AddressDto getAddress() {
        return address;
    }

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public String getEmail() {
        return email;
    }

    public static class UserDtoBuilder {

        private String email;
        private LocalDateTime registeredAt;
        private String firstName;
        private String lastName;
        private String instagramUsername;
        private String twitterUsername;
        private String developmentEnvironment;
        private AddressDto address;

        public UserDtoBuilder() {
        }

        public UserDto build() {
            return new UserDto(this);
        }

        public UserDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder registeredAt(LocalDateTime registeredAt) {
            this.registeredAt = registeredAt;
            return this;
        }

        public UserDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDtoBuilder instagramUsername(String instagramUsername) {
            this.instagramUsername = instagramUsername;
            return this;
        }

        public UserDtoBuilder twitterUsername(String twitterUsername) {
            this.twitterUsername = twitterUsername;
            return this;
        }

        public UserDtoBuilder developmentEnvironment(String developmentEnvironment) {
            this.developmentEnvironment = developmentEnvironment;
            return this;
        }

        public UserDtoBuilder address(Address address) {
            AddressDto addressDto =
                    new AddressDto.AddressDtoBuilder()
                            .city(address.getCity())
                            .state(address.getState()).build();
            this.address = addressDto;
            return this;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public LocalDateTime getRegisteredAt() {
            return registeredAt;
        }

        public String getInstagramUsername() {
            return instagramUsername;
        }

        public String getTwitterUsername() {
            return twitterUsername;
        }

        public String getDevelopmentEnvironment() {
            return developmentEnvironment;
        }

        public AddressDto getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }
    }

}
