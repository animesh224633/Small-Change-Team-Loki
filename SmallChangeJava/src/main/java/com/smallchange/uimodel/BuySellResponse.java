package com.smallchange.uimodel;

import java.util.Objects;

public class BuySellResponse {
boolean response;

public BuySellResponse() {}
public boolean isResponse() {
	return response;
}

public void setResponse(boolean response) {
	this.response = response;
}

@Override
public int hashCode() {
	return Objects.hash(response);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	BuySellResponse other = (BuySellResponse) obj;
	return response == other.response;
}

@Override
public String toString() {
	return "BuySellResponse [response=" + response + "]";
}

public BuySellResponse(boolean response) {
	super();
	this.response = response;
}
}
