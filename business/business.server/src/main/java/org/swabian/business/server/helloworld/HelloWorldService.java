package org.swabian.business.server.helloworld;

import org.swabian.business.server.ServerSession;
import org.swabian.business.shared.helloworld.HelloWorldFormData;
import org.swabian.business.shared.helloworld.IHelloWorldService;

/**
 * @author phohmann
 */
public class HelloWorldService implements IHelloWorldService {

	@Override
	public HelloWorldFormData load(HelloWorldFormData input) {
		StringBuilder msg = new StringBuilder();
		msg.append("Hello ").append(ServerSession.get().getUserId()).append('!');
		input.getMessage().setValue(msg.toString());
		return input;
	}
}
