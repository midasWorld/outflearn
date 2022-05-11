package com.midas.outflearn.model.order;

import static com.google.common.base.Preconditions.*;
import static java.util.regex.Pattern.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Email {

	private final String address;

	public Email(String address) {
		checkArgument(isNotEmpty(address), "address must be provided.");
		checkArgument(
			address.length() >= 4 && address.length() <= 50,
			"address length must be between 4 and 50 characters."
		);
		checkArgument(checkAddress(address), "Invalid email address: " + address);

		this.address = address;
	}

	private boolean checkAddress(String address) {
		return matches("[a-zA-Z0-9._+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9.]+", address);
	}

	public String getAddress() {
		return address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Email email = (Email)o;
		return Objects.equals(address, email.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("address", address)
			.toString();
	}
}
