package de.mosmann.topics.basics.events;

public final class Increment {

	private final int _delta;

	public Increment(int delta) {
		_delta = delta;
	}

	public int delta() {
		return _delta;
	}
}
