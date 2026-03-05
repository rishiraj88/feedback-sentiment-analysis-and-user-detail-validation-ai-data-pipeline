package rr.userdetails.userbatch.domain;

import lombok.Builder;

@Builder
public record User(String id, String firstName, String lastName, Contact contact, Location location) {
}
