package PART2;

import java.util.List;

import PART1.Message;

public abstract class SpamObserver {
	//Atributs, constructor i mètodes ja implementats
	public abstract void update(List<Message> messages, List<Message> spam);
}
