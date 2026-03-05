package rr.userdetails.userbatch.domain;

import lombok.Builder;

@Builder
public record Contact(String email, String phone) {
}
