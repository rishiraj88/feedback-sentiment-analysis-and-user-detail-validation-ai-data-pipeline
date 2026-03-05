package rr.userdetails.userbatch.domain;

import lombok.Builder;

@Builder
public record User(String id, String firstName, String lastName, ai.data.pipeline.spring.customer.domain.Contact contact, Location location) {
}
